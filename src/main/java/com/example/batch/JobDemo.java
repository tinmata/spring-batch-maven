package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job job01() {
    return this.jobBuilderFactory.get("job01")
        .start(step01())
        .next(step02())
        .next(step03())
        .build();
  }

  @Bean
  public Step step01() {
    return this.stepBuilderFactory.get("step01")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("step01");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step step02() {
    return this.stepBuilderFactory.get("step02")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("step02");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step step03() {
    return this.stepBuilderFactory.get("step03")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("step03");
          return RepeatStatus.FINISHED;
        }).build();
  }
}
