/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : homework

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-10 23:44:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_homework
-- ----------------------------
DROP TABLE IF EXISTS `back_homework`;
CREATE TABLE `back_homework` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `homework_title` varchar(255) NOT NULL COMMENT '作业名字',
  `homework_content` text NOT NULL COMMENT '作业内容',
  `homework_deadline` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='homeworkTable';

-- ----------------------------
-- Records of back_homework
-- ----------------------------
INSERT INTO `back_homework` VALUES ('1', 'aaasda', '随着一阵拔尖的煞车声，樱子的一生轻轻地飞了起来。缓缓地，飘落在湿冷的街面上，好像一只夜晚的蝴蝶。\r\n\r\n虽然是春天，好像已是秋深了。\r\n\r\n她只是过马路去帮我寄信。这简单的行动，却要叫我终身难忘了。我缓缓睁开眼，茫然站在骑楼下，眼里裹着滚烫的泪水。世上所有的车子都停了下来，人潮涌向马路中央。没有人知道那躺在街面的，就是我的，蝴蝶。这时她只离我五公尺，竟是那么遥远。更大的雨点溅在我的眼镜上，溅到我的生命里来。\r\n\r\n为什么呢？只带一把雨伞？\r\n\r\n然而我又看到樱子穿着白色的风衣，撑着伞，静静地过马路了。她是要帮我寄信的。那，那是一封写给南部母亲的信。我茫然站在骑楼下，我又看到永远的樱子走到街心。其实雨下得并不大，却是一生一世中最大的一场雨。而那封信是这样写的，年轻的樱子知不知道呢？', '2017-09-09 00:00:00', null, '2017-09-03 23:38:42', '1', '2017-09-03 23:43:59', '文章有几点尤值得我们好好品味。一是作品以“雨”为线索，贯穿全文的始终。悲剧因“雨”而生，小说开篇写“雨”，正是对不幸和灾难起因的一个交代。樱子遭遇不幸后，又写“更大的雨点溅在我的眼镜上', '0');
INSERT INTO `back_homework` VALUES ('2', 'ASDsd', 'ASDFASFAD', '2017-09-06 00:00:00', '1', '2017-09-06 01:08:38', null, null, 'ASDAFSD', '0');

-- ----------------------------
-- Table structure for back_homework_comment
-- ----------------------------
DROP TABLE IF EXISTS `back_homework_comment`;
CREATE TABLE `back_homework_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `homework_id` bigint(20) NOT NULL,
  `comment_content` varchar(255) NOT NULL COMMENT '作业名字',
  `comment_time` datetime NOT NULL COMMENT '作业内容',
  `comment_username` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='homeworkCommont';

-- ----------------------------
-- Records of back_homework_comment
-- ----------------------------
INSERT INTO `back_homework_comment` VALUES ('6', '1', 'adsad', '2017-09-06 00:55:27', 'super', '1', '2017-09-06 00:55:27', null, '0');
INSERT INTO `back_homework_comment` VALUES ('7', '1', 'asdfa', '2017-09-06 01:06:55', 'super', '1', '2017-09-06 01:06:55', null, '0');
INSERT INTO `back_homework_comment` VALUES ('8', '1', 'asdasdfadfa', '2017-09-06 01:07:33', 'super', '1', '2017-09-06 01:07:33', null, '0');
INSERT INTO `back_homework_comment` VALUES ('9', '1', 'asdafsdaf', '2017-09-06 01:07:48', 'super', '1', '2017-09-06 01:07:48', null, '0');
INSERT INTO `back_homework_comment` VALUES ('10', '2', 'xC的示范法', '2017-09-06 01:08:45', 'super', '1', '2017-09-06 01:08:45', null, '0');

-- ----------------------------
-- Table structure for back_homework_submit
-- ----------------------------
DROP TABLE IF EXISTS `back_homework_submit`;
CREATE TABLE `back_homework_submit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `homework_id` bigint(20) NOT NULL COMMENT '作业名字',
  `user_id` bigint(20) NOT NULL COMMENT '作业名字',
  `user_name` varchar(255) DEFAULT NULL,
  `homework_grade` int(10) DEFAULT NULL COMMENT '作业内容',
  `file_name` varchar(255) DEFAULT NULL COMMENT '作业内容',
  `file_url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='homework submit';

-- ----------------------------
-- Records of back_homework_submit
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sys_name` varchar(255) NOT NULL COMMENT '系统名称',
  `module_name` varchar(255) NOT NULL COMMENT '模块名称',
  `config_name` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(255) DEFAULT NULL COMMENT '配置值',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公共配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'sys', 'common', 'project.name', 'homework', '1', '2017-05-16 14:33:01', '1', '2017-08-26 15:11:04', 'Project Name', '0');
