package jp.co.fly.job.usersExport;

import jp.co.fly.consts.Consts;
import jp.co.fly.consts.FileHeader;
import jp.co.fly.model.entity.ExportUsersEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.ExtractorLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * ステップの書き込み処理実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ExportUsersWriter {

  /**
   * ファイル出力時のパスとファイル名の定義。
   */
  private static final String OUTPUT = Consts.OUTUT_FILE_PATH + "UserList" + Consts.FILE_SURFFIX;

  /**
   * 出力ファイルのヘッダーの定義。
   */
  private static final String[] FILE_HEADER = {FileHeader.username.name(),
      FileHeader.firstName.name(), FileHeader.lastName.name(), FileHeader.age.name(),
      FileHeader.tel.name(), FileHeader.prefName.name(), FileHeader.address.name()};

  /**
   * FlatFileItemWriterによるCSVファイル書き込み処理実装例
   *
   * @return FlatFileItemWriter
   */
  public ItemWriter<ExportUsersEntity> writer() {

    BeanWrapperFieldExtractor<ExportUsersEntity> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(FILE_HEADER);

    DelimitedLineAggregator<ExportUsersEntity> lineAggregator = new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(Consts.DELIMITER);
    lineAggregator.setFieldExtractor(fieldExtractor);

    FlatFileItemWriter<ExportUsersEntity> usersWriter = new FlatFileItemWriter<>();
    usersWriter.setResource(new FileSystemResource(OUTPUT));
    usersWriter.setLineAggregator(lineAggregator);

    return usersWriter;
  }
}
