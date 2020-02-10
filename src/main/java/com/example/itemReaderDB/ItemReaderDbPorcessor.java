package com.example.itemReaderDB;

import org.springframework.batch.item.ItemProcessor;

public class ItemReaderDbPorcessor implements ItemProcessor<User, User> {

  @Override
  public User process(User user) throws Exception {
    return user;
  }
}
