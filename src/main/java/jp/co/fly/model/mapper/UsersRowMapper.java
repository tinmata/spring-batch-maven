package jp.co.fly.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.fly.model.entity.UsersEntity;
import org.springframework.jdbc.core.RowMapper;

/**
 * JDBCによるテーブルにアクセス用のRowMapper実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
public class UsersRowMapper implements RowMapper<UsersEntity> {

  // カラム名定数を定義する。
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String FIRST_NAME = "firstname";
  public static final String LAST_NAME = "lastname";

  @Override
  public UsersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

    // テーブルのカラム名による項目マッピング
    UsersEntity user = new UsersEntity();
    user.username = rs.getString(USERNAME);
    user.password = rs.getString(PASSWORD);
    user.firstName = rs.getString(FIRST_NAME);
    user.lastName = rs.getString(LAST_NAME);

    return user;
  }
}
