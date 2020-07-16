/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : classmanager

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 16/06/2020 19:36:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `urlname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES (1, '修改密码', '/user/uPassword', -1);
INSERT INTO `auth` VALUES (2, '查看学习情况', '/user/rStudies', -1);
INSERT INTO `auth` VALUES (3, '查看作业', 'queryDayhomework.html', 0);
INSERT INTO `auth` VALUES (4, '班费详情', 'qClassFee.html', 0);
INSERT INTO `auth` VALUES (5, '课堂提问', '/user/cQuiz', -1);
INSERT INTO `auth` VALUES (6, '收取班费', 'cClassFee.html', 0);
INSERT INTO `auth` VALUES (7, '发布作业', 'addDayhomework.html', 0);
INSERT INTO `auth` VALUES (8, '学生信息', '/user/crudStudent', 0);
INSERT INTO `auth` VALUES (9, '教师信息', '/user/crudTeacher', 0);
INSERT INTO `auth` VALUES (10, '发布考试', '', 0);
INSERT INTO `auth` VALUES (12, '多班单科', 'exam21.html', 10);
INSERT INTO `auth` VALUES (13, '单班多科', 'exam12.html', 10);
INSERT INTO `auth` VALUES (14, '多班多科', 'exam22.html', 10);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `tNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班主任编号',
  `startNum` int(11) NULL DEFAULT NULL COMMENT '入学人数',
  `nowNum` int(11) NULL DEFAULT NULL,
  `createTime` date NULL DEFAULT NULL,
  `remain` double(11, 0) NULL DEFAULT 0 COMMENT '班费剩余',
  `status` int(11) NULL DEFAULT 1 COMMENT '0(静止)1(活跃)',
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '2001', '0001', 7, 7, '2020-09-10', 36, 1);
INSERT INTO `class` VALUES (2, '2002', '0002', 6, 6, '2020-04-01', 0, 1);
INSERT INTO `class` VALUES (3, '2003', '0003', 6, 6, '2020-05-07', 0, 1);

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '数据是否生效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_user
-- ----------------------------
INSERT INTO `class_user` VALUES (1, '2001', '0001', 1);
INSERT INTO `class_user` VALUES (2, '2001', '0002', 1);
INSERT INTO `class_user` VALUES (3, '2001', '0003', 1);
INSERT INTO `class_user` VALUES (4, '2002', '0001', 1);
INSERT INTO `class_user` VALUES (5, '2002', '0002', 1);
INSERT INTO `class_user` VALUES (6, '2001', '202042001', 1);
INSERT INTO `class_user` VALUES (7, '2001', '202042002', 1);
INSERT INTO `class_user` VALUES (8, '2001', '202042003', 1);
INSERT INTO `class_user` VALUES (9, '2001', '202042004', 1);
INSERT INTO `class_user` VALUES (10, '2001', '202042005', 1);
INSERT INTO `class_user` VALUES (11, '2001', '202042006', 1);
INSERT INTO `class_user` VALUES (12, '2001', '202042007', 1);
INSERT INTO `class_user` VALUES (13, '2002', '202042008', 1);
INSERT INTO `class_user` VALUES (14, '2002', '202042009', 1);
INSERT INTO `class_user` VALUES (15, '2002', '202042010', 1);
INSERT INTO `class_user` VALUES (16, '2002', '202042011', 1);
INSERT INTO `class_user` VALUES (17, '2002', '202042012', 1);
INSERT INTO `class_user` VALUES (18, '2002', '202042013', 1);
INSERT INTO `class_user` VALUES (19, '2002', '0003', 1);
INSERT INTO `class_user` VALUES (20, '2001', '0004', 1);
INSERT INTO `class_user` VALUES (21, '2001', '0005', 1);
INSERT INTO `class_user` VALUES (22, '2002', '0005', 1);
INSERT INTO `class_user` VALUES (23, '2002', '0004', 1);

