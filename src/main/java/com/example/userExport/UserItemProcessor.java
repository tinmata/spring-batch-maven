package com.example.userExport;

import javax.sql.DataSource;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class UserItemProcessor implements ItemProcessor<User, User> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User process(User user) throws Exception {

    User newUser = new User();
    newUser.username = user.username;
    newUser.password = user.password;

    User selectUser = userRepository.findUserByKey(user.username);

    newUser.firstName = selectUser.getFirstName();
    newUser.lastName = selectUser.getLastName();

    return newUser;
  }
}
