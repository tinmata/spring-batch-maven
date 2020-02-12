package jp.co.fly.job.usersExport;

import java.util.Optional;
import jp.co.fly.model.entity.ExportUsersEntity;
import jp.co.fly.model.entity.PrefectureEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.entity.UsersInfoEntity;
import jp.co.fly.repository.PrefectureRepository;
import jp.co.fly.repository.UsersInfoRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ItemProccessorによる、ステップのロジック処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ExportUsersProcessor implements ItemProcessor<UsersEntity, ExportUsersEntity> {

  @Autowired
  private UsersInfoRepository usersInfoRepository;
  @Autowired
  private PrefectureRepository prefectureRepository;

  /**
   * Processor処理実装
   *
   * @param users
   * @return
   * @throws Exception
   */
  @Override
  public ExportUsersEntity process(UsersEntity users) throws Exception {
    ExportUsersEntity userInfo = new ExportUsersEntity();

    userInfo.username = users.username;
    userInfo.firstName = users.firstName;
    userInfo.lastName = users.lastName;

    Optional<UsersInfoEntity> usersInfoEntity = usersInfoRepository.findById(users.username);
    if (usersInfoEntity.isPresent()) {
      userInfo.age = usersInfoEntity.get().age;
      userInfo.tel = usersInfoEntity.get().tel;
      userInfo.address = usersInfoEntity.get().address;

      Optional<PrefectureEntity> prefectureEntity = prefectureRepository
          .findById(usersInfoEntity.get().prefId);
      if (prefectureEntity.isPresent()) {
        userInfo.prefName = prefectureEntity.get().prefName;
        if (userInfo.address == null || userInfo.address.isEmpty()) {
          userInfo.address = prefectureEntity.get().prefCapital;
        }
      } else {
        userInfo.prefName = "";
      }
    }

    return userInfo;
  }
}
