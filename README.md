# graphdb
## 1.0
### 特色
不需要你再去创建 单一 重复的关系表代码 sql语句, 通过bean合理注入由框架统一调度.   
### 表结构
SET NAMES utf8mb4;   
SET FOREIGN_KEY_CHECKS = 0;   
   
DROP TABLE IF EXISTS `left_right_relation`;   
CREATE TABLE `left_right_relation`  (   
  `left_id` bigint(20) NOT NULL,   
  `right_id` bigint(20) NOT NULL,   
  `create_time` bigint(13) NOT NULL,   
  `update_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),   
  `state` tinyint(1) NOT NULL,   
  `ext_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,    
  UNIQUE INDEX `ux_left_right`(`left_id`, `right_id`) USING BTREE,   
  INDEX `idx_right_id`(`right_id`) USING BTREE   
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;   
   
SET FOREIGN_KEY_CHECKS = 1;   
   
### 用法
表结构请用统一的, 表名  left_right_relation   
`sky-graphdb.properties` 配置文件 配置 `prefixTableName` "["a","b"]"     
beanName  leftRightGraphDao   
### 后续更新
1.考虑在测试环境 动态创建关系表, 从配置文件中读取相应信息, 避免表结构不统一造成的 bug 。    
2.下一版会简化一些配置方式, 更换命名等。   
3.现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis, 但是目前没这个打算。   
