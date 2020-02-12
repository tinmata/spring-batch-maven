package com.example.userExport;

import lombok.Data;

//@Entity
//@Table(name = "user")
@Data
//@Getter
//@Setter
public class User {

  public User() {
  }

  public User(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  //  @Id
//  @Column(name = "username")
  public String username;

  //  @Column(name = "password")
  public String password;

  //  @Column(name = "firstname")
  public String firstName;

  //  @Column(name = "lastname")
  public String lastName;

  @Override
  public String toString() {
    return "User [username=" + this.username + ", firstName=" + this.firstName + ", lastName="
        + this.lastName;
  }
}
