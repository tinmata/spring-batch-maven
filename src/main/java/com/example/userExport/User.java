package com.example.userExport;

import lombok.Data;

@Data
public class User {

  public String username;
  public String password;
  public String firstName;
  public String lastName;

  @Override
  public String toString() {
    return "User [username=" + this.username + ", firstName=" + this.firstName + ", lastName="
        + this.lastName;
  }
}
