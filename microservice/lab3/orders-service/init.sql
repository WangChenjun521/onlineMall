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
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '父订单id',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `state` int NOT NULL COMMENT '前台订单状态',
  `phone` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `com_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉理由',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `amount` int NULL DEFAULT NULL COMMENT '父订单总价',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, '李华', 0, '12345678999', 'FDU', '', '2021-12-12 21:12:16', NULL);
INSERT INTO `orders` VALUES (7, 'xm', 4, '12332112321', 'SH', '', '2021-12-12 21:12:21', NULL);

SET FOREIGN_KEY_CHECKS = 1;


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
DROP TABLE IF EXISTS `goods_in_suborders`;
CREATE TABLE `goods_in_suborders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '仅用作区分',
  `goodsId` int NULL DEFAULT NULL COMMENT '商品id',
  `orderId` int NULL DEFAULT NULL COMMENT '对应子订单id（为与前端同步此字段名设为orderId）',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片链接',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `specName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格参数',
  `price` int NULL DEFAULT NULL COMMENT '商品单价',
  `amount` int NULL DEFAULT NULL COMMENT '商品总价',
  `num` int NULL DEFAULT NULL COMMENT '商品数量',
  `state` int NULL DEFAULT NULL COMMENT '订单状态（后台）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_in_suborders
-- ----------------------------
INSERT INTO `goods_in_suborders` VALUES (25, NULL, 12, NULL, '薯条', '香脆', 50, 50, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (27, NULL, 12, NULL, '汉堡', '香辣', 50, 50, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (28, NULL, 13, NULL, '薯条', '摇摇', 200, 400, 2, 4);
INSERT INTO `goods_in_suborders` VALUES (32, NULL, 13, NULL, '汉堡', '田园', 50, 50, 1, 4);
INSERT INTO `goods_in_suborders` VALUES (33, NULL, 13, NULL, '可乐', '零度', 50, 50, 1, 4);

SET FOREIGN_KEY_CHECKS = 1;

