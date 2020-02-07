package com.example.itemReader;

import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Retryable;

@Configuration
public class ItemReaderDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job itemReaderDemoJob() {
    return this.jobBuilderFactory.get("itemReaderDemoJob")
        .start(this.itemReaderDemoStep())
        .build();
  }

  @Bean
  public Step itemReaderDemoStep() {
    return this.stepBuilderFactory.get("itemReaderDemoStep")
        .<String, String>chunk(2)
        .reader(this.itemReaderDemoRead())
        .writer(dataList -> {
          dataList.forEach(System.out::println);
        }).build();
  }

  @Bean
  public MyReader itemReaderDemoRead() {
    List<String> dataList = Arrays.asList("cat", "dog", "pig", "duck");
    return new MyReader(dataList);
  }
}
