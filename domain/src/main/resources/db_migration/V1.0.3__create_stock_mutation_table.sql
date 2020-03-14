CREATE TABLE `stock_mutation`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `productid`     BIGINT(20)   NOT NULL,
    `mutationtype`  VARCHAR(255) NOT NULL,
    `mutationcount` INT(5)       NOT NULL,
    `createdon`     DATETIME     NOT NULL,
    `updatedon`     DATETIME     NULL,
    PRIMARY KEY (`id`),
    KEY `FK_stock_mutation_product` (`productid`),
    CONSTRAINT `FK_stock_mutation_product` FOREIGN KEY (`productid`) references `product` (id)
);
