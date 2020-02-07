package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableBatchProcessing
@ComponentScan("com.example")
public class BatchApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(BatchApplication.class, args);
  }
}
