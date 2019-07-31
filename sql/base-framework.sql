CREATE TABLE `user` (
  `id` varchar(100) NOT NULL COMMENT '主键',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机',
  `active_flag` varchar(100) NOT NULL DEFAULT 'Y' COMMENT '是否激活:是Y,否N',
  `del_flag` varchar(100) NOT NULL DEFAULT 'N' COMMENT '是否删除:是Y,否N',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UN` (`username`),
  UNIQUE KEY `user_email_UN` (`email`),
  UNIQUE KEY `user_phone_UN` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
