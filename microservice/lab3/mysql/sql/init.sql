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
  `balance` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', 'user', '123456', '张三', '复旦大学', '11111111', 3, 6000);
INSERT INTO `user` VALUES (2, 'shop', 'shop', 'shop', NULL, NULL, NULL, 2, NULL);
INSERT INTO `user` VALUES (3, 'admin', 'admin', '123456', 'scy', NULL, '13344445555', 3, 11000);
INSERT INTO `user` VALUES (4, 'xiaoming', 'xiaoming', 'xm', NULL, 'FDU', '12345678999', 3, 10000);
INSERT INTO `user` VALUES (5, 'lihua', 'lihua', 'lh', NULL, NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (6, 'xiaohong', 'xiaohong', 'xh', NULL, 'SH', '12332112345', 3, 5000);

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
INSERT INTO `product` VALUES (1, 'phone', 'big phone', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg', 5000, 'lihua');
INSERT INTO `product` VALUES (2, 'phone2', 'phone', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2yjl0hcj20hd0af74t.jpg', 4000, 'lihua');
INSERT INTO `product` VALUES (3, '汉堡', '汉堡', 'https://img0.baidu.com/it/u=3414057707,938174722&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=282', 20, 'lihua');
INSERT INTO `product` VALUES (4, '薯条', '薯条', 'https://img1.baidu.com/it/u=3782792993,1634841116&fm=26&fmt=auto', 10, 'lihua');
INSERT INTO `product` VALUES (5, '可乐', '可乐', 'https://img0.baidu.com/it/u=3752216122,2603301198&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', 12, 'lihua');

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
INSERT INTO `orders` VALUES (1, 'xiaoming', 0, '12345678999', 'FDU', '', '2021-12-12 21:12:16', NULL);
INSERT INTO `orders` VALUES (2, 'xiaohong', 2, '12332112345', 'SH', '', '2021-12-12 21:12:21', NULL);

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
INSERT INTO `suborders` VALUES (1, 1, 3, 42, 'lihua');
INSERT INTO `suborders` VALUES (2, 2, 2, 40, 'lihua');

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
INSERT INTO `goods_in_suborders` VALUES (1, 4, 1, 'https://img1.baidu.com/it/u=3782792993,1634841116&fm=26&fmt=auto', '薯条', '香脆', 10, 10, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (2, 3, 1, 'https://img0.baidu.com/it/u=3414057707,938174722&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=282', '汉堡', '香辣', 20, 20, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (3, 5, 1, 'https://img0.baidu.com/it/u=3752216122,2603301198&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '可乐', '零度', 12, 12, 1, 0);
INSERT INTO `goods_in_suborders` VALUES (4, 4, 2, 'https://img1.baidu.com/it/u=3782792993,1634841116&fm=26&fmt=auto', '薯条', '摇摇', 10, 20, 2, 2);
INSERT INTO `goods_in_suborders` VALUES (5, 3, 2, 'https://img0.baidu.com/it/u=3414057707,938174722&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=282', '汉堡', '田园', 20, 20, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `collections`;
CREATE TABLE `collections`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsId` int(0) NOT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `specName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collections
-- ----------------------------
INSERT INTO `collections` VALUES ('xiaoming', 2, 1, 'pro', 'http://img.alicdn.com/imgextra/i1/1880612425/O1CN01KqxzQZ1Tmh2f8qYIz_%21%211880612425-0-beehive-scenes.jpg', 'phone max', 'lihua', 5000.00);
INSERT INTO `collections` VALUES ('xiaoming', 10, 1, 'pro', 'http://img.alicdn.com/imgextra/i1/1880612425/O1CN01KqxzQZ1Tmh2f8qYIz_%21%211880612425-0-beehive-scenes.jpg', 'phone max1', 'lihua', 7663);
INSERT INTO `collections` VALUES ('xiaoming', 6, 1, 'pro','http://img.alicdn.com/imgextra/i1/1880612425/O1CN01KqxzQZ1Tmh2f8qYIz_%21%211880612425-0-beehive-scenes.jpg', 'phone max2', 'lihua', 7653);
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsId` int NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `specName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `cart` VALUES ('user', 1, 10,'个','http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg','phone','wcj', 5000);

SET FOREIGN_KEY_CHECKS = 1;