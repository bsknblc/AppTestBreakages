-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thesis
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application_language`
--

DROP TABLE IF EXISTS `application_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_language` (
  `application_id` int NOT NULL,
  `language_id` int NOT NULL,
  KEY `FK2t9msgvgdq0oucd858ejgiks` (`language_id`),
  KEY `FKiytavek8h1bseq8jf94e4e7pg` (`application_id`),
  CONSTRAINT `FK2t9msgvgdq0oucd858ejgiks` FOREIGN KEY (`language_id`) REFERENCES `t_language` (`id`),
  CONSTRAINT `FKiytavek8h1bseq8jf94e4e7pg` FOREIGN KEY (`application_id`) REFERENCES `t_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_language`
--

LOCK TABLES `application_language` WRITE;
/*!40000 ALTER TABLE `application_language` DISABLE KEYS */;
INSERT INTO `application_language` VALUES (1,1),(1,3),(1,4),(3,1),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(4,4),(4,1),(4,3),(4,11),(4,9),(4,10),(4,8),(5,4),(5,14),(5,12),(5,13),(5,6),(5,3),(5,8),(6,4),(6,3),(6,8),(7,3),(7,15),(7,1),(7,17),(7,8),(8,4),(8,3),(8,17),(8,1),(8,7),(8,6);
/*!40000 ALTER TABLE `application_language` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-03 12:03:19
