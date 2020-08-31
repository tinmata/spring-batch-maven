 /*----------------------------------------------------------------------
  * プログラム名    ：ExportUsersJobTest.java
  * 作成日          ：2020/07/27
  * 作成者          ：(名前)/IBM
  *----------------------------------------------------------------------
  * 修正履歴 (No.: 修正日: 担当者: 修正内容)
  *  No.01: 2020/07/27: (名前)/IBM: 新規作成。
  *----------------------------------------------------------------------
  */

 package jp.co.fly.job.usersExport;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.fail;

 import javax.sql.DataSource;
 import jp.co.fly.consts.Consts;
 import jp.co.fly.model.entity.UsersEntity;
 import org.apache.ibatis.session.ExecutorType;
 import org.apache.ibatis.session.SqlSessionFactory;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mybatis.spring.SqlSessionFactoryBean;
 import org.springframework.batch.item.ItemReader;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
 import org.springframework.jdbc.datasource.DriverManagerDataSource;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 /**
  * 最初の１文はクラスの機能を敬体で簡潔に記述し、「。」か「.」で終えます。 詳細な説明があれば続けて記述します。自由に折り返すことができます。
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 public class ExportUsersJobTest {

   private ExportUsersReader exportUsersReader = new ExportUsersReader();

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

     SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
     factoryBean.setDataSource(dataSource());
     factoryBean.setMapperLocations(
         new PathMatchingResourcePatternResolver()
             .getResources(Consts.MAPPER_RESOURCE_PATH));
     org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
     configuration.setDefaultExecutorType(ExecutorType.BATCH);
     factoryBean.setConfiguration(configuration);
     return factoryBean.getObject();
   }

   @Test
   public void myBatisReaderTest001() throws Exception {
     ItemReader<UsersEntity> usersList = exportUsersReader.myBatisReader(sqlSessionFactory());
     try {
       UsersEntity usersEntity = usersList.read();
       assertEquals("user010@example.com", usersEntity.getUsername());
       System.out.println("実行完了");
     } catch (Exception e) {
       fail();
     }
   }
 }
