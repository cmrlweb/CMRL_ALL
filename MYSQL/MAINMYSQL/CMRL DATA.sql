# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.20)
# Database: CMRL
# Generation Time: 2016-01-30 02:11:39 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table apiuser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `apiuser`;

CREATE TABLE `apiuser` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `lastlogged` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `apiuser` WRITE;
/*!40000 ALTER TABLE `apiuser` DISABLE KEYS */;

INSERT INTO `apiuser` (`id`, `name`, `email`, `password`, `created_at`, `updated_at`, `lastlogged`)
VALUES
	(1,'Maintainence1','mohan@gmail.com','password','2016-01-14 04:01:08','2016-01-14 04:01:08','');

/*!40000 ALTER TABLE `apiuser` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table AssetCodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AssetCodes`;

CREATE TABLE `AssetCodes` (
  `ID` int(100) NOT NULL AUTO_INCREMENT,
  `assetcode` varchar(100) NOT NULL,
  `Ecode` int(10) NOT NULL,
  `Lcode` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `assetcode` (`assetcode`),
  KEY `Ecode` (`Ecode`),
  KEY `Lcode` (`Lcode`),
  CONSTRAINT `Ecode in AssetCodes` FOREIGN KEY (`Ecode`) REFERENCES `Equipment` (`Ecode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Lcode in AssetCodes` FOREIGN KEY (`Lcode`) REFERENCES `Location` (`Lcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `AssetCodes` WRITE;
/*!40000 ALTER TABLE `AssetCodes` DISABLE KEYS */;

INSERT INTO `AssetCodes` (`ID`, `assetcode`, `Ecode`, `Lcode`)
VALUES
	(1,'C2/STI/LHS/TVF/01/FW/Sept 2015',1,1),
	(2,'C2/STI/LHS/TVD/01/TX/Sept 2015',2,1);

/*!40000 ALTER TABLE `AssetCodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Equipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Equipment`;

CREATE TABLE `Equipment` (
  `Ecode` int(10) NOT NULL,
  `Name` varchar(80) NOT NULL,
  PRIMARY KEY (`Ecode`),
  UNIQUE KEY `Ecode_2` (`Ecode`),
  KEY `Ecode` (`Ecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Equipment` WRITE;
/*!40000 ALTER TABLE `Equipment` DISABLE KEYS */;

INSERT INTO `Equipment` (`Ecode`, `Name`)
VALUES
	(1,'Tunnel_Ventilation_Fan'),
	(2,'Tunnel_Ventilation_Damper');

/*!40000 ALTER TABLE `Equipment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table errorlog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `errorlog`;

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

LOCK TABLES `errorlog` WRITE;
/*!40000 ALTER TABLE `errorlog` DISABLE KEYS */;

INSERT INTO `errorlog` (`id`, `Name`, `Message`, `assetcode`, `archive`)
VALUES
	(1,'Header','Sample Error','C2/STI/LHS/TVF/01/FW/Sept 2015',0),
	(2,'Header','Sample Error','C2/STI/LHS/TVF/01/FW/Sept 2015',0);

/*!40000 ALTER TABLE `errorlog` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `history`;

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

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;

INSERT INTO `history` (`id`, `assetcode`, `username`, `created_at`, `updated_at`, `status`)
VALUES
	(1,'C2/STI/LHS/TVF/01/FW/Sept 2015','Mohan Rajan','2015-11-18 14:56:56',NULL,'QR_FETCH'),
	(2,'C2/STI/LHS/TVF/01/FW/Sept 2015','Hello user','2015-11-18 14:56:56',NULL,'CHANGED'),
	(3,'C2/STI/LHS/TVD/01/TX/Sept 2015','Different User','2015-11-23 17:19:01',NULL,'QR_FETCH'),
	(4,'C2/STI/LHS/TVD/01/TX/Sept 2015','Diff User 2','2015-11-23 17:19:01',NULL,'CHANGED');

/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Location`;

CREATE TABLE `Location` (
  `Lcode` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  KEY `Lcode` (`Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;

INSERT INTO `Location` (`Lcode`, `Name`)
VALUES
	(1,'Thirumangalam');

/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Maintainence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Maintainence`;

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

LOCK TABLES `Maintainence` WRITE;
/*!40000 ALTER TABLE `Maintainence` DISABLE KEYS */;

