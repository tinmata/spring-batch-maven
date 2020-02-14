package jp.co.fly.job.usersImport;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import jp.co.fly.consts.Consts;
import jp.co.fly.consts.FileHeader;
import jp.co.fly.model.entity.PrefectureEntity;
import jp.co.fly.model.entity.UsersEntity;
import jp.co.fly.model.entity.UsersInfoEntity;
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
public class ImportReader {

  /**
   * 入力ファイルのパスとファイル名の定義。
   */
  private static final String USER_INPUT =
      Consts.INPUT_FILE_PATH + "user_list" + Consts.FILE_SURFFIX;
  private static final String USER_INFO_INPUT =
      Consts.INPUT_FILE_PATH + "user_info_list" + Consts.FILE_SURFFIX;
  private static final String PREF_INPUT =
      Consts.INPUT_FILE_PATH + "prefecture_list" + Consts.FILE_SURFFIX;

  /**
   * 入力ファイルのヘッダーの定義。
   */
  private static final String[] USER_IN_HEADER = {FileHeader.username.name(),
      FileHeader.password.name(), FileHeader.firstName.name(), FileHeader.lastName.name()};
  private static final String[] USER_INFO_IN_HEADER = {FileHeader.username.name(),
      FileHeader.age.name(), FileHeader.tel.name(), FileHeader.prefId.name(),
      FileHeader.address.name()};
  private static final String[] PREF_IN_HEADER = {FileHeader.prefId.name(),
      FileHeader.prefName.name(), FileHeader.prefCaptial.name()};

  @StepScope
  public FlatFileItemReader<UsersEntity> usersInputReader() {
    return new FlatFileItemReaderBuilder<UsersEntity>()
        .name("usersInputReader")
        .resource(new FileSystemResource(USER_INPUT))
        .encoding("UTF-8")
        .delimited()
        .names(USER_IN_HEADER)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<UsersEntity>() {{
          setTargetType(UsersEntity.class);
        }})
        .build();
  }

  @StepScope
  public FlatFileItemReader<UsersInfoEntity> usersInfoInputReader() {
    return new FlatFileItemReaderBuilder<UsersInfoEntity>()
        .name("usersInfoInputReader")
        .resource(new FileSystemResource(USER_INFO_INPUT))
        .encoding("UTF-8")
        .delimited()
        .names(USER_INFO_IN_HEADER)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<UsersInfoEntity>() {{
          setTargetType(UsersInfoEntity.class);
        }})
        .build();
  }

  @StepScope
  public FlatFileItemReader<PrefectureEntity> prefInputReader() {
    return new FlatFileItemReaderBuilder<PrefectureEntity>()
        .name("prefInputReader")
        .resource(new FileSystemResource(PREF_INPUT))
        .encoding(StandardCharsets.UTF_8.name())
        .delimited()
        .names(PREF_IN_HEADER)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<PrefectureEntity>() {{
          setTargetType(PrefectureEntity.class);
        }})
        .build();
  }
}
