package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildJob1 {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step childJob1Step1() {
    return this.stepBuilderFactory.get("childJob1Step1")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("childJob1Step1");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Job childJobOne() {
    return this.jobBuilderFactory.get("childJobOne")
        .start(this.childJob1Step1())
        .build();
  }
}
