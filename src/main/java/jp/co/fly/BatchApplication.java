package jp.co.fly;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.boot.CommandLineRunner;
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
public class BatchApplication implements CommandLineRunner {

  /**
   * SpringBootBatch入り口
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(BatchApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    CommandLineJobRunner.main(new String[]{"jp.co.fly.JobConfig",""});
  }
}
