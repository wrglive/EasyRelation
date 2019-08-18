
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_channel_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_channel_relation`;
CREATE TABLE `user_channel_relation`  (
  `left_id` bigint(20) NOT NULL,
  `right_id` bigint(20) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  `update_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `state` tinyint(1) NOT NULL,
  `ext_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`left_id`) USING BTREE,
  UNIQUE INDEX `ux_left_right`(`left_id`, `right_id`) USING BTREE,
  INDEX `idx_right_id`(`right_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
