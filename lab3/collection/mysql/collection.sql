CREATE TABLE `collections` (
  `id_user` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;