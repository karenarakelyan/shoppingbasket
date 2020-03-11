CREATE TABLE `user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `role`     VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
