package com.example.userExport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select username, password, firstname, lastname from user where username = :username")
  User findUserByKey(@Param("username") String username);
}
