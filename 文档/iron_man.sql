# Host: localhost  (Version: 5.5.53)
# Date: 2020-09-17 15:04:15
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "adm_info"
#

DROP TABLE IF EXISTS `adm_info`;
CREATE TABLE `adm_info` (
  `adm_id` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adm_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "adm_info"
#

/*!40000 ALTER TABLE `adm_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_info` ENABLE KEYS */;

#
# Structure for table "application"
#

DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `application_id` varchar(36) NOT NULL,
  `card_id` varchar(36) NOT NULL,
  `applicant_id` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `target_user_id` varchar(50) NOT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "application"
#

/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;

#
# Structure for table "card"
#

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `card_id` varchar(36) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `type` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date` varchar(30) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `introduction` varchar(200) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `min_price` double DEFAULT NULL,
  `max_price` double DEFAULT NULL,
  `min_square` double DEFAULT NULL,
  `max_square` double DEFAULT NULL,
  `unit_type` int(11) DEFAULT NULL,
  `requirement` varchar(100) DEFAULT NULL,
  `union_num` int(11) DEFAULT NULL,
  `has_house_resource` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "card"
#

/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;

#
# Structure for table "card_coordinate"
#

DROP TABLE IF EXISTS `card_coordinate`;
CREATE TABLE `card_coordinate` (
  `card_id` varchar(36) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "card_coordinate"
#


#
# Structure for table "comment"
#

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(36) NOT NULL,
  `card_id` varchar(36) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `reply_to_id` varchar(36) DEFAULT NULL,
  `belong_to_id` varchar(36) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "comment"
#

/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

#
# Structure for table "image"
#

DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `card_id` varchar(36) NOT NULL,
  `image_url` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "image"
#

/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;

#
# Structure for table "log"
#

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `card_id` varchar(45) DEFAULT NULL,
  `apply_id` varchar(45) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `aim_user_id` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

#
# Data for table "log"
#

/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

#
# Structure for table "team"
#

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `team_id` varchar(36) NOT NULL,
  `captain_id` varchar(50) NOT NULL,
  `card_id` varchar(36) NOT NULL,
  `max_num` int(11) NOT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "team"
#

/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;

#
# Structure for table "team_application"
#

DROP TABLE IF EXISTS `team_application`;
CREATE TABLE `team_application` (
  `application_id` varchar(36) NOT NULL,
  `team_id` varchar(36) NOT NULL,
  `applicant_id` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `target_user_id` varchar(50) NOT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "team_application"
#

/*!40000 ALTER TABLE `team_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_application` ENABLE KEYS */;

#
# Structure for table "team_member"
#

DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `team_id` varchar(36) NOT NULL,
  `user_id` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "team_member"
#

/*!40000 ALTER TABLE `team_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member` ENABLE KEYS */;

#
# Structure for table "uncomplete_card"
#

DROP TABLE IF EXISTS `uncomplete_card`;
CREATE TABLE `uncomplete_card` (
  `user_id` varchar(50) NOT NULL,
  `card_id` varchar(36) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "uncomplete_card"
#

/*!40000 ALTER TABLE `uncomplete_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `uncomplete_card` ENABLE KEYS */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `introduction` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
