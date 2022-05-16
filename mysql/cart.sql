create table cart
(
    username varchar(20)  not null,
    goodsId  int          not null,
    num      int          not null,
    specName varchar(50)  not null,
    img      varchar(100) null,
    name     varchar(50)  null,
    seller   varchar(50)  null,
    price    float(64, 3) null,
    primary key (username, goodsId, specName)
)
    engine = MyISAM
    
    INSERT INTO lab2.cart (username, goodsId, num, specName, img, name, seller, price) VALUES ('test', 1, 1, '', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg', 'phone', 'seller1', 5000);

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
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