INSERT INTO `Maintainence` (`mnid`, `Ecode`, `Name`, `timer`)
VALUES
	(1,1,'Check_Impeller',91),
	(2,1,'Lubricate_Motor',364),
	(3,1,'Secure_Wiring',182),
	(4,1,'Clean_Terminal_Box',182),
	(5,1,'Check_Insulation',182),
	(6,1,'Check_Blade_Tips',91),
	(7,1,'Tighten_Flexi_Nuts',182),
	(8,1,'General_Cleaning',91),
	(9,2,'Clean_Blades',91),
	(10,2,'Check_Linkages',91),
	(11,2,'Manual_closeopen',91),
	(12,2,'Frame_Tightness',364),
	(13,2,'Actuator_Wiring',182);

/*!40000 ALTER TABLE `Maintainence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Manu_det
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Manu_det`;

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

LOCK TABLES `Manu_det` WRITE;
/*!40000 ALTER TABLE `Manu_det` DISABLE KEYS */;

INSERT INTO `Manu_det` (`Mid`, `Name`, `email`, `ctno`, `ctname`, `ctno2`, `ctname2`, `Ecode`, `Pname`)
VALUES
	(1,'CMRLGOV','cmrl@cmrlgov.in',8144010101,'CMRLGOVERNER',8144080808,'CMRLGOVERNER2',1,'FAN_SIDE');

/*!40000 ALTER TABLE `Manu_det` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table migrations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `migrations`;

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;

INSERT INTO `migrations` (`migration`, `batch`)
VALUES
	('2014_10_12_000000_create_users_table',1),
	('2014_10_12_100000_create_password_resets_table',1),
	('2015_11_18_114715_entrust_setup_tables',1);

/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table password_resets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `password_resets`;

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table permission_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permission_role`;

CREATE TABLE `permission_role` (
  `permission_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `permission_role_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permissions`;

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



# Dump of table role_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role_user`;

CREATE TABLE `role_user` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_user_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;

INSERT INTO `role_user` (`user_id`, `role_id`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_name_unique` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;

INSERT INTO `roles` (`id`, `name`, `display_name`, `description`, `created_at`, `updated_at`)
VALUES
	(1,'admin','Administrator','He will be the Administrator','0000-00-00 00:00:00','0000-00-00 00:00:00');

/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Tunnel_Ventilation_Damper
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tunnel_Ventilation_Damper`;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `Tunnel_Ventilation_Damper` WRITE;
/*!40000 ALTER TABLE `Tunnel_Ventilation_Damper` DISABLE KEYS */;

INSERT INTO `Tunnel_Ventilation_Damper` (`id`, `AssetCode`, `Clean_Blades`, `Clean_Blades_d`, `Check_Linkages`, `Check_Linkages_d`, `Manual_closeopen`, `Manual_closeopen_d`, `Frame_Tightness`, `Frame_Tightness_d`, `Actuator_Wiring`, `Actuator_Wirig_d`)
VALUES
	(1,'C2/STI/LHS/TVD/01/TX/Sept 2015',0,'91',0,'91',0,'91',0,'364',0,'182');

/*!40000 ALTER TABLE `Tunnel_Ventilation_Damper` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Tunnel_Ventilation_Fan
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tunnel_Ventilation_Fan`;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Tunnel_Ventilation_Fan` WRITE;
/*!40000 ALTER TABLE `Tunnel_Ventilation_Fan` DISABLE KEYS */;

INSERT INTO `Tunnel_Ventilation_Fan` (`id`, `AssetCode`, `Check_Impeller`, `Check_Impeller_d`, `Lubricate_Motor`, `Lubricate_Motor_d`, `Secure_Wiring`, `Secure_Wiring_d`, `Clean_Terminal_Box`, `Clean_Terminal_Box_d`, `Check_Insulation`, `Check_Insulation_d`, `Check_Blade_Tips`, `Check_Blade_Tips_d`, `Tighten_Flexi_Nuts`, `Tighten_Flexi_Nuts_d`, `General_Cleaning`, `General_Cleaning_d`)
VALUES
	(1,'C2/STI/LHS/TVF/01/FW/Sept 2015',0,'91',0,'364',0,'182',0,'182',0,'182',0,'91',0,'182',0,'91');

/*!40000 ALTER TABLE `Tunnel_Ventilation_Fan` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`)
VALUES
	(1,'Mohan Rajan','mohanrajan1996@gmail.com','$2y$10$c8TfHOmiYPu1O4dz9QEoOOFkV.g4wcGn9NI5zW2K1aciwAlF18opG','klp7rnPBPdrLNyT8cBLqJDL3z7mQTFNWOB6eGmffZ5F1oYq4mDSWebl9vmXZ','2015-11-19 00:52:54','2015-11-26 22:43:19');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
