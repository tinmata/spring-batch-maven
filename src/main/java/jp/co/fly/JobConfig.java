package jp.co.fly;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

public class JobConfig {

  @Autowired
  private JobLauncher jobLauncher;
}
