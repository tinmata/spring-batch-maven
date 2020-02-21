package jp.co.fly.model.mapper;

import jp.co.fly.model.entity.PrefectureEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrefectureDao {

  PrefectureEntity findByKey(String prefId);

  void createPrefecture(PrefectureEntity prefectureEntity);

  void deleteAll();
}
