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
-- Table structure for table `t_test_case_version`
--

DROP TABLE IF EXISTS `t_test_case_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_test_case_version` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `test_case_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnp1d3s0bdyum11v0n4di6a9w8` (`test_case_id`),
  CONSTRAINT `FKnp1d3s0bdyum11v0n4di6a9w8` FOREIGN KEY (`test_case_id`) REFERENCES `t_test_case` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_test_case_version`
--

LOCK TABLES `t_test_case_version` WRITE;
/*!40000 ALTER TABLE `t_test_case_version` DISABLE KEYS */;
INSERT INTO `t_test_case_version` VALUES (1,'AddContentTest 2.3.4',1),(2,'ChangeUrlTest 2.3.4',2),(3,'ChangePositionTest 2.3.4',3),(4,'ChangeParentTest 2.3.4',4),(5,'AddAsDraftTest 2.3.4',5),(6,'SetStickyPostTest 2.3.4',6),(7,'DeletePostTest 2.3.4',7),(8,'AddUserTest 2.3.4',8),(9,'ChangePasswordTest 2.3.4',9),(10,'AddSocialsTest 2.3.4',10),(11,'ChangeHomeTest 2.3.4',11),(12,'OrderByPositionTest 2.3.4',12),(13,'AddSiteSocialTest 2.3.4',13),(14,'SetFooterTest 2.3.4',14),(15,'ChangeLanguageTest 2.3.4',15),(16,'DeleteUserTest 2.3.4',16),(17,'ChangePostDateTest 2.3.4',17),(18,'BadLoginFailsTest 2.3.4',18),(19,'AddCategoryTest 2.3.4',19),(20,'AssignCategoryTest 2.3.4',20),(21,'SeeCategoryArticlesTest 2.3.4',21),(22,'DeleteCategoryTest 2.3.4',22),(23,'EmptyLoginFailsTest 2.3.4',23),(25,'AdminLoginTest 3.10.11',25),(26,'AddUser 3.10.11',28),(27,'LoginAsNewUser 3.10.11',29),(28,'BadSiteAdminLogin 3.10.11',30),(29,'EmptySiteAdminLogin 3.10.11',31),(30,'AddNewArticle 3.10.11',32),(31,'AddEmptyArticle 3.10.11',33),(32,'EditArticle 3.10.11',34),(33,'DeleteArticle 3.10.11',35),(34,'AddCategory 3.10.11',36),(35,'AddEmptyCategory 3.10.11',37),(36,'AssignCategory 3.10.11',38),(37,'ChangePassword 3.10.11',39),(38,'ChangePasswordDontMatch 3.10.11',40),(39,'AddMenuItem 3.10.11',41),(40,'AddMenuItem_EmptyTitle 3.10.11',42),(41,'AddMenuItem_MenuNotSelected 3.10.11',43),(42,'AddMenuItem_EmptyMenuType 3.10.11',44),(43,'ArchiveArticle 3.10.11',45),(44,'SeeArchivedArticle 3.10.11',46),(45,'AddGroup 3.10.11',47),(46,'AddEmptyGroup 3.10.11',48),(47,'AssignUserToGroup 3.10.11',49),(48,'AddField 3.10.11',50),(49,'AddEmptyField 3.10.11',51),(51,'AddFieldGroup 3.10.11',52),(52,'AddEmptyFieldGroup 3.10.11',53),(53,'AssignFieldToGroup 3.10.11',54),(54,'DeleteUserGroup 3.10.11',55),(55,'DeleteField 3.10.11',56),(56,'DeleteFieldGroup 3.10.11',57),(57,'DeleteUser 3.10.11',58),(58,'DeleteCategory 3.10.11',59),(59,'AddUser 1.2.0',60),(60,'AddExistingUserFails 1.2.0',61),(61,'AddEmptyUserFails 1.2.0',62),(62,'AddProject 1.2.0',63),(63,'AddExistingProjectFails 1.2.0',64),(64,'AddCategory 1.2.0',65),(65,'AddExistingCategoryFails 1.2.0',66),(66,'AddIssue 1.2.0',67),(67,'AssignIssue 1.2.0',68),(68,'UpdateIssuePriority 1.2.0',69),(69,'UpdateIssueSeverity 1.2.0',70),(70,'UpdateIssueStatusAcknowledged 1.2.0',71),(71,'UpdateIssueStatusAssigned 1.2.0',72),(72,'UpdateIssueStatusConfirmed 1.2.0',73),(73,'UpdateIssueStatusFeedback 1.2.0',74),(74,'UpdateIssueStatusNew 1.2.0',75),(75,'UpdateIssueStatusResolved 1.2.0',76),(76,'UpdateIssueSummary 1.2.0',77),(77,'UpdateProjectStatus 1.2.0',78),(78,'UpdateProjectDescription  1.2.0',79),(79,'UpdateProjectViewStatus 1.2.0',80),(80,'UpdateCategory 1.2.0',81),(81,'UpdateCategoryEmpty 1.2.0',82),(82,'UpdateUser 1.2.0',83),(83,'UpdateUserEmpty 1.2.0',84),(84,'DeleteIssue 1.2.0',85),(85,'DeleteCategory 1.2.0',86),(86,'DeleteProject 1.2.0',87),(89,'DeleteUser 1.2.0',88),(90,'BadLogin 1.2.0',89),(91,'AddMultipleUsers 1.2.0',90),(92,'DeleteMultipleUsers 1.2.0',91),(93,'AddMultipleSubprojects 1.2.0',92),(94,'UnlinkMultipleSubprojects 1.2.0',93),(95,'LinkMultipleSubprojects 1.2.0',94),(96,'DeleteMultipleProjects 1.2.0',95),(97,'Logout 1.2.0',96),(98,'AddNewProduct 1.6.1.23',97),(99,'AddEmptyProduct 1.6.1.23',98),(100,'EditProduct 1.6.1.23',99),(101,'AddNewState 1.6.1.23',100),(102,'AddEmptyState 1.6.1.23',101),(104,' AddNewProductWithTax 1.6.1.23',102),(105,'AddNewProductWithTax10 1.6.1.23',103),(106,'AddNewFeatures 1.6.1.23',104),(107,'AddEmptyFeature 1.6.1.23',105),(108,'AddNewAttribute 1.6.1.23',106),(109,'AddEmptyAttribute 1.6.1.23',107),(110,'AddNewAddress 1.6.1.23',108),(111,'AddEmptyAddress 1.6.1.23',109),(112,'AddNewCategory 1.6.1.23',110),(113,'AddEmptyCategory 1.6.1.23',111),(114,'EditCategory 1.6.1.23',112),(115,'AddNewManufacturer 1.6.1.23',113),(116,'AddEmptyManufacturer 1.6.1.23',114),(117,'EditManufacturer 1.6.1.23',115),(118,'AddNewSupplier 1.6.1.23',116),(119,'AddEmptySupplier 1.6.1.23',117),(120,'RemoveSupplier 1.6.1.23',118),(121,'RegisterEmployee 1.6.1.23',119),(122,'RegisterEmployee_NoName 1.6.1.23',120),(123,'RegisterEmployee_NoEmail 1.6.1.23',121),(124,'RegisterEmployee_NoPassword 1.6.1.23',122),(125,'RegisterEmployee_NoPermission 1.6.1.23',123),(126,'OutOfStockValidity 1.6.1.23',124),(127,'AddTag 1.6.1.23',125),(128,'AddEmptyTag 1.6.1.23',126),(129,'AddLanguageIsoCodeValidity 1.6.1.23',127),(130,'AddCurrency 1.6.1.23',128),(131,'AddEmptyCurrency 1.6.1.23',129),(132,'EditAddress 1.6.1.23',130),(133,'EditEmployee 1.6.1.23',131),(134,'AddNewTask 1.2.15',132),(135,'AddEmptyTask 1.2.15',133),(136,'SearchInProject 1.2.15',134),(137,'ChangeTask 1.2.15',135),(138,'ClosedTasks 1.2.15',136),(139,'AddNewCurrencyRate 1.2.15',137),(140,'AddDiscountCodeAmountTest 1.19',138),(141,'AddDiscountCodePercentTest 1.19',139),(142,'DeleteDiscountCodeAmountTest 1.19',140),(143,'DeleteDiscountCodePercentTest 1.19',141),(144,'CloseInitialEditorPopupTest 1.40.0',142),(145,'CreatePageTest 1.40.0',143),(146,'CreateAndLinkPageTest 1.40.0',144),(147,'SearchPageTest 1.40.0',145),(148,'EditPageTest 1.40.0',146),(149,'CreatePageFromSourceTest 1.40.0',147),(150,'ApplyTemplateTest 1.40.0',148),(151,'AddCategoryTest 1.40.0',149),(152,'ProtectPageTest 1.40.0',150),(153,'AdminEditProtectedPage_GetsWarningTest 1.40.0',151),(154,'EditProtectedPage_ForbiddenTest 1.40.0',152),(155,'PromoteToAdminTest 1.40.0',153),(156,'EditProtectedPage_NewAdmin_SuccessTest 1.40.0',154),(157,'RevertLastCommitTest 1.40.0',155),(158,'CreateUserPageTest 1.40.0',156),(159,'BlockUserTest 1.40.0',157),(160,'BlockedUser_CantEditTest 1.40.0',158),(161,'DeletePageTest 1.40.0',159),(162,'CreateRedirectTest 1.40.0',160),(163,'FollowRedirectTest 1.40.0',161),(164,'ChangePassword_TooShortTest 1.40.0',162),(165,'CreateBlankPage_GetsWarningTest 1.40.0',163),(166,'test case version',1),(167,'test case version',1);
/*!40000 ALTER TABLE `t_test_case_version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-29 11:19:20
