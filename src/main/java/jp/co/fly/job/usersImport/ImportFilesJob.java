package jp.co.fly.job.usersImport;

import jp.co.fly.job.JobConfig;
import jp.co.fly.model.entity.PrefectureEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.entity.UsersInfoEntity;
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
public class ImportFilesJob extends JobConfig {

  @Autowired
  private ImportUsersJobListener jobListener;
  @Autowired
  private ImportUserStepListener stepListener;
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

  /**
   * ステップ定義
   *
   * @return org.springframework.batch.core.Step
   * @throws Exception
   */
  @Bean
  public Step importUserStep() throws Exception {
    return stepBuilderFactory.get("importUserStep")
        .<UsersEntity, UsersEntity>chunk(10)
        .listener(stepListener)
        .reader(importReader.usersInputReader())
        .processor(importUsersProcessor)
        .writer(importWriter.usersWriter(sqlSessionFactory()))
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

  /**
   * ジョブ定義
   *
   * @return org.springframework.batch.core.Job
   * @throws Exception
   */
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
}
