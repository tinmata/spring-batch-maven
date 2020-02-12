package jp.co.fly.repository;

import jp.co.fly.model.entity.UsersInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepositoryによるテーブルアクセス用Interface実装
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
public interface UsersInfoRepository extends JpaRepository<UsersInfoEntity, String> {

}
