
-- 初始数据
INSERT INTO `sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (1, 'admin', '09bb905401e0fd23f5cc41e94e205efed33da3984a6679aacfeecd60c555b96d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2019-07-16 10:47:49');
INSERT INTO `sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (2, 'test1', '2065f80b31381dd53c32c25b4c74a7b6f153dd22eda2087ee0d9c6c432275b16', '85656fff2d', 'keane.@gmail.com', '15988745578', 1, 1, '2019-07-16 11:43:04');
INSERT INTO `sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (3, 'test2', 'da730106f4aad8e5328243fa296b044c77c5dfa476817e4905c3688be54a53c2', '3321ae1f8c', 'marry.@gmail.com', '13565547845', 1, 1, '2019-07-16 11:46:02');
INSERT INTO `sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (4, 'test3', '5c23655ce2d2ff06540ce944070090b9974f3e484593556fc0fa6a2ebf8af64f', '31db8a3783', 'andy.@gmail.com', '177556458741', 1, 1, '2019-07-16 11:47:41');



INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (1, 0, '查看公司列表', '/company/list', 'sys:company:list', 1, '1', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (2, 0, '添加公司', '/company/add', 'sys:company:add', 1, '1', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (3, 0, '删除公司', '/company/del', 'sys:company:del', 1, '1', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (4, 0, '查看公司详情', '/company/detail', 'sys:company:detail', 1, '1', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (5, 0, '修改公司', '/company/update', 'sys:company:update', 1, '1', 1);




INSERT INTO `sys_role`(`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES (1, 'company_admin', '企业模块管理员', 1, '2019-07-16 15:49:14');
INSERT INTO `sys_role`(`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES (2, 'company_user', '普通用户', 2, '2019-07-16 15:49:44');




INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (1, 1, 1);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (2, 1, 2);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (3, 1, 3);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (4, 1, 4);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (5, 1, 5);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (6, 2, 1);
INSERT INTO `sys_role_menu`(`id`, `role_id`, `menu_id`) VALUES (7, 2, 4);



INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`) VALUES (2, 2, 2);



INSERT INTO `company` VALUES (1, '小米', '小米科技。。。。');
INSERT INTO `company` VALUES (2, '华为', '华为科技。。。。');
