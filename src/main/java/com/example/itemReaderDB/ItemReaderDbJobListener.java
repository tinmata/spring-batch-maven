package com.example.itemReaderDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class ItemReaderDbJobListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(ItemReaderDbJobListener.class);

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("Job Start...");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("Job End...");
  }
}
