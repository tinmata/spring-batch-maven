package com.example.userExport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

  @Id
  @Column(name = "username")
  public String username;
  @Column(name = "password")
  public String password;
  @Column(name = "firstname")
  public String firstName;
  @Column(name = "lastname")
  public String lastName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "User [username=" + this.username + ", firstName=" + this.firstName + ", lastName="
        + this.lastName;
  }
}
