package jp.co.fly.model.mapper;

import jp.co.fly.model.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  void createUser(UsersEntity usersEntity);

  void deleteAll();
}
