package jp.co.fly.job.usersImport;

import jp.co.fly.model.entity.PrefectureEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ImportPrefProcessor implements ItemProcessor<PrefectureEntity, PrefectureEntity> {

  @Override
  public PrefectureEntity process(PrefectureEntity prefectureEntity) throws Exception {
    return prefectureEntity;
  }
}
