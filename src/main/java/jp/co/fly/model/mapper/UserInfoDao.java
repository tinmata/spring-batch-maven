package jp.co.fly.model.mapper;

import jp.co.fly.model.entity.UsersInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {

  UsersInfoEntity findByKey(String username);
}
