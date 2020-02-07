package com.example.batch;

import com.example.listener.MyChunkListener;
import com.example.listener.MyJobListener;
import java.util.Arrays;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job listenerJob() {
    return this.jobBuilderFactory.get("listenerJob")
        .start(this.step1())
        .listener(new MyJobListener())
        .build();
  }

  @Bean
  public Step step1() {
    return this.stepBuilderFactory.get("step1")
        .<String, String>chunk(2) // read, process, write
        .faultTolerant()
        .listener(new MyChunkListener())
        .reader(read())
        .writer(write())
        .build();
  }

  @Bean
  public ItemReader<String> read() {
    return new ListItemReader<>(Arrays.asList("java", "spring", "mybatis"));
  }

  @Bean
  public ItemWriter<String> write() {
    return items -> items.forEach(System.out::println);
  }
}
