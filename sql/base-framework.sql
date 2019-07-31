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


CREATE TABLE `sys_role` (
  `id` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL COMMENT '角色',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `del_flag` varchar(100) DEFAULT 'N',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_role_IDX` (`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `sys_permission` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `resource_type` varchar(100) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `parent_id` varchar(100) DEFAULT NULL COMMENT '父亲节点',
  `role_id` varchar(100) DEFAULT NULL COMMENT '权限对应的角色',
  `del_flag` varchar(100) DEFAULT 'N',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_permission_name_IDX` (`name`) USING BTREE,
  KEY `sys_permission_role_id_IDX` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

