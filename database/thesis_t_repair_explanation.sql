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
-- Table structure for table `t_repair_explanation`
--

DROP TABLE IF EXISTS `t_repair_explanation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_repair_explanation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `explanation` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_repair_explanation`
--

LOCK TABLES `t_repair_explanation` WRITE;
/*!40000 ALTER TABLE `t_repair_explanation` DISABLE KEYS */;
INSERT INTO `t_repair_explanation` VALUES (1,'Fixed the Locator'),(2,'Run previous tests'),(3,'Added/Navigated to New Tab'),(4,'Returned From Newly Added Tab'),(5,'Added Wait'),(6,'Send Keys Before Selecting'),(7,'Selector Strategy Adjustment'),(8,'Interaction with Alert Removed'),(9,'alert added'),(10,'Clicked a Button'),(11,'Value Retrieving Function Changed'),(12,'Assertion Value Changed'),(13,'Removed Action'),(14,'removed wait'),(15,'test exp'),(16,'Action Clicking Button Removed'),(17,'Interaction Fixed'),(18,'Choose an Item From the Dropdown List'),(19,'Time Value Changed'),(20,'Click Somewhere Else Before Clicking the Added/Navigated to New Button'),(21,'Assertion Behaviour Fixed'),(22,'Field With Default Value Removed'),(23,'Clicked on Message Box\'s Button'),(24,'locator fixed with id'),(25,'locator fixed with xpath'),(26,'locator fixed with name'),(27,'locator fixed with className'),(28,'locator fixed with linkText');
/*!40000 ALTER TABLE `t_repair_explanation` ENABLE KEYS */;
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
