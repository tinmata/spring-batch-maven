package jp.co.fly.consts;

/**
 * プロジェクト内使用する定数を定義するクラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
public class Consts {

  /**
   * CSVファイル項目区切り：半角カンマ
   */
  public static final String DELIMITER = ",";

  /**
   * CSVファイル出力パス
   */
  public static final String FILE_PATH = "target/";

  /**
   * 出力ファイル拡張子：csv
   */
  public static final String FILE_SURFFIX = ".csv";

  /**
   * データベース接続Mapperリソース格納パス
   */
  public static final String MAPPER_RESOURCE_PATH = "jp/co/fly/model/mapper/*.xml";
}
