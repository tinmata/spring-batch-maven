package com.example.itemReaderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String FIRST_NAME = "firstname";
  public static final String LAST_NAME = "lastname";

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();

    user.username = rs.getString(USERNAME);
    user.password = rs.getString(PASSWORD);
    user.firstName = rs.getString(FIRST_NAME);
    user.lastName = rs.getString(LAST_NAME);

    return user ;
  }
}
