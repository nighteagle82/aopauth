/*
 Navicat Premium Data Transfer

 Source Server         : Mac-MySQL
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : aopauth

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 03/12/2019 14:54:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bind_organize
-- ----------------------------
DROP TABLE IF EXISTS `bind_organize`;
CREATE TABLE `bind_organize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgCode` int(12) NOT NULL DEFAULT '0' COMMENT 'orgCode编号',
  `authUrl` varchar(128) DEFAULT NULL COMMENT '绑定授权URL',
  `createDate` datetime DEFAULT NULL COMMENT '绑定时间',
  `isDelete` int(1) DEFAULT NULL COMMENT '0未删，1已删',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of bind_organize
-- ----------------------------
BEGIN;
INSERT INTO `bind_organize` VALUES (4, 10000, '/add1', '2019-12-02 16:16:36', 0);
INSERT INTO `bind_organize` VALUES (5, 10000, '/add2', '2019-12-02 16:16:36', 0);
INSERT INTO `bind_organize` VALUES (6, 10000, '/del', '2019-12-02 16:16:36', 0);
COMMIT;

-- ----------------------------
-- Table structure for organize
-- ----------------------------
DROP TABLE IF EXISTS `organize`;
CREATE TABLE `organize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orgName` varchar(50) DEFAULT NULL COMMENT 'org名称',
  `orgPwd` varchar(50) DEFAULT NULL COMMENT 'org密码',
  `orgCode` int(12) DEFAULT '10000' COMMENT 'org代号',
  `serctKey` varchar(120) DEFAULT NULL COMMENT 'org密钥',
  `effectDate` datetime DEFAULT NULL COMMENT 'org有效期',
  `createDate` datetime DEFAULT NULL COMMENT 'org创建日期',
  `isDelete` int(1) DEFAULT '0' COMMENT '0未删，1已删',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of organize
-- ----------------------------
BEGIN;
INSERT INTO `organize` VALUES (2, 'test', '16d7a4fca7442dda3ad93c9a726597e4', 10000, '843f8a0567a3b74ba3e49bfa2eaf73d4', '2020-06-02 16:13:26', '2019-12-02 16:13:26', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
