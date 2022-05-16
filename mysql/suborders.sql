/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : lab2

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 12/12/2021 21:15:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for suborders
-- ----------------------------
DROP TABLE IF EXISTS `suborders`;
CREATE TABLE `suborders`  (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '子订单id',
  `fatherorderId` int NOT NULL COMMENT '对应的父定单id',
  `goodsNum` int NULL DEFAULT NULL COMMENT '子订单商品类型数',
  `amount` int NULL DEFAULT NULL COMMENT '子订单总价',
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子订单卖家',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suborders
-- ----------------------------
INSERT INTO `suborders` VALUES (12, 6, 2, 100, 'wcj');
INSERT INTO `suborders` VALUES (13, 7, 3, 500, 'wcj');

SET FOREIGN_KEY_CHECKS = 1;
