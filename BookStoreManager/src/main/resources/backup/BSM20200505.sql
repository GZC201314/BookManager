-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 114.116.252.220    Database: bookmanager
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `README`
--

DROP TABLE IF EXISTS `README`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `README` (
  `id` int NOT NULL,
  `readme` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `BTC_address` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `email` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `README`
--

LOCK TABLES `README` WRITE;
/*!40000 ALTER TABLE `README` DISABLE KEYS */;
INSERT INTO `README` VALUES (1,'以下数据库已被删除：bookmanager, docker_test。 我们有完整的备份。 要恢复它，您必须向我们的比特币地址bc1qd5cnuvgntg0xf5vh9hau57xqgz8700wkhgevt3支付0.006比特币（BTC）。 如果您需要证明，请通过以下电子邮件与我们联系。 meng18@tutanota.com 。 任何与付款无关的邮件都将被忽略！','bc1qd5cnuvgntg0xf5vh9hau57xqgz8700wkhgevt3','meng18@tutanota.com');
/*!40000 ALTER TABLE `README` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tauthorize`
--

DROP TABLE IF EXISTS `tauthorize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tauthorize` (
  `id` varchar(36) NOT NULL,
  `authorizepage` varchar(36) DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK93B49F523D1A41A` (`roleid`),
  KEY `FK93B49F58983EEA` (`authorizepage`),
  CONSTRAINT `FK93B49F523D1A41A` FOREIGN KEY (`roleid`) REFERENCES `trole` (`roleid`),
  CONSTRAINT `FK93B49F58983EEA` FOREIGN KEY (`authorizepage`) REFERENCES `tmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tauthorize`
--

LOCK TABLES `tauthorize` WRITE;
/*!40000 ALTER TABLE `tauthorize` DISABLE KEYS */;
/*!40000 ALTER TABLE `tauthorize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmenu`
--

DROP TABLE IF EXISTS `tmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tmenu` (
  `id` varchar(36) NOT NULL,
  `iconCls` varchar(50) DEFAULT NULL,
  `text` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK695BBD35AF9A27D` (`pid`),
  CONSTRAINT `FK695BBD35AF9A27D` FOREIGN KEY (`pid`) REFERENCES `tmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmenu`
--

LOCK TABLES `tmenu` WRITE;
/*!40000 ALTER TABLE `tmenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trole`
--

DROP TABLE IF EXISTS `trole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trole` (
  `roleid` int NOT NULL,
  `disabled` int NOT NULL,
  `excol` varchar(200) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `rolename` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trole`
--

LOCK TABLES `trole` WRITE;
/*!40000 ALTER TABLE `trole` DISABLE KEYS */;
/*!40000 ALTER TABLE `trole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ttag`
--

DROP TABLE IF EXISTS `ttag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ttag` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ttag`
--

LOCK TABLES `ttag` WRITE;
/*!40000 ALTER TABLE `ttag` DISABLE KEYS */;
/*!40000 ALTER TABLE `ttag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tuser` (
  `name` varchar(36) NOT NULL,
  `createdatetime` datetime DEFAULT NULL,
  `id` varchar(36) NOT NULL,
  `isFaceValid` int DEFAULT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `userlog` varchar(256) DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`),
  KEY `FK699923F23D1A41A` (`roleid`),
  CONSTRAINT `FK699923F23D1A41A` FOREIGN KEY (`roleid`) REFERENCES `trole` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuser`
--

LOCK TABLES `tuser` WRITE;
/*!40000 ALTER TABLE `tuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `tuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-10 22:22:27
