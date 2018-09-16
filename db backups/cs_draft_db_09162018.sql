CREATE DATABASE  IF NOT EXISTS `cs_draft_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cs_draft_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: cs_draft_db
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_access_level`
--

DROP TABLE IF EXISTS `tbl_access_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_access_level` (
  `al_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_user_id` int(11) NOT NULL,
  `can_create` tinyint(1) NOT NULL,
  `can_update` tinyint(1) NOT NULL,
  `can_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`al_id`),
  UNIQUE KEY `al_id_UNIQUE` (`al_id`),
  KEY `fk_user_id_idx` (`fk_user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_access_level`
--

LOCK TABLES `tbl_access_level` WRITE;
/*!40000 ALTER TABLE `tbl_access_level` DISABLE KEYS */;
INSERT INTO `tbl_access_level` VALUES (9,12,1,1,1),(10,13,1,1,1),(11,14,1,1,1),(12,15,1,1,0);
/*!40000 ALTER TABLE `tbl_access_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_batch`
--

DROP TABLE IF EXISTS `tbl_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_batch` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_product_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `comments` varchar(250) NOT NULL,
  `supplier` varchar(45) NOT NULL,
  `entry_timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_id_UNIQUE` (`batch_id`),
  KEY `fk_tbl_batch_tbl_product1_idx` (`fk_product_id`),
  CONSTRAINT `fk_tbl_batch_tbl_product1` FOREIGN KEY (`fk_product_id`) REFERENCES `tbl_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_batch`
--

LOCK TABLES `tbl_batch` WRITE;
/*!40000 ALTER TABLE `tbl_batch` DISABLE KEYS */;
INSERT INTO `tbl_batch` VALUES (5,1,150,'shipment of 150 colgate white toothpastes 150ml tubes','Boghart','2018-09-10 16:23:28'),(6,2,230,'shipment of 230 pcs Colgate toothpastes (red 150ml tubes), supplied by Kukert. Noted the slightly damaged packaging','Kukert','2018-09-10 16:26:05'),(7,2,90,'toothpaste galing kay boyet','Boyet','2018-09-11 08:13:54'),(8,4,50,'ground beef from sobaking. Freshly produced, very clean, wow','Sobaking','2018-09-11 16:21:14');
/*!40000 ALTER TABLE `tbl_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `category_type` varchar(45) NOT NULL,
  `is_perishable` tinyint(1) NOT NULL,
  `is_recyclable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name_UNIQUE` (`category_name`),
  UNIQUE KEY `category_id_UNIQUE` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
INSERT INTO `tbl_category` VALUES (1,'toothpaste','Personal Care',1,NULL),(2,'tooth brush','Personal Care',0,0),(3,'Ground Beef','Meat',1,NULL),(4,'Cabbage','Produce',1,NULL),(6,'Carrot','Produce',1,NULL),(7,'Plastic cup','Other',0,1);
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_item`
--

DROP TABLE IF EXISTS `tbl_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_batch_id` int(11) NOT NULL,
  `manufacture_date` date DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `purchase_price` double NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_tbl_item_tbl_batch1_idx` (`fk_batch_id`),
  CONSTRAINT `fk_tbl_item_tbl_batch1` FOREIGN KEY (`fk_batch_id`) REFERENCES `tbl_batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11671 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_item`
--

LOCK TABLES `tbl_item` WRITE;
/*!40000 ALTER TABLE `tbl_item` DISABLE KEYS */;
INSERT INTO `tbl_item` VALUES (1155,5,'2018-08-17','2035-10-17',120.75),(1156,5,'2018-08-17','2035-10-16',120.75),(1157,5,'2018-08-16','2035-10-17',120.75),(1158,5,'2018-08-29','2035-10-16',120.75),(1159,5,'2018-08-16','2035-10-17',120.75),(1160,5,'2018-08-16','2035-10-17',120.75),(1161,5,'2018-08-16','2035-10-17',120.75),(1162,5,'2018-08-16','2035-10-17',120.75),(1163,5,'2018-08-16','2035-10-17',120.75),(1164,5,'2018-08-16','2035-10-17',120.75),(1165,5,'2018-08-16','2035-10-17',120.75),(1166,5,'2018-08-16','2035-10-17',120.75),(1167,5,'2018-08-16','2035-10-17',120.75),(1168,5,'2018-08-16','2035-10-17',120.75),(1169,5,'2018-08-16','2035-10-17',120.75),(1170,5,'2018-08-16','2035-10-17',120.75),(1171,5,'2018-08-16','2035-10-17',120.75),(1172,5,'2018-08-16','2035-10-17',120.75),(1173,5,'2018-08-16','2035-10-17',120.75),(1174,5,'2018-08-16','2035-10-17',120.75),(1175,5,'2018-08-16','2035-10-17',120.75),(1176,5,'2018-08-16','2035-10-17',120.75),(1177,5,'2018-08-16','2035-10-17',120.75),(1178,5,'2018-08-16','2035-10-17',120.75),(1179,5,'2018-08-16','2035-10-17',120.75),(1180,5,'2018-08-16','2035-10-17',120.75),(1181,5,'2018-08-16','2035-10-17',120.75),(1182,5,'2018-08-16','2035-10-17',120.75),(1183,5,'2018-08-16','2035-10-17',120.75),(1184,5,'2018-08-16','2035-10-17',120.75),(1185,5,'2018-08-16','2035-10-17',120.75),(1186,5,'2018-08-16','2035-10-17',120.75),(1187,5,'2018-08-16','2035-10-17',120.75),(1188,5,'2018-08-16','2035-10-17',120.75),(1189,5,'2018-08-16','2035-10-17',120.75),(1190,5,'2018-08-16','2035-10-17',120.75),(1191,5,'2018-08-16','2035-10-17',120.75),(1192,5,'2018-08-16','2035-10-17',120.75),(1193,5,'2018-08-16','2035-10-17',120.75),(1194,5,'2018-08-16','2035-10-17',120.75),(1195,5,'2018-08-16','2035-10-17',120.75),(1196,5,'2018-08-16','2035-10-17',120.75),(1197,5,'2018-08-16','2035-10-17',120.75),(1198,5,'2018-08-16','2035-10-17',120.75),(1199,5,'2018-08-16','2035-10-17',120.75),(1200,5,'2018-08-16','2035-10-17',120.75),(1201,5,'2018-08-16','2035-10-17',120.75),(1202,5,'2018-08-16','2035-10-17',120.75),(1203,5,'2018-08-16','2035-10-17',120.75),(1204,5,'2018-08-16','2035-10-17',120.75),(1205,5,'2018-08-16','2035-10-17',120.75),(1206,5,'2018-08-16','2035-10-17',120.75),(1207,5,'2018-08-16','2035-10-17',120.75),(1208,5,'2018-08-16','2035-10-17',120.75),(1209,5,'2018-08-16','2035-10-17',120.75),(1210,5,'2018-08-16','2035-10-17',120.75),(1211,5,'2018-08-16','2035-10-17',120.75),(1212,5,'2018-08-16','2035-10-17',120.75),(1213,5,'2018-08-16','2035-10-17',120.75),(1214,5,'2018-08-16','2035-10-17',120.75),(1215,5,'2018-08-16','2035-10-17',120.75),(1216,5,'2018-08-16','2035-10-17',120.75),(1217,5,'2018-08-16','2035-10-17',120.75),(1218,5,'2018-08-16','2035-10-17',120.75),(1219,5,'2018-08-16','2035-10-17',120.75),(1220,5,'2018-08-16','2035-10-17',120.75),(1221,5,'2018-08-16','2035-10-17',120.75),(1222,5,'2018-08-16','2035-10-17',120.75),(1223,5,'2018-08-16','2035-10-17',120.75),(1224,5,'2018-08-16','2035-10-17',120.75),(1225,5,'2018-08-16','2035-10-17',120.75),(1226,5,'2018-08-16','2035-10-17',120.75),(1227,5,'2018-08-16','2035-10-17',120.75),(1228,5,'2018-08-16','2035-10-17',120.75),(1229,5,'2018-08-16','2035-10-17',120.75),(1230,5,'2018-08-16','2035-10-17',120.75),(1231,5,'2018-08-16','2035-10-17',120.75),(1232,5,'2018-08-16','2035-10-17',120.75),(1233,5,'2018-08-16','2035-10-17',120.75),(1234,5,'2018-08-16','2035-10-17',120.75),(1235,5,'2018-08-16','2035-10-17',120.75),(1236,5,'2018-08-16','2035-10-17',120.75),(1237,5,'2018-08-16','2035-10-17',120.75),(1238,5,'2018-08-16','2035-10-17',120.75),(1239,5,'2018-08-16','2035-10-17',120.75),(1240,5,'2018-08-16','2035-10-17',120.75),(1241,5,'2018-08-16','2035-10-17',120.75),(1242,5,'2018-08-16','2035-10-17',120.75),(1243,5,'2018-08-16','2035-10-17',120.75),(1244,5,'2018-08-16','2035-10-17',120.75),(1245,5,'2018-08-16','2035-10-17',120.75),(1246,5,'2018-08-16','2035-10-17',120.75),(1247,5,'2018-08-16','2035-10-17',120.75),(1248,5,'2018-08-16','2035-10-17',120.75),(1249,5,'2018-08-16','2035-10-17',120.75),(1250,5,'2018-08-16','2035-10-17',120.75),(1251,5,'2018-08-16','2035-10-17',120.75),(1252,5,'2018-08-16','2035-10-17',120.75),(1253,5,'2018-08-16','2035-10-17',120.75),(1254,5,'2018-08-16','2035-10-17',120.75),(1255,5,'2018-08-16','2035-10-17',120.75),(1256,5,'2018-08-16','2035-10-17',120.75),(1257,5,'2018-08-16','2035-10-17',120.75),(1258,5,'2018-08-16','2035-10-17',120.75),(1259,5,'2018-08-16','2035-10-17',120.75),(1260,5,'2018-08-16','2035-10-17',120.75),(1261,5,'2018-08-16','2035-10-17',120.75),(1262,5,'2018-08-16','2035-10-17',120.75),(1263,5,'2018-08-16','2035-10-17',120.75),(1264,5,'2018-08-16','2035-10-17',120.75),(1265,5,'2018-08-16','2035-10-17',120.75),(1266,5,'2018-08-16','2035-10-17',120.75),(1267,5,'2018-08-16','2035-10-17',120.75),(1268,5,'2018-08-16','2035-10-17',120.75),(1269,5,'2018-08-16','2035-10-17',120.75),(1270,5,'2018-08-16','2035-10-17',120.75),(1271,5,'2018-08-16','2035-10-17',120.75),(1272,5,'2018-08-16','2035-10-17',120.75),(1273,5,'2018-08-16','2035-10-17',120.75),(1274,5,'2018-08-16','2035-10-17',120.75),(1275,5,'2018-08-16','2035-10-17',120.75),(1276,5,'2018-08-16','2035-10-17',120.75),(1277,5,'2018-08-16','2035-10-17',120.75),(1278,5,'2018-08-16','2035-10-17',120.75),(1279,5,'2018-08-16','2035-10-17',120.75),(1280,5,'2018-08-16','2035-10-17',120.75),(1281,5,'2018-08-16','2035-10-17',120.75),(1282,5,'2018-08-16','2035-10-17',120.75),(1283,5,'2018-08-16','2035-10-17',120.75),(1284,5,'2018-08-16','2035-10-17',120.75),(1285,5,'2018-08-16','2035-10-17',120.75),(1286,5,'2018-08-16','2035-10-17',120.75),(1287,5,'2018-08-16','2035-10-17',120.75),(1288,5,'2018-08-16','2035-10-17',120.75),(1289,5,'2018-08-16','2035-10-17',120.75),(1290,5,'2018-08-16','2035-10-17',120.75),(1291,5,'2018-08-16','2035-10-17',120.75),(1292,5,'2018-08-16','2035-10-17',120.75),(1293,5,'2018-08-16','2035-10-17',120.75),(1294,5,'2018-08-16','2035-10-17',120.75),(1295,5,'2018-08-16','2035-10-17',120.75),(1296,5,'2018-08-16','2035-10-17',120.75),(1297,5,'2018-08-16','2035-10-17',120.75),(1298,5,'2018-08-16','2035-10-17',120.75),(1299,5,'2018-08-16','2035-10-17',120.75),(1300,5,'2018-08-16','2035-10-17',120.75),(1301,5,'2018-08-16','2035-10-17',120.75),(1302,6,'2018-08-09','2039-10-26',120.75),(1303,6,'2017-12-31','2018-12-30',0.18),(1304,6,'2017-12-31','2018-12-30',0.18),(1306,6,'2018-08-09','2039-10-26',120.75),(1308,6,'2017-12-31','2018-12-30',0.18),(1310,6,'2017-12-31','2018-12-30',0.18),(1311,6,'2018-08-09','2039-10-26',120.75),(1312,6,'2017-12-31','2018-12-30',0.18),(1313,6,'2018-08-09','2039-10-26',120.75),(1314,6,'2018-08-09','2039-10-26',120.75),(1316,6,'2018-08-09','2039-10-26',120.75),(1318,6,'2018-08-09','2039-10-26',120.75),(1319,6,'2018-08-09','2039-10-26',120.75),(1320,6,'2018-08-09','2039-10-26',120.75),(1321,6,'2018-08-09','2039-10-26',120.75),(1322,6,'2018-08-09','2039-10-26',120.75),(1323,6,'2018-08-09','2039-10-26',120.75),(1324,6,'2018-08-09','2039-10-26',120.75),(1325,6,'2018-08-09','2039-10-26',120.75),(1326,6,'2018-08-09','2039-10-26',120.75),(1327,6,'2018-08-09','2039-10-26',120.75),(1328,6,'2018-08-09','2039-10-26',120.75),(1329,6,'2018-08-09','2039-10-26',120.75),(1330,6,'2018-08-09','2039-10-26',120.75),(1331,6,'2018-08-09','2039-10-26',120.75),(1332,6,'2018-08-09','2039-10-26',120.75),(1333,6,'2018-08-09','2039-10-26',120.75),(1334,6,'2018-08-09','2039-10-26',120.75),(1335,6,'2018-08-09','2039-10-26',120.75),(1336,6,'2018-08-09','2039-10-26',120.75),(1337,6,'2018-08-09','2039-10-26',120.75),(1338,6,'2018-08-09','2039-10-26',120.75),(1339,6,'2018-08-09','2039-10-26',120.75),(1340,6,'2018-08-09','2039-10-26',120.75),(1341,6,'2018-08-09','2039-10-26',120.75),(1342,6,'2018-08-09','2039-10-26',120.75),(1343,6,'2018-08-09','2039-10-26',120.75),(1344,6,'2018-08-09','2039-10-26',120.75),(1345,6,'2018-08-09','2039-10-26',120.75),(1346,6,'2018-08-09','2039-10-26',120.75),(1347,6,'2018-08-09','2039-10-26',120.75),(1348,6,'2018-08-09','2039-10-26',120.75),(1349,6,'2018-08-09','2039-10-26',120.75),(1350,6,'2018-08-09','2039-10-26',120.75),(1351,6,'2018-08-09','2039-10-26',120.75),(1352,6,'2018-08-09','2039-10-26',120.75),(1353,6,'2018-08-09','2039-10-26',120.75),(1354,6,'2018-08-09','2039-10-26',120.75),(1355,6,'2018-08-09','2039-10-26',120.75),(1356,6,'2018-08-09','2039-10-26',120.75),(1357,6,'2018-08-09','2039-10-26',120.75),(1358,6,'2018-08-09','2039-10-26',120.75),(1359,6,'2018-08-09','2039-10-26',120.75),(1360,6,'2018-08-09','2039-10-26',120.75),(1361,6,'2018-08-09','2039-10-26',120.75),(1362,6,'2018-08-09','2039-10-26',120.75),(1363,6,'2018-08-09','2039-10-26',120.75),(1364,6,'2018-08-09','2039-10-26',120.75),(1365,6,'2018-08-09','2039-10-26',120.75),(1366,6,'2018-08-09','2039-10-26',120.75),(1367,6,'2018-08-09','2039-10-26',120.75),(1368,6,'2018-08-09','2039-10-26',120.75),(1369,6,'2018-08-09','2039-10-26',120.75),(1370,6,'2018-08-09','2039-10-26',120.75),(1371,6,'2018-08-09','2039-10-26',120.75),(1372,6,'2018-08-09','2039-10-26',120.75),(1373,6,'2018-08-09','2039-10-26',120.75),(1374,6,'2018-08-09','2039-10-26',120.75),(1375,6,'2018-08-09','2039-10-26',120.75),(1376,6,'2018-08-09','2039-10-26',120.75),(1377,6,'2018-08-09','2039-10-26',120.75),(1378,6,'2018-08-09','2039-10-26',120.75),(1379,6,'2018-08-09','2039-10-26',120.75),(1380,6,'2018-08-09','2039-10-26',120.75),(1381,6,'2018-08-09','2039-10-26',120.75),(1382,6,'2018-08-09','2039-10-26',120.75),(1383,6,'2018-08-09','2039-10-26',120.75),(1384,6,'2018-08-09','2039-10-26',120.75),(1385,6,'2018-08-09','2039-10-26',120.75),(1386,6,'2018-08-09','2039-10-26',120.75),(1387,6,'2018-08-09','2039-10-26',120.75),(1388,6,'2018-08-09','2039-10-26',120.75),(1389,6,'2018-08-09','2039-10-26',120.75),(1390,6,'2018-08-09','2039-10-26',120.75),(1391,6,'2018-08-09','2039-10-26',120.75),(1392,6,'2018-08-09','2039-10-26',120.75),(1393,6,'2018-08-09','2039-10-26',120.75),(1394,6,'2018-08-09','2039-10-26',120.75),(1395,6,'2018-08-09','2039-10-26',120.75),(1396,6,'2018-08-09','2039-10-26',120.75),(1397,6,'2018-08-09','2039-10-26',120.75),(1398,6,'2018-08-09','2039-10-26',120.75),(1399,6,'2018-08-09','2039-10-26',120.75),(1400,6,'2018-08-09','2039-10-26',120.75),(1401,6,'2018-08-09','2039-10-26',120.75),(1402,6,'2018-08-09','2039-10-26',120.75),(1403,6,'2018-08-09','2039-10-26',120.75),(1404,6,'2018-08-09','2039-10-26',120.75),(1405,6,'2018-08-09','2039-10-26',120.75),(1406,6,'2018-08-09','2039-10-26',120.75),(1407,6,'2018-08-09','2039-10-26',120.75),(1408,6,'2018-08-09','2039-10-26',120.75),(1409,6,'2018-08-09','2039-10-26',120.75),(1410,6,'2018-08-09','2039-10-26',120.75),(1411,6,'2018-08-09','2039-10-26',120.75),(1412,6,'2018-08-09','2039-10-26',120.75),(1413,6,'2018-08-09','2039-10-26',120.75),(1414,6,'2018-08-09','2039-10-26',120.75),(1415,6,'2018-08-09','2039-10-26',120.75),(1416,6,'2018-08-09','2039-10-26',120.75),(1417,6,'2018-08-09','2039-10-26',120.75),(1418,6,'2018-08-09','2039-10-26',120.75),(1419,6,'2018-08-09','2039-10-26',120.75),(1420,6,'2018-08-09','2039-10-26',120.75),(1421,6,'2018-08-09','2039-10-26',120.75),(1422,6,'2018-08-09','2039-10-26',120.75),(1423,6,'2018-08-09','2039-10-26',120.75),(1424,6,'2018-08-09','2039-10-26',120.75),(1425,6,'2018-08-09','2039-10-26',120.75),(1426,6,'2018-08-09','2039-10-26',120.75),(1427,6,'2018-08-09','2039-10-26',120.75),(1428,6,'2018-08-09','2039-10-26',120.75),(1429,6,'2018-08-09','2039-10-26',120.75),(1430,6,'2018-08-09','2039-10-26',120.75),(1431,6,'2018-08-09','2039-10-26',120.75),(1432,6,'2018-08-09','2039-10-26',120.75),(1433,6,'2018-08-09','2039-10-26',120.75),(1434,6,'2018-08-09','2039-10-26',120.75),(1435,6,'2018-08-09','2039-10-26',120.75),(1436,6,'2018-08-09','2039-10-26',120.75),(1437,6,'2018-08-09','2039-10-26',120.75),(1438,6,'2018-08-09','2039-10-26',120.75),(1439,6,'2018-08-09','2039-10-26',120.75),(1440,6,'2018-08-09','2039-10-26',120.75),(1441,6,'2018-08-09','2039-10-26',120.75),(1442,6,'2018-08-09','2039-10-26',120.75),(1443,6,'2018-08-09','2039-10-26',120.75),(1444,6,'2018-08-09','2039-10-26',120.75),(1445,6,'2018-08-09','2039-10-26',120.75),(1446,6,'2018-08-09','2039-10-26',120.75),(1447,6,'2018-08-09','2039-10-26',120.75),(1448,6,'2018-08-09','2039-10-26',120.75),(1449,6,'2018-08-09','2039-10-26',120.75),(1450,6,'2018-08-09','2039-10-26',120.75),(1451,6,'2018-08-09','2039-10-26',120.75),(1452,6,'2018-08-09','2039-10-26',120.75),(1453,6,'2018-08-09','2039-10-26',120.75),(1454,6,'2018-08-09','2039-10-26',120.75),(1455,6,'2018-08-09','2039-10-26',120.75),(1456,6,'2018-08-09','2039-10-26',120.75),(1457,6,'2018-08-09','2039-10-26',120.75),(1458,6,'2018-08-09','2039-10-26',120.75),(1459,6,'2018-08-09','2039-10-26',120.75),(1460,6,'2018-08-09','2039-10-26',120.75),(1461,6,'2018-08-09','2039-10-26',120.75),(1462,6,'2018-08-09','2039-10-26',120.75),(1463,6,'2018-08-09','2039-10-26',120.75),(1464,6,'2018-08-09','2039-10-26',120.75),(1465,6,'2018-08-09','2039-10-26',120.75),(1466,6,'2018-08-09','2039-10-26',120.75),(1467,6,'2018-08-09','2039-10-26',120.75),(1468,6,'2018-08-09','2039-10-26',120.75),(1469,6,'2018-08-09','2039-10-26',120.75),(1470,6,'2018-08-09','2039-10-26',120.75),(1471,6,'2018-08-09','2039-10-26',120.75),(1472,6,'2018-08-09','2039-10-26',120.75),(1473,6,'2018-08-09','2039-10-26',120.75),(1474,6,'2018-08-09','2039-10-26',120.75),(1475,6,'2018-08-09','2039-10-26',120.75),(1476,6,'2018-08-09','2039-10-26',120.75),(1477,6,'2018-08-09','2039-10-26',120.75),(1478,6,'2018-08-09','2039-10-26',120.75),(1479,6,'2018-08-09','2039-10-26',120.75),(1480,6,'2018-08-09','2039-10-26',120.75),(1481,6,'2018-08-09','2039-10-26',120.75),(1482,6,'2018-08-09','2039-10-26',120.75),(1483,6,'2018-08-09','2039-10-26',120.75),(1484,6,'2018-08-09','2039-10-26',120.75),(1485,6,'2018-08-09','2039-10-26',120.75),(1486,6,'2018-08-09','2039-10-26',120.75),(1487,6,'2018-08-09','2039-10-26',120.75),(1488,6,'2018-08-09','2039-10-26',120.75),(1489,6,'2018-08-09','2039-10-26',120.75),(1490,6,'2018-08-09','2039-10-26',120.75),(1491,6,'2018-08-09','2039-10-26',120.75),(1492,6,'2018-08-09','2039-10-26',120.75),(1493,6,'2018-08-09','2039-10-26',120.75),(1494,6,'2018-08-09','2039-10-26',120.75),(1495,6,'2018-08-09','2039-10-26',120.75),(1496,6,'2018-08-09','2039-10-26',120.75),(1497,6,'2018-08-09','2039-10-26',120.75),(1498,6,'2018-08-09','2039-10-26',120.75),(1499,6,'2018-08-09','2039-10-26',120.75),(1500,6,'2018-08-09','2039-10-26',120.75),(1501,6,'2018-08-09','2039-10-26',120.75),(1502,6,'2018-08-09','2039-10-26',120.75),(1503,6,'2018-08-09','2039-10-26',120.75),(1504,6,'2018-08-09','2039-10-26',120.75),(1505,6,'2018-08-09','2039-10-26',120.75),(1506,6,'2018-08-09','2039-10-26',120.75),(1507,6,'2018-08-09','2039-10-26',120.75),(1508,6,'2018-08-09','2039-10-26',120.75),(1509,6,'2018-08-09','2039-10-26',120.75),(1510,6,'2018-08-09','2039-10-26',120.75),(1511,6,'2018-08-09','2039-10-26',120.75),(1512,6,'2018-08-09','2039-10-26',120.75),(1513,6,'2018-08-09','2039-10-26',120.75),(1514,6,'2018-08-09','2039-10-26',120.75),(1515,6,'2018-08-09','2039-10-26',120.75),(1516,6,'2018-08-09','2039-10-26',120.75),(1517,6,'2018-08-09','2039-10-26',120.75),(1518,6,'2018-08-09','2039-10-26',120.75),(1519,6,'2018-08-09','2039-10-26',120.75),(1520,6,'2018-08-09','2039-10-26',120.75),(1521,6,'2018-08-09','2039-10-26',120.75),(1522,6,'2018-08-09','2039-10-26',120.75),(1523,6,'2018-08-09','2039-10-26',120.75),(1524,6,'2018-08-09','2039-10-26',120.75),(1525,6,'2018-08-09','2039-10-26',120.75),(1526,6,'2018-08-09','2039-10-26',120.75),(1527,6,'2018-08-09','2039-10-26',120.75),(1528,6,'2018-08-09','2039-10-26',120.75),(1529,6,'2018-08-09','2039-10-26',120.75),(1530,6,'2018-08-09','2039-10-26',120.75),(1531,6,'2018-08-09','2039-10-26',120.75),(1532,7,'2018-09-28','2020-09-30',99.78),(1533,7,'2018-09-28','2020-09-30',99.78),(1534,7,'2018-09-28','2020-09-30',99.78),(1535,7,'2018-09-28','2020-09-30',99.78),(1536,7,'2018-09-28','2020-09-30',99.78),(1537,7,'2018-09-28','2020-09-30',99.78),(1538,7,'2018-09-28','2020-09-30',99.78),(1539,7,'2018-09-28','2020-09-30',99.78),(1540,7,'2018-09-28','2020-09-30',99.78),(1541,7,'2018-09-28','2020-09-30',99.78),(1542,7,'2018-09-28','2020-09-30',99.78),(1543,7,'2018-09-28','2020-09-30',99.78),(1544,7,'2018-09-28','2020-09-30',99.78),(1545,7,'2018-09-28','2020-09-30',99.78),(1546,7,'2018-09-28','2020-09-30',99.78),(1547,7,'2018-09-28','2020-09-30',99.78),(1548,7,'2018-09-28','2020-09-30',99.78),(1549,7,'2018-09-28','2020-09-30',99.78),(1550,7,'2018-09-28','2020-09-30',99.78),(1551,7,'2018-09-28','2020-09-30',99.78),(1552,7,'2018-09-28','2020-09-30',99.78),(1553,7,'2018-09-28','2020-09-30',99.78),(1554,7,'2018-09-28','2020-09-30',99.78),(1555,7,'2018-09-28','2020-09-30',99.78),(1556,7,'2018-09-28','2020-09-30',99.78),(1557,7,'2018-09-28','2020-09-30',99.78),(1558,7,'2018-09-28','2020-09-30',99.78),(1559,7,'2018-09-28','2020-09-30',99.78),(1560,7,'2018-09-28','2020-09-30',99.78),(1561,7,'2018-09-28','2020-09-30',99.78),(1562,7,'2018-09-28','2020-09-30',99.78),(1563,7,'2018-09-28','2020-09-30',99.78),(1564,7,'2018-09-28','2020-09-30',99.78),(1565,7,'2018-09-28','2020-09-30',99.78),(1566,7,'2018-09-28','2020-09-30',99.78),(1567,7,'2018-09-28','2020-09-30',99.78),(1568,7,'2018-09-28','2020-09-30',99.78),(1569,7,'2018-09-28','2020-09-30',99.78),(1570,7,'2018-09-28','2020-09-30',99.78),(1571,7,'2017-12-31','2018-12-30',0.18),(1572,7,'2018-09-28','2020-09-30',99.78),(1573,7,'2018-09-28','2020-09-30',99.78),(1574,7,'2018-09-28','2020-09-30',99.78),(1575,7,'2018-09-28','2020-09-30',99.78),(1576,7,'2018-09-28','2020-09-30',99.78),(1577,7,'2017-12-31','2018-12-30',0.18),(1578,7,'2018-09-28','2020-09-30',99.78),(1579,7,'2017-12-31','2018-12-30',0.18),(1580,7,'2018-09-28','2020-09-30',99.78),(1581,7,'2018-09-28','2020-09-30',99.78),(1582,7,'2018-09-28','2020-09-30',99.78),(1583,7,'2018-09-28','2020-09-30',99.78),(1584,7,'2018-09-28','2020-09-30',99.78),(1585,7,'2018-09-28','2020-09-30',99.78),(1586,7,'2018-09-28','2020-09-30',99.78),(1587,7,'2018-09-28','2020-09-30',99.78),(1588,7,'2018-09-28','2020-09-30',99.78),(1589,7,'2018-09-28','2020-09-30',99.78),(1590,7,'2018-09-28','2020-09-30',99.78),(1591,7,'2018-09-28','2020-09-30',99.78),(1592,7,'2018-09-28','2020-09-30',99.78),(1593,7,'2018-09-28','2020-09-30',99.78),(1594,7,'2018-09-28','2020-09-30',99.78),(1595,7,'2018-09-28','2020-09-30',99.78),(1596,7,'2018-09-28','2020-09-30',99.78),(1597,7,'2018-09-28','2020-09-30',99.78),(1598,7,'2018-09-28','2020-09-30',99.78),(1599,7,'2018-09-28','2020-09-30',99.78),(1600,7,'2018-09-28','2020-09-30',99.78),(1601,7,'2018-09-28','2020-09-30',99.78),(1602,7,'2018-09-28','2020-09-30',99.78),(1603,7,'2018-09-28','2020-09-30',99.78),(1604,7,'2018-09-28','2020-09-30',99.78),(1605,7,'2018-09-28','2020-09-30',99.78),(1606,7,'2018-09-28','2020-09-30',99.78),(1607,7,'2018-09-28','2020-09-30',99.78),(1608,7,'2018-09-28','2020-09-30',99.78),(1609,7,'2018-09-28','2020-09-30',99.78),(1610,7,'2018-09-28','2020-09-30',99.78),(1611,7,'2018-09-28','2020-09-30',99.78),(1612,7,'2018-09-28','2020-09-30',99.78),(1613,7,'2018-09-28','2020-09-30',99.78),(1614,7,'2018-09-28','2020-09-30',99.78),(1615,7,'2018-09-28','2020-09-30',99.78),(1616,7,'2018-09-28','2020-09-30',99.78),(1617,7,'2018-09-28','2020-09-30',99.78),(1618,7,'2018-09-28','2020-09-30',99.78),(1619,7,'2018-09-28','2020-09-30',99.78),(1620,7,'2018-09-28','2020-09-30',99.78),(1621,7,'2018-09-28','2020-09-30',99.78),(1622,8,'2018-09-09','2018-10-09',200),(1623,8,'2017-12-31','2018-12-30',0.18),(1624,8,'2018-09-09','2018-10-09',200),(1625,8,'2018-09-09','2018-10-09',200),(1626,8,'2018-09-09','2018-10-09',200),(1627,8,'2018-09-09','2018-10-09',200),(1628,8,'2018-09-09','2018-10-09',200),(1629,8,'2018-09-09','2018-10-09',200),(1630,8,'2018-09-09','2018-10-09',200),(1631,8,'2018-09-09','2018-10-09',200),(1632,8,'2018-09-09','2018-10-09',200),(1633,8,'2018-09-09','2018-10-09',200),(1634,8,'2018-09-09','2018-10-09',200),(1635,8,'2018-09-09','2018-10-09',200),(1636,8,'2017-12-31','2018-12-30',0.18),(1637,8,'2018-09-09','2018-10-09',200),(1638,8,'2018-09-09','2018-10-09',200),(1639,8,'2018-09-09','2018-10-09',200),(1640,8,'2018-09-09','2018-10-09',200),(1641,8,'2018-09-09','2018-10-09',200),(1642,8,'2018-09-09','2018-10-09',200),(1643,8,'2018-09-09','2018-10-09',200),(1644,8,'2018-09-09','2018-10-09',200),(1645,8,'2018-09-09','2018-10-09',200),(1646,8,'2018-09-09','2018-10-09',200),(1647,8,'2018-09-09','2018-10-09',200),(1648,8,'2018-09-09','2018-10-09',200),(1649,8,'2018-09-09','2018-10-09',200),(1650,8,'2018-09-09','2018-10-09',200),(1651,8,'2018-09-09','2018-10-09',200),(1652,8,'2018-09-09','2018-10-09',200),(1653,8,'2017-12-31','2018-12-30',0.18),(1654,8,'2018-09-09','2018-10-09',200),(1655,8,'2018-09-09','2018-10-09',200),(1656,8,'2018-09-09','2018-10-09',200),(1657,8,'2018-09-09','2018-10-09',200),(1658,8,'2018-09-09','2018-10-09',200),(1659,8,'2018-09-09','2018-10-09',200),(1660,8,'2018-09-09','2018-10-09',200),(1661,8,'2018-09-09','2018-10-09',200),(1662,8,'2018-09-09','2018-10-09',200),(1663,8,'2018-09-09','2018-10-09',200),(1664,8,'2018-09-09','2018-10-09',200),(1665,8,'2018-09-09','2018-10-09',200),(1666,8,'2018-09-09','2018-10-09',200),(1667,8,'2018-09-09','2018-10-09',200),(1668,8,'2018-09-09','2018-10-09',200),(1669,8,'2018-09-09','2018-10-09',200),(1670,8,'2018-09-09','2018-10-09',200),(1671,8,'2018-09-09','2018-10-09',200);
/*!40000 ALTER TABLE `tbl_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_product`
--

DROP TABLE IF EXISTS `tbl_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_category_id` int(11) NOT NULL,
  `SKU` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `variant` varchar(45) NOT NULL,
  `size` varchar(45) NOT NULL,
  `measurement_unit` varchar(45) NOT NULL,
  `description` varchar(250) NOT NULL,
  `special_handling` varchar(45) NOT NULL,
  `sell_price` double NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `SKU_UNIQUE` (`SKU`),
  KEY `fk_tbl_product_tbl_category1_idx` (`fk_category_id`),
  CONSTRAINT `fk_tbl_product_tbl_category1` FOREIGN KEY (`fk_category_id`) REFERENCES `tbl_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_product`
--

LOCK TABLES `tbl_product` WRITE;
/*!40000 ALTER TABLE `tbl_product` DISABLE KEYS */;
INSERT INTO `tbl_product` VALUES (1,1,'TOCOWH-150ML','Colgate','White','150','ml','Colgate toothpaste, white variant, tube type 150 ml','',95),(2,1,'TOCORE-150ML','Colgate','Red','150','ml','Colgate toothpaste, red variant, tube type, 150 ml','',95),(3,1,'TOCLSI-10ML','Close Up','Silver','10','ml','Close Up toothpaste, silver variant, sachet type, 10ml, smells good, very silver indeed','',10),(4,3,'GRDAAN-1KG','Dan\'s Farm','angus','1','kg','angus ground beef from danots farm','keep frozen',250),(5,4,'CADANO-1PC','Dan\'s Farm','none','1','pc','very good cabbage from danots farm','keep refrigerated',96),(6,6,'CADANO-4PC','Danot Karot','none','4','pcs','4 pcs / pack of Danot Karot carrots','keep refrigerated',12);
/*!40000 ALTER TABLE `tbl_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `usertype` enum('admin','user') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (12,'dan','202cb962ac59075b964b07152d234b70','admin'),(13,'alen','1552c03e78d38d5005d4ce7b8018addf','user'),(14,'qwerty','ccc73e18345b3f6aa97100fbc61fc5e1','user'),(15,'Badjula','3337381473e04c05fafc4db52fac78cd','user');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-17  2:50:49
