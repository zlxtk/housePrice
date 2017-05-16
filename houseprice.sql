/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : houseprice

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-02-28 14:37:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meanprice
-- ----------------------------
DROP TABLE IF EXISTS `meanprice`;
CREATE TABLE `meanprice` (
  `id` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '区的名字',
  `price` int(10) NOT NULL,
  `city` varchar(255) NOT NULL COMMENT '所在城市的缩写，比如“zz”:郑州',
  `date` datetime NOT NULL COMMENT '数据日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


