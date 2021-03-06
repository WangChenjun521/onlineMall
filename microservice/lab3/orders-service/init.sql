create database `lab2` default character set utf8 collate utf8_general_ci;

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

 Date: 13/12/2021 12:15:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
use lab2;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addressee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `goodsId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`goodsId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'phone', 'big phone', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg', 5000, 'wcj');
INSERT INTO `product` VALUES (2, 'phone2', 'phone', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2yjl0hcj20hd0af74t.jpg', 4000, 'wcj');

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '?????????id',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????????',
  `state` int NOT NULL COMMENT '??????????????????',
  `phone` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
  `com_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '????????????',
  `amount` int NULL DEFAULT NULL COMMENT '???????????????',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, '??????', 0, '12345678999', 'FDU', '', '2021-12-12 21:12:16', NULL);
INSERT INTO `orders` VALUES (7, 'xm', 4, '12332112321', 'SH', '', '2021-12-12 21:12:21', NULL);

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `suborders`;
CREATE TABLE `suborders`  (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '?????????id',
  `fatherorderId` int NOT NULL COMMENT '??????????????????id',
  `goodsNum` int NULL DEFAULT NULL COMMENT '????????????????????????',
  `amount` int NULL DEFAULT NULL COMMENT '???????????????',
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???????????????',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suborders
-- ----------------------------
INSERT INTO `suborders` VALUES (12, 6, 2, 100, 'wcj');
INSERT INTO `suborders` VALUES (13, 7, 3, 500, 'wcj');

SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS `goods_in_suborders`;
CREATE TABLE `goods_in_suborders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '???????????????',
  `goodsId` int NULL DEFAULT NULL COMMENT '??????id',
  `orderId` int NULL DEFAULT NULL COMMENT '???????????????id???????????????????????????????????????orderId???',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????????????????',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
  `specName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????????????',
  `price` int NULL DEFAULT NULL COMMENT '????????????',
  `amount` int NULL DEFAULT NULL COMMENT '????????????',
  `num` int NULL DEFAULT NULL COMMENT '????????????',
  `state` int NULL DEFAULT NULL COMMENT '????????????????????????',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_in_suborders
-- ----------------------------
INSERT INTO `goods_in_suborders` VALUES (25, NULL, 12, NULL, '??????', '??????', 50, 50, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (27, NULL, 12, NULL, '??????', '??????', 50, 50, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (28, NULL, 13, NULL, '??????', '??????', 200, 400, 2, 4);
INSERT INTO `goods_in_suborders` VALUES (32, NULL, 13, NULL, '??????', '??????', 50, 50, 1, 4);
INSERT INTO `goods_in_suborders` VALUES (33, NULL, 13, NULL, '??????', '??????', 50, 50, 1, 4);

SET FOREIGN_KEY_CHECKS = 1;

