package jp.co.fly.model.entity;

import lombok.Data;

/**
 * ファイル入出力時にアクセス用のEntity実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Data
public class ExportUsersEntity {

  public String username;

  public String firstName;

  public String lastName;

  public int age;

  public String tel;

  public String prefName;

  public String address;
}
