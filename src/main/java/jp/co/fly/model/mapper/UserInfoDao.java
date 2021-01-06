package jp.co.fly.model.mapper;

import java.util.Map;
import jp.co.fly.model.entity.UsersInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {

  //  UsersInfoEntity findByKey(String username);
  UsersInfoEntity findByKey(Map<String, String> userInfo);

  void createUserInfo(UsersInfoEntity usersInfoEntity);

  void deleteAll();
}
