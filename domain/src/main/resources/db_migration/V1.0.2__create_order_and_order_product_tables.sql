CREATE TABLE `order`
(
    `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `status`    VARCHAR(255) NOT NULL,
    `userid`    BIGINT(20)   NOT NULL,
    `createdon` DATETIME     NOT NULL,
    `updatedon` DATETIME     NULL,
    PRIMARY KEY (`id`),
    KEY `FK_order_user` (`userid`),
    CONSTRAINT `FK_order_user` FOREIGN KEY (`userid`) references `user` (id)
);

CREATE TABLE `order_product`
(
    `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
    `orderid`   BIGINT(20) NOT NULL,
    `productid` BIGINT(20) NOT NULL,
    `quantity`  INT(5)     NOT NULL,
    `createdon` DATETIME   NOT NULL,
    `updatedon` DATETIME   NULL,
    PRIMARY KEY (`id`),
    KEY `FK_order_product_order` (`orderid`),
    CONSTRAINT `FK_order_product_order` FOREIGN KEY (`orderid`) references `order` (id),
    KEY `FK_order_product_product` (`productid`),
    CONSTRAINT `FK_order_product_product` FOREIGN KEY (`productid`) references `product` (id),
    UNIQUE INDEX `UK_order_product` (`orderid` ASC, `productid` ASC)
);
