CREATE TABLE `user`
(
    `id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role`     VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_user_username` (`username` ASC)
);

INSERT INTO `user` (`username`, `password`, `role`)
VALUES ('admin@gmail.com', '$2a$11$cIrY4zoYzE9GZWeZklsx1eY3EfNb8XsA.xN9wU0DKEZUwyvXikDvW', 'ADMIN');

CREATE TABLE `token`
(
    `id`             BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `value`          VARCHAR(255) NOT NULL,
    `type`           VARCHAR(255) NOT NULL,
    `expirationdate` DATETIME     NOT NULL,
    `userid`         BIGINT(20)   NOT NULL,
    `createdon`      DATETIME     NOT NULL,
    `updatedon`      DATETIME     NULL,
    `deletedon`      DATETIME     NULL,
    PRIMARY KEY (`id`),
    KEY `FK_token_user` (`userid`),
    CONSTRAINT `FK_token_user` FOREIGN KEY (`userid`) references `user` (id)
);
