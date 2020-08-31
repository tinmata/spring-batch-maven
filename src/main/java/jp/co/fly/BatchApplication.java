package jp.co.fly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * SpringBoot起動
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@SpringBootApplication
@Slf4j
public class BatchApplication {

  /**
   * SpringBootBatch入り口
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    try {
//      System.exit(SpringApplication.exit(SpringApplication.run(BatchApplication.class, args)));
      log.info("start...{}", String.join(",", args));
      ApplicationContext context = SpringApplication.run(BatchApplication.class, args);
      int exitCode = SpringApplication.exit(context);
      log.info("exit...{}", exitCode);
      System.exit(exitCode);
    } catch (Exception e) {
      System.out.println("critical error!!");
      System.exit(1);
    }
  }
}