-- ----------------------------
-- Table structure for classfee
-- ----------------------------
DROP TABLE IF EXISTS `classfee`;
CREATE TABLE `classfee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `money` double(4, 0) NULL DEFAULT NULL COMMENT '金额',
  `startTime` datetime(0) NULL DEFAULT NULL COMMENT '发起时间 根据系统时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班费描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classfee
-- ----------------------------
INSERT INTO `classfee` VALUES (5, '2001', '0001', 3, '2020-04-15 23:44:50', '买书');
INSERT INTO `classfee` VALUES (6, '2001', '0001', 10, '2020-05-11 19:34:23', '元旦节组办经费');
INSERT INTO `classfee` VALUES (10, '2001', '0001', 3, '2020-04-01 23:21:00', '购买班级作业');
INSERT INTO `classfee` VALUES (11, '2001', '0001', 10, '2020-05-10 09:18:23', '开展班级活动');
INSERT INTO `classfee` VALUES (12, '2001', '0001', 10, '2020-05-26 10:32:00', '运动会布置资金');
INSERT INTO `classfee` VALUES (14, '2001', '0001', 10, '2020-06-02 22:11:26', '订购暑假作业书籍');
INSERT INTO `classfee` VALUES (15, '2001', '0001', 10, '2020-06-03 17:04:01', '元旦布置资金');

-- ----------------------------
-- Table structure for classfee_student
-- ----------------------------
DROP TABLE IF EXISTS `classfee_student`;
CREATE TABLE `classfee_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) NOT NULL COMMENT '班费id',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '缴费状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classfee_student
-- ----------------------------
INSERT INTO `classfee_student` VALUES (2, 5, '202042001', 1);
INSERT INTO `classfee_student` VALUES (3, 5, '202042002', 0);
INSERT INTO `classfee_student` VALUES (4, 5, '202042003', 0);
INSERT INTO `classfee_student` VALUES (5, 5, '202042004', 0);
INSERT INTO `classfee_student` VALUES (6, 5, '202042005', 1);
INSERT INTO `classfee_student` VALUES (7, 5, '202042006', 0);
INSERT INTO `classfee_student` VALUES (8, 5, '202042007', 0);
INSERT INTO `classfee_student` VALUES (9, 6, '202042001', 1);
INSERT INTO `classfee_student` VALUES (10, 6, '202042002', 0);
INSERT INTO `classfee_student` VALUES (11, 6, '202042003', 0);
INSERT INTO `classfee_student` VALUES (12, 6, '202042004', 0);
INSERT INTO `classfee_student` VALUES (13, 6, '202042005', 1);
INSERT INTO `classfee_student` VALUES (14, 6, '202042006', 0);
INSERT INTO `classfee_student` VALUES (15, 6, '202042007', 0);
INSERT INTO `classfee_student` VALUES (16, 7, '202042001', 0);
INSERT INTO `classfee_student` VALUES (17, 7, '202042002', 0);
INSERT INTO `classfee_student` VALUES (18, 7, '202042003', 0);
INSERT INTO `classfee_student` VALUES (19, 7, '202042004', 0);
INSERT INTO `classfee_student` VALUES (20, 7, '202042005', 1);
INSERT INTO `classfee_student` VALUES (21, 7, '202042006', 0);
INSERT INTO `classfee_student` VALUES (22, 7, '202042007', 0);
INSERT INTO `classfee_student` VALUES (23, 8, '202042001', 0);
INSERT INTO `classfee_student` VALUES (24, 8, '202042002', 0);
INSERT INTO `classfee_student` VALUES (25, 8, '202042003', 0);
INSERT INTO `classfee_student` VALUES (26, 8, '202042004', 0);
INSERT INTO `classfee_student` VALUES (27, 8, '202042005', 1);
INSERT INTO `classfee_student` VALUES (28, 8, '202042006', 0);
INSERT INTO `classfee_student` VALUES (29, 8, '202042007', 0);
INSERT INTO `classfee_student` VALUES (30, 9, '202042001', 0);
INSERT INTO `classfee_student` VALUES (31, 9, '202042002', 0);
INSERT INTO `classfee_student` VALUES (32, 9, '202042003', 0);
INSERT INTO `classfee_student` VALUES (33, 9, '202042004', 0);
INSERT INTO `classfee_student` VALUES (34, 9, '202042005', 0);
INSERT INTO `classfee_student` VALUES (35, 9, '202042006', 0);
INSERT INTO `classfee_student` VALUES (36, 9, '202042007', 0);
INSERT INTO `classfee_student` VALUES (37, 10, '202042001', 0);
INSERT INTO `classfee_student` VALUES (38, 10, '202042002', 0);
INSERT INTO `classfee_student` VALUES (39, 10, '202042003', 0);
INSERT INTO `classfee_student` VALUES (40, 10, '202042004', 0);
INSERT INTO `classfee_student` VALUES (41, 10, '202042005', 0);
INSERT INTO `classfee_student` VALUES (42, 10, '202042006', 0);
INSERT INTO `classfee_student` VALUES (43, 10, '202042007', 0);
INSERT INTO `classfee_student` VALUES (44, 11, '202042001', 0);
INSERT INTO `classfee_student` VALUES (45, 11, '202042002', 0);
INSERT INTO `classfee_student` VALUES (46, 11, '202042003', 0);
INSERT INTO `classfee_student` VALUES (47, 11, '202042004', 0);
INSERT INTO `classfee_student` VALUES (48, 11, '202042005', 0);
INSERT INTO `classfee_student` VALUES (49, 11, '202042006', 0);
INSERT INTO `classfee_student` VALUES (50, 11, '202042007', 0);
INSERT INTO `classfee_student` VALUES (51, 12, '202042001', 0);
INSERT INTO `classfee_student` VALUES (52, 12, '202042002', 0);
INSERT INTO `classfee_student` VALUES (53, 12, '202042003', 0);
INSERT INTO `classfee_student` VALUES (54, 12, '202042004', 0);
INSERT INTO `classfee_student` VALUES (55, 12, '202042005', 0);
INSERT INTO `classfee_student` VALUES (56, 12, '202042006', 0);
INSERT INTO `classfee_student` VALUES (57, 12, '202042007', 0);
INSERT INTO `classfee_student` VALUES (58, 13, '202042001', 1);
INSERT INTO `classfee_student` VALUES (59, 13, '202042002', 0);
INSERT INTO `classfee_student` VALUES (60, 13, '202042003', 0);
INSERT INTO `classfee_student` VALUES (61, 13, '202042004', 0);
INSERT INTO `classfee_student` VALUES (62, 13, '202042005', 0);
INSERT INTO `classfee_student` VALUES (63, 13, '202042006', 0);
INSERT INTO `classfee_student` VALUES (64, 13, '202042007', 0);
INSERT INTO `classfee_student` VALUES (65, 14, '202042001', 0);
INSERT INTO `classfee_student` VALUES (66, 14, '202042002', 0);
INSERT INTO `classfee_student` VALUES (67, 14, '202042003', 0);
INSERT INTO `classfee_student` VALUES (68, 14, '202042004', 0);
INSERT INTO `classfee_student` VALUES (69, 14, '202042005', 0);
INSERT INTO `classfee_student` VALUES (70, 14, '202042006', 0);
INSERT INTO `classfee_student` VALUES (71, 14, '202042007', 0);
INSERT INTO `classfee_student` VALUES (72, 15, '202042001', 1);
INSERT INTO `classfee_student` VALUES (73, 15, '202042002', 0);
INSERT INTO `classfee_student` VALUES (74, 15, '202042003', 0);
INSERT INTO `classfee_student` VALUES (75, 15, '202042004', 0);
INSERT INTO `classfee_student` VALUES (76, 15, '202042005', 0);
INSERT INTO `classfee_student` VALUES (77, 15, '202042006', 0);
INSERT INTO `classfee_student` VALUES (78, 15, '202042007', 0);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isScience` int(1) NULL DEFAULT 1,
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '语文', 0);
INSERT INTO `course` VALUES (2, '数学', 1);
INSERT INTO `course` VALUES (3, '英语', 0);
INSERT INTO `course` VALUES (4, '品德与生活', 0);
INSERT INTO `course` VALUES (5, '科学', 1);

-- ----------------------------
-- Table structure for daywork
-- ----------------------------
DROP TABLE IF EXISTS `daywork`;
CREATE TABLE `daywork`  (
  `dId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `day` date NULL DEFAULT NULL,
  `cName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daywork
-- ----------------------------
INSERT INTO `daywork` VALUES (1, '测试作业', '2020-05-13', '语文', '2001');
INSERT INTO `daywork` VALUES (2, '1.写一篇300字日记 。\n2.将课后词语盘点抄写2遍', '2020-05-13', '语文', '2002');
INSERT INTO `daywork` VALUES (3, '将课本 P35  练习题做完，明天检查', '2020-05-13', '数学', '2001');
INSERT INTO `daywork` VALUES (4, '你好啊', '2020-05-13', '数学', '2002');
INSERT INTO `daywork` VALUES (6, '将今天讲的内容理解，明天让同学上台做类似的题目', '2020-05-14', '数学', '2001');
INSERT INTO `daywork` VALUES (7, '将今天讲的内容理解，明天让同学上台做类似的题目', '2020-05-14', '数学', '2002');
INSERT INTO `daywork` VALUES (8, '1.将今天讲的双曲线函数 理解了\n2.课本P35 第3，5两到解答题 做完，明天挨个检查\n3.预习明天讲的 椭圆函数', '2020-05-15', '数学', '2001');
INSERT INTO `daywork` VALUES (9, '1.将今天讲的双曲线函数 理解了\n2.课本P35 第3，5两到解答题 做完，明天挨个检查\n3.预习明天讲的 椭圆函数', '2020-05-15', '数学', '2002');
INSERT INTO `daywork` VALUES (10, '将《蜀道难》文章第2，3，4段 背会，早自习抽查', '2020-05-15', '语文', '2001');
INSERT INTO `daywork` VALUES (11, '将《蜀道难》文章第2，3，4段 背会，早自习抽查', '2020-05-15', '语文', '2002');
INSERT INTO `daywork` VALUES (12, '将5年高考3年模拟的P68，做完', '2020-05-18', '数学', '2001');
INSERT INTO `daywork` VALUES (13, '将5年高考3年模拟的P68，做完', '2020-05-18', '数学', '2002');
INSERT INTO `daywork` VALUES (14, '1.写一篇300字日记 。\n2.将课后词语盘点抄写2遍', '2020-06-03', '语文', '2001');
INSERT INTO `daywork` VALUES (15, '将今天讲的内容理解，明天让同学上台做类似的题目', '2020-06-03', '数学', '2001');
INSERT INTO `daywork` VALUES (16, '将考试卷的阅读理解和作文写完', '2020-06-03', '英语', '2001');
INSERT INTO `daywork` VALUES (17, '阅读《品德与生活》P35-P40内容', '2020-06-03', '品德与生活', '2001');
INSERT INTO `daywork` VALUES (18, '将99乘法表背会', '2020-06-03', '数学', '2002');
INSERT INTO `daywork` VALUES (21, '将动滑轮概念', '2020-06-03', '科学', '2001');
INSERT INTO `daywork` VALUES (22, '将动滑轮概念', '2020-06-03', '科学', '2002');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作为考试的主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名称',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试类型',
  `startTime` date NULL DEFAULT NULL COMMENT '考试开始时间',
  `endTime` date NULL DEFAULT NULL COMMENT '考试结束时间',
  PRIMARY KEY (`id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, '入学考试', '0001', '多班单科', '2001-09-05', '2001-09-05');
