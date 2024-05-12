

CREATE TABLE `ware_info`
(
    `id`         varchar(64) NOT NULL,
    `shop_name`  varchar(255) DEFAULT NULL COMMENT '商品名称',
    `shop_price` varchar(255) DEFAULT NULL COMMENT '商品价格',
    `shop_pic`   varchar(255) DEFAULT NULL COMMENT '商品图片',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;