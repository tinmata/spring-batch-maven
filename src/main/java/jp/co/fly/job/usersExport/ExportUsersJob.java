package jp.co.fly.job.usersExport;

import jp.co.fly.job.JobConfig;
import jp.co.fly.model.entity.ExportUsersEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.repository.UsersRepository;
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
 *   入力：データベース
 *   出力：CSVファイル
 * </pre>
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Configuration
@EnableBatchProcessing
public class ExportUsersJob extends JobConfig {

  @Autowired
  protected ExportUsersJobListener jobListener;
  @Autowired
  protected ExportUsersStepListener stepListener;
  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private ExportUsersReader exportUsersReader;
  @Autowired
  private ExportUsersProcessor exportUsersProcessor;
  @Autowired
  private ExportUsersWriter exportUsersWriter;

  /**
   * ステップ定義
   *
   * @return org.springframework.batch.core.Step
   * @throws Exception
   */
  @Bean
  public Step exportUserStep01() throws Exception {
    return stepBuilderFactory.get("exportUserStep01")
        // チャンクによるステップ処理を実装
        .<UsersEntity, ExportUsersEntity>chunk(100)
        // ステップ前後処理のリスナーを設定
        .listener(stepListener)
        // MyBatisとMapper定義でDBに接続
        .reader(exportUsersReader.myBatisReader(sqlSessionFactory()))
        // JPAによるDBに接続
        //.reader(exportUsersReader.jpaReader(usersRepository))
        // JDBCによるDBに接続
        //.reader(exportUsersReader.jdbcReader(dataSource))
        .processor(exportUsersProcessor)
        .writer(exportUsersWriter.writer())
        .build();
  }

  /**
   * ジョブ定義
   *
   * @return org.springframework.batch.core.Job
   * @throws Exception
   */
  @Bean
  public Job exportUserJob() throws Exception {
    return jobBuilderFactory.get("exportUserJob")
        // ジョブ実行時にランダムなパラメータを渡す
        .incrementer(new RunIdIncrementer())
        // ジョブ前後処理のリスナーを設定
        .listener(jobListener)
        .flow(exportUserStep01())
        .end()
        .build();
  }
}
