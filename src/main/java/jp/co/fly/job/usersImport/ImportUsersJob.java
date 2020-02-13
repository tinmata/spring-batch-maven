package jp.co.fly.job.usersImport;

import jp.co.fly.job.JobConfig;
import jp.co.fly.model.entity.UsersEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ジョブ＆ステップの実装例
 * <pre>
 *   入力：CSVファイル
 *   出力：データベース
 * </pre>
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Configuration
@EnableBatchProcessing
public class ImportUsersJob extends JobConfig {

  @Autowired
  private ImportUsersJobListener jobListener;
  @Autowired
  private ImportUserStepListener stepListener;
  @Autowired
  private ImportUsersReader importUsersReader;
  @Autowired
  private ImportUsersProcessor importUsersProcessor;
  @Autowired
  private ImportUsersWriter importUsersWriter;

  /**
   * ステップ定義
   *
   * @return org.springframework.batch.core.Step
   * @throws Exception
   */
  @Bean
  public Step importUserStep01() throws Exception {
    return stepBuilderFactory.get("importUserStep01")
        .<UsersEntity, UsersEntity>chunk(10)
        .listener(jobListener)
        .reader(importUsersReader.flatFileItemReader())
        .processor(importUsersProcessor)
        .writer(importUsersWriter.writer(sqlSessionFactory()))
        .build();
  }

  /**
   * ジョブ定義
   *
   * @return org.springframework.batch.core.Job
   * @throws Exception
   */
  @Bean
  public Job importUserJob() throws Exception {
    return jobBuilderFactory.get("importUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(jobListener)
        .flow(importUserStep01())
        .end()
        .build();
  }
}
