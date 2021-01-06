package jp.co.fly.job;

import javax.sql.DataSource;
import jp.co.fly.consts.Consts;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * ジョブ＆ステップ定義の親クラス
 * <pre>
 *   １．共通DIコンポーネント宣言。
 *
 *     StepBuilderFactoryを利用する。
 *     DataSourceを利用する。
 *   ２．MyBatisのSqlSessionFactory生成。
 *     SqlSessionFactoryBeanを利用する。
 * </pre>
 */
public class JobConfig {

  /**
   * JobBuilderFactoryコンポーネントを注入する。
   */
  @Autowired
  protected JobBuilderFactory jobBuilderFactory;
  /**
   * StepBuilderFactoryコンポーネントを注入する。
   */
  @Autowired
  protected StepBuilderFactory stepBuilderFactory;
  /**
   * DataSourceコンポーネントを注入する。
   */
  @Autowired
  protected DataSource dataSource;

  /**
   * SqlSessionFactoryを使ってSqlSessionを生成する。
   *
   * @return SqlSessionFactory
   * @throws Exception
   */
  @Bean
  protected SqlSessionFactory sqlSessionFactory() throws Exception {

    // SpringのSqlSessionFactoryBean
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    // 環境設定のDataSourceリソース追加
    factoryBean.setDataSource(dataSource);
    // DBアクセス時に利用するMapper追加
    factoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(Consts.MAPPER_RESOURCE_PATH));
    // 現在環境で設定されているDataSourceインスタンス取得
    Configuration configuration = new Configuration();
    // インスタンス引数
    // ExecutorType.SIMPLE: 特別なことは行いません。ステートメントを実行するたびに新しいPreparedStatementを作成します。
    // ExecutorType.REUSE: PreparedStatementを再利用します。
    // ExecutorType.BATCH: 全ての更新ステートメントをバッチで実行し、途中でSELECTが実行される場合は、より分かりやすい動作となるよう必要に応じてトランザクション境界を設定します。
    configuration.setDefaultExecutorType(ExecutorType.BATCH);
    factoryBean.setConfiguration(configuration);
    // Factoryオブジェクト返却
    return factoryBean.getObject();
  }
}
