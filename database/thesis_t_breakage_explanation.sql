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
-- Table structure for table `t_breakage_explanation`
--

DROP TABLE IF EXISTS `t_breakage_explanation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_breakage_explanation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `explanation` varchar(255) NOT NULL,
  `type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjme20ripsxvwi5hc9abk71wiy` (`type_id`),
  CONSTRAINT `FKjme20ripsxvwi5hc9abk71wiy` FOREIGN KEY (`type_id`) REFERENCES `t_cause_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_breakage_explanation`
--

LOCK TABLES `t_breakage_explanation` WRITE;
/*!40000 ALTER TABLE `t_breakage_explanation` DISABLE KEYS */;
INSERT INTO `t_breakage_explanation` VALUES (1,'Element Location Changed',1),(2,'Run the previous test succesfully',2),(3,'New tab added.',1),(4,'items are not listed before typing, thus selecting them them them are impossible',1),(5,'Native <select> is hidden and empty because Select2 hijacks it.',1),(6,'Wait Needed For Loading',1),(7,'Alert Removed',1),(8,'Alert Added',1),(9,'Hierarchy-based Target Not Found',2),(10,'Index-based Target Not Found',2),(11,'Element Text Not Found',2),(12,'Invalid Text Field Value Input',2),(13,'Missing Value/Action',2),(14,'Value Deleted from Dropdown List',2),(15,'Unexpected Assertion Value',2),(16,'Page Reload Needed',2),(18,'Page Reload No Longer Needed',2),(19,'Id Attribute Not Found',2),(20,'Href Attribute Not Found',2),(21,'Alt Attribute Not Found',2),(22,'Name Attribute Not Found',2),(23,'Type Attribute Not Found',2),(24,'Value Attribute Not Found',2),(25,'Class Attribute Not Found',2),(26,'OnClick Attribute Not Found',2),(27,'Popup Box Removed',2),(28,'dropdown element is changed to select2',1),(29,'Dropdown logic changed',1),(30,'Action No Longer Needed',2),(31,'CSS Selector Attribute Not Found',2),(32,'Popup Interaction Changed',1),(33,'ClassName Attribute Not Found',2),(34,'Value retrieving function isn\'t working',1),(35,'Button Added',1),(36,'Selenium\'s By.linkText will not match buttons',1),(37,'Button Moved Inside a Dropdown List',1),(39,'Needs To Navigate To a Different Page',1),(40,'Needs Wait After a Save',1),(41,'Field is No Longer Necessary',1),(42,'Button Moved Inside Another Tab',1),(43,'Button Removed',1),(44,'Field Removed',1),(50,'Semantic Change In Interaction',2),(51,'item is hidden and only becomes visible after clicking, not just a hover',1),(52,'default item is changed needed to select item first',1),(53,'Hardcoded Value Outdated',2),(54,'hardcoded time value outdated',1),(55,'Ovelapping element prevents click',2),(56,'Button place is changed, now to buttons are overlapping',1),(57,'Password requirements changed',1),(59,'Button is no longer clickable',1),(60,'Message Box Added',1);
/*!40000 ALTER TABLE `t_breakage_explanation` ENABLE KEYS */;
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
