CREATE TABLE `product`
(
    `id`          BIGINT(20)     NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)   NOT NULL,
    `description` VARCHAR(255)   NULL,
    `type`        VARCHAR(255)   NOT NULL,
    `price`       DECIMAL(19, 2) NOT NULL,
    `createdon`   DATETIME       NOT NULL,
    `updatedon`   DATETIME       NULL,
    `deletedon`   DATETIME       NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT UNIQUE INDEX `UK_product_name_deletedon` (`name` ASC, `deletedon` ASC)
);
