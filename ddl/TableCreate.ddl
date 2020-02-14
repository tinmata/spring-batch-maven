-- 検証用スキーマ作成SQL
CREATE DATABASE `testdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- 検証用テーブル作成SQL
CREATE TABLE `mst_prefecture` (
  `pref_id` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs NOT NULL,
  `pref_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pref_capital` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`pref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ja_0900_as_cs;

CREATE TABLE `user` (
  `username` varchar(128) COLLATE utf8mb4_ja_0900_as_cs NOT NULL,
  `password` varchar(128) COLLATE utf8mb4_ja_0900_as_cs NOT NULL,
  `firstname` varchar(45) COLLATE utf8mb4_ja_0900_as_cs DEFAULT NULL,
  `lastname` varchar(45) COLLATE utf8mb4_ja_0900_as_cs DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ja_0900_as_cs;

CREATE TABLE `user_info` (
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs NOT NULL,
  `age` int(11) DEFAULT NULL,
  `tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs DEFAULT NULL,
  `pref_id` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_ja_0900_as_cs DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ja_0900_as_cs;
