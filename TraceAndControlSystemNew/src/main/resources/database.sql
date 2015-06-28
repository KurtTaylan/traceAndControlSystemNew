CREATE DATABASE  IF NOT EXISTS `demotest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `demotest`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: demotest
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `work_date` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Taylan Kurt','Lider','30/12/2015'),(2,'Ugurcan Lacin','Görevli','30/12/2015'),(3,'Ender Arslan','Lider','30/12/2015'),(4,'Baran Ak','Görevli','30/12/2015'),(5,'Okan akca','Görevli','30/12/2015'),(6,'Hatice Yasar','Lider','03/02/2010'),(7,'Okan Kaynarca','Görevli','03/02/2010'),(8,'Fuzili Gürbüz','Görevli','03/02/2010'),(9,'Ali Pekmez','Görevli','03/02/2010'),(10,'Ali Pekmez','Görevli','03/02/2010'),(11,'Ali Pekmez','Görevli','03/02/2010'),(12,'Ali Pekmez','Görevli','03/02/2010'),(13,'Ali Pekmez','Görevli','03/02/2010'),(14,'Ali Pekmez','Görevli','03/02/2010'),(15,'Ahmet Kale','Lider','03/02/1997'),(16,'Ahmet Kale','Görevli','03/02/2010'),(17,'Ahmet Kale','Görevli','03/02/2010'),(18,'Ali Pekmez','Görevli','03/02/2010'),(19,'Ahmet Kale','Görevli','03/02/2010'),(20,'Ali Pekmez','Görevli','03/02/2010'),(21,'Ahmet Kale','Görevli','03/02/2010'),(22,'Ali Pekmez','Görevli','03/02/2010'),(23,'Ahmet Kale','Görevli','03/02/2010'),(24,'Ahmet Kale','Görevli','03/02/2010'),(25,'Ali Pekmez','Görevli','03/02/2010'),(26,'Ali Pekmez','Görevli','03/02/2010'),(27,'Ahmet Kale','Lider','03/02/1997'),(28,'Ahmet Kale','Görevli','03/02/2010'),(29,'Ahmet Kale','Görevli','03/02/2010'),(30,'Ali Pekmez','Görevli','03/02/2010'),(31,'Ahmet Kale','Görevli','03/02/2010'),(32,'Ali Pekmez','Görevli','03/02/2010'),(33,'Ahmet Kale','Görevli','03/02/2010'),(34,'Ali Pekmez','Görevli','03/02/2010'),(35,'Ahmet Kale','Görevli','03/02/2010'),(36,'Ahmet Kale','Görevli','03/02/2010'),(37,'Ali Pekmez','Görevli','03/02/2010'),(38,'Ali Pekmez','Görevli','03/02/2010'),(39,'Ahmet Kale','Lider','03/02/1997'),(40,'Ahmet Kale','Görevli','03/02/2010'),(41,'Ahmet Kale','Görevli','03/02/2010'),(42,'Ali Pekmez','Görevli','03/02/2010'),(43,'Ahmet Kale','Görevli','03/02/2010'),(44,'Ali Pekmez','Görevli','03/02/2010'),(45,'Ahmet Kale','Görevli','03/02/2010'),(46,'Ali Pekmez','Görevli','03/02/2010'),(47,'Ahmet Kale','Görevli','03/02/2010'),(48,'Ahmet Kale','Görevli','03/02/2010'),(49,'Ali Pekmez','Görevli','03/02/2010'),(50,'Ali Pekmez','Görevli','03/02/2010');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe`
--

DROP TABLE IF EXISTS `tribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `numberOfPeople` int(11) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe`
--

LOCK TABLES `tribe` WRITE;
/*!40000 ALTER TABLE `tribe` DISABLE KEYS */;
INSERT INTO `tribe` VALUES (1,'Ekip1',2,'730.0,373.0'),(2,'Ekip2',2,'565.0,132.0'),(3,'Ekip3',1,'652.0,257.0'),(4,'Ekip4',3,'857.0,189.0'),(5,'Ekip5',6,'857.0,189.0'),(6,'Ekip6',12,'653.0,295.0'),(7,'Ekip7',24,'495.0,244.0');
/*!40000 ALTER TABLE `tribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_person`
--

DROP TABLE IF EXISTS `tribe_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_person` (
  `person_id` int(6) NOT NULL,
  `tribe_id` int(6) NOT NULL,
  KEY `person` (`person_id`),
  KEY `tribe` (`tribe_id`),
  CONSTRAINT `tribe` FOREIGN KEY (`tribe_id`) REFERENCES `tribe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_person`
--

LOCK TABLES `tribe_person` WRITE;
/*!40000 ALTER TABLE `tribe_person` DISABLE KEYS */;
INSERT INTO `tribe_person` VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,4),(7,4),(8,4),(9,5),(10,5),(11,5),(12,5),(13,5),(14,5),(15,6),(16,6),(17,6),(18,6),(19,6),(20,6),(21,6),(22,6),(23,6),(24,6),(25,6),(26,6),(27,7),(28,7),(29,7),(30,7),(31,7),(32,7),(33,7),(34,7),(35,7),(36,7),(37,7),(38,7),(39,7),(40,7),(41,7),(42,7),(43,7),(44,7),(45,7),(46,7),(47,7),(48,7),(49,7),(50,7);
/*!40000 ALTER TABLE `tribe_person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-24 22:48:03
