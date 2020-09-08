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
-- Records of monitor_job_log
-- ----------------------------
INSERT INTO `monitor_job_log` VALUES (1, 3, 'testTask', 'test1', '', 0, NULL, 2, '2020-07-27 15:55:21');
INSERT INTO `monitor_job_log` VALUES (2, 3, 'testTask', 'test1', '', 0, NULL, 2, '2020-08-19 16:29:30');
INSERT INTO `monitor_job_log` VALUES (3, 3, 'testTask', 'test1', '', 0, NULL, 1, '2020-08-19 16:29:40');
INSERT INTO `monitor_job_log` VALUES (4, 3, 'testTask', 'test1', '', 0, NULL, 3, '2020-08-19 16:29:45');
INSERT INTO `monitor_job_log` VALUES (5, 3, 'testTask', 'test1', '', 0, NULL, 4, '2020-08-19 16:29:50');
INSERT INTO `monitor_job_log` VALUES (6, 3, 'testTask', 'test1', '', 0, NULL, 1, '2020-08-19 16:29:55');
INSERT INTO `monitor_job_log` VALUES (7, 1, 'testTask', 'test', '1', 0, NULL, 2, '2020-08-19 16:30:15');
INSERT INTO `monitor_job_log` VALUES (8, 1, 'testTask', 'test', '1', 0, NULL, 1, '2020-08-19 16:30:20');

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
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监控日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_log
-- ----------------------------
INSERT INTO `monitor_log` VALUES (1, 'admin', '执行了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.runJob()', ' jobId: \"3\"', 19, '0:0:0:0:0:0:0:1', '2020-07-27 15:48:00');
INSERT INTO `monitor_log` VALUES (2, 'admin', '执行了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.runJob()', ' jobId: \"3\"', 32, '0:0:0:0:0:0:0:1', '2020-07-27 15:55:21');
INSERT INTO `monitor_log` VALUES (3, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=null, name=监控调度, url=, icon=layui-icon layui-icon-engine, perms=, sort=2, type=0, createTime=null, open=true, disabled=null)\"', 35, '0:0:0:0:0:0:0:1', '2020-07-27 16:10:23');
INSERT INTO `monitor_log` VALUES (4, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=22, parentId=2, name=定时任务, url=/job/view, icon=, perms=, sort=1, type=0, createTime=null, open=true, disabled=null)\"', 6, '0:0:0:0:0:0:0:1', '2020-07-27 16:10:49');
INSERT INTO `monitor_log` VALUES (5, 'admin', '删除了菜单', 'com.unshell.easyshiro.system.controller.MenuController.deleteMenu()', ' id: \"[4, 41]\"', 20, '0:0:0:0:0:0:0:1', '2020-07-27 16:19:46');
INSERT INTO `monitor_log` VALUES (6, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=22, parentId=2, name=定时任务, url=/monitor/job, icon=, perms=, sort=3, type=0, createTime=null, open=true, disabled=null)\"', 25, '0:0:0:0:0:0:0:1', '2020-07-27 16:32:34');
INSERT INTO `monitor_log` VALUES (7, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=23, parentId=2, name=调度日志, url=/monitor/job/log, icon=, perms=, sort=4, type=0, createTime=null, open=true, disabled=null)\"', 16, '0:0:0:0:0:0:0:1', '2020-07-27 16:32:38');
INSERT INTO `monitor_log` VALUES (8, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=31, parentId=3, name=生成配置, url=/configure/generator, icon=, perms=, sort=2, type=0, createTime=null, open=true, disabled=null)\"', 25, '0:0:0:0:0:0:0:1', '2020-07-27 16:45:12');
INSERT INTO `monitor_log` VALUES (9, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login()', '参数内容涉及隐私部分', 112, '0:0:0:0:0:0:0:1', '2020-08-18 11:44:04');
INSERT INTO `monitor_log` VALUES (10, 'admin', '删除了菜单', 'com.unshell.easyshiro.system.controller.MenuController.deleteMenu()', ' id: \"[3, 30, 31]\"', 46, '0:0:0:0:0:0:0:1', '2020-08-18 17:37:37');
INSERT INTO `monitor_log` VALUES (11, 'admin', '创建了菜单', 'com.unshell.easyshiro.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=24, parentId=2, name=代码生成, url=/, icon=, perms=, sort=5, type=0, createTime=Tue Aug 18 17:38:48 CST 2020, open=true, disabled=null)\"', 29, '0:0:0:0:0:0:0:1', '2020-08-18 17:38:49');
INSERT INTO `monitor_log` VALUES (12, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=24, parentId=2, name=代码生成, url=/monitor/generate, icon=, perms=, sort=5, type=0, createTime=null, open=true, disabled=null)\"', 18, '0:0:0:0:0:0:0:1', '2020-08-18 17:40:40');
INSERT INTO `monitor_log` VALUES (13, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login()', '参数内容涉及隐私部分', 102, '0:0:0:0:0:0:0:1', '2020-08-19 14:42:06');
INSERT INTO `monitor_log` VALUES (14, 'admin', '更新了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.updateJob', ' job: \"Job(jobId=3, beanName=testTask, methodName=test1, params=, cronExpression=0/5 * * * * ?, status=1, remark=调度无参方法, createTime=null)\"', 78, '0:0:0:0:0:0:0:1', '2020-08-19 16:29:26');
INSERT INTO `monitor_log` VALUES (15, 'admin', '执行了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.runJob', ' jobId: \"3\"', 30, '0:0:0:0:0:0:0:1', '2020-08-19 16:29:30');
INSERT INTO `monitor_log` VALUES (16, 'admin', '恢复了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.resumeJob', ' jobId: \"3\"', 36, '0:0:0:0:0:0:0:1', '2020-08-19 16:29:39');
INSERT INTO `monitor_log` VALUES (17, 'admin', '停止了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.pauseJob', ' jobId: \"3\"', 39, '0:0:0:0:0:0:0:1', '2020-08-19 16:29:55');
INSERT INTO `monitor_log` VALUES (18, 'admin', '恢复了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.resumeJob', ' jobId: \"1\"', 38, '0:0:0:0:0:0:0:1', '2020-08-19 16:30:14');
INSERT INTO `monitor_log` VALUES (19, 'admin', '停止了定时任务', 'com.unshell.easyshiro.monitor.controller.JobController.pauseJob', ' jobId: \"1\"', 31, '0:0:0:0:0:0:0:1', '2020-08-19 16:30:23');
INSERT INTO `monitor_log` VALUES (20, 'admin', '创建了菜单', 'com.unshell.easyshiro.system.controller.MenuController.addMenu', ' menu: \"Menu(menuId=3, parentId=-1, name=HomePage, url=, icon=, perms=, sort=1, type=0, createTime=Wed Aug 19 16:52:03 CST 2020, open=true, disabled=null)\"', 30, '0:0:0:0:0:0:0:1', '2020-08-19 16:52:03');
INSERT INTO `monitor_log` VALUES (21, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=3, parentId=null, name=HomePage, url=, icon=layui-icon layui-icon-home, perms=, sort=1, type=0, createTime=null, open=true, disabled=null)\"', 16, '0:0:0:0:0:0:0:1', '2020-08-19 16:52:41');
INSERT INTO `monitor_log` VALUES (22, 'admin', '创建了菜单', 'com.unshell.easyshiro.system.controller.MenuController.addMenu', ' menu: \"Menu(menuId=31, parentId=3, name=欢迎页, url=/welcome, icon=, perms=, sort=1, type=1, createTime=Wed Aug 19 16:53:47 CST 2020, open=true, disabled=null)\"', 17, '0:0:0:0:0:0:0:1', '2020-08-19 16:53:47');
INSERT INTO `monitor_log` VALUES (23, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=31, parentId=3, name=欢迎页, url=/welcome, icon=, perms=, sort=1, type=0, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-19 16:53:54');
INSERT INTO `monitor_log` VALUES (24, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=31, parentId=3, name=欢迎页, url=welcome, icon=, perms=, sort=1, type=0, createTime=null, open=true, disabled=null)\"', 28, '0:0:0:0:0:0:0:1', '2020-08-19 16:55:09');
INSERT INTO `monitor_log` VALUES (25, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 7, '0:0:0:0:0:0:0:1', '2020-08-20 09:02:56');
INSERT INTO `monitor_log` VALUES (26, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 84, '0:0:0:0:0:0:0:1', '2020-08-20 09:03:03');
INSERT INTO `monitor_log` VALUES (27, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 8, '0:0:0:0:0:0:0:1', '2020-08-20 09:11:35');
INSERT INTO `monitor_log` VALUES (28, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 93, '0:0:0:0:0:0:0:1', '2020-08-20 09:11:46');
INSERT INTO `monitor_log` VALUES (29, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2, parentId=null, name=监控调度, url=, icon=layui-icon layui-icon-engine, perms=, sort=3, type=0, createTime=null, open=true, disabled=null)\"', 27, '0:0:0:0:0:0:0:1', '2020-08-20 09:48:38');
INSERT INTO `monitor_log` VALUES (30, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=1, parentId=null, name=系统管理, url=, icon=layui-icon layui-icon-senior, perms=, sort=2, type=0, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 09:48:41');
INSERT INTO `monitor_log` VALUES (31, 'admin', '更新了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.updateUser', ' manager: \"Manager(managerId=2, loginName=null, password=null, nickName=亚历克斯, avatar=null, phone=12345678910, email=alex@qq.com, address=null, status=null, tags=null, signature=null, roleId=2, introduction=null, modifyTime=Thu Aug 20 10:00:43 CST 2020, createTime=null)\"', 33, '0:0:0:0:0:0:0:1', '2020-08-20 10:00:43');
INSERT INTO `monitor_log` VALUES (32, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=1003, parentId=10, name=修改用户, url=, icon=, perms=manager:update, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 16, '0:0:0:0:0:0:0:1', '2020-08-20 10:02:32');
INSERT INTO `monitor_log` VALUES (33, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=1102, parentId=11, name=添加角色, url=, icon=, perms=role:add, sort=2, type=1, createTime=null, open=true, disabled=null)\"', 15, '0:0:0:0:0:0:0:1', '2020-08-20 10:02:44');
INSERT INTO `monitor_log` VALUES (34, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=1103, parentId=11, name=修改角色, url=, icon=, perms=role:update, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 13, '0:0:0:0:0:0:0:1', '2020-08-20 10:02:51');
INSERT INTO `monitor_log` VALUES (35, 'admin', '创建了菜单', 'com.unshell.easyshiro.system.controller.MenuController.addMenu', ' menu: \"Menu(menuId=1005, parentId=10, name=重置密码, url=, icon=, perms=manager:reset, sort=5, type=0, createTime=Thu Aug 20 10:08:55 CST 2020, open=true, disabled=null)\"', 17, '0:0:0:0:0:0:0:1', '2020-08-20 10:08:56');
INSERT INTO `monitor_log` VALUES (36, 'admin', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=1005, parentId=10, name=重置密码, url=, icon=, perms=manager:reset, sort=5, type=1, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 10:09:01');
INSERT INTO `monitor_log` VALUES (37, 'admin', '更新了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.updateUser', ' manager: \"Manager(managerId=2, loginName=null, password=null, nickName=亚历克斯, avatar=null, phone=12345678910, email=alex@qq.com, address=null, status=null, tags=null, signature=null, roleId=2, introduction=null, modifyTime=Thu Aug 20 10:24:35 CST 2020, createTime=null)\"', 19, '0:0:0:0:0:0:0:1', '2020-08-20 10:24:35');
INSERT INTO `monitor_log` VALUES (38, 'admin', '创建了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.insertUser', '参数内容涉及隐私部分', 21, '0:0:0:0:0:0:0:1', '2020-08-20 10:30:43');
INSERT INTO `monitor_log` VALUES (39, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 1, '0:0:0:0:0:0:0:1', '2020-08-20 10:31:00');
INSERT INTO `monitor_log` VALUES (40, 'delete', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 44, '0:0:0:0:0:0:0:1', '2020-08-20 10:32:25');
INSERT INTO `monitor_log` VALUES (41, 'delete', '更新了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.resetPassword', ' managerId: \"3\"', 17, '0:0:0:0:0:0:0:1', '2020-08-20 10:33:16');
INSERT INTO `monitor_log` VALUES (42, 'delete', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 0, '0:0:0:0:0:0:0:1', '2020-08-20 10:33:19');
INSERT INTO `monitor_log` VALUES (43, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2101, parentId=21, name=查看角色, url=, icon=, perms=role:view, sort=1, type=1, createTime=null, open=true, disabled=null)\"', 24, '0:0:0:0:0:0:0:1', '2020-08-20 14:10:42');
INSERT INTO `monitor_log` VALUES (44, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2002, parentId=20, name=创建用户, url=, icon=, perms=manager:add, sort=2, type=1, createTime=null, open=true, disabled=null)\"', 18, '0:0:0:0:0:0:0:1', '2020-08-20 14:10:52');
INSERT INTO `monitor_log` VALUES (45, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2003, parentId=20, name=更新用户, url=, icon=, perms=manager:update, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:03');
INSERT INTO `monitor_log` VALUES (46, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2102, parentId=21, name=创建角色, url=, icon=, perms=role:add, sort=2, type=1, createTime=null, open=true, disabled=null)\"', 15, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:10');
INSERT INTO `monitor_log` VALUES (47, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2103, parentId=21, name=更新角色, url=, icon=, perms=role:update, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 15, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:15');
INSERT INTO `monitor_log` VALUES (48, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2202, parentId=22, name=创建菜单, url=, icon=, perms=menu:add, sort=2, type=1, createTime=null, open=true, disabled=null)\"', 12, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:25');
INSERT INTO `monitor_log` VALUES (49, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2203, parentId=22, name=更新菜单, url=, icon=, perms=menu:edit, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:31');
INSERT INTO `monitor_log` VALUES (50, 'delete', '更新了菜单', 'com.unshell.easyshiro.system.controller.MenuController.updateMenu', ' menu: \"Menu(menuId=2203, parentId=22, name=更新菜单, url=, icon=, perms=menu:update, sort=3, type=1, createTime=null, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 14:11:40');
INSERT INTO `monitor_log` VALUES (51, 'delete', '创建了菜单', 'com.unshell.easyshiro.system.controller.MenuController.addMenu', ' menu: \"Menu(menuId=2105, parentId=21, name=权限分配, url=, icon=, perms=role:perms, sort=5, type=1, createTime=Thu Aug 20 14:15:34 CST 2020, open=true, disabled=null)\"', 14, '0:0:0:0:0:0:0:1', '2020-08-20 14:15:34');
INSERT INTO `monitor_log` VALUES (52, 'delete', '删除了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.deleteUser', ' id: null', 11, '0:0:0:0:0:0:0:1', '2020-08-20 14:29:44');
INSERT INTO `monitor_log` VALUES (53, 'delete', '删除了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.deleteUser', ' id: \"3\"', 22, '0:0:0:0:0:0:0:1', '2020-08-20 14:31:24');
INSERT INTO `monitor_log` VALUES (54, 'delete', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 1, '0:0:0:0:0:0:0:1', '2020-08-20 14:31:46');
INSERT INTO `monitor_log` VALUES (55, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 50, '0:0:0:0:0:0:0:1', '2020-08-20 14:31:53');
INSERT INTO `monitor_log` VALUES (56, 'admin', '删除了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.deleteUser', ' id: \"1\"', 7, '0:0:0:0:0:0:0:1', '2020-08-20 14:38:46');
INSERT INTO `monitor_log` VALUES (57, 'admin', '删除了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.deleteUser', ' id: \"2\"', 7, '0:0:0:0:0:0:0:1', '2020-08-20 14:39:32');
INSERT INTO `monitor_log` VALUES (58, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"57\"', 20, '0:0:0:0:0:0:0:1', '2020-08-20 14:55:39');
INSERT INTO `monitor_log` VALUES (59, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=68, groupId=null, dictName=字典组测试, sign=null, dictKey=test, dictValue=null, isGroup=true, sort=2, remark=null)', 12, '0:0:0:0:0:0:0:1', '2020-08-20 14:56:10');
INSERT INTO `monitor_log` VALUES (60, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=69, groupId=68, dictName=第一项, sign=test&first, dictKey=first, dictValue=10, isGroup=false, sort=1, remark=)', 27, '0:0:0:0:0:0:0:1', '2020-08-20 14:57:22');
INSERT INTO `monitor_log` VALUES (61, 'admin', '创建了字典项', 'com.unshell.easyshiro.system.controller.DictionaryController.dictDelete', ' dictId: \"[69]\"', 12, '0:0:0:0:0:0:0:1', '2020-08-20 14:57:31');
INSERT INTO `monitor_log` VALUES (62, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=70, groupId=68, dictName=第一项, sign=test&first, dictKey=first, dictValue=1230, isGroup=false, sort=1, remark=)', 23, '0:0:0:0:0:0:0:1', '2020-08-20 15:00:20');
INSERT INTO `monitor_log` VALUES (63, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"68\"', 17, '0:0:0:0:0:0:0:1', '2020-08-20 15:00:23');
INSERT INTO `monitor_log` VALUES (64, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=71, groupId=null, dictName=阿大, sign=null, dictKey=123, dictValue=null, isGroup=true, sort=1, remark=null)', 24, '0:0:0:0:0:0:0:1', '2020-08-20 15:01:41');
INSERT INTO `monitor_log` VALUES (65, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"71\"', 18, '0:0:0:0:0:0:0:1', '2020-08-20 15:01:47');
INSERT INTO `monitor_log` VALUES (66, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=72, groupId=null, dictName=阿大, sign=null, dictKey=123, dictValue=null, isGroup=true, sort=1, remark=null)', 24, '0:0:0:0:0:0:0:1', '2020-08-20 15:02:17');
INSERT INTO `monitor_log` VALUES (67, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"72\"', 17, '0:0:0:0:0:0:0:1', '2020-08-20 15:02:22');
INSERT INTO `monitor_log` VALUES (68, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=73, groupId=null, dictName=阿大, sign=null, dictKey=qwe, dictValue=null, isGroup=true, sort=1, remark=null)', 22, '0:0:0:0:0:0:0:1', '2020-08-20 15:03:37');
INSERT INTO `monitor_log` VALUES (69, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"73\"', 16, '0:0:0:0:0:0:0:1', '2020-08-20 15:03:44');
INSERT INTO `monitor_log` VALUES (70, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=74, groupId=null, dictName=搜索测试, sign=null, dictKey=source, dictValue=null, isGroup=true, sort=1, remark=null)', 10, '0:0:0:0:0:0:0:1', '2020-08-20 15:04:37');
INSERT INTO `monitor_log` VALUES (71, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=75, groupId=74, dictName=第一项, sign=source&qweuiyi, dictKey=qweuiyi, dictValue=asda , isGroup=false, sort=1, remark=)', 37, '0:0:0:0:0:0:0:1', '2020-08-20 15:08:28');
INSERT INTO `monitor_log` VALUES (72, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=76, groupId=74, dictName=第二项, sign=source&onhu, dictKey=onhu, dictValue=80, isGroup=false, sort=2, remark=)', 10, '0:0:0:0:0:0:0:1', '2020-08-20 15:08:42');
INSERT INTO `monitor_log` VALUES (73, 'admin', '删除了字典组', 'com.unshell.easyshiro.system.controller.DictionaryController.groupDelete', ' dictId: \"74\"', 22, '0:0:0:0:0:0:0:1', '2020-08-20 15:14:03');
INSERT INTO `monitor_log` VALUES (74, 'admin', '创建了字典', 'com.unshell.easyshiro.system.controller.DictionaryController.dictAdd', ' dictionary: Dictionary(dictId=77, groupId=1, dictName=版权, sign=system&copyright, dictKey=copyright, dictValue=easyweb.vip, isGroup=false, sort=2, remark=后台框架版权)', 13, '0:0:0:0:0:0:0:1', '2020-08-20 15:17:12');
INSERT INTO `monitor_log` VALUES (75, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 85, '0:0:0:0:0:0:0:1', '2020-08-20 15:50:08');
INSERT INTO `monitor_log` VALUES (76, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 101, '0:0:0:0:0:0:0:1', '2020-09-07 15:59:36');
INSERT INTO `monitor_log` VALUES (77, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 99, '0:0:0:0:0:0:0:1', '2020-09-07 16:20:12');
INSERT INTO `monitor_log` VALUES (78, 'admin', '重置了系统用户密码', 'com.unshell.easyshiro.system.controller.ManagerController.resetPassword', ' managerId: \"1\"', 21, '0:0:0:0:0:0:0:1', '2020-09-07 16:20:25');
INSERT INTO `monitor_log` VALUES (79, 'admin', '重置了系统用户密码', 'com.unshell.easyshiro.system.controller.ManagerController.resetPassword', ' managerId: \"2\"', 19, '0:0:0:0:0:0:0:1', '2020-09-07 16:20:35');
INSERT INTO `monitor_log` VALUES (80, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 1, '0:0:0:0:0:0:0:1', '2020-09-07 16:21:03');
INSERT INTO `monitor_log` VALUES (81, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 24, '0:0:0:0:0:0:0:1', '2020-09-07 16:21:12');
INSERT INTO `monitor_log` VALUES (82, '', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 8, '0:0:0:0:0:0:0:1', '2020-09-07 17:31:23');
INSERT INTO `monitor_log` VALUES (83, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 64, '0:0:0:0:0:0:0:1', '2020-09-07 17:31:29');
INSERT INTO `monitor_log` VALUES (84, '', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 7, '0:0:0:0:0:0:0:1', '2020-09-07 17:46:04');
INSERT INTO `monitor_log` VALUES (85, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 50, '0:0:0:0:0:0:0:1', '2020-09-07 17:46:17');
INSERT INTO `monitor_log` VALUES (86, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 6, '0:0:0:0:0:0:0:1', '2020-09-07 17:50:59');
INSERT INTO `monitor_log` VALUES (87, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 69, '0:0:0:0:0:0:0:1', '2020-09-07 17:51:12');
INSERT INTO `monitor_log` VALUES (88, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 20, '0:0:0:0:0:0:0:1', '2020-09-08 09:19:34');
INSERT INTO `monitor_log` VALUES (89, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 85, '0:0:0:0:0:0:0:1', '2020-09-08 09:19:41');
INSERT INTO `monitor_log` VALUES (90, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 6, '0:0:0:0:0:0:0:1', '2020-09-08 09:22:17');
INSERT INTO `monitor_log` VALUES (91, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 68, '0:0:0:0:0:0:0:1', '2020-09-08 09:22:24');
INSERT INTO `monitor_log` VALUES (92, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 6, '0:0:0:0:0:0:0:1', '2020-09-08 09:24:04');
INSERT INTO `monitor_log` VALUES (93, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 77, '0:0:0:0:0:0:0:1', '2020-09-08 09:24:13');
INSERT INTO `monitor_log` VALUES (94, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 1, '0:0:0:0:0:0:0:1', '2020-09-08 09:27:43');
INSERT INTO `monitor_log` VALUES (95, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 18, '0:0:0:0:0:0:0:1', '2020-09-08 09:27:51');
INSERT INTO `monitor_log` VALUES (96, 'admin', '更新了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.updateUser', ' manager: \"Manager(managerId=1, loginName=null, password=null, nickName=unshell, avatar=null, phone=14777777777, email=1548462908@qq.com, address=null, status=null, signature=null, roleId=1, introduction=null, modifyTime=null, createTime=null)\"', 13, '0:0:0:0:0:0:0:1', '2020-09-08 09:28:48');
INSERT INTO `monitor_log` VALUES (97, 'admin', '更新了系统用户', 'com.unshell.easyshiro.system.controller.ManagerController.updateUser', ' manager: \"Manager(managerId=2, loginName=null, password=null, nickName=亚历克斯, avatar=null, phone=13777777777, email=alex@qq.com, address=null, status=null, signature=null, roleId=2, introduction=null, modifyTime=null, createTime=null)\"', 12, '0:0:0:0:0:0:0:1', '2020-09-08 09:32:54');
INSERT INTO `monitor_log` VALUES (98, 'admin', '退出了系统', 'com.unshell.easyshiro.system.controller.ViewController.logoutSystem', '', 6, '0:0:0:0:0:0:0:1', '2020-09-08 09:34:40');
INSERT INTO `monitor_log` VALUES (99, 'admin', '登录了系统', 'com.unshell.easyshiro.monitor.controller.LoginController.login', '参数内容涉及隐私部分', 47, '0:0:0:0:0:0:0:1', '2020-09-08 09:34:46');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_login_log
-- ----------------------------
INSERT INTO `monitor_login_log` VALUES (1, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-18 11:44:04');
INSERT INTO `monitor_login_log` VALUES (2, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-19 14:42:06');
INSERT INTO `monitor_login_log` VALUES (3, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 09:03:03');
INSERT INTO `monitor_login_log` VALUES (4, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 09:11:46');
INSERT INTO `monitor_login_log` VALUES (5, 'delete', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 10:32:25');
INSERT INTO `monitor_login_log` VALUES (6, 'delete', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 10:33:34');
INSERT INTO `monitor_login_log` VALUES (7, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 14:31:53');
INSERT INTO `monitor_login_log` VALUES (8, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-08-20 15:50:08');
INSERT INTO `monitor_login_log` VALUES (9, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 15:59:36');
INSERT INTO `monitor_login_log` VALUES (10, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 16:20:12');
INSERT INTO `monitor_login_log` VALUES (11, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 16:21:12');
INSERT INTO `monitor_login_log` VALUES (12, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 17:31:29');
INSERT INTO `monitor_login_log` VALUES (13, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 17:46:17');
INSERT INTO `monitor_login_log` VALUES (14, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-07 17:51:12');
INSERT INTO `monitor_login_log` VALUES (15, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-08 09:19:41');
INSERT INTO `monitor_login_log` VALUES (16, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-08 09:22:24');
INSERT INTO `monitor_login_log` VALUES (17, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-08 09:24:13');
INSERT INTO `monitor_login_log` VALUES (18, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-08 09:27:51');
INSERT INTO `monitor_login_log` VALUES (19, 'admin', 'Windows 10', 'Chrome 80', '127.0.0.1', '2020-09-08 09:34:46');

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
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
INSERT INTO `system_dictionary` VALUES (1, NULL, '系统', 'system', 'system', NULL, b'1', 1, NULL);
INSERT INTO `system_dictionary` VALUES (67, 1, '版本', 'system&release', 'release', '3.1.8', b'0', 1, '系统版本号');
INSERT INTO `system_dictionary` VALUES (77, 1, '版权', 'system&copyright', 'copyright', 'easyweb.vip', b'0', 2, '后台框架版权');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_manager
-- ----------------------------
INSERT INTO `system_manager` VALUES (1, 'admin', '0ce3e25de5134d7b0ddfd94a49675bf5', 'unshell', 'boy-01.jpg', '14777777777', '1548462908@qq.com', '常山真定', 0, '7ec9f640c0f349aeb046490f15d7a919', 1, '我相信所有坚韧不拔的努力，迟早会得来好报酬。', '2020-07-17 11:24:12', '2020-07-08 17:06:49', NULL);
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
INSERT INTO `system_menu` VALUES (33, 3, '调度日志', '/monitor/job/log', '', '', 4, 0, '2020-07-09 17:09:01');
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

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
INSERT INTO `system_role_menu` VALUES (1, 33);
INSERT INTO `system_role_menu` VALUES (1, 34);

SET FOREIGN_KEY_CHECKS = 1;
