package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DeciderDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step deciderDemoStep1() {
    return this.stepBuilderFactory.get("deciderDemoStep1")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("deciderDemoStep1");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step deciderDemoStep2() {
    return this.stepBuilderFactory.get("deciderDemoStep2")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("even");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step deciderDemoStep3() {
    return this.stepBuilderFactory.get("deciderDemoStep3")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("odd");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public JobExecutionDecider myDecider() {
    return new MyDecider();
  }

  @Bean
  public Job deciderDemoJob() {
    return this.jobBuilderFactory.get("deciderDemoJob")
        .start(this.deciderDemoStep1())
        .next(this.myDecider())
        .from(this.myDecider()).on("even").to(this.deciderDemoStep2())
        .from(this.myDecider()).on("odd").to(this.deciderDemoStep3())
        .from(this.deciderDemoStep3()).on("*").to(myDecider())
        .end()
        .build();
  }
}
