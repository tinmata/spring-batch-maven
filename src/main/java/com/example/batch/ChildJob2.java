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
public class ChildJob2 {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step childJob2Step1() {
    return this.stepBuilderFactory.get("childJob2Step1")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("childJob2Step1");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step childJob2Step2() {
    return this.stepBuilderFactory.get("childJob2Step2")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("childJob2Step2");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Job childJobTwo() {
    return this.jobBuilderFactory.get("childJobTwo")
        .start(this.childJob2Step1())
        .next(this.childJob2Step2())
        .build();
  }
}
