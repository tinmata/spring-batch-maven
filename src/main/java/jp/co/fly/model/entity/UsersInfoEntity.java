package jp.co.fly.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * JDBCによるテーブルにアクセス用のRowMapper実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Data
@Entity
@Table(name = "user_info")
public class UsersInfoEntity {

  @Id
  @Column(name = "username")
  public String username;

  @Column(name = "age")
  public String age;

  @Column(name = "tel")
  public String tel;

  @Column(name = "pref_id")
  public String prefId;

  @Column(name = "address")
  public String address;
}