INSERT INTO `sys_config` VALUES ('2', 'sys', 'common', 'project.copyright', 'CopyRight©2017 david.com All Rights Reserved.', '1', '2017-05-16 14:46:29', '1', '2017-08-26 15:10:56', 'Version Information', '0');

-- ----------------------------
-- Table structure for sys_msg_receive
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg_receive`;
CREATE TABLE `sys_msg_receive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `msg_id` bigint(20) NOT NULL COMMENT '消息编号',
  `update_by` bigint(20) NOT NULL COMMENT '接收人',
  `update_date` datetime NOT NULL COMMENT '接收时间',
  `status` char(1) DEFAULT NULL COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息接收表';

-- ----------------------------
-- Records of sys_msg_receive
-- ----------------------------
INSERT INTO `sys_msg_receive` VALUES ('11', '10', '1', '2017-04-12 14:00:23', '1');
INSERT INTO `sys_msg_receive` VALUES ('12', '11', '1', '2017-04-12 16:00:09', '1');
INSERT INTO `sys_msg_receive` VALUES ('13', '12', '1', '2017-04-12 16:02:33', '1');
INSERT INTO `sys_msg_receive` VALUES ('14', '13', '1', '2017-04-12 16:10:27', '1');
INSERT INTO `sys_msg_receive` VALUES ('15', '14', '1', '2017-08-24 22:05:28', '1');