INSERT INTO `exam` VALUES (2, '第一次月考', '0001', '多班单科', '2020-05-09', '2020-05-10');
INSERT INTO `exam` VALUES (3, '第二次月考', '0001', '多班多科', '2020-05-09', '2020-05-10');
INSERT INTO `exam` VALUES (4, '2001、2002兄弟班考试', '0001', '多班多科', '2020-05-09', '2020-05-10');

-- ----------------------------
-- Table structure for exam_class
-- ----------------------------
DROP TABLE IF EXISTS `exam_class`;
CREATE TABLE `exam_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cNum` int(11) NULL DEFAULT NULL COMMENT '班级编号',
  `examId` int(11) NULL DEFAULT NULL COMMENT '考试主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_class
-- ----------------------------
INSERT INTO `exam_class` VALUES (1, 2001, 1);
INSERT INTO `exam_class` VALUES (2, 2001, 1);
INSERT INTO `exam_class` VALUES (3, 2001, 2);
INSERT INTO `exam_class` VALUES (4, 2002, 2);
INSERT INTO `exam_class` VALUES (5, 2001, 3);
INSERT INTO `exam_class` VALUES (6, 2002, 3);
INSERT INTO `exam_class` VALUES (7, 2001, 4);
INSERT INTO `exam_class` VALUES (8, 2002, 4);

