package com.example.itemReaderDB;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ItemReaderDbDemo {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private DataSource dataSource;
  @Autowired
  @Qualifier("dbJdbcWriter")
  private ItemWriter<User> dbJdbcWriter;

  @Bean
  public Job itemReaderDbJob() {
    return jobBuilderFactory.get("itemReaderDbJob01")
        .incrementer(new RunIdIncrementer())
        .flow(this.itemReaderDbStep01())
        .end()
        .build();
  }

  @Bean
  public Step itemReaderDbStep01() {
    return stepBuilderFactory.get("itemReaderDbStep01")
        .<User, User>chunk(2)
        .reader(this.dbJdbcReader())
        .processor(dbJdbcPorcessor())
        .writer(this.dbJdbcWriter)
        .build();
  }

  @Bean
  @StepScope
  public JdbcPagingItemReader<User> dbJdbcReader() {

    // DataSourceをセットする
    JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<User>();
    reader.setDataSource(this.dataSource);
    reader.setFetchSize(2);

    // 検索結果をMapperにセットする
    reader.setRowMapper((rs, rowNum) -> {
      User user = new User();
      user.username = rs.getString(1);
      user.password = rs.getString(2);
      user.firstName = rs.getString(3);
      user.lastName = rs.getString(4);
      return user;
    });

    // 検索SQLをセットする
    MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
    provider.setSelectClause("username, password, firstname, lastname");
    provider.setFromClause("from user");

    // ソート設定
    Map<String, Order> sort = new HashMap<String, Order>(1);
    sort.put("username", Order.DESCENDING);
    provider.setSortKeys(sort);

    reader.setQueryProvider(provider);
    return reader;
  }

  @Bean
  public ItemReaderDbPorcessor dbJdbcPorcessor() {
    return new ItemReaderDbPorcessor();
  }
}
