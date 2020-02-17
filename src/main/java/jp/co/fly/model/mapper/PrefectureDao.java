package jp.co.fly.model.mapper;

import jp.co.fly.model.entity.PrefectureEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrefectureDao {

  void createPrefecture(PrefectureEntity prefectureEntity);
}
