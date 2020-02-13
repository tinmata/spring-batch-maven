package jp.co.fly.job.usersImport;

import jp.co.fly.consts.Consts;
import jp.co.fly.consts.FileHeader;
import jp.co.fly.model.entity.UsersEntity;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * ステップの呼び込み処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ImportUsersReader {

  /**
   * 入力ファイルのパスとファイル名の定義。
   */
  private static final String INPUT = Consts.FILE_PATH + "user_list" + Consts.FILE_SURFFIX;

  /**
   * 入力ファイルのヘッダーの定義。
   */
  private static final String[] FILE_HEADER = {FileHeader.username.name(),
      FileHeader.password.name(), FileHeader.firstName.name(), FileHeader.lastName.name()};

  @StepScope
  public FlatFileItemReader<UsersEntity> flatFileItemReader() {
    return new FlatFileItemReaderBuilder<UsersEntity>()
        .name("userReader")
        .resource(new FileSystemResource(INPUT))
        .delimited()
        .names(FILE_HEADER)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<UsersEntity>() {{
          setTargetType(UsersEntity.class);
        }})
        .build();
  }
}
