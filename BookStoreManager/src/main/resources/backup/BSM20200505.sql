/*
MySQL Backup
Source Server Version: 8.0.18
Source Database: bookmanager
Date: 2020/5/5 13:51:48
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `booksimpleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `booksimpleinfo`;
CREATE TABLE `booksimpleinfo`
(
    `id`           int(10) unsigned NOT NULL AUTO_INCREMENT,
    `isbn`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `name`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `title`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `author`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tauthorize`
-- ----------------------------
DROP TABLE IF EXISTS `tauthorize`;
CREATE TABLE `tauthorize`
(
    `id`            varchar(36) NOT NULL,
    `authorizepage` varchar(100) DEFAULT NULL,
    `roleid`        int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `roleid` (`roleid`),
    KEY             `authorizepage` (`authorizepage`),
    CONSTRAINT `tauthorize_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `trole` (`roleid`),
    CONSTRAINT `tauthorize_ibfk_2` FOREIGN KEY (`authorizepage`) REFERENCES `tmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `tbook`
-- ----------------------------
DROP TABLE IF EXISTS `tbook`;
CREATE TABLE `tbook`
(
    `code`              varchar(32) NOT NULL,
    `id`                int(11) unsigned NOT NULL AUTO_INCREMENT,
    `isbn`              varchar(32) NOT NULL,
    `splitIsbn`         varchar(36)  DEFAULT NULL,
    `name`              varchar(128) DEFAULT NULL,
    `englishName`       varchar(128) DEFAULT NULL,
    `title`             varchar(128) DEFAULT NULL,
    `seriesName`        varchar(128) DEFAULT NULL,
    `copiesCount`       varchar(128) DEFAULT NULL,
    `cip`               varchar(128) DEFAULT NULL,
    `clc`               varchar(32)  DEFAULT NULL,
    `author`            varchar(32)  DEFAULT NULL,
    `introduction`      text,
    `content`           text,
    `publisher`         varchar(128) DEFAULT NULL,
    `publishingTime`    varchar(32)  DEFAULT NULL,
    `publishingAddress` varchar(128) DEFAULT NULL,
    `edition`           varchar(32)  DEFAULT NULL,
    `print`             varchar(32)  DEFAULT NULL,
    `score`             varchar(32)  DEFAULT NULL,
    `translate`         varchar(32)  DEFAULT NULL,
    `editor`            varchar(32)  DEFAULT NULL,
    `illustrator`       varchar(32)  DEFAULT NULL,
    `pageCount`         varchar(32)  DEFAULT NULL,
    `folio`             varchar(32)  DEFAULT NULL,
    `size`              varchar(32)  DEFAULT NULL,
    `weight`            varchar(32)  DEFAULT NULL,
    `price`             varchar(32)  DEFAULT NULL,
    `image`             text,
    PRIMARY KEY (`id`, `code`, `isbn`),
    KEY                 `Book_name_index` (`name`) USING BTREE,
    KEY                 `clc` (`clc`),
    CONSTRAINT `tbook_ibfk_1` FOREIGN KEY (`clc`) REFERENCES `ttag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='书本表';

-- ----------------------------
--  Table structure for `tmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tmenu`;
CREATE TABLE `tmenu`
(
    `id`      varchar(36) NOT NULL,
    `pid`     varchar(36)                                                   DEFAULT NULL,
    `text`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `iconCls` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `url`     varchar(200)                                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY       `id` (`id`),
    KEY       `FK_Tmenu_id` (`pid`),
    CONSTRAINT `FK_Tmenu_id` FOREIGN KEY (`pid`) REFERENCES `tmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `trole`
-- ----------------------------
DROP TABLE IF EXISTS `trole`;
CREATE TABLE `trole`
(
    `roleid`   int(11) NOT NULL,
    `rolename` varchar(36)  DEFAULT NULL,
    `remark`   varchar(100) DEFAULT NULL,
    `excol`    varchar(200) DEFAULT NULL,
    `disabled` int(11) NOT NULL,
    PRIMARY KEY (`roleid`),
    KEY        `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Table structure for `ttag`
-- ----------------------------
DROP TABLE IF EXISTS `ttag`;
CREATE TABLE `ttag`
(
    `id`   varchar(20) NOT NULL,
    `name` varchar(50) NOT NULL COMMENT '标签名',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
--  Table structure for `tuser`
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser`
(
    `id`             varchar(36)                                                  NOT NULL,
    `name`           varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `pwd`            varchar(255) DEFAULT NULL,
    `createdatetime` datetime     DEFAULT NULL,
    `lastmodifytime` datetime     DEFAULT NULL,
    `roleid`         int(11) DEFAULT NULL,
    `userlog`        varchar(256) DEFAULT NULL,
    PRIMARY KEY (`name`),
    KEY              `index_name` (`name`),
    KEY              `roleid` (`roleid`),
    CONSTRAINT `tuser_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `trole` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `booksimpleinfo`
VALUES ('1', '1', '123', '1234', 'gzc', 'Linux is very good.Linux是一个很好的操作系统.');
INSERT INTO `tauthorize`
VALUES ('066db6b3-9fef-4100-b88c-4764664ca24d', 'xxxg', '0'),
       ('0e4e4626-1913-49f1-88d3-cd452832566d', '0', '0'),
       ('1ff9a85b-b59a-46ca-ae0e-d1c823c1b266', 'tszq', '0'),
       ('27d15953-51d5-4c39-bf19-ddf648e2f5b0', 'xtgl', '0'),
       ('32cf38b0-e6c9-40b4-b329-1d2fd9a51d6e', 'buggl', '3'),
       ('33108b98-b40a-43b5-b58c-d21fdb2a896e', 'xtgl', '1'),
       ('33680b22-8174-44d5-8eee-f3b5e01126ac', 'tpsb', '0'),
       ('35191d83-bb19-4789-9c9d-611851b6b29f', 'yhgl', '0'),
       ('35ffabb8-b6f9-4aa6-b3a4-e496e9fc35f2', 'aizq', '0'),
       ('3a2f6be3-8249-41f7-897e-131ce29b5ba9', 'buggl', '0'),
       ('42b9b99d-f7e6-41c4-95b3-964d9900633b', 'tszq', '0'),
       ('460d0bef-98d7-4c1f-b978-44a701581fef', 'buggl', '2'),
       ('462f5326-9450-4001-beae-26d6ca2c3f09', 'jsgl', '0'),
       ('53264fcf-40ab-4c1c-9344-677c62d3f3aa', 'grzx', '0'),
       ('5c91b73c-93b4-4fe7-85b0-8d65c95b5a4b', 'qxgl', '3'),
       ('699facd6-6221-4ffb-8845-dfe67306cccd', 'qxgl', '0'),
       ('6e13479d-da14-4939-8c19-a92933e5aba8', 'yhzx', '0'),
       ('7296bba1-3e76-4666-b354-93a768469db3', 'grzx', '1'),
       ('797e71fc-513b-4515-959b-2bafb7d96085', 'tsgl', '0'),
       ('7df78759-7fa2-4a1e-ad5a-31aa8e081759', 'xtgl', '0'),
       ('7edf18ce-72c5-4547-b61e-3966da5c02d5', 'yhzx', '1'),
       ('8b3d30f2-1626-4d80-a475-054ba37a1432', 'cdgl', '3'),
       ('905a1258-4ba2-433c-9449-4205b6c84f73', 'cdgl', '1'),
       ('a43951e6-ae48-4aea-b88f-4d303dcc2dbc', 'cdgl', '2'),
       ('ad1fca38-91ab-4b07-b9ba-d9129d7dd597', 'jsgl', '3'),
       ('ce01248f-180b-44b2-a25a-7911c471038a', 'aizq', '0'),
       ('d23a2e09-d1de-4b2f-839e-393438c5f315', 'cdgl', '0'),
       ('fe2ca46d-91b0-40c9-8afa-55933659a9a3', 'yhzx', '0');
INSERT INTO `tbook`
VALUES ('677918988361142272', '1', '9787312026065', NULL, '数据库系统及应用', '', '数据库系统--高等学校--教材', NULL, '1', NULL, 'TP311.1',
        '戴小平主编', '安徽省高等学校“十一五”省级规划教材本书全面地介绍了数据库系统的基本原理、设计和应用技术。内容包括数据库基础知识、关系数据模型、关系数据库语言SQL、关系数据库理论、关系数据库设计等。', NULL,
        '中国科学技术大学出版社', '2010', '合肥', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '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');
INSERT INTO `tmenu`
VALUES ('0', NULL, '首页', NULL, ''),
       ('aizq', '0', '智能专区', NULL, NULL),
       ('buggl', 'xtgl', 'BUG管理', NULL, '/admin/buggl.jsp'),
       ('cdgl', 'xtgl', '菜单管理', NULL, '/admin/cdgl.jsp'),
       ('grzx', 'yhzx', '个人中心', NULL, '/admin/grzx.jsp'),
       ('jsgl', 'xtgl', '角色管理', 'icon-save', '/admin/jsgl.jsp'),
       ('qxgl', 'xtgl', '权限管理', NULL, '/admin/qxgl.jsp'),
       ('tpsb', 'aizq', '图片识别', NULL, '/aizq/tpsb.jsp'),
       ('tsgl', 'tszq', '图书管理', NULL, '/tszq/tsgl.jsp'),
       ('tszq', '0', '图书专区', NULL, NULL),
       ('xtgl', '0', '系统管理', NULL, NULL),
       ('xxxg', 'yhzx', '信息修改', NULL, '/admin/xxxg.jsp'),
       ('yhgl', 'xtgl', '用户管理', NULL, '/admin/yhgl.jsp'),
       ('yhzx', '0', '用户中心', NULL, NULL);
INSERT INTO `trole`
VALUES ('0', '超级管理员', '超级管理员', NULL, '0'),
       ('1', '管理员', '一般管理员', NULL, '0'),
       ('2', 'operater', '操作员', NULL, '0'),
       ('3', 'seller', '买家', NULL, '0'),
       ('4', '管理员11', '管理员', NULL, '1'),
       ('5', '管理员12', '管理员', NULL, '1'),
       ('6', '管理员11', '管理员', NULL, '1');
INSERT INTO `ttag`
VALUES ('K209', '普及读物'),
       ('TP311.1', '程序设计'),
       ('TP312', '程序语言、算法语言');
INSERT INTO `tuser`
VALUES ('0', 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, '0', NULL),
       ('6fbee2bd-aa90-48fa-84c7-b090901b0353', 'gzc', '81dc9bdb52d04dc20036dbd8313ed055', '2020-02-09 14:58:18',
        '2020-02-10 04:23:08', '6', NULL),
       ('ca7c09df-14d7-4b8d-82a2-f4ef08ab2fa1', 'GZC005dc033-4', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-12 03:54:05', '2020-02-12 03:54:05', '1', NULL),
       ('1f24147a-ceb9-4316-82f3-f4fd0b6e890f', 'GZC25fc1817-3', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-27 06:51:44', '2020-02-27 06:51:44', '1', NULL),
       ('0b338832-8610-4b3e-89c4-958b4e5940bf', 'GZC25fda340-d', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-11 13:44:43', '2020-02-11 13:44:43', '1', NULL),
       ('e12028ca-84d5-4779-a8e9-144bb33c9f12', 'GZC2d077718-6', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-27 13:22:06', '2020-02-27 13:22:06', '1', NULL),
       ('c3b2fcf9-c301-4147-8590-6f9b52a3d6e4', 'GZC3094551a-9', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-13 05:12:54', '2020-02-13 05:12:54', '1', NULL),
       ('6ed4c7c5-5528-49d7-b9bf-62a238305929', 'GZC341aa9f3-3', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-27 11:59:19', '2020-02-27 11:59:19', '1', NULL),
       ('afe1d006-62ae-4f1e-b9de-3f7d1c10e604', 'GZC3874464e-6', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-26 02:45:43', '2020-03-26 02:45:43', '1', NULL),
       ('e01a68df-e5d4-402c-a333-890dd3896d1b', 'GZC448d0d8f-c', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-04 15:17:16', '2020-02-04 15:17:16', '1', NULL),
       ('64876718-0f78-4332-bf23-2f1838196824', 'GZC47c2c7a1-8', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-13 08:35:43', '2020-02-13 08:35:43', '1', NULL),
       ('16610eeb-406a-4b4d-aaab-f538e9bbe57c', 'GZC4c1dcc54-e', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-11 10:05:23', '2020-02-11 10:05:23', '1', NULL),
       ('7df05ae8-7a36-43dd-8763-de942d9d2819', 'GZC4fc4d899-d', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-11 08:17:43', '2020-02-11 08:17:43', '1', NULL),
       ('e3f737fe-fdc6-45f1-af1c-5ec071f5c3b1', 'GZC506cbf71-1', '202cb962ac59075b964b07152d234b70',
        '2020-01-21 02:17:37', '2020-01-21 02:17:37', '3', NULL),
       ('cd1d2b0c-5328-49ed-8a21-78f92b3c1d90', 'GZC521b3728-e', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-15 08:20:08', '2020-02-15 08:20:08', '1', NULL),
       ('fac6d6d8-c06d-403e-9460-7829ef6599ad', 'GZC561dc810-a', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-17 12:38:55', '2020-03-17 12:38:55', '1', NULL),
       ('b88f8b99-b6f1-4bcb-b007-9e620743a50a', 'GZC5828d685-c', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-12 14:08:50', '2020-02-12 14:08:50', '1', NULL),
       ('963c5b1b-a23d-488c-9fca-3c69e461414e', 'GZC58fcf594-0', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-11 14:04:32', '2020-02-11 14:04:32', '1', NULL),
       ('ab91804d-d190-4b49-a57e-dc029b17c9a7', 'GZC63f907db-4', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-05 07:42:05', '2020-02-05 07:42:05', '1', NULL),
       ('a94ac244-2ef0-4bb2-8b2c-66cd3fa6b21b', 'GZC6597a513-6', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-13 05:32:20', '2020-02-13 05:32:20', '1', NULL),
       ('9c101d44-abbd-4193-b5df-03adf47d05c2', 'GZC67a17131-1', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-12 10:06:11', '2020-02-12 10:06:11', '1', NULL),
       ('130de268-44f5-427f-b356-bd4e91574b0d', 'GZC6a1b2aac-6', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-27 13:24:52', '2020-02-27 13:24:52', '1', NULL),
       ('810578a4-41ea-4a9c-8271-569260764ff8', 'GZC6bd964a8-b', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-26 09:28:20', '2020-02-26 09:28:20', '1', NULL),
       ('2e80fa11-5124-47f6-863a-1de0257ba931', 'GZC7dd29e56-d', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-12 04:12:36', '2020-02-12 04:12:36', '1', NULL),
       ('06809160-1138-48f4-bbaa-0040f2a683a5', 'GZC89f217f6-5', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-03 09:33:24', '2020-02-03 09:33:24', '1', NULL),
       ('6e9b7033-beff-4c86-a10d-7b3eaec31650', 'GZC9b5183c8-1', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-05 06:15:03', '2020-02-09 07:26:10', '2', NULL),
       ('bf699936-f7d7-4fa6-aa92-39474a981428', 'GZCa2a1d413-c', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-06 10:13:22', '2020-03-06 10:13:22', '1', NULL),
       ('4f1a9488-d177-4bc2-8eb2-8157a196b2b0', 'GZCa49fbe2c-b', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-21 10:03:04', '2020-03-21 10:03:04', '1', NULL),
       ('e33f9e50-a2c2-4491-a833-6712abe31aa3', 'GZCaa32e9d9-8', '57f231b1ec41dc6641270cb09a56f897',
        '2020-01-31 10:20:47', '2020-01-31 10:20:47', '1', NULL),
       ('6aa7af7d-dd1f-4eee-b323-8159ef1632b5', 'GZCaebd4cab-1', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-21 10:34:00', '2020-03-21 10:34:00', '1', NULL),
       ('4fd2d349-5d2e-48b9-ad2c-c31a012f1dff', 'GZCb6ae518d-f', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-09 14:38:04', '2020-02-09 14:38:04', '1', NULL),
       ('89004c3b-3f3d-47f6-96ff-9c222e4545dd', 'GZCb80beba1-c', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-12 05:04:22', '2020-02-12 05:04:22', '1', NULL),
       ('5d15ab3c-f052-4de0-b10a-ba6f535dcb4f', 'GZCba280f97-3', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-01 04:35:35', '2020-02-01 04:35:35', '1', NULL),
       ('791ae871-b253-4fa9-bd8c-f3d29e19b21a', 'GZCc6d18878-0', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-01 02:03:59', '2020-02-01 02:03:59', '1', NULL),
       ('9a5655ba-857f-4da7-9574-239370980378', 'GZCcb80138d-1', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-05 00:54:46', '2020-02-05 00:54:46', '1', NULL),
       ('e4b66291-08e2-416b-9dba-dfece8d5a609', 'GZCcf77e10b-4', '57f231b1ec41dc6641270cb09a56f897',
        '2020-03-26 02:17:17', '2020-03-26 02:17:17', '1', NULL),
       ('f271923e-408e-4bb8-ba1a-75f68dbb5416', 'GZCd175344c-1', '57f231b1ec41dc6641270cb09a56f897',
        '2020-01-21 07:41:44', '2020-01-21 07:41:44', '1', NULL),
       ('d68021ca-1d2e-4708-992d-bdc46ea768ea', 'GZCd7a5beb9-6', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-27 06:39:29', '2020-02-27 06:39:29', '1', NULL),
       ('1bd56a8a-030d-4025-8303-3fcaa592cba6', 'GZCda9225bb-5', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-13 08:49:01', '2020-02-13 08:49:01', '1', NULL),
       ('5eb586f2-0ac4-4459-b6fc-68559ebcf0d1', 'GZCe226ed24-a', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-13 08:25:37', '2020-02-13 08:25:37', '1', NULL),
       ('c5282720-de9a-4161-b239-c51e199e683f', 'GZCe87cf814-8', '57f231b1ec41dc6641270cb09a56f897',
        '2020-02-11 07:47:21', '2020-02-11 07:47:21', '1', NULL),
       ('87573c59-d460-41a7-a1e8-699abb1cc180', 'GZCe97f2469-c', '57f231b1ec41dc6641270cb09a56f897',
        '2020-01-25 10:57:21', '2020-01-25 10:57:21', '1', NULL),
       ('cb4a354c-2429-439d-bfd9-6d34cd2e51fa', '江湖儿女', '81dc9bdb52d04dc20036dbd8313ed055', '2020-02-09 07:22:48',
        '2020-02-10 11:38:24', '1', NULL),
       ('a360c307-f68e-4758-81b6-cab3d16e6bad', '江湖儿女201314', '21232f297a57a5a743894a0e4a801fc3', '2020-01-20 13:22:43',
        '2020-01-31 02:55:43', '0',
        'http://localhost:8080/BookStoreManager/upload/b6b996e9-66ef-45d8-a732-7cce5f5f0fb6.JPG'),
       ('708a9fa5-1e28-45ee-b3ec-bb1d5c285689', '江湖儿女2013143', '062e058bab4712217e0afdc987457430',
        '2020-01-31 08:27:17', '2020-01-31 08:27:43', '2', NULL);
