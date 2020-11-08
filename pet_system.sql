

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `uk_admin_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员表';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
BEGIN;
INSERT INTO `admin_user` VALUES (1, 'admin', 'c41d7c66e1b8404545aa3a0ece2006ac', '/resources/images/default-avatar.jpg', '2020-10-29 12:39:57', '2020-11-02 11:50:16');
COMMIT;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '关注者ID',
  `fid` int(11) DEFAULT NULL COMMENT '被关注者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `node_code` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '节点编码',
  `node_title` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '节点名称',
  `avatar_normal` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点头像',
  `avatar_mini` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点小头像',
  `avatar_large` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点背景头像',
  `node_desc` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点描述',
  `tab_id` int(11) DEFAULT NULL COMMENT '板块ID',
  `url` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'url',
  `parent_node_code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '父节点',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0:否 1:是',
  `add_index` tinyint(1) DEFAULT NULL COMMENT '添加到首页',
  `add_nav` tinyint(1) DEFAULT NULL COMMENT '添加到导航栏',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`node_id`),
  KEY `key_node_code` (`node_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='节点表';

-- ----------------------------
-- Records of node
-- ----------------------------
BEGIN;
INSERT INTO `node` VALUES (1, 'Dog', 'Dog', '/resources/images/node/java_normal.png', '/resources/images/node/java_mini.png', NULL, 'Dog', 14, '/n/Dog', 'Dog', '2020-11-03 11:50:18', '2020-11-06 23:48:45', 0, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for node_tab
-- ----------------------------
DROP TABLE IF EXISTS `node_tab`;
CREATE TABLE `node_tab` (
  `node_tab_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点板块id',
  `node_tab_code` varchar(45) DEFAULT NULL COMMENT '节点板块编码',
  `node_tab_title` varchar(45) DEFAULT NULL COMMENT '节点板块名称',
  `node_tab_desc` varchar(300) DEFAULT NULL COMMENT '板块描述',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除，0:否 1:是',
  `node_tab_order` int(11) DEFAULT NULL COMMENT '节点板块排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`node_tab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='节点板块表';

-- ----------------------------
-- Records of node_tab
-- ----------------------------
BEGIN;
INSERT INTO `node_tab` VALUES (1, 'all', 'all', '', 0, 1, '2020-11-03 11:50:18', NULL);
INSERT INTO `node_tab` VALUES (2, 'good', 'good', '', 0, 2, '2020-11-03 11:50:18', NULL);
INSERT INTO `node_tab` VALUES (3, 'newest', 'newest', '', 0, 3, '2020-11-03 11:50:18', NULL);
INSERT INTO `node_tab` VALUES (4, 'noReply', 'no reply', '', 0, 4, '2020-11-03 11:50:18', NULL);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息通知标识',
  `notice_title` varchar(200) DEFAULT '' COMMENT '通知标题',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读：0:默认 1:已读',
  `notice_author_id` int(11) DEFAULT NULL COMMENT '发起通知用户id',
  `notice_author_name` varchar(50) NOT NULL DEFAULT '' COMMENT '发起通知用户昵称',
  `target_author_id` int(11) DEFAULT NULL COMMENT '要通知用户id',
  `target_author_name` varchar(50) NOT NULL DEFAULT '' COMMENT '要通知用户的昵称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '阅读时间',
  `notice_action` varchar(255) NOT NULL DEFAULT '' COMMENT '通知动作',
  `topic_id` int(11) DEFAULT NULL COMMENT '话题id',
  `topic_author_id` int(11) DEFAULT NULL COMMENT '话题作者id',
  `topic_section_id` int(11) DEFAULT NULL COMMENT '话题板块id',
  `notice_content` longtext COMMENT '通知内容',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '通知状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复标识',
  `topic_id` int(11) DEFAULT NULL COMMENT '话题id',
  `topic_author_id` int(11) DEFAULT NULL COMMENT '话题作者id',
  `reply_content` longtext COMMENT '回复内容',
  `create_date` datetime DEFAULT NULL COMMENT '回复时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `reply_author_id` int(11) DEFAULT NULL COMMENT '当前回复用户id',
  `reply_author_name` varchar(50) DEFAULT NULL COMMENT '当前回复用户昵称',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除 0:默认 1:删除',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读 0:默认 1:未读',
  `is_show` tinyint(1) DEFAULT '0' COMMENT '是否可见 0:默认 1:不可见',
  `reply_good_count` int(10) DEFAULT '0' COMMENT '点赞',
  `reply_bad_count` int(10) DEFAULT '0' COMMENT '踩数',
  `reply_type` varchar(16) DEFAULT NULL COMMENT '回复类型',
  `reply_read_count` int(11) DEFAULT NULL COMMENT '回复阅读数量',
  `status_cd` varchar(4) DEFAULT '1000' COMMENT '回复状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`reply_id`),
  KEY `key_topic_id` (`topic_id`),
  KEY `key_reply_author_name` (`reply_author_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='回复表';

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `system_config_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` varchar(1000) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `option` varchar(255) DEFAULT NULL,
  `reboot` int(11) NOT NULL DEFAULT '0',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除 0: 否 1: 是',
  PRIMARY KEY (`system_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config
-- ----------------------------
BEGIN;
INSERT INTO `system_config` VALUES (1, NULL, NULL, 'Basic Settings', 0, '', NULL, 0, 0);
INSERT INTO `system_config` VALUES (2, NULL, NULL, 'Upload Settings', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (3, NULL, NULL, 'email Settings', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (4, NULL, NULL, 'point Settings', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (5, NULL, NULL, 'Redis', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (6, NULL, NULL, 'Elasticsearch Settings', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (7, NULL, NULL, 'Github Settings，<a href=\"https://github.com/settings/developers\" target=\"_blank\">Address</a>', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (8, NULL, NULL, 'WebSocket Settings', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (9, 'base_url', 'http://localhost:8080', 'for deployment', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (10, 'site_name', 'PetGo', 'Site name', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (11, 'site_intro', 'PetGo', 'Site Intro', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (12, 'cookie_name', 'user', 'cookie name', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (13, 'cookie_max_age', '2592000', 'cookie max time', 1, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (14, 'cookie_domain', 'localhost', '', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (15, 'cookie_path', '/', 'cookie', 1, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (16, 'page_size', '5', ' ', 1, 'number', NULL, 0, 1);
INSERT INTO `system_config` VALUES (17, 'local_upload_filedir', 'file:F:/upload/', '', 30, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (18, 'local_upload_user_filedir', 'file:F:/upload/roothub/user/', 'deployment', 30, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (19, 'local_upload_node_filedir', 'file:F:/upload/roothub/node/', 'deployment', 30, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (20, 'local_upload_tag_filedir', 'file:F:/upload/roothub/tag/', 'deployment', 30, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (21, 'redis_host', '127.0.0.1', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (22, 'redis_port', '6379', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (23, 'redis_password', '', 'redis', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (24, 'redis_timeout', '2000', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (25, 'redis_max_idle', '20', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (26, 'redis_max_total', '50', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (27, 'redis_database', '0', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (28, 'redis_ssl', '0', '', 5, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (29, 'default_upload', '1', '', 2, 'radio', NULL, 0, 0);
INSERT INTO `system_config` VALUES (30, 'local_upload', '0', '', 2, 'radio', NULL, 0, 0);
INSERT INTO `system_config` VALUES (31, 'static_url', '/static/**', '', 30, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (32, 'accessKeyId', '', '', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (33, 'accessKeySecret', '', 'AccessKeySecret', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (34, 'endpoint', 'http://oss-cn-shenzhen.aliyuncs.com', 'Endpoint', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (35, 'bucketName', 'roothub', 'BucketName', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (36, 'oss_filedir', 'images/', '', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (37, 'oss_static_url', ' ', 45, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (38, 'open_redis', '1', '', 5, 'radio', NULL, 0, 0);
INSERT INTO `system_config` VALUES (39, 'create_topic_score', '10', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (40, 'create_reply_score', '5', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (41, 'delete_topic_score', '10', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (42, 'delete_reply_score', '5', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (43, 'up_topic_score', '3', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (44, 'up_reply_score', '3', '', 4, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (45, 'oss_upload', '0', '', 2, 'radio', NULL, 0, 0);
INSERT INTO `system_config` VALUES (46, 'default_upload_filedir', '/upload/', '', 29, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (47, 'static_url', '/static/**', '', 29, 'text', NULL, 0, 0);
INSERT INTO `system_config` VALUES (48, NULL, NULL, '', 0, NULL, NULL, 0, 0);
INSERT INTO `system_config` VALUES (49, 'index_page_size', '5', 'HomePage', 48, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (50, 'node_page_size', '5', '', 48, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (51, 'tag_page_size', '5', '', 48, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (52, 'user_page_size', '5', '', 48, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (53, 'search_page_size', '5', '', 48, 'number', NULL, 0, 0);
INSERT INTO `system_config` VALUES (54, 'default_upload_node_filedir', '/upload/node/', '', 29, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (55, 'default_upload_tag_filedir', '/upload/tag/', '', 29, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (56, 'default_upload_user_filedir', '/upload/user/', '', 29, 'text', NULL, 0, 1);
INSERT INTO `system_config` VALUES (57, 'upload_type', '29', '', 2, 'hidden', NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for tab
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `tab_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `tab_desc` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0：否 1：是',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `tab_order` int(2) DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='父板块表';

-- ----------------------------
-- Records of tab
-- ----------------------------
BEGIN;
INSERT INTO `tab` VALUES (1, 'all', 'all', 0, '2020-10-25 15:39:48', 1);

COMMIT;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `ptab` varchar(50) DEFAULT NULL COMMENT '父板块',
  `tab` varchar(50) DEFAULT NULL COMMENT '版块',
  `title` varchar(250) NOT NULL COMMENT '标题',
  `tag` varchar(250) DEFAULT NULL COMMENT '标签',
  `content` longtext COMMENT '正文',
  `excerpt` varchar(500) DEFAULT NULL COMMENT '摘录',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `last_reply_time` datetime DEFAULT NULL COMMENT '最后回复时间，用于排序',
  `last_reply_author` varchar(50) DEFAULT NULL COMMENT '最后回复用户',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览量',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `top` tinyint(1) DEFAULT '0' COMMENT '1置顶 0默认',
  `good` tinyint(1) DEFAULT '0' COMMENT '1精华 0默认',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '1显示 0不显示',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '1删除 0默认',
  `tag_is_count` int(1) DEFAULT '0' COMMENT '文章内容标签是否被统计过 1是 0否默认',
  `post_good_count` int(10) DEFAULT '0' COMMENT '点赞',
  `post_bad_count` int(10) DEFAULT '0' COMMENT '踩数',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '文章状态 1000:有效 1100:无效 1200:未生效',
  `node_slug` varchar(50) DEFAULT NULL COMMENT '节点编码',
  `node_title` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `avatar` varchar(250) DEFAULT NULL COMMENT '头图',
  `url` varchar(250) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`topic_id`),
  KEY `key_tab` (`tab`),
  KEY `key_author` (`author`),
  KEY `key_node_title` (`node_title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='帖子表';

-- ----------------------------
-- Records of topic
-- ----------------------------
BEGIN;
INSERT INTO `topic` VALUES (1, NULL, NULL, 'abc', NULL, '<p>abc</p>', NULL, '2020-10-22 13:00:22', '020-10-22 12:14:54', NULL, NULL, 1, 'jiangshan', 1, 1, 1, 0, 0, 1, NULL, NULL, '', NULL, 'Java', NULL, '/resources/images/default-avatar.jpg', NULL);
COMMIT;

-- ----------------------------
-- Table structure for up_down
-- ----------------------------
DROP TABLE IF EXISTS `up_down`;
CREATE TABLE `up_down` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `up_down` tinyint(1) DEFAULT NULL COMMENT '0:down 1:up',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `user_name` varchar(50) NOT NULL COMMENT '昵称',
  `password` varchar(250) NOT NULL COMMENT '密码',
  `user_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `user_addr` varchar(250) DEFAULT NULL COMMENT '地址',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `url` varchar(255) DEFAULT NULL COMMENT '个人主页',
  `signature` varchar(1000) DEFAULT NULL COMMENT '个性签名',
  `third_id` varchar(50) DEFAULT NULL COMMENT '第三方账户id',
  `receive_msg` tinyint(1) DEFAULT NULL COMMENT '邮箱是否接收社区消息 0:默认 1:不接收',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_block` tinyint(1) DEFAULT NULL COMMENT '用户是否禁用 0:默认 1:禁用',
  `third_access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '用户状态 1000:有效 1100:无效 1200:未生效',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '用户登入ip',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '用户最后登入ip',
  `user_type` int(2) DEFAULT NULL COMMENT '用户类型 0:超级管理员 1:版主 2:普通用户',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UNIQUE_NAME` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'jiangshan', '$2a$10$IbaB5bBM6J9Q6tJABB2V1eEW5GBWrwlrV9CzaY/zChe85N92idvfq', NULL, NULL, 30, '/resources/images/default-avatar.jpg', '847064370@qq.com', NULL, 'This is admin', NULL, 0, '2020-11-02 11:00:11', NULL, 0, 'd4749dbfb75247feb338a72b928b26d1', '1000', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (2, 'chao', '$2a$10$EAESKMBg0riXeX6pKKmuR.X3ZsyvCoYVcG.LE2dRaOpuClOOhubKC', NULL, NULL, 20, '/resources/images/default-avatar.jpg', '303695336@qq.com', NULL, 'This is admin', NULL, 0, '2020-11-02 13:06:21', NULL, 0, 'c72fc6f818444d44a8665c294b130b39', NULL, NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (3, 'junyu', '$2a$10$EAESKMBg0riXeX6pKKmuR.X3ZsyvCoYVcG.LE2dRaOpuClOOhubKC', NULL, NULL, 20, '/resources/images/default-avatar.jpg', '11@qq.com', NULL, 'This is admin', NULL, 0, '2020-11-02 16:32:12', NULL, 0, 'c72fc6f818444d44a8665c294b130b39', NULL, NULL, NULL, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for visit
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '访问者ID',
  `vid` int(11) DEFAULT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问记录表';

SET FOREIGN_KEY_CHECKS = 1;
