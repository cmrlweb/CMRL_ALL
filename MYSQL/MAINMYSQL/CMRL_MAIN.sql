# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.20)
# Database: CMRL
# Generation Time: 2016-01-19 10:51:34 +0000
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

CREATE TABLE `apiuser` (
  `id` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `lastlogged` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table AssetCodes
# ------------------------------------------------------------

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



# Dump of table Equipment
# ------------------------------------------------------------

CREATE TABLE `Equipment` (
  `Ecode` int(10) NOT NULL,
  `Name` varchar(80) NOT NULL,
  PRIMARY KEY (`Ecode`),
  UNIQUE KEY `Ecode_2` (`Ecode`),
  KEY `Ecode` (`Ecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




# Dump of table errorlog
# ------------------------------------------------------------

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



# Dump of table history
# ------------------------------------------------------------

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


# Dump of table Location
# ------------------------------------------------------------

CREATE TABLE `Location` (
  `Lcode` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  KEY `Lcode` (`Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Dump of table Maintainence
# ------------------------------------------------------------

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




# Dump of table Manu_det
# ------------------------------------------------------------

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


# Dump of table migrations
# ------------------------------------------------------------

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

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table permission_role
# ------------------------------------------------------------

CREATE TABLE `permission_role` (
  `permission_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `permission_role_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table permissions
# ------------------------------------------------------------

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

CREATE TABLE `role_user` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_user_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table roles
# ------------------------------------------------------------

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



# Dump of table users
# ------------------------------------------------------------

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

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
