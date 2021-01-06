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
  public static final String DELIMITER_COMMA = ",";

  /**
   * CSVファイル項目区切り：半角縦線
   */
  public static final String DELIMITER_VERTICAL = "|";

  /**
   * CSVファイル項目囲み文字：半角ダブルクォーテーション
   */
  public static final char ENCLOSE = '\"';

  /**
   * CSVファイル項目囲み文字：半角ダブルクォーテーション
   */
  public static final String NO_ENCLOSE = "";

  /**
   * CSVファイル入力パス
   */
  public static final String INPUT_FILE_PATH = "input/";

  /**
   * CSVファイル出力パス
   */
  public static final String OUTUT_FILE_PATH = "output/";

  /**
   * 出力ファイル拡張子：csv
   */
  public static final String FILE_SURFFIX = ".csv";

  /**
   * データベース接続Mapperリソース格納パス
   */
  public static final String MAPPER_RESOURCE_PATH = "jp/co/fly/model/mapper/*.xml";

  /**
   * 一般ログ（INFO、WARN、DEBUGなど）出力パス
   */
  public static final String DEFAULT_LOG_PATH = "logs/";

  /**
   * エラーログ（ERROR）出力パス
   */
  public static final String ERROR_LOG_PATH = "logs/";
}
