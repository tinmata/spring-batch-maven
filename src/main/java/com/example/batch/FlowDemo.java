package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FlowDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step flowDemoStep1() {
    return this.stepBuilderFactory.get("flowDemoStep1")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("flowDemoStep1");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step flowDemoStep2() {
    return this.stepBuilderFactory.get("flowDemoStep2")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("flowDemoStep2");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step flowDemoStep3() {
    return this.stepBuilderFactory.get("flowDemoStep3")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("flowDemoStep3");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Flow flowDemoFlow() {
    return new FlowBuilder<Flow>("flowDemoFlow")
        .start(this.flowDemoStep1())
        .next(this.flowDemoStep2())
        .build();
  }

  @Bean
  public Job flowDemoJob() {
    return this.jobBuilderFactory.get("flowDemoJob")
        .start(this.flowDemoFlow())
        .next(this.flowDemoStep3())
        .end()
        .build();
  }
}
