# フレームワーク選定
Spring Batch (Spring Boot)

# 検証環境
    OS： Windows 10 Enterprise 1903(18362.592)
    DB： MySQL 8.0.18

# ビルド
    Maven 3.6.3

## Mavenコマンド
    1. コマンドレットで、ビルド後のtargetフォルダへ移動
      $ cd zip解凍後のProjectフォルダ

    2. デプロイ時パッケージング
      $ ./mvn package

    3. 前回ビルド結果クリア
      $ ./mvn clean

# ジョブ
    ・実行可能ジョブ名
      # importJob
        CSVファイルを読み込み、DBへ書き込む
      # exportUserJob
        DBにあるレコードを取り込み、ｃｓｖファイルへ書き出す

## ジョブ実行コマンド
    1. コマンドレットで、ビルド後のtargetフォルダへ移動
      $ cd Projectフォルダ/target

    2. importUserJobを実行する
      $ java -jar .\spring-batch-maven.jar --spring.batch.job.names=importJob

    3. exportUserJobを実行する
      $ java -jar .\spring-batch-maven.jar --spring.batch.job.names=exportUserJob
