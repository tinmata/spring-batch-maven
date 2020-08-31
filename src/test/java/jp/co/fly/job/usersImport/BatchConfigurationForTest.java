 /*----------------------------------------------------------------------
  * プログラム名    ：BatchConfigurationForTest.java
  * 作成日          ：2020/04/28
  * 作成者          ：(名前)/IBM
  *----------------------------------------------------------------------
  * 修正履歴 (No.: 修正日: 担当者: 修正内容)
  *  No.01: 2020/04/28: (名前)/IBM: 新規作成。
  *----------------------------------------------------------------------
  */

 package jp.co.fly.job.usersImport;

 import javax.sql.DataSource;
 import jp.co.fly.consts.Consts;
 import jp.co.fly.model.entity.PrefectureEntity;
 import jp.co.fly.model.entity.UsersEntity;
 import jp.co.fly.model.entity.UsersInfoEntity;
 import org.apache.ibatis.session.ExecutorType;
 import org.apache.ibatis.session.SqlSessionFactory;
 import org.mybatis.spring.SqlSessionFactoryBean;
 import org.springframework.batch.core.Job;
 import org.springframework.batch.core.Step;
 import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
 import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
 import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
 import org.springframework.batch.core.launch.support.RunIdIncrementer;
 import org.springframework.batch.test.JobLauncherTestUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
 import org.springframework.jdbc.datasource.DriverManagerDataSource;

 /**
  * 最初の１文はクラスの機能を敬体で簡潔に記述し、「。」か「.」で終えます。 詳細な説明があれば続けて記述します。自由に折り返すことができます。
  */
 @Configuration
 @EnableBatchProcessing
 public class BatchConfigurationForTest {

   @Autowired
   public JobBuilderFactory jobBuilderFactory;
   @Autowired
   public StepBuilderFactory stepBuilderFactory;
   @Autowired
   private ImportUsersJobListener jobListener;
   @Autowired
   private ImportUserStepListener importUserStepListener;
   @Autowired
   private ImportUserStepSkipper importUserStepSkipper;
   @Autowired
   private ImportReader importReader;
   @Autowired
   private ImportUsersProcessor importUsersProcessor;
   @Autowired
   private ImportUsersInfoProcessor importUsersInfoProcessor;
   @Autowired
   private ImportPrefProcessor importPrefProcessor;
   @Autowired
   private ImportWriter importWriter;

   @Bean
   public DataSource dataSource() {
     DriverManagerDataSource dataSource = new DriverManagerDataSource();
     dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
     dataSource.setUrl(
         "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8");
     dataSource.setUsername("test");
     dataSource.setPassword("5ql@dmin2019");
     return dataSource;
   }

   @Bean
   public SqlSessionFactory sqlSessionFactory() throws Exception {

     // SpringのSqlSessionFactoryBean
     SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
     // 環境設定のDataSourceリソース追加
     factoryBean.setDataSource(dataSource());
     // DBアクセス時に利用するMapper追加
     factoryBean.setMapperLocations(
         new PathMatchingResourcePatternResolver()
             .getResources(Consts.MAPPER_RESOURCE_PATH));
     // 現在環境で設定されているDataSourceインスタンス取得
     org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
     configuration.setDefaultExecutorType(ExecutorType.BATCH);
     factoryBean.setConfiguration(configuration);
     // Factoryオブジェクト返却
     return factoryBean.getObject();
   }

   @Bean
   public Step importUserStep() throws Exception {
     return stepBuilderFactory.get("importUserStep")
         .<UsersEntity, UsersEntity>chunk(1000)
         .listener(importUserStepListener)
         .reader(importReader.usersInputReader())
         .processor(importUsersProcessor)
         .writer(importWriter.usersWriter(sqlSessionFactory()))
         // ビジネス例外スキップ処理
         .faultTolerant()
         .skipPolicy(importUserStepSkipper)
         .build();
   }

   @Bean
   public Step importUserInfoStep() throws Exception {
     return stepBuilderFactory.get("importUserInfoStep")
         .<UsersInfoEntity, UsersInfoEntity>chunk(10)
         .reader(importReader.usersInfoInputReader())
         .processor(importUsersInfoProcessor)
         .writer(importWriter.usersInfoWriter(sqlSessionFactory()))
         .build();
   }

   @Bean
   public Step importPrefStep() throws Exception {
     return stepBuilderFactory.get("importPrefStep")
         .<PrefectureEntity, PrefectureEntity>chunk(10)
         .reader(importReader.prefInputReader())
         .processor(importPrefProcessor)
         .writer(importWriter.prefWriter(sqlSessionFactory()))
         .build();
   }

   @Bean
   public Job importJob() throws Exception {
     return jobBuilderFactory.get("importJob")
         .incrementer(new RunIdIncrementer())
         .listener(jobListener)
         .flow(importPrefStep())
         .next(importUserStep())
         .next(importUserInfoStep())
         .end()
         .build();
   }


   @Bean
   public JobLauncherTestUtils jobLauncherTestUtils() throws Exception {
     JobLauncherTestUtils utils = new JobLauncherTestUtils();
     utils.setJob(importJob());
     return utils;
   }
 }
