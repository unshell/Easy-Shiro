/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : easy_base

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/09/2020 17:29:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for monitor_job
-- ----------------------------
DROP TABLE IF EXISTS `monitor_job`;
CREATE TABLE `monitor_job`  (
  `JobId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `BeanName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Bean名称',
  `MethodName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名称',
  `Params` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `CronExpression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
  `Status` int(11) NULL DEFAULT NULL COMMENT '调度状态',
  `Remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JobId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务调度' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_job
-- ----------------------------
INSERT INTO `monitor_job` VALUES (1, 'testTask', 'test', '1', '0/5 * * * * ?', 1, '调度有参方法', '2020-07-10 09:22:26');
INSERT INTO `monitor_job` VALUES (3, 'testTask', 'test1', '', '0/5 * * * * ?', 1, '调度无参方法', '2020-07-10 15:19:13');

-- ----------------------------
-- Table structure for monitor_job_log
-- ----------------------------
DROP TABLE IF EXISTS `monitor_job_log`;
CREATE TABLE `monitor_job_log`  (
  `LogId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '调度日志编号',
  `JobId` bigint(20) NOT NULL COMMENT '任务编号',
  `BeanName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Bean名称',
  `MethodName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名称',
  `Params` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法参数',
  `Status` int(11) NOT NULL COMMENT '调度状态',
  `Error` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调度错误信息',
  `Times` bigint(20) NOT NULL COMMENT '耗时',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LogId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调度日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for monitor_log
-- ----------------------------
DROP TABLE IF EXISTS `monitor_log`;
CREATE TABLE `monitor_log`  (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统日志编号',
  `Operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `Operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `Method` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法',
  `Params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `Time` decimal(20, 0) NULL DEFAULT NULL COMMENT '运行时间',
  `IpAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监控日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for monitor_login_log
-- ----------------------------
DROP TABLE IF EXISTS `monitor_login_log`;
CREATE TABLE `monitor_login_log`  (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '登入日志编号',
  `LoginName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员名称',
  `Device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行的设备',
  `BrowserType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `IpAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `LoginTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary`  (
  `DictId` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置编号',
  `GroupId` int(11) NULL DEFAULT NULL COMMENT '组编号',
  `DictName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `Sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `DictKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `DictValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `IsGroup` bit(1) NOT NULL COMMENT '是否为组',
  `Sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `Remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DictId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
INSERT INTO `system_dictionary` VALUES (1, NULL, '短信模板', 'sms', 'sms', NULL, b'1', 1, NULL);
INSERT INTO `system_dictionary` VALUES (2, 1, '注册验证码', 'sms#register', 'register', '您正在注册，验证码是：%s，请在30分钟之内完成验证，请勿泄露，感谢您的支持。', b'0', 1, '注册操作时的验证码');
INSERT INTO `system_dictionary` VALUES (3, 1, '修改密码', 'sms#reset', 'reset', '您正在修改密码，验证码是：%s，请在30分钟之内完成验证，请勿泄露，感谢您的支持。', b'0', 2, '修改密码时的验证码');
INSERT INTO `system_dictionary` VALUES (4, 1, '登录', 'sms#login', 'login', '您的验证码是：%s，请在3分钟内完成验证', b'0', 3, '登录账户时的验证码');

-- ----------------------------
-- Table structure for system_manager
-- ----------------------------
DROP TABLE IF EXISTS `system_manager`;
CREATE TABLE `system_manager`  (
  `ManagerId` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `LoginName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `NickName` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `Avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `Phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `Address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `Status` int(11) NOT NULL COMMENT '状态 - 0:有效\\1:锁定',
  `Signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密签名',
  `RoleId` int(11) NOT NULL COMMENT '角色编号',
  `Introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `ModifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `LastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登入时间',
  PRIMARY KEY (`ManagerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_manager
-- ----------------------------
INSERT INTO `system_manager` VALUES (1, 'admin', 'a95e67b189ae40489bea1586f7c0d5a7', 'unshell', 'boy-01.jpg', '14777777777', '1548462908@qq.com', '常山真定', 0, '9e685a8ecf824253a2cf2977fac8c1ba', 1, '我相信所有坚韧不拔的努力，迟早会得来好报酬。', '2020-07-17 11:24:12', '2020-07-08 17:06:49', NULL);
INSERT INTO `system_manager` VALUES (2, 'alex', 'bc46f76ad036f83b0685cbf7b12dd419', '亚历克斯', NULL, '13777777777', 'alex@qq.com', NULL, 0, '56cd87f0d04947028649b3199a47abe0', 2, NULL, '2020-08-20 10:24:35', '2020-07-17 10:38:06', NULL);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `MenuId` int(11) NOT NULL COMMENT '菜单编号',
  `ParentId` int(11) NOT NULL COMMENT '父级编号',
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `Url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `Icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `Perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `Sort` int(11) NOT NULL COMMENT '排序',
  `Type` int(11) NOT NULL COMMENT '类型 - 0:菜单\\1:按钮',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`MenuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, -1, 'HomePage', '', 'layui-icon layui-icon-home', '', 1, 0, '2020-08-19 16:52:03');
INSERT INTO `system_menu` VALUES (2, -1, '系统管理', '', 'layui-icon layui-icon-senior', '', 2, 0, '2020-07-08 12:35:22');
INSERT INTO `system_menu` VALUES (3, -1, '监控调度', '', 'layui-icon layui-icon-engine', '', 3, 0, '2020-07-09 12:49:38');
INSERT INTO `system_menu` VALUES (10, 1, '欢迎页', 'welcome', '', '', 1, 0, '2020-08-19 16:53:47');
INSERT INTO `system_menu` VALUES (20, 2, '用户管理', '/system/manager', '', '', 1, 0, '2020-07-08 12:39:50');
INSERT INTO `system_menu` VALUES (21, 2, '角色管理', '/system/role', '', '', 2, 0, '2020-07-08 12:40:22');
INSERT INTO `system_menu` VALUES (22, 2, '菜单管理', '/system/menu', '', '', 3, 0, '2020-07-08 12:40:58');
INSERT INTO `system_menu` VALUES (23, 2, '字典管理', '/system/dictionary', '', '', 4, 0, '2020-07-09 16:50:24');
INSERT INTO `system_menu` VALUES (30, 3, '登入日志', '/monitor/login/log', '', '', 1, 0, '2020-07-09 12:54:18');
INSERT INTO `system_menu` VALUES (31, 3, '操作日志', '/monitor/log', '', '', 2, 0, '2020-07-09 16:49:06');
INSERT INTO `system_menu` VALUES (32, 3, '定时任务', '/monitor/job', '', '', 3, 0, '2020-07-09 17:08:48');
INSERT INTO `system_menu` VALUES (34, 3, '代码生成', '/monitor/generate', '', '', 5, 0, '2020-08-18 17:38:49');
INSERT INTO `system_menu` VALUES (2001, 20, '查看用户', '', '', 'manager:view', 1, 1, '2020-07-08 13:28:13');
INSERT INTO `system_menu` VALUES (2002, 20, '创建用户', '', '', 'manager:add', 2, 1, '2020-07-08 13:28:47');
INSERT INTO `system_menu` VALUES (2003, 20, '更新用户', '', '', 'manager:update', 3, 1, '2020-07-08 13:30:15');
INSERT INTO `system_menu` VALUES (2004, 20, '删除用户', '', '', 'manager:delete', 4, 1, '2020-07-08 13:32:41');
INSERT INTO `system_menu` VALUES (2005, 20, '重置密码', '', '', 'manager:reset', 5, 1, '2020-08-20 10:08:56');
INSERT INTO `system_menu` VALUES (2101, 21, '查看角色', '', '', 'role:view', 1, 1, '2020-07-08 13:30:49');
INSERT INTO `system_menu` VALUES (2102, 21, '创建角色', '', '', 'role:add', 2, 1, '2020-07-08 13:31:21');
INSERT INTO `system_menu` VALUES (2103, 21, '更新角色', '', '', 'role:update', 3, 1, '2020-07-08 13:31:47');
INSERT INTO `system_menu` VALUES (2104, 21, '删除角色', '', '', 'role:delete', 4, 1, '2020-07-08 13:32:15');
INSERT INTO `system_menu` VALUES (2105, 21, '权限分配', '', '', 'role:perms', 5, 1, '2020-08-20 14:15:34');
INSERT INTO `system_menu` VALUES (2201, 22, '查看菜单', '', '', 'menu:view', 1, 1, '2020-07-08 12:42:25');
INSERT INTO `system_menu` VALUES (2202, 22, '创建菜单', '', '', 'menu:add', 2, 1, '2020-07-08 12:43:29');
INSERT INTO `system_menu` VALUES (2203, 22, '更新菜单', '', '', 'menu:update', 3, 1, '2020-07-08 12:44:22');
INSERT INTO `system_menu` VALUES (2204, 22, '删除菜单', '', '', 'menu:delete', 4, 1, '2020-07-08 12:44:47');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `RoleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `RoleCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
  `Remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ModifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`RoleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, '管理员', 'administrator', '管理一切', '2020-07-17 11:46:35', '2020-07-08 17:01:53');
INSERT INTO `system_role` VALUES (2, '游客', 'visitor', '只是看看', '2020-07-19 17:16:39', '2020-07-17 11:21:28');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `RoleId` int(11) NOT NULL COMMENT '角色编号',
  `MenuId` int(11) NOT NULL COMMENT '权限编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色关联的菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES (2, 1);
INSERT INTO `system_role_menu` VALUES (2, 10);
INSERT INTO `system_role_menu` VALUES (2, 3);
INSERT INTO `system_role_menu` VALUES (2, 31);
INSERT INTO `system_role_menu` VALUES (1, 1);
INSERT INTO `system_role_menu` VALUES (1, 10);
INSERT INTO `system_role_menu` VALUES (1, 2);
INSERT INTO `system_role_menu` VALUES (1, 20);
INSERT INTO `system_role_menu` VALUES (1, 2001);
INSERT INTO `system_role_menu` VALUES (1, 2002);
INSERT INTO `system_role_menu` VALUES (1, 2003);
INSERT INTO `system_role_menu` VALUES (1, 2004);
INSERT INTO `system_role_menu` VALUES (1, 2005);
INSERT INTO `system_role_menu` VALUES (1, 21);
INSERT INTO `system_role_menu` VALUES (1, 2101);
INSERT INTO `system_role_menu` VALUES (1, 2102);
INSERT INTO `system_role_menu` VALUES (1, 2103);
INSERT INTO `system_role_menu` VALUES (1, 2104);
INSERT INTO `system_role_menu` VALUES (1, 2105);
INSERT INTO `system_role_menu` VALUES (1, 22);
INSERT INTO `system_role_menu` VALUES (1, 2201);
INSERT INTO `system_role_menu` VALUES (1, 2202);
INSERT INTO `system_role_menu` VALUES (1, 2203);
INSERT INTO `system_role_menu` VALUES (1, 2204);
INSERT INTO `system_role_menu` VALUES (1, 23);
INSERT INTO `system_role_menu` VALUES (1, 3);
INSERT INTO `system_role_menu` VALUES (1, 30);
INSERT INTO `system_role_menu` VALUES (1, 31);
INSERT INTO `system_role_menu` VALUES (1, 32);
INSERT INTO `system_role_menu` VALUES (1, 34);

SET FOREIGN_KEY_CHECKS = 1;
