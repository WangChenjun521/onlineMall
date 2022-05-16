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

 Date: 12/12/2021 21:15:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods_in_suborders
-- ----------------------------
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
