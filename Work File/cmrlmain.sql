-- MySQL dump 10.13  Distrib 5.5.45, for Linux (x86_64)
--
-- Host: localhost    Database: cmrlmain
-- ------------------------------------------------------
-- Server version	5.5.45-cll-lve

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
-- Table structure for table `AssetCodes`
--

DROP TABLE IF EXISTS `AssetCodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetCodes` (
  `ID` int(100) NOT NULL AUTO_INCREMENT,
  `assetcode` varchar(100) NOT NULL,
  `Ecode` int(10) NOT NULL,
  `Lcode` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `assetcode` (`assetcode`),
  KEY `Ecode` (`Ecode`),
  KEY `Lcode` (`Lcode`),
  CONSTRAINT `Lcode in AssetCodes` FOREIGN KEY (`Lcode`) REFERENCES `Location` (`Lcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Ecode in AssetCodes` FOREIGN KEY (`Ecode`) REFERENCES `Equipment` (`Ecode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetCodes`
--

LOCK TABLES `AssetCodes` WRITE;
/*!40000 ALTER TABLE `AssetCodes` DISABLE KEYS */;
INSERT INTO `AssetCodes` (`ID`, `assetcode`, `Ecode`, `Lcode`) VALUES (1,'C2/STI/LHS/TVF/01/FW/Sept 2015',1,1),(2,'C2/STI/LHS/TVD/01/TX/Sept 2015',2,1);
/*!40000 ALTER TABLE `AssetCodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipment`
--

DROP TABLE IF EXISTS `Equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipment` (
  `Ecode` int(10) NOT NULL,
  `Name` varchar(80) NOT NULL,
  PRIMARY KEY (`Ecode`),
  UNIQUE KEY `Ecode_2` (`Ecode`),
  KEY `Ecode` (`Ecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipment`
--

LOCK TABLES `Equipment` WRITE;
/*!40000 ALTER TABLE `Equipment` DISABLE KEYS */;
INSERT INTO `Equipment` (`Ecode`, `Name`) VALUES (1,'Tunnel_Ventilation_Fan'),(2,'Tunnel_Ventilation_Damper');
/*!40000 ALTER TABLE `Equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `Lcode` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  KEY `Lcode` (`Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` (`Lcode`, `Name`) VALUES (1,'Thirumangalam');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Maintainence`
--

DROP TABLE IF EXISTS `Maintainence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Maintainence` (
  `mnid` int(10) NOT NULL,
  `Ecode` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `timer` int(10) DEFAULT NULL,
  PRIMARY KEY (`mnid`),
  UNIQUE KEY `mnid` (`mnid`),
  KEY `Ecode` (`Ecode`),
  KEY `Ecode_2` (`Ecode`),
  CONSTRAINT `Ecode in Maintainence` FOREIGN KEY (`Ecode`) REFERENCES `Equipment` (`Ecode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Maintainence`
--

LOCK TABLES `Maintainence` WRITE;
/*!40000 ALTER TABLE `Maintainence` DISABLE KEYS */;
INSERT INTO `Maintainence` (`mnid`, `Ecode`, `Name`, `timer`) VALUES (1,1,'Check_Impeller',91),(2,1,'Lubricate_Motor',364),(3,1,'Secure_Wiring',182),(4,1,'Clean_Terminal_Box',182),(5,1,'Check_Insulation',182),(6,1,'Check_Blade_Tips',91),(7,1,'Tighten_Flexi_Nuts',182),(8,1,'General_Cleaning',91),(9,2,'Clean_Blades',91),(10,2,'Check_Linkages',91),(11,2,'Manual_closeopen',91),(12,2,'Frame_Tightness',364),(13,2,'Actuator_Wiring',182);
/*!40000 ALTER TABLE `Maintainence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manu_det`
--

DROP TABLE IF EXISTS `Manu_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Manu_det` (
  `Mid` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  `ctno` bigint(20) NOT NULL,
  `ctname` varchar(100) NOT NULL,
  `ctno2` bigint(20) DEFAULT NULL,
  `ctname2` varchar(100) DEFAULT NULL,
  `Ecode` int(10) NOT NULL,
  `Pname` varchar(60) NOT NULL,
  PRIMARY KEY (`Mid`),
  KEY `Ecode` (`Ecode`),
  CONSTRAINT `Ecode on Manu_det` FOREIGN KEY (`Ecode`) REFERENCES `Equipment` (`Ecode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manu_det`
--

LOCK TABLES `Manu_det` WRITE;
/*!40000 ALTER TABLE `Manu_det` DISABLE KEYS */;
INSERT INTO `Manu_det` (`Mid`, `Name`, `email`, `ctno`, `ctname`, `ctno2`, `ctname2`, `Ecode`, `Pname`) VALUES (1,'CMRLGOV','cmrl@cmrlgov.in',8144010101,'CMRLGOVERNER',8144080808,'CMRLGOVERNER2',1,'FAN_SIDE');
/*!40000 ALTER TABLE `Manu_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apiuser`
--

DROP TABLE IF EXISTS `apiuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apiuser` (
  `id` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `lastlogged` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apiuser`
--

LOCK TABLES `apiuser` WRITE;
/*!40000 ALTER TABLE `apiuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `apiuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `errorlog`
--

DROP TABLE IF EXISTS `errorlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `errorlog` (
  `id` int(10) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Message` varchar(300) NOT NULL,
  `assetcode` varchar(100) NOT NULL,
  `archive` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `assetcode` (`assetcode`),
  CONSTRAINT `AssetCode in ErrorLog` FOREIGN KEY (`assetcode`) REFERENCES `AssetCodes` (`assetcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `errorlog`
--

LOCK TABLES `errorlog` WRITE;
/*!40000 ALTER TABLE `errorlog` DISABLE KEYS */;
INSERT INTO `errorlog` (`id`, `Name`, `Message`, `assetcode`, `archive`) VALUES (1,'Header','Sample Error','C2/STI/LHS/TVF/01/FW/Sept 2015',0),(2,'Header','Sample Error','C2/STI/LHS/TVF/01/FW/Sept 2015',0);
/*!40000 ALTER TABLE `errorlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(10) NOT NULL,
  `assetcode` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `assetcode` (`assetcode`),
  CONSTRAINT `AssetCode in History` FOREIGN KEY (`assetcode`) REFERENCES `AssetCodes` (`assetcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` (`id`, `assetcode`, `username`, `created_at`, `updated_at`, `status`) VALUES (1,'C2/STI/LHS/TVF/01/FW/Sept 2015','Mohan Rajan','2015-11-18 09:26:56',NULL,'QR_FETCH'),(2,'C2/STI/LHS/TVF/01/FW/Sept 2015','Hello user','2015-11-18 09:26:56',NULL,'CHANGED'),(3,'C2/STI/LHS/TVD/01/TX/Sept 2015','Different User','2015-11-23 11:49:01',NULL,'QR_FETCH'),(4,'C2/STI/LHS/TVD/01/TX/Sept 2015','Diff User 2','2015-11-23 11:49:01',NULL,'CHANGED');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migrations`
--

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;
INSERT INTO `migrations` (`migration`, `batch`) VALUES ('2014_10_12_000000_create_users_table',1),('2014_10_12_100000_create_password_resets_table',1),('2015_11_18_114715_entrust_setup_tables',1);
/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_resets`
--

LOCK TABLES `password_resets` WRITE;
/*!40000 ALTER TABLE `password_resets` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_resets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_role`
--

DROP TABLE IF EXISTS `permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_role` (
  `permission_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `permission_role_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_role`
--

LOCK TABLES `permission_role` WRITE;
/*!40000 ALTER TABLE `permission_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permissions_name_unique` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_user` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_user_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` (`user_id`, `role_id`) VALUES (1,1);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_name_unique` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`, `display_name`, `description`, `created_at`, `updated_at`) VALUES (1,'admin','Administrator','He will be the Administrator','0000-00-00 00:00:00','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES (1,'Mohan Rajan','mohanrajan1996@gmail.com','$2y$10$c8TfHOmiYPu1O4dz9QEoOOFkV.g4wcGn9NI5zW2K1aciwAlF18opG','klp7rnPBPdrLNyT8cBLqJDL3z7mQTFNWOB6eGmffZ5F1oYq4mDSWebl9vmXZ','2015-11-18 19:22:54','2015-11-26 17:13:19');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cmrlmain'
--

--
-- Dumping routines for database 'cmrlmain'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-27  4:58:26