-- ----------------------------
-- Table structure for exam_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_course`;
CREATE TABLE `exam_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) NULL DEFAULT NULL COMMENT '考试id',
  `cName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_course
-- ----------------------------
INSERT INTO `exam_course` VALUES (1, 1, '数学');
INSERT INTO `exam_course` VALUES (2, 1, '语文');
INSERT INTO `exam_course` VALUES (3, 2, '数学');
INSERT INTO `exam_course` VALUES (4, 3, '语文');
INSERT INTO `exam_course` VALUES (5, 3, ' 数学');
INSERT INTO `exam_course` VALUES (6, 3, ' 英语');
INSERT INTO `exam_course` VALUES (7, 4, '语文');
INSERT INTO `exam_course` VALUES (8, 4, ' 数学');
INSERT INTO `exam_course` VALUES (9, 4, ' 英语');
INSERT INTO `exam_course` VALUES (10, 4, ' 品德与生活');
INSERT INTO `exam_course` VALUES (11, 4, ' 科学');

-- ----------------------------
-- Table structure for examscore
-- ----------------------------
DROP TABLE IF EXISTS `examscore`;
CREATE TABLE `examscore`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NULL DEFAULT NULL COMMENT '谁',
  `examid` int(11) NULL DEFAULT NULL COMMENT '那场考试',
  `subjectid` int(11) NULL DEFAULT NULL COMMENT '哪个学科',
  `score` double(5, 0) NULL DEFAULT NULL COMMENT '考了多少分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '学生');
INSERT INTO `role` VALUES (2, '家长');
INSERT INTO `role` VALUES (3, '教师');
INSERT INTO `role` VALUES (4, '班主任');
INSERT INTO `role` VALUES (5, '超级管理员');

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `aid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_auth
-- ----------------------------
INSERT INTO `role_auth` VALUES (1, 1, 1);
INSERT INTO `role_auth` VALUES (2, 1, 2);
INSERT INTO `role_auth` VALUES (3, 1, 3);
INSERT INTO `role_auth` VALUES (4, 1, 4);
INSERT INTO `role_auth` VALUES (5, 2, 1);
INSERT INTO `role_auth` VALUES (6, 2, 2);
INSERT INTO `role_auth` VALUES (7, 2, 3);
INSERT INTO `role_auth` VALUES (8, 2, 4);
INSERT INTO `role_auth` VALUES (9, 3, 1);
INSERT INTO `role_auth` VALUES (10, 3, 2);
INSERT INTO `role_auth` VALUES (11, 3, 3);
INSERT INTO `role_auth` VALUES (12, 3, 5);
INSERT INTO `role_auth` VALUES (13, 3, 7);
INSERT INTO `role_auth` VALUES (14, 4, 1);
INSERT INTO `role_auth` VALUES (15, 4, 2);
INSERT INTO `role_auth` VALUES (16, 4, 3);
INSERT INTO `role_auth` VALUES (17, 4, 4);
INSERT INTO `role_auth` VALUES (18, 4, 6);
INSERT INTO `role_auth` VALUES (19, 4, 7);
INSERT INTO `role_auth` VALUES (20, 5, 8);
INSERT INTO `role_auth` VALUES (21, 5, 9);
INSERT INTO `role_auth` VALUES (22, 3, 10);
INSERT INTO `role_auth` VALUES (24, 3, 12);
INSERT INTO `role_auth` VALUES (25, 4, 10);
INSERT INTO `role_auth` VALUES (26, 4, 13);
INSERT INTO `role_auth` VALUES (27, 4, 14);
INSERT INTO `role_auth` VALUES (28, 5, 10);
INSERT INTO `role_auth` VALUES (30, 5, 14);
INSERT INTO `role_auth` VALUES (31, 3, 4);

