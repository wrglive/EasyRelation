package com.marshall.sky.graph.init;

public class CreateTableSqlBuilder {

  private static final String CREATE = "CREATE TABLE IF NOT EXISTS `%s_relation`  (\n"
      + "  `left_id` bigint(20) NOT NULL,\n"
      + "  `right_id` bigint(20) NOT NULL,\n"
      + "  `create_time` bigint(13) NOT NULL,\n"
      + "  `update_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),\n"
      + "  `state` tinyint(1) NOT NULL,\n"
      + "  `ext_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\n"
      + "  UNIQUE INDEX `ux_left_right`(`left_id`, `right_id`) USING BTREE,\n"
      + "  INDEX `idx_right_id`(`right_id`) USING BTREE\n"
      + ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;\n";

  protected static String buildCreateSQL(String tableName) {
    return String.format(CREATE, tableName);
  }


}
