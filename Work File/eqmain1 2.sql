-- MySQL dump 10.13  Distrib 5.5.45, for Linux (x86_64)
--
-- Host: localhost    Database: eqmain1
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
-- Table structure for table `Tunnel_Ventilation_Damper`
--

DROP TABLE IF EXISTS `Tunnel_Ventilation_Damper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tunnel_Ventilation_Damper` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `AssetCode` varchar(100) NOT NULL,
  `Clean_Blades` tinyint(1) DEFAULT '0',
  `Clean_Blades_d` varchar(5) NOT NULL,
  `Check_Linkages` tinyint(1) DEFAULT '0',
  `Check_Linkages_d` varchar(5) NOT NULL,
  `Manual_closeopen` tinyint(1) DEFAULT '0',
  `Manual_closeopen_d` varchar(5) NOT NULL,
  `Frame_Tightness` tinyint(1) DEFAULT '0',
  `Frame_Tightness_d` varchar(5) NOT NULL,
  `Actuator_Wiring` tinyint(1) NOT NULL DEFAULT '0',
  `Actuator_Wirig_d` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tunnel_Ventilation_Damper`
--

LOCK TABLES `Tunnel_Ventilation_Damper` WRITE;
/*!40000 ALTER TABLE `Tunnel_Ventilation_Damper` DISABLE KEYS */;
INSERT INTO `Tunnel_Ventilation_Damper` (`id`, `AssetCode`, `Clean_Blades`, `Clean_Blades_d`, `Check_Linkages`, `Check_Linkages_d`, `Manual_closeopen`, `Manual_closeopen_d`, `Frame_Tightness`, `Frame_Tightness_d`, `Actuator_Wiring`, `Actuator_Wirig_d`) VALUES (1,'C2/STI/LHS/TVD/01/TX/Sept 2015',0,'91',0,'91',0,'91',0,'364',0,'182');
/*!40000 ALTER TABLE `Tunnel_Ventilation_Damper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tunnel_Ventilation_Fan`
--

DROP TABLE IF EXISTS `Tunnel_Ventilation_Fan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tunnel_Ventilation_Fan` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `AssetCode` varchar(100) NOT NULL,
  `Check_Impeller` tinyint(1) NOT NULL DEFAULT '0',
  `Check_Impeller_d` varchar(5) NOT NULL,
  `Lubricate_Motor` tinyint(1) NOT NULL DEFAULT '0',
  `Lubricate_Motor_d` varchar(5) NOT NULL,
  `Secure_Wiring` tinyint(1) NOT NULL DEFAULT '0',
  `Secure_Wiring_d` varchar(5) NOT NULL,
  `Clean_Terminal_Box` tinyint(1) NOT NULL DEFAULT '0',
  `Clean_Terminal_Box_d` varchar(5) NOT NULL,
  `Check_Insulation` tinyint(1) NOT NULL DEFAULT '0',
  `Check_Insulation_d` varchar(5) NOT NULL,
  `Check_Blade_Tips` tinyint(1) NOT NULL DEFAULT '0',
  `Check_Blade_Tips_d` varchar(5) NOT NULL,
  `Tighten_Flexi_Nuts` tinyint(1) NOT NULL DEFAULT '0',
  `Tighten_Flexi_Nuts_d` varchar(5) NOT NULL,
  `General_Cleaning` tinyint(1) NOT NULL DEFAULT '0',
  `General_Cleaning_d` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tunnel_Ventilation_Fan`
--

LOCK TABLES `Tunnel_Ventilation_Fan` WRITE;
/*!40000 ALTER TABLE `Tunnel_Ventilation_Fan` DISABLE KEYS */;
INSERT INTO `Tunnel_Ventilation_Fan` (`id`, `AssetCode`, `Check_Impeller`, `Check_Impeller_d`, `Lubricate_Motor`, `Lubricate_Motor_d`, `Secure_Wiring`, `Secure_Wiring_d`, `Clean_Terminal_Box`, `Clean_Terminal_Box_d`, `Check_Insulation`, `Check_Insulation_d`, `Check_Blade_Tips`, `Check_Blade_Tips_d`, `Tighten_Flexi_Nuts`, `Tighten_Flexi_Nuts_d`, `General_Cleaning`, `General_Cleaning_d`) VALUES (1,'C2/STI/LHS/TVF/01/FW/Sept 2015',0,'91',0,'364',0,'182',0,'182',0,'182',0,'91',0,'182',0,'91');
/*!40000 ALTER TABLE `Tunnel_Ventilation_Fan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'eqmain1'
--

--
-- Dumping routines for database 'eqmain1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-27  4:58:28
