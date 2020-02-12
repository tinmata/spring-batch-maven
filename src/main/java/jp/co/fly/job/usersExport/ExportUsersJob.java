package jp.co.fly.job.usersExport;

import javax.sql.DataSource;
import jp.co.fly.model.entity.ExportUsersEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.repository.UsersRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ジョブ＆ステップの実装例
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Configuration
@EnableBatchProcessing
public class ExportUsersJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private ExportUsersJobListener jobListener;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private ExportUsersStepListener stepListener;
  @Autowired
  private DataSource dataSource;
  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private ExportUsersReader exportUsersReader;
  @Autowired
  private ExportUsersProcessor exportUsersProcessor;
  @Autowired
  private ExportUsersWriter exportUsersWriter;

  /**
   * ジョブ定義
   *
   * @return
   */
  @Bean
  public Job exportUserJob() {
    return this.jobBuilderFactory.get("exportUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(jobListener)
        .flow(exportUserStep01())
        .end()
        .build();
  }

  /**
   * ステップ定義
   *
   * @return
   */
  @Bean
  public Step exportUserStep01() {
    return this.stepBuilderFactory.get("exportUserStep01")
        .<UsersEntity, ExportUsersEntity>chunk(5)
        .listener(stepListener)
        .reader(exportUsersReader.jpaReader(usersRepository))
        .processor(exportUsersProcessor)
        .writer(exportUsersWriter.writer())
        .build();
  }
}
