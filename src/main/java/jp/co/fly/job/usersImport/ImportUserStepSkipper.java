package jp.co.fly.job.usersImport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

/**
 * SkipPolicyによる、ステップのスキップ処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/3/3
 */
@Component
public class ImportUserStepSkipper implements SkipPolicy {

  private static final Logger log = LoggerFactory.getLogger("ErrorLogger");

  @Override
  public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
    if (exception instanceof Exception) {
      log.error("{}", exception.getMessage());
      return true;
    } else {
      return false;
    }
  }
}