-- ----------------------------
-- Table structure for sys_msg_send
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg_send`;
CREATE TABLE `sys_msg_send` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(50) NOT NULL COMMENT '消息类型  系统通知  站内信 用户通知',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `level` int(1) DEFAULT NULL COMMENT '级别',
  `users` varchar(255) NOT NULL COMMENT '接收人',
  `create_by` bigint(20) DEFAULT NULL COMMENT '发送人',
  `create_date` datetime DEFAULT NULL COMMENT '发送时间',
  `status` char(1) DEFAULT NULL COMMENT '状态 0草稿 1已发送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息发送表';

-- ----------------------------
-- Records of sys_msg_send
-- ----------------------------
INSERT INTO `sys_msg_send` VALUES ('10', 'mail', '测试邮件', '                                                                                        \r\n                                        <p>测试邮件</p><p><br></p>\r\n                                        ', '0', '1,', '1', '2017-04-12 14:00:14', '0');
INSERT INTO `sys_msg_send` VALUES ('11', 'mail', '你好，我是管理员，现在测试站内信', '                                            \r\n                                        <p>你好，我是管理员，现在测试站内信</p><p><br></p>', '0', '1,', '1', '2017-04-12 16:00:09', '0');
INSERT INTO `sys_msg_send` VALUES ('12', 'notice', '请管理员来开会', '                                            \r\n                                        <p>请管理员来开会</p><p><br></p>', '0', '1,', '1', '2017-04-12 16:02:33', '0');
INSERT INTO `sys_msg_send` VALUES ('13', 'mail', '消息', '                                            \r\n                                        <p>是多少</p>', '0', '1,', '1', '2017-04-12 16:10:27', '0');
INSERT INTO `sys_msg_send` VALUES ('14', 'notice', '测试', '                                            \r\n                                        <p>哈哈哈</p>', '0', '1,', '1', '2017-08-24 22:05:28', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `url` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(5000) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否可用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(225) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`(255))
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', 'resource', 'menu', '', '', '0', '0/', '', '1', '0', '1', null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('2', 'SystemManagement', 'menu', '', '', '1', '0/1/', 'sys:manage', '1', '10', '1', '2016-06-14 09:33:06', '1', '2016-11-18 01:21:14', null, '1');
INSERT INTO `sys_resource` VALUES ('21', 'UserManagement', 'menu', '', 'user/list', '75', '0/1/2/', 'sys:user:view', '1', '10', '1', null, '1', '2017-02-18 18:11:51', null, '0');
INSERT INTO `sys_resource` VALUES ('22', 'view', 'form', '', '', '21', '0/1/2/21/', 'sys:user:view', '1', '4', '1', null, '1', '2017-01-10 10:38:16', null, '0');
INSERT INTO `sys_resource` VALUES ('23', 'modify', 'form', '', '', '21', '0/1/2/21/', 'sys:user:update', '1', '3', '1', null, '1', '2017-01-10 11:14:13', null, '0');
INSERT INTO `sys_resource` VALUES ('31', 'ResourceSettings', 'menu', '', 'resource', '75', '0/1/75/', 'sys:resource', '1', '20', '1', null, '1', '2016-10-21 01:32:27', null, '0');
INSERT INTO `sys_resource` VALUES ('32', 'view', 'form', '', '', '31', '0/1/2/31/', 'sys:resource:view', '1', '3', '1', null, '1', '2017-01-10 10:49:52', null, '0');
INSERT INTO `sys_resource` VALUES ('33', 'modify', 'form', '', '', '31', '0/1/2/31/', 'sys:resource:edit', '1', '1', '1', null, '1', '2017-01-10 10:49:08', null, '0');
INSERT INTO `sys_resource` VALUES ('41', 'RoleSettings', 'menu', '', 'role', '75', '0/1/75/', 'sys:role:view', '1', '30', '1', null, '1', '2016-10-21 01:32:44', null, '0');
INSERT INTO `sys_resource` VALUES ('42', 'view', 'form', '', '', '41', '0/1/2/41/', 'sys:role:view', '1', '1', '1', null, '1', '2017-01-10 14:42:16', null, '0');
INSERT INTO `sys_resource` VALUES ('43', 'modify', 'form', '', '', '41', '0/1/2/41/', 'sys:role:edit', '1', '2', '1', null, '1', '2017-01-10 14:42:20', null, '0');
INSERT INTO `sys_resource` VALUES ('75', 'SystemSettings', 'menu', '', '', '1', '0/1/', 'sys:setting', '1', '20', '1', '2016-10-09 00:04:34', '1', '2016-11-18 01:18:34', null, '0');
INSERT INTO `sys_resource` VALUES ('97', 'create', 'form', '', '', '21', '0/1/2/21/', 'sys:user:create', '1', '1', '1', '2017-01-10 10:30:07', '1', '2017-01-10 10:37:20', null, '0');
INSERT INTO `sys_resource` VALUES ('98', 'delete', 'form', '', '', '21', '0/1/2/21/', 'sys:user:delete', '1', '2', '1', '2017-01-10 10:37:07', '1', '2017-01-10 10:50:33', null, '0');
INSERT INTO `sys_resource` VALUES ('101', 'PublicConfiguration', 'menu', '', 'sys/config', '75', '0/1/2/', 'sys:config:view', '1', '20', '1', '2017-01-14 21:31:11', null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('102', 'create', 'form', '', '', '101', '0/1/2/101/', 'sys:config:create', '1', '1', '1', '2017-01-14 21:31:37', null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('103', 'delete', 'form', '', '', '101', '0/1/2/101/', 'sys:config:delete', '1', '2', '1', '2017-01-14 21:31:55', '1', '2017-01-14 21:32:20', null, '0');
INSERT INTO `sys_resource` VALUES ('104', 'modify', 'form', '', '', '101', '0/1/2/101/', 'sys:config:update', '1', '3', '1', '2017-01-14 21:32:12', null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('107', 'notification', 'form', '', '', '1', '0/1/', 'sys:msgSend:create', '1', '90', '1', '2017-04-11 14:28:28', '1', '2017-04-11 14:30:07', null, '1');
INSERT INTO `sys_resource` VALUES ('108', 'HomeworkCenter', 'menu', '', '', '1', '0/1/', '', '1', '0', '1', '2017-08-29 14:48:13', null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('109', 'HomeworkList', 'menu', '', 'homework/list', '108', '0/1/108/', 'homework:homework', '1', '0', '1', '2017-08-29 14:48:42', '1', '2017-08-29 14:51:27', null, '0');
INSERT INTO `sys_resource` VALUES ('110', 'TeamUserManage', 'menu', '', 'user/teamuser', '108', '0/1/108/', 'homework:homework:teamadd', '1', '0', '1', '2017-08-29 14:49:46', '1', '2017-08-30 00:22:54', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `role` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `resource_ids` varchar(5000) DEFAULT NULL COMMENT '资源编号集合',
  `data_scope` varchar(50) NOT NULL COMMENT '数据范围',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否可用',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(225) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'teacher', 'SuperManager', '21,22,23,31,32,33,41,42,43,75,97,98,101,102,103,104,108,109,110,', 'self', '1', '1', '2016-10-08 12:32:47', '1', '2017-08-30 00:24:01', 'Super Right', '0');
INSERT INTO `sys_role` VALUES ('2', 'student', 'Student', '2,51,105,', 'self', '1', '1', '2017-04-10 16:18:11', '1', '2017-08-26 13:37:24', '', '0');
INSERT INTO `sys_role` VALUES ('3', 'leader', 'TeamLeader', '', 'self', '1', '1', '2017-08-24 22:14:51', '1', '2017-08-26 13:37:37', 'Group leader', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `no` varchar(100) DEFAULT NULL COMMENT '用户编号',
  `username` varchar(100) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL COMMENT '权限编号集合',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话号码',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号码',
  `photo` varchar(1000) DEFAULT NULL COMMENT '头像',
  `login_ip` varchar(45) DEFAULT NULL COMMENT '登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `is_dept` tinyint(1) DEFAULT NULL COMMENT '是否部门管理员',
  `teamleader_id` int(20) DEFAULT '-1',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(225) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, 'super', 'ff7e419b2147a346fcf97e8a0d439143', '7b5f5d3a1d3ba80fed0ad6256eb0fc3c', '1,', 'SuperManage', 'a1600783592@gmail.com', '', '11111', '', null, null, '0', '0', '1', '1', '2016-10-15 17:13:38', '1', '2017-09-10 20:29:57', '', '0');
INSERT INTO `sys_user` VALUES ('2', null, '123', '0dd9ed6f736008be705ff12427ad947b', '9c872f6737ab417d2b8e96fabda72ffd', '', 'asda', 'asda', 'asd', 'asd', null, null, null, '0', '0', '1', '1', '2017-08-30 14:01:48', '1', '2017-09-08 23:41:18', null, '0');
INSERT INTO `sys_user` VALUES ('3', null, 'asdfasd', 'ffa3d73b01d26ab38bdb852797566c88', '606aa5093dacaaad2dc74c2da4944176', '2,', 'asda', 'asda', '12312', '123123', null, null, null, '0', '0', '-1', '1', '2017-09-08 23:22:52', '1', '2017-09-08 23:40:15', null, '0');