-- ----------------------------
-- Table structure for student_classfee
-- ----------------------------
DROP TABLE IF EXISTS `student_classfee`;
CREATE TABLE `student_classfee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raisingCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentId` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_parent
-- ----------------------------
DROP TABLE IF EXISTS `student_parent`;
CREATE TABLE `student_parent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_parent
-- ----------------------------
INSERT INTO `student_parent` VALUES (1, '202042001', '15233860813');
INSERT INTO `student_parent` VALUES (8, '202042002', '17732037218');
INSERT INTO `student_parent` VALUES (11, '202042003', '18812008918');
INSERT INTO `student_parent` VALUES (12, '202042009', '18812008919');
INSERT INTO `student_parent` VALUES (13, '202042006', '17732037219');
INSERT INTO `student_parent` VALUES (14, '202042004', '15369315136');
INSERT INTO `student_parent` VALUES (21, '202042001', '15369315316');
INSERT INTO `student_parent` VALUES (25, '202042001', '17732037217');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT 0,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `courseId` int(11) NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '教师、学生：是否在职\r\n\r\n家长：注册步骤第几步\r\nstatus:\r\n-1.下岗\r\n0.在职\r\n\r\n1.2.3那就是家长的注册状态\r\n1.已注册\r\n2.已绑定学生\r\n3.已完善个人信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0001', '0001', '刘春叶', '女', 28, '17732037218', 2, 0);
INSERT INTO `user` VALUES (2, '0002', '0002', '刘似琴', '女', 30, '0', 1, 0);
INSERT INTO `user` VALUES (3, '0003', '0003', '李老师', '女', 32, '0', 3, 0);
INSERT INTO `user` VALUES (4, '202042001', '202042001', '王磊', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (5, '15233860813', 'ylh218610', '尹利红', '女', 42, '15233860813', 0, 3);
INSERT INTO `user` VALUES (7, '202042002', '202042002', '李明', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (8, '202042003', '202042003', '杨雷', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (9, '202042004', '202042004', '张学斌', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (10, '202042005', '202042005', '石琦', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (11, '202042006', '202042006', '牛嘉欣', '女', 6, '0', 0, 0);
INSERT INTO `user` VALUES (12, '202042007', '202042007', '魏强', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (13, '202042008', '202042008', '齐占洁', '女', 6, '0', 0, 0);
INSERT INTO `user` VALUES (14, '202042009', '202042009', '齐秀', '女', 6, '0', 0, 0);
INSERT INTO `user` VALUES (15, '202042010', '202042010', '王宁', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (16, '202042011', '202042011', '李东', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (31, '17732037218', '112233', 'Tom', '男', 33, '17732037218', 0, 3);
INSERT INTO `user` VALUES (33, '18812008918', '112233', '牛伟国', '男', 42, '18812008918', 0, 3);
INSERT INTO `user` VALUES (42, '18812008919', '218610', '王利生', '男', 56, '18812008919', 0, 3);
INSERT INTO `user` VALUES (43, '17732037219', '112233', '李小红', '女', 46, '17732037219', 0, 3);
INSERT INTO `user` VALUES (44, '15369315136', '12345678', '张宇鑫', '女', 45, '15369315136', 0, 3);
INSERT INTO `user` VALUES (45, '0004', '0004', '张老师', '男', 35, '0', 5, 0);
INSERT INTO `user` VALUES (47, 'root', '112233', '佚名', '男', 0, '0', 0, 0);
INSERT INTO `user` VALUES (50, '0005', '0005', '杨老师', '女', 29, '0', 4, 0);
INSERT INTO `user` VALUES (52, '202042012', '202042012', '兰海南', '男', 6, '0', 0, 0);
INSERT INTO `user` VALUES (53, '202042013', '202042013', '李玲', '女', 6, '0', 0, 0);
INSERT INTO `user` VALUES (55, '15369315316', '123456', '章鱼性', '女', 31, '15369315316', 0, 3);
INSERT INTO `user` VALUES (58, '17732037217', '112233', '王来', '男', 23, '17732037217', 0, 3);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '0001', 3);
INSERT INTO `user_role` VALUES (2, '0001', 4);
INSERT INTO `user_role` VALUES (3, '0002', 3);
INSERT INTO `user_role` VALUES (4, '0002', 4);
INSERT INTO `user_role` VALUES (5, '0003', 3);
INSERT INTO `user_role` VALUES (6, '202042001', 1);
INSERT INTO `user_role` VALUES (7, '15233860813', 2);
INSERT INTO `user_role` VALUES (8, '202042002', 1);
INSERT INTO `user_role` VALUES (9, '202042003', 1);
INSERT INTO `user_role` VALUES (10, '202042004', 1);
INSERT INTO `user_role` VALUES (11, '202042005', 1);
INSERT INTO `user_role` VALUES (12, '202042006', 1);
INSERT INTO `user_role` VALUES (13, '202042007', 1);
INSERT INTO `user_role` VALUES (14, '202042008', 1);
INSERT INTO `user_role` VALUES (15, '202042009', 1);
INSERT INTO `user_role` VALUES (16, '202042010', 1);
INSERT INTO `user_role` VALUES (17, '202042011', 1);
INSERT INTO `user_role` VALUES (18, '202042012', 1);
INSERT INTO `user_role` VALUES (19, '202042013', 1);
INSERT INTO `user_role` VALUES (33, '17732037218', 2);
INSERT INTO `user_role` VALUES (35, '18812008918', 2);
INSERT INTO `user_role` VALUES (44, '18812008919', 2);
INSERT INTO `user_role` VALUES (45, '17732037219', 2);
INSERT INTO `user_role` VALUES (46, '15369315136', 2);
INSERT INTO `user_role` VALUES (47, '0004', 3);
INSERT INTO `user_role` VALUES (48, '19131300370', 2);
INSERT INTO `user_role` VALUES (49, 'root', 5);
INSERT INTO `user_role` VALUES (53, '15369315316', 2);
INSERT INTO `user_role` VALUES (55, '0005', 3);
INSERT INTO `user_role` VALUES (57, '17732037217', 2);

SET FOREIGN_KEY_CHECKS = 1;
