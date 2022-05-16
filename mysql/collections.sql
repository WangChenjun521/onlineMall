CREATE TABLE `collections` (
  `username` varchar(255) NOT NULL,
  `goodsId` int NOT NULL,
  `num` int DEFAULT NULL,
  `specName` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seller` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
INSERT INTO `collections` VALUES ('test', 1, 1, '', 'http://wx4.sinaimg.cn/mw600/7dd42f11ly1gxb2xocxdgj20kt0nmacs.jpg', 'phone', 'seller1', 5000);
