package com.example.userExport;

import org.hibernate.metamodel.model.domain.DomainType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserItemProcessor implements ItemProcessor<User, User> {

//  @Autowired
//  private UserRepository userRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public User process(User user) throws Exception {

    User newUser = new User();
    newUser.username = user.username;
    newUser.password = user.password;

//    userRepository.findUserByKey(user.username);
    User selectUser = jdbcTemplate.queryForObject(
        "select username, password, firstname, lastname from user where username = '"
            + user.username + "'",
        new UserRowMapper());
    newUser.firstName = selectUser.firstName;
    newUser.lastName = selectUser.lastName;

    return newUser;
  }
}
