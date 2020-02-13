package jp.co.fly.job.usersExport;

import java.util.Optional;
import jp.co.fly.model.entity.ExportUsersEntity;
import jp.co.fly.model.entity.PrefectureEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.entity.UsersInfoEntity;
import jp.co.fly.model.mapper.UserInfoDao;
import jp.co.fly.model.mapper.UserInfoMapper;
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

  // MyBatisによるDB接続時のMapper
  @Autowired
  private UserInfoDao userInfoDao;

  // MyBatis-SpringによるDB接続時のMapper
  @Autowired
  private UserInfoMapper userInfoMapper;

  // JPAによるDB接続時のリポジトリ
  @Autowired
  private UsersInfoRepository usersInfoRepository;
  @Autowired
  private PrefectureRepository prefectureRepository;

  /**
   * Processor処理実装
   *
   * @param users
   * @return ExportUsersEntity
   * @throws Exception
   */
  @Override
  public ExportUsersEntity process(UsersEntity users) throws Exception {
    ExportUsersEntity userInfo = new ExportUsersEntity();

    userInfo.username = users.username;
    userInfo.firstName = users.firstName;
    userInfo.lastName = users.lastName;

    // MyBatisによるDBにアクセス
    userInfo = mybatisDbAccess(users, userInfo);
    // MyBatis-SpringによるDBにアクセス
//    userInfo = mybatisSpringDbAccess(users, userInfo);
    // JPAによるDBにアクセス
//    userInfo = jpaDbAccess(users, userInfo);

    return userInfo;
  }

  /**
   * MyBatisとMapperによるDBにアクセスする実装例
   *
   * @param users
   * @param userInfo
   * @return ExportUsersEntity
   */
  private ExportUsersEntity mybatisDbAccess(UsersEntity users, ExportUsersEntity userInfo) {
    // 定義したSQLを発行してデータを取得する
    // InterfaceとMapperのxml定義が必要
    UsersInfoEntity usersInfoEntity = userInfoDao.findByKey(users.username);
    // DB取得結果処理
    if (null != usersInfoEntity) {
      userInfo.age = usersInfoEntity.age;
      userInfo.tel = usersInfoEntity.tel;
      userInfo.address = usersInfoEntity.address;
    }
    return userInfo;
  }


  /**
   * MyBatis-SpringによるDBにアクセスする実装例
   *
   * @param users
   * @param userInfo
   * @return ExportUsersEntity
   */
  private ExportUsersEntity mybatisSpringDbAccess(UsersEntity users, ExportUsersEntity userInfo) {
    // 定義したSQLを発行してデータを取得する
    UsersInfoEntity usersInfoEntity = userInfoMapper.findByUsername(users.username);
    // DB取得結果処理
    if (null != usersInfoEntity) {
      userInfo.age = usersInfoEntity.age;
      userInfo.tel = usersInfoEntity.tel;
      userInfo.address = usersInfoEntity.address;
    }
    return userInfo;
  }

  /**
   * JPAによるDBにアクセスする実装例
   *
   * @param users
   * @param userInfo
   * @return ExportUsersEntity
   */
  private ExportUsersEntity jpaDbAccess(UsersEntity users, ExportUsersEntity userInfo) {
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
