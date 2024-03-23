
USE `tacocloud_0`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`(
    `id`            BIGINT NOT NULL,
    `username`      VARCHAR(64) NOT NULL,
    `password`      VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`(
                       `id`            BIGINT NOT NULL,
                       `username`      VARCHAR(64) NOT NULL,
                       `password`      VARCHAR(64) NOT NULL,
                       PRIMARY KEY (`id`)
);

# tacokitchen的数据库表
USE `tacokitchen_0`;

DROP TABLE IF EXISTS `order_0`;
CREATE TABLE `order_0`(
    `id`            BIGINT NOT NULL,
    `user_id`       BIGINT NOT NULL,
    `tacos`         VARCHAR(256) NOT NULL,
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1`(
                          `id`            BIGINT NOT NULL,
                          `user_id`       BIGINT NOT NULL,
                          `tacos`         VARCHAR(256) NOT NULL,
                          PRIMARY KEY (`id`)
);

USE `tacocloud_1`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`(
                       `id`            BIGINT NOT NULL,
                       `username`      VARCHAR(64) NOT NULL,
                       `password`      VARCHAR(64) NOT NULL,
                       PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`(
                         `id`            BIGINT NOT NULL,
                         `username`      VARCHAR(64) NOT NULL,
                         `password`      VARCHAR(64) NOT NULL,
                         PRIMARY KEY (`id`)
);

# tacokitchen的数据库表
USE `tacokitchen_1`;

DROP TABLE IF EXISTS `order_0`;
CREATE TABLE `order_0`(
                        `id`            BIGINT NOT NULL,
                        `user_id`       BIGINT NOT NULL,
                        `tacos`         VARCHAR(256) NOT NULL,
                        PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1`(
                          `id`            BIGINT NOT NULL,
                          `user_id`       BIGINT NOT NULL,
                          `tacos`         VARCHAR(256) NOT NULL,
                          PRIMARY KEY (`id`)
);