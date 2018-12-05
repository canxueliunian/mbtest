/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mb_plus

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-05 22:38:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL COMMENT '书籍名称',
  `book_no` bigint(20) DEFAULT NULL COMMENT '索书号',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `type` int(20) DEFAULT NULL COMMENT '类型',
  `type_dec` varchar(255) DEFAULT NULL COMMENT '类型描述',
  `entry_date` date DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '小王子', '1', '安托万·德·圣·埃克苏佩里', '1', '童话', '2018-12-01');
INSERT INTO `book` VALUES ('2', '女孩的故事', '2', '秦文君', '2', '短篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('3', '夏洛的网', '3', 'E.B.怀特', '1', '童话', '2018-12-01');
INSERT INTO `book` VALUES ('4', '麦田的守望者', '4', '杰罗姆·大卫·塞林格', '3', '长篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('5', '偷影子的人', '5', '马克李维', '3', '长篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('6', '伊斯坦布尔的假期', '6', '马克李维', '3', '长篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('7', '基督山伯爵', '7', '大仲马', '3', '长篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('8', '一个陌生女人的来信', '8', '茨威格', '3', '长篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('9', '了不起的盖茨比', '9', '弗·司各特·菲茨杰拉德', '2', '短篇小说', '2018-12-01');
INSERT INTO `book` VALUES ('10', '上海宝贝', '10', '卫慧', '4', '情色小说', '2018-12-01');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` bigint(20) DEFAULT NULL COMMENT '学号',
  `name` varchar(255) DEFAULT NULL,
  `ever_name` varchar(255) DEFAULT NULL COMMENT '曾用名\n',
  `sex` int(20) DEFAULT NULL COMMENT '0 男 ,1 女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '41420015', '江欣', '江小欣', '1', '23', 'jiangxin@qq.com');
INSERT INTO `student` VALUES ('2', '41421090', '陈家琦', null, '0', '22', 'jiaqi@qq.com');
INSERT INTO `student` VALUES ('3', '41423093', '杨雪', '杨小雪', '1', '21', 'yangxue@qq.com');
INSERT INTO `student` VALUES ('4', '41420007', '罗晶', null, '1', '20', 'luojing@qq.com');
INSERT INTO `student` VALUES ('5', '41426140', '彭娅', null, '0', '20', 'pengya@qq.com');
INSERT INTO `student` VALUES ('6', '41432128', '耿敏', null, '1', '24', 'gengmin@qq.com');
INSERT INTO `student` VALUES ('7', '41432043', '王雪', null, '1', '24', 'gengmin@163.com');
INSERT INTO `student` VALUES ('8', '41439046', '郝东宇', null, '0', '24', 'dongyu@163.com');
INSERT INTO `student` VALUES ('9', '41436016', '邹雪怡', null, '1', '22', 'xueyi@163.com');
INSERT INTO `student` VALUES ('10', '414369019', '张宇阁', null, '0', '22', 'yuge@163.com');
