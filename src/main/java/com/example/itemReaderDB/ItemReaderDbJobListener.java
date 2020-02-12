package com.example.itemReaderDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * ジョブ処理リスナーの実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ItemReaderDbJobListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(ItemReaderDbJobListener.class);

  /**
   * ジョブの前処理を実装する。
   *
   * @param jobExecution
   */
  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("Job Start...");
  }

  /**
   * ジョブの後処理を実装する。
   *
   * @param jobExecution
   */
  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("Job End...");
  }
}
