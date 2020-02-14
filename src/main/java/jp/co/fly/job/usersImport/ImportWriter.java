package jp.co.fly.job.usersImport;

import jp.co.fly.model.entity.PrefectureEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.entity.UsersInfoEntity;
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
public class ImportWriter {

  public ItemWriter<UsersEntity> usersWriter(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisBatchItemWriterBuilder<UsersEntity>()
        .sqlSessionFactory(sqlSessionFactory)
        .statementId("jp.co.fly.model.mapper.UserDao.createUser")
        .build();
  }

  public ItemWriter<UsersInfoEntity> usersInfoWriter(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisBatchItemWriterBuilder<UsersInfoEntity>()
        .sqlSessionFactory(sqlSessionFactory)
        .statementId("jp.co.fly.model.mapper.UserInfoDao.createUserInfo")
        .build();
  }

  public ItemWriter<PrefectureEntity> prefWriter(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisBatchItemWriterBuilder<PrefectureEntity>()
        .sqlSessionFactory(sqlSessionFactory)
        .statementId("jp.co.fly.model.mapper.PrefectureDao.createPrefecture")
        .build();
  }
}
