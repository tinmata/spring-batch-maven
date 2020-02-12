package jp.co.fly.job.usersExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.stereotype.Component;

/**
 * ステップ処理リスナーの実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ExportUsersStepListener {

  private static final Logger log = LoggerFactory.getLogger(ExportUsersStepListener.class);

  /**
   * ステップの前処理を実装する。
   */
  @BeforeChunk
  public void beforeStep() {
    log.info("STEP Start ...");
  }

  /**
   * ステップの後処理を実装する。
   */
  @AfterChunk
  public void afterStep() {
    log.info("STEP End ...");
  }
}
