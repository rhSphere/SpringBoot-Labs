CREATE
DATABASE IF NOT EXISTS lab_21_cache_demo
    DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `users`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `username`    varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
    `password`    varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
    `create_time` datetime                        DEFAULT NULL COMMENT '创建时间',
    `deleted`     bit(1)                          DEFAULT NULL COMMENT '是否删除。0-未删除；1-删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1
    DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users`(`id`, `username`, `password`, `create_time`, `deleted`)
VALUES (1, 'yudaoyuanma', 'buzhidao', now(), 0);


