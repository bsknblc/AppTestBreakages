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
-- Table structure for table `t_test_case`
--

DROP TABLE IF EXISTS `t_test_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_test_case` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `test_suite_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38j3max3f8e6oi8358q52mdpf` (`test_suite_id`),
  CONSTRAINT `FK38j3max3f8e6oi8358q52mdpf` FOREIGN KEY (`test_suite_id`) REFERENCES `t_test_suite` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_test_case`
--

LOCK TABLES `t_test_case` WRITE;
/*!40000 ALTER TABLE `t_test_case` DISABLE KEYS */;
INSERT INTO `t_test_case` VALUES (1,'-','AddContentTest',1),(2,'-','ChangeUrlTest',1),(3,'-','ChangePositionTest',1),(4,'-','ChangeParentTest',1),(5,'-','AddAsDraftTest',1),(6,'-','SetStickyPostTest',1),(7,'-','DeletePostTest',1),(8,'-','AddUserTest',1),(9,'-','ChangePasswordTest',1),(10,'-','AddSocialsTest',1),(11,'-','ChangeHomeTest',1),(12,'-','OrderByPositionTest',1),(13,'-','AddSiteSocialTest',1),(14,'-','SetFooterTest',1),(15,'-','ChangeLanguageTest',1),(16,'-','DeleteUserTest',1),(17,'-','ChangePostDateTest',1),(18,'-','BadLoginFailsTest',1),(19,'-','AddCategoryTest',1),(20,'-','AssignCategoryTest',1),(21,'-','SeeCategoryArticlesTest',1),(22,'-','DeleteCategoryTest',1),(23,'-','EmptyLoginFailsTest',1),(25,'-','AdminLoginTest',3),(26,'-','BadLogin',3),(27,'-','EmptyLogin',3),(28,'-','AddUser',3),(29,'-','LoginAsNewUser',3),(30,'-','BadSiteAdminLogin',3),(31,'-','EmptySiteAdminLogin',3),(32,'-','AddNewArticle',3),(33,'-','AddEmptyArticle',3),(34,'-','EditArticle',3),(35,'-','DeleteArticle',3),(36,'-','AddCategory',3),(37,'-','AddEmptyCategory',3),(38,'-','AssignCategory',3),(39,'-','ChangePassword',3),(40,'-','ChangePasswordDontMatch',3),(41,'-','AddMenuItem',3),(42,'-','AddMenuItem_EmptyTitle',3),(43,'-','AddMenuItem_MenuNotSelected',3),(44,'-','AddMenuItem_EmptyMenuType',3),(45,'-','ArchiveArticle',3),(46,'-','SeeArchivedArticle',3),(47,'-','AddGroup',3),(48,'-','AddEmptyGroup',3),(49,'-','AssignUserToGroup',3),(50,'-','AddField',3),(51,'-','AddEmptyField',3),(52,'-','AddFieldGroup',3),(53,'-','AddEmptyFieldGroup',3),(54,'-','AssignFieldToGroup',3),(55,'-','DeleteUserGroup',3),(56,'-','DeleteField',3),(57,'-','DeleteFieldGroup',3),(58,'-','DeleteUser',3),(59,'-','DeleteCategory',3),(60,'-','AddUser',4),(61,'-','AddExistingUserFails',4),(62,'-','AddEmptyUserFails',4),(63,'-','AddProject',4),(64,'-','AddExistingProjectFails',4),(65,'-','AddCategory',4),(66,'-','AddExistingCategoryFails',4),(67,'-','AddIssue',4),(68,'-','AssignIssue',4),(69,'-','UpdateIssuePriority',4),(70,'-','UpdateIssueSeverity',4),(71,'-','UpdateIssueStatusAcknowledged',4),(72,'-','UpdateIssueStatusAssigned',4),(73,'-','UpdateIssueStatusConfirmed',4),(74,'-','UpdateIssueStatusFeedback',4),(75,'-','UpdateIssueStatusNew',4),(76,'-','UpdateIssueStatusResolved',4),(77,'-','UpdateIssueSummary',4),(78,'-','UpdateProjectStatus',4),(79,'-','UpdateProjectDescription',4),(80,'-','UpdateProjectViewStatus',4),(81,'-','UpdateCategory',4),(82,'-','UpdateCategoryEmpty',4),(83,'-','UpdateUser',4),(84,'-','UpdateUserEmpty',4),(85,'-','DeleteIssue',4),(86,'-','DeleteCategory',4),(87,'-','DeleteProject',4),(88,'-','DeleteUser',4),(89,'-','BadLogin',4),(90,'-','AddMultipleUsers',4),(91,'-','DeleteMultipleUsers',4),(92,'-','AddMultipleSubprojects',4),(93,'-','UnlinkMultipleSubprojects',4),(94,'-','LinkMultipleSubprojects',4),(95,'-','DeleteMultipleProjects',4),(96,'-','Logout',4),(97,'-','AddNewProduct',5),(98,'-','AddEmptyProduct',5),(99,'-','EditProduct',5),(100,'-','AddNewState',5),(101,'-','AddEmptyState',5),(102,'-','AddNewProductWithTax',5),(103,'-','AddNewProductWithTax10',5),(104,'-','AddNewFeatures',5),(105,'-','AddEmptyFeature',5),(106,'-','AddNewAttribute',5),(107,'-','AddEmptyAttribute',5),(108,'-','AddNewAddress',5),(109,'-','AddEmptyAddress',5),(110,'-','AddNewCategory',5),(111,'-','AddEmptyCategory',5),(112,'-','EditCategory',5),(113,'-','AddNewManufacturer',5),(114,'-','AddEmptyManufacturer',5),(115,'-','EditManufacturer',5),(116,'-','AddNewSupplier',5),(117,'-','AddEmptySupplier',5),(118,'-','RemoveSupplier',5),(119,'-','RegisterEmployee',5),(120,'-','RegisterEmployee_NoName',5),(121,'-','RegisterEmployee_NoEmail',5),(122,'-','RegisterEmployee_NoPassword',5),(123,'-','RegisterEmployee_NoPermission',5),(124,'-','OutOfStockValidity',5),(125,'-','AddTag',5),(126,'-','AddEmptyTag',5),(127,'-','AddLanguageIsoCodeValidity',5),(128,'-','AddCurrency',5),(129,'-','AddEmptyCurrency',5),(130,'-','EditAddress',5),(131,'-','EditEmployee',5),(132,'-','AddNewTask',6),(133,'-','AddEmptyTask',6),(134,'-','SearchInProject',6),(135,'-','ChangeTask',6),(136,'-','ClosedTasks',6),(137,'-','AddNewCurrencyRate',6),(138,'-','AddDiscountCodeAmountTest',7),(139,'-','AddDiscountCodePercentTest',7),(140,'-','DeleteDiscountCodeAmountTest',7),(141,'-','DeleteDiscountCodePercentTest',7),(142,'-','CloseInitialEditorPopupTest',8),(143,'-','CreatePageTest',8),(144,'-','CreateAndLinkPageTest',8),(145,'-','SearchPageTest',8),(146,'-','EditPageTest',8),(147,'-','CreatePageFromSourceTest',8),(148,'-','ApplyTemplateTest',8),(149,'-','AddCategoryTest',8),(150,'-','ProtectPageTest',8),(151,'-','AdminEditProtectedPage_GetsWarningTest',8),(152,'-','EditProtectedPage_ForbiddenTest',8),(153,'-','PromoteToAdminTest',8),(154,'-','EditProtectedPage_NewAdmin_SuccessTest',8),(155,'-','RevertLastCommitTest',8),(156,'-','CreateUserPageTest',8),(157,'-','BlockUserTest',8),(158,'-','BlockedUser_CantEditTest',8),(159,'-','DeletePageTest',8),(160,'-','CreateRedirectTest',8),(161,'-','FollowRedirectTest',8),(162,'-','ChangePassword_TooShortTest',8),(163,'-','CreateBlankPage_GetsWarningTest',8);
/*!40000 ALTER TABLE `t_test_case` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-03 12:03:20
