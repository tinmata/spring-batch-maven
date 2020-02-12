package jp.co.fly.job.usersExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ExportUsersJobListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(ExportUsersJobListener.class);

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("JOB Start .....");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("JOB End .....");

    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("Job execution successful!!!");
    } else {
      log.info("Job execution failure!!!");
    }
  }
}
