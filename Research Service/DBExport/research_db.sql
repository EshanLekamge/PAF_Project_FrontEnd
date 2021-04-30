-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: research_db
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `researchprojects_tb`
--

DROP TABLE IF EXISTS `researchprojects_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `researchprojects_tb` (
  `researchID` int NOT NULL AUTO_INCREMENT,
  `researchName` varchar(45) NOT NULL,
  `researcherId` int NOT NULL,
  `researcherName` varchar(45) DEFAULT NULL,
  `researchCategory` varchar(45) DEFAULT NULL,
  `researchDescription` varchar(45) NOT NULL,
  `researchCost` float DEFAULT NULL,
  `researchDuration` varchar(10) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  PRIMARY KEY (`researchID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `researchprojects_tb`
--

LOCK TABLES `researchprojects_tb` WRITE;
/*!40000 ALTER TABLE `researchprojects_tb` DISABLE KEYS */;
INSERT INTO `researchprojects_tb` VALUES (8,'Solar Power Vehicle',105,'Nimal','Automobile','Fully Electric Solar Powered Bus',25000000,'730','2021-03-08 00:00:00'),(9,'Elephant Detection System',120,'Amal','Wildlife','Fully Automated Elephant',5000000,'182','2021-04-05 00:00:00'),(10,'Mobile Phone',115,'Amali','Device','Customizable Smart Phone',25000000,'730','2021-05-08 00:00:00'),(11,'Wide Monitor',125,'Sunil','Device','Eye Friendly Monitor',5200000,'364','2021-04-05 00:00:00'),(12,'Online Exams',110,'Sunil.T','Software','Encylopedia',5200000,'364','2021-04-05 00:00:00'),(13,'Keyboard',1,'Nirman','Device','Water-Proof Keyboard',15000000,'720','2021-04-05 00:00:00'),(14,'Wildlife App',1,'Naveen','Wildlife','Wild Animal Warning',5000000,'182','2021-04-05 00:00:00'),(15,'Workout App',1,'Aravinda','Software','Online guiding App',5000000,'128','2021-06-08 00:00:00'),(16,'Traffic App',1,'Nirman','Device','Traffic Control App',15000000,'720','2021-07-05 00:00:00');
/*!40000 ALTER TABLE `researchprojects_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 18:45:55
