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
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobSplitDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step sliptDemoStep1() {
    return this.stepBuilderFactory.get("sliptDemoStep1")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("slipDemoStep1");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step sliptDemoStep2() {
    return this.stepBuilderFactory.get("sliptDemoStep2")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("slipDemoStep2");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Step sliptDemoStep3() {
    return this.stepBuilderFactory.get("sliptDemoStep3")
        .tasklet((contribution, chunkContext) -> {
          System.out.println("slipDemoStep3");
          return RepeatStatus.FINISHED;
        }).build();
  }

  @Bean
  public Flow sliptDemoFlow1() {
    return new FlowBuilder<Flow>("sliptDemoFlow1")
        .start(this.sliptDemoStep1())
        .build();
  }

  @Bean
  public Flow sliptDemoFlow2() {
    return new FlowBuilder<Flow>("sliptDemoFlow2")
        .start(this.sliptDemoStep2())
        .next(this.sliptDemoStep3())
        .build();
  }

  @Bean
  public Job sliptDemoJob() {
    return this.jobBuilderFactory.get("sliptDemoJob")
        .start(this.sliptDemoFlow1())
        .split(new SimpleAsyncTaskExecutor()).add(this.sliptDemoFlow2())
        .end()
        .build();
  }
}
