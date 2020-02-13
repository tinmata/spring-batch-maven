package jp.co.fly.consts;

/**
 * 出力ファイルのヘッダー項目を定義する列挙型クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
public enum FileHeader {
  /**
   * ファイル項目定義
   */
  username,                   // 入出力
  password,                   // 入力
  firstName,                  // 入出力
  lastName,                   // 入出力
  age,                        // 出力
  tel,                        // 出力
  prefName,                   // 出力
  address;                    // 出力
}
