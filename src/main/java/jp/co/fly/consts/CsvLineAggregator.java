 /*----------------------------------------------------------------------
  * プログラム名    ：CsvLineAggregator.java
  * 作成日          ：2020/05/11
  * 作成者          ：陳俣/IBM
  *----------------------------------------------------------------------
  * 修正履歴 (No.: 修正日: 担当者: 修正内容)
  *  No.01: 2020/05/11: 陳俣/IBM: 新規作成。
  *----------------------------------------------------------------------
  */

 package jp.co.fly.consts;

 import java.util.Arrays;
 import org.springframework.batch.item.file.transform.ExtractorLineAggregator;
 import org.springframework.util.StringUtils;

 /**
  * CSV出力ファイルの項目区切りと囲み文字を設定します。 区切り文字は「,」とします。 項目囲み文字は["]とします。
  */
 public class CsvLineAggregator<T> extends ExtractorLineAggregator<T> {

   @Override
   protected String doAggregate(Object[] fields) {
     return StringUtils.collectionToDelimitedString(Arrays.asList(fields),
         Consts.DELIMITER_VERTICAL, Consts.NO_ENCLOSE,
         Consts.NO_ENCLOSE);
   }
 }
