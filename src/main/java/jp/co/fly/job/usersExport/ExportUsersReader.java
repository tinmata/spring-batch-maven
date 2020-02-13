package jp.co.fly.job.usersExport;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.mapper.UsersRowMapper;
import jp.co.fly.repository.UsersRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

/**
 * ステップの呼び込み処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ExportUsersReader {

  /**
   * MyBatis(Cursor)によるReaderの実装例
   *
   * @param sqlSessionFactory
   * @return MyBatisCursorItemReader
   */
  @StepScope
  public ItemReader<UsersEntity> myBatisReader(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisCursorItemReaderBuilder<UsersEntity>()
        .sqlSessionFactory(sqlSessionFactory)
        .queryId("jp.co.fly.model.mapper.UserMapper.findAll")
        .build();
  }

  /**
   * JPAによるReaderの実装例
   *
   * @param usersRepository
   * @return RepositoryItemReader
   */
  @StepScope
  public ItemReader<UsersEntity> jpaReader(UsersRepository usersRepository) {
    RepositoryItemReader<UsersEntity> reader = new RepositoryItemReader<>();

    reader.setRepository(usersRepository);
    reader.setMethodName("findAll");
    Map<String, Direction> sort = new HashMap<>();
    sort.put("username", Direction.DESC);
    reader.setSort(sort);

    return reader;
  }

  /**
   * JDBC(Cursor)によるReaderの実装例
   *
   * @param dataSource
   * @return JdbcCursorItemReader
   */
  @StepScope
  public ItemReader<UsersEntity> jdbcReader(DataSource dataSource) {
    JdbcCursorItemReader<UsersEntity> usersReader = new JdbcCursorItemReader<>();

    usersReader.setDataSource(dataSource);
    usersReader.setSql("select username, password, firstname, lastname "
        + "from user "
        + "order by username desc");
    usersReader.setRowMapper(new UsersRowMapper());

    return usersReader;
  }
}
