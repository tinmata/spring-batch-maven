package jp.co.fly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot起動
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@SpringBootApplication
public class BatchApplication {

  /**
   * SpringBootBatch入り口
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(BatchApplication.class, args);
  }
}
