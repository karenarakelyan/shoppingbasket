CREATE TABLE `product`
(
    `id`          INT            NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)    NOT NULL,
    `description` VARCHAR(45)    NULL,
    `type`        VARCHAR(45)    NOT NULL,
    `price`       DECIMAL(19, 2) NOT NULL,
    `createon`    DATETIME       NOT NULL,
    `updateon`    DATETIME       NULL,
    PRIMARY KEY (`id`)
);
