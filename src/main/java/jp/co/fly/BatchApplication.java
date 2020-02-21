package jp.co.fly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

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
      System.exit(SpringApplication.exit(SpringApplication.run(BatchApplication.class, args)));
    } catch (Exception e) {
      System.out.println("critical error!!");
      System.exit(1);
    }
  }
}
