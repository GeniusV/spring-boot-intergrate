-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: cached_demo
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Current Database: `cached_demo`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cached_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cached_demo`;

--
-- Table structure for table `v_role`
--

DROP TABLE IF EXISTS `v_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `v_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_role`
--

LOCK TABLES `v_role` WRITE;
/*!40000 ALTER TABLE `v_role` DISABLE KEYS */;
INSERT INTO `v_role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `v_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_user`
--

DROP TABLE IF EXISTS `v_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `v_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `status` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `v_user_id_index` (`id`),
  KEY `v_user_user_name_id_index` (`user_name`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_user`
--

LOCK TABLES `v_user` WRITE;
/*!40000 ALTER TABLE `v_user` DISABLE KEYS */;
INSERT INTO `v_user` VALUES (1,'admin','admin',1),(51,'user23','test',1),(54,'user26','test',1),(55,'user27','test',1),(56,'user28','test',1),(57,'user29','test',1),(58,'user30','test',1),(59,'user31','test',1),(60,'user32','test',1),(61,'user33','test',1),(62,'user34','test',1),(63,'user35','test',1),(64,'user36','test',1),(65,'user37','test',1),(66,'user38','test',1),(67,'user39','test',1),(68,'user40','test',1),(69,'user41','test',1),(70,'user42','test',1),(71,'user43','test',1),(72,'user44','test',1),(73,'user45','test',1),(74,'user46','test',1),(75,'user47','test',1),(76,'user48','test',1),(77,'user49','test',1),(78,'user50','test',1),(79,'user51','test',1),(80,'user52','test',1),(81,'user53','test',1),(82,'user54','test',1),(83,'user55','test',1),(84,'user56','test',1),(85,'user57','test',1),(86,'user58','test',1),(87,'user59','test',1),(88,'user60','test',1),(89,'user61','test',1),(90,'user62','test',1),(91,'user63','test',1),(92,'user64','test',1),(93,'user65','test',1),(94,'user66','test',1),(95,'user67','test',1),(96,'user68','test',1),(97,'user69','test',1),(98,'user70','test',1),(99,'user71','test',1),(100,'user72','test',1),(101,'user73','test',1),(102,'user74','test',1),(103,'user75','test',1),(104,'user76','test',1),(105,'user77','test',1),(106,'user78','test',1),(107,'user79','test',1),(108,'user80','test',1),(109,'user81','test',1),(110,'user82','test',1),(111,'user83','test',1),(112,'user84','test',1),(113,'user85','test',1),(114,'user86','test',1),(115,'user87','test',1),(116,'user88','test',1),(117,'user89','test',1),(118,'user90','test',1),(119,'user91','test',1),(120,'user92','test',1),(121,'user93','test',1),(122,'user94','test',1),(123,'user95','test',1);
/*!40000 ALTER TABLE `v_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `v_user_role`
--

DROP TABLE IF EXISTS `v_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `v_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `v_user_role_user_id_role_id_index` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `v_user_role`
--

LOCK TABLES `v_user_role` WRITE;
/*!40000 ALTER TABLE `v_user_role` DISABLE KEYS */;
INSERT INTO `v_user_role` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `v_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-23 11:34:36
