package jp.co.fly.job.usersImport;

import jp.co.fly.job.usersExport.ExportUsersJobListener;
import jp.co.fly.model.mapper.PrefectureDao;
import jp.co.fly.model.mapper.UserDao;
import jp.co.fly.model.mapper.UserInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ジョブ処理リスナーの実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ImportUsersJobListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(ExportUsersJobListener.class);

  @Autowired
  private PrefectureDao prefectureDao;
  @Autowired
  private UserDao userDao;
  @Autowired
  private UserInfoDao userInfoDao;

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("-------------------- JOB Start --------------------");
    prefectureDao.deleteAll();
    userDao.deleteAll();
    userInfoDao.deleteAll();
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("-------------------- JOB End --------------------");

    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("Job execution successful!!!");
    } else {
      log.info("Job execution failure!!!");
    }
  }
}
