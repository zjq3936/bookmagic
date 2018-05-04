/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.6.37-log : Database - cynick
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '书名',
  `title_image_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '主图地址',
  `source_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原链接地址',
  `description` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简介',
  `author` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '作者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=861 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `book` */

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` text COLLATE utf8_unicode_ci COMMENT '正文',
  `site` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '来源站点',
  `page` int(20) DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `book_id` bigint(20) DEFAULT NULL COMMENT '图书ID',
  `qidian_updateTime` timestamp NULL DEFAULT NULL COMMENT '起点更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `content` */

/*Table structure for table `jobinfo` */
