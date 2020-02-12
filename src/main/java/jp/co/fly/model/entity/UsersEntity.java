package jp.co.fly.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * JpaRepositoryによるテーブルにアクセス用のEntity実装
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Data
@Entity
@Table(name = "user")
public class UsersEntity implements Serializable {

  @Id
  @Column(name = "username")
  public String username;

  @Column(name = "password")
  public String password;

  @Column(name = "firstname")
  public String firstName;

  @Column(name = "lastname")
  public String lastName;
}
