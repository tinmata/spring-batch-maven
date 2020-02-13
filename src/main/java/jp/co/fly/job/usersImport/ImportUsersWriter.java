package jp.co.fly.job.usersImport;

import jp.co.fly.model.entity.UsersEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * ステップの書き込み処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ImportUsersWriter {

  public ItemWriter<UsersEntity> writer(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisBatchItemWriterBuilder<UsersEntity>()
        .sqlSessionFactory(sqlSessionFactory)
        .statementId("jp.co.fly.model.mapper.UserDao.createUser")
        .build();
  }
}
