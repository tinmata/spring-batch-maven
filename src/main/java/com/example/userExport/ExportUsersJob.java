package com.example.userExport;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class ExportUsersJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private DataSource dataSource;

  @Bean
  public Job userExportJob() {
    return jobBuilderFactory.get("userExportJob")
        .incrementer(new RunIdIncrementer())
        .flow(userExportStep01())
        .end()
        .build();
  }

  @Bean
  public Step userExportStep01() {
    return stepBuilderFactory.get("userExportStep01")
        .<User, User>chunk(5)
        .reader(userReader())
        .processor(userProcessor())
        .writer(userWriter())
        .build();
  }

  @Bean
  public JdbcCursorItemReader<User> userReader() {
    JdbcCursorItemReader<User> cursorItemReader = new JdbcCursorItemReader<>();
    cursorItemReader.setDataSource(dataSource);
    cursorItemReader.setSql("select username, password, firstname, lastname "
        + "from user "
        + "order by username desc");
    cursorItemReader.setRowMapper(new UserRowMapper());
    return cursorItemReader;
  }

  @Bean
  public UserItemProcessor userProcessor() {
    return new UserItemProcessor();
  }

  @Bean
  public FlatFileItemWriter<User> userWriter() {
    FlatFileItemWriter<User> writer = new FlatFileItemWriter<>();
    writer.setResource(new FileSystemResource("target/users.csv"));

    DelimitedLineAggregator<User> lineAggregator = new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(",");

    BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[]{"username", "password", "firstName", "lastName"});

    lineAggregator.setFieldExtractor(fieldExtractor);

    writer.setLineAggregator(lineAggregator);
    return writer;
  }
}
