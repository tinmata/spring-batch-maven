package jp.co.fly.model.mapper;

import jp.co.fly.model.entity.UsersInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;

@Mapper
public interface UserInfoMapper {

  @Query("select username, age, tel, pref_id, address from user_info where username = #{username}")
  UsersInfoEntity findByUsername(@Param("username") String username);
}
