-- 系统用户
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
  `user_id`        bigint      NOT NULL AUTO_INCREMENT,
  `username`       varchar(50) NOT NULL COMMENT '用户名',
  `password`       varchar(100) COMMENT '密码',
  `salt`           varchar(20) COMMENT '盐',
  `email`          varchar(100) COMMENT '邮箱',
  `mobile`         varchar(100) COMMENT '手机号',
  `status`         tinyint COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time`    datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统用户';

-- 用户与角色对应关系
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
  `id`      bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户与角色对应关系';

-- 角色
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
  `role_id`        bigint NOT NULL AUTO_INCREMENT,
  `role_name`      varchar(100) COMMENT '角色名称',
  `remark`         varchar(100) COMMENT '备注',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time`    datetime COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色';

-- 菜单
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
  `menu_id`   bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
  `name`      varchar(50) COMMENT '菜单名称',
  `url`       varchar(200) COMMENT '菜单URL',
  `perms`     varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type`      int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon`      varchar(50) COMMENT '菜单图标',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='菜单管理';

-- 角色与菜单对应关系
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
  `id`      bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色与菜单对应关系';

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`
(
  `id`       int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '' COMMENT '公司名称',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '' COMMENT '公司描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_german2_ci COMMENT = '公司表，用来测试权限'
  ROW_FORMAT = Dynamic;