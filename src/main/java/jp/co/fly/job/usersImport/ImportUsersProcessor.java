package jp.co.fly.job.usersImport;

import jp.co.fly.model.entity.UsersEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * ItemProccessorによる、ステップのロジック処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ImportUsersProcessor implements ItemProcessor<UsersEntity, UsersEntity> {

  @Override
  public UsersEntity process(UsersEntity usersEntity) throws Exception {
    return usersEntity;
  }
}
