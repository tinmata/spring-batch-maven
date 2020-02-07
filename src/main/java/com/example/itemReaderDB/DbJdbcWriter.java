package com.example.itemReaderDB;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("dbJdbcWriter")
public class DbJdbcWriter implements ItemWriter<User> {

  @Override
  public void write(List<? extends User> items) throws Exception {
    items.forEach(System.out::println);
  }
}
