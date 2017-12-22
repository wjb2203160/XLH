/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : 127.0.0.1:3306
 Source Schema         : pay_state_nuclear_db

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 30/11/2017 10:39:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appealform
-- ----------------------------
DROP TABLE IF EXISTS `appealform`;
CREATE TABLE `appealform`  (
  `complaintDate` date NULL DEFAULT NULL COMMENT '日期',
  `complaintNum` int(10) NULL DEFAULT 0 COMMENT '对应投诉编号',
  `complaintType` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '对应投诉类型',
  `franchiseeID` int(10) NULL DEFAULT 0 COMMENT '加盟商ID',
  `teamID` int(10) NULL DEFAULT 0 COMMENT '团队ID',
  `merchantID` int(10) NULL DEFAULT 0 COMMENT '骑手ID',
  `orderNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `billNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '运单号',
  `complaintAmount` double(10, 0) NULL DEFAULT 0 COMMENT '申诉应收金额',
  `reason` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '原因',
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of appealform
-- ----------------------------
INSERT INTO `appealform` VALUES ('2017-10-13', 6014434, '索赔', 14645403, 5002145, 100050585, '	3013677405335962731', '	3000005415925721919', 200, '申诉通过', 6, NULL);
INSERT INTO `appealform` VALUES ('2017-10-17', 6026786, '索赔', 14645403, 17407256, 100015264, '	3013707096713208921', '	3000005487411636906', 200, '申诉通过', 7, NULL);
INSERT INTO `appealform` VALUES ('2017-10-20', 6263026, '索赔', 14645403, 5002145, 102266250, '	3014271891531252884', '	3000006869788108807', 100, '申诉通过', 8, NULL);
INSERT INTO `appealform` VALUES ('2017-10-20', 6263546, '索赔', 14645403, 17409446, 100087587, '	3014274103246428246', '	3000006875035838491', 100, '申诉通过', 9, NULL);
INSERT INTO `appealform` VALUES ('2017-10-24', 6263482, '索赔', 14645403, 2434, 10791092, '	3014275262006718666', '	3000006878260963351', 200, '申诉通过', 10, NULL);

-- ----------------------------
-- Table structure for complaintform
-- ----------------------------
DROP TABLE IF EXISTS `complaintform`;
CREATE TABLE `complaintform`  (
  `complaintDate` date NULL DEFAULT NULL COMMENT '日期',
  `complaintNum` int(10) NULL DEFAULT 0 COMMENT '投诉编号',
  `complaintType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '投诉类型',
  `franchiseeID` bigint(19) NULL DEFAULT 0 COMMENT '加盟商ID',
  `teamID` bigint(18) NULL DEFAULT 0 COMMENT '团队ID',
  `merchantID` bigint(18) NULL DEFAULT 0 COMMENT '骑手ID',
  `orderNum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `billNum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '运单号',
  `complaintAmount` double(10, 2) NULL DEFAULT 0.00 COMMENT '投诉应扣金额',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '原因',
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for franchisee
-- ----------------------------
DROP TABLE IF EXISTS `franchisee`;
CREATE TABLE `franchisee`  (
  `franchiseeID` bigint(18) NULL DEFAULT 0 COMMENT '加盟商ID',
  `franchiseeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加盟商名称',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createBy` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `teamID` bigint(18) NULL DEFAULT 0 COMMENT '团队ID',
  `teamName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '团队名称',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createBy` bigint(18) NULL DEFAULT 0 COMMENT '创建人',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for orderform
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform`  (
  `orderDate` date NULL DEFAULT NULL COMMENT '日期',
  `franchiseeID` bigint(18) NULL DEFAULT 0 COMMENT '加盟商ID',
  `teamID` bigint(18) NULL DEFAULT 0 COMMENT '团队ID',
  `merchantID` bigint(18) NULL DEFAULT 0 COMMENT '商户ID',
  `orderFrom` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单来源',
  `orderNum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `billNum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '运单号',
  `riderID` bigint(18) NULL DEFAULT 0 COMMENT '骑手ID',
  `overTime` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否超时',
  `status` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '配送状态',
  `fastDelivery` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '快送/专送圈',
  `reason` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常原因',
  `result` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '清算结果',
  `illegal` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否违规',
  `orderTime` datetime NULL DEFAULT NULL COMMENT '骑手接单时间',
  `endTime` datetime NULL DEFAULT NULL COMMENT '运单结束时间',
  `tdFee` double(9, 2) NULL DEFAULT 0.00 COMMENT '提点配送费',
  `jcFee` double(9, 2) NULL DEFAULT 0.00 COMMENT '基础配送费',
  `hdSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '活动补贴',
  `djSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '等级补贴',
  `sxSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '时效补贴',
  `jlSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '距离补贴',
  `sdSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '时段补贴',
  `tqSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '天气补贴',
  `yxsSubsidy` double(9, 2) NULL DEFAULT 0.00 COMMENT '优先送补贴',
  `xtDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '系统异常扣款',
  `psDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '配送异常扣款',
  `ddDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '调度超时扣款',
  `sxDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '时效扣款',
  `wgDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '网格补贴',
  `weightMarkup` double(9, 2) NULL DEFAULT 0.00 COMMENT '重量加价',
  `jjDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '降级补贴',
  `llDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '冷链补贴',
  `wgfDebit` double(9, 2) NULL DEFAULT 0.00 COMMENT '午高峰补贴',
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for rider
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider`  (
  `riderID` bigint(18) NULL DEFAULT 0 COMMENT '骑手ID',
  `riderName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '骑手名称',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createBy` bigint(18) NULL DEFAULT 0 COMMENT '创建人',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for salary_d
-- ----------------------------
DROP TABLE IF EXISTS `salary_d`;
CREATE TABLE `salary_d`  (
  `siteId` bigint(18) NOT NULL COMMENT '站点id',
  `orderFrom` int(9) NOT NULL DEFAULT 0 COMMENT '单量从',
  `orderTo` int(9) NOT NULL DEFAULT 0 COMMENT '单量到',
  `price` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '单价',
  `isSubsidization` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '是否为奖励补助',
  `subsidization` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '奖励补助',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `createBy` bigint(9) NOT NULL DEFAULT 0 COMMENT '创建人',
  `modifyTime` datetime NOT NULL COMMENT '修改时间',
  `modifyBy` bigint(9) NOT NULL DEFAULT 0 COMMENT '修改人',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `id` bigint(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for salary_h
-- ----------------------------
DROP TABLE IF EXISTS `salary_h`;
CREATE TABLE `salary_h`  (
  `siteName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '站点名称',
  `basicSalary` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '底薪',
  `perfectAttendance` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '全勤奖',
  `attendanceDay` int(9) NOT NULL DEFAULT 0 COMMENT '全勤天数',
  `OrderNum` int(9) NOT NULL DEFAULT 0 COMMENT '超时单量',
  `orderPrice` double(10, 0) NOT NULL DEFAULT 0 COMMENT '超时单价',
  `executionDate` date NOT NULL COMMENT '执行日期',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `createBy` bigint(18) NOT NULL DEFAULT 0 COMMENT '创建人',
  `modifyTime` datetime NOT NULL COMMENT '修改时间',
  `modifyBy` bigint(19) NOT NULL DEFAULT 0 COMMENT '修改人',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `siteId` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '站点id',
  PRIMARY KEY (`siteId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of salary_h
-- ----------------------------
INSERT INTO `salary_h` VALUES ('华为', 2600.00, 200.00, 28, 0, 3, '2017-04-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 1);
INSERT INTO `salary_h` VALUES ('大浪', 2600.00, 200.00, 28, 0, 3, '2017-04-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 2);
INSERT INTO `salary_h` VALUES ('苹果园', 0.00, 100.00, 28, 0, 3, '2017-05-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 3);
INSERT INTO `salary_h` VALUES ('民治', 0.00, 100.00, 28, 0, 3, '2017-05-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 4);
INSERT INTO `salary_h` VALUES ('万众城', 0.00, 100.00, 28, 0, 3, '2017-05-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 5);
INSERT INTO `salary_h` VALUES ('石厦', 0.00, 0.00, 0, 0, 4, '2017-05-01', '2017-11-23 00:00:00', 1, '2017-11-23 00:00:00', 0, '', 6);
INSERT INTO `salary_h` VALUES ('西丽', 0.00, 0.00, 0, 0, 3, '2017-05-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 7);
INSERT INTO `salary_h` VALUES ('下梅林', 0.00, 0.00, 0, 0, 3, '2017-05-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 8);
INSERT INTO `salary_h` VALUES ('五和', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 9);
INSERT INTO `salary_h` VALUES ('吉祥', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 10);
INSERT INTO `salary_h` VALUES ('福永', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 11);
INSERT INTO `salary_h` VALUES ('水围', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 12);
INSERT INTO `salary_h` VALUES ('星海名城', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 13);
INSERT INTO `salary_h` VALUES ('大新', 0.00, 0.00, 0, 0, 3, '2017-08-01', '2017-11-23 00:00:00', 0, '2017-11-23 00:00:00', 0, '', 14);

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `teamID` bigint(18) NOT NULL DEFAULT 0 COMMENT '团队ID',
  `teamName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '团队名称',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `createBy` bigint(18) NOT NULL DEFAULT 0 COMMENT '创建人',
  `modifyTime` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for u_authority
-- ----------------------------
DROP TABLE IF EXISTS `u_authority`;
CREATE TABLE `u_authority`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(18) NULL DEFAULT NULL COMMENT '角色id',
  `functionId` bigint(18) NULL DEFAULT NULL COMMENT '功能id',
  `userTypeId` bigint(18) NULL DEFAULT NULL COMMENT '用户类型id',
  `createTime` datetime NULL DEFAULT NULL,
  `createBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for u_bank_account
-- ----------------------------
DROP TABLE IF EXISTS `u_bank_account`;
CREATE TABLE `u_bank_account`  (
  `id` bigint(18) NULL DEFAULT NULL,
  `userId` bigint(18) NULL DEFAULT NULL COMMENT '持有人',
  `bankName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行',
  `bankAccount` varchar(180) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡账号',
  `purpose` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡作用',
  `createBy` bigint(18) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `modifyTime` datetime NULL DEFAULT NULL,
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for u_function
-- ----------------------------
DROP TABLE IF EXISTS `u_function`;
CREATE TABLE `u_function`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `functionCode` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能编码',
  `functionName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能名称',
  `funcUrl` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能URL',
  `parentId` int(20) NULL DEFAULT NULL COMMENT '功能的上一级',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for u_state
-- ----------------------------
DROP TABLE IF EXISTS `u_state`;
CREATE TABLE `u_state`  (
  `id` bigint(18) NOT NULL,
  `state` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createBy` bigint(18) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `modifyTime` datetime NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of u_state
-- ----------------------------
INSERT INTO `u_state` VALUES (1000, '在职', 1001, '2017-11-28 16:18:15', 1001, '2017-11-28 16:18:21', NULL);
INSERT INTO `u_state` VALUES (1001, '离职', 1001, '2017-11-28 16:35:34', 1001, '2017-11-28 16:35:40', NULL);
INSERT INTO `u_state` VALUES (1002, '其他', 1001, '2017-11-28 16:35:53', 1001, '2017-11-28 16:35:59', NULL);

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `userName` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(6) NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `cardTypeName` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件名',
  `cardCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编号',
  `country` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `county` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体地址',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` bigint(18) NULL DEFAULT NULL,
  `idCardPicPathA` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照片',
  `idCardPicPathB` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面照片',
  `headPortraitPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人头像照',
  `superior` bigint(18) NULL DEFAULT NULL COMMENT '上级id',
  `lastLoginTime` datetime NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `createBy` bigint(18) NULL DEFAULT NULL,
  `modifyTime` datetime NULL DEFAULT NULL,
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `stateId` bigint(18) NULL DEFAULT NULL COMMENT '职工状态 1在职  2离职  3其他',
  `userTypeId` bigint(18) NULL DEFAULT NULL COMMENT ' 是否转正：正式 1 ，试用 2   ，临时工 3',
  `isStart` int(6) NULL DEFAULT NULL COMMENT '是否启用  1启用  2禁用',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES (1001, '13800138000', '123', 'admin', 1, '2017-11-24', '身份证', '45262420171124123x', '中国', '广东省', '深圳市', '福田区', '啊啊', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `u_user` VALUES (1002, '18818991825', '123', 'lingd', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-11-29 23:40:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `u_user` VALUES (1003, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-11-29 18:14:15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `u_user` VALUES (1004, '12', '23', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-11-29 18:11:17', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for u_user_type
-- ----------------------------
DROP TABLE IF EXISTS `u_user_type`;
CREATE TABLE `u_user_type`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `userId` bigint(18) NULL DEFAULT NULL,
  `userType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `typeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `modifyTime` datetime NULL DEFAULT NULL,
  `createBy` bigint(18) NULL DEFAULT NULL,
  `modifyBy` bigint(18) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
