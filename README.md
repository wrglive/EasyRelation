# graphdb
## 1.0
### 特色
不需要你再去创建 单一 重复的关系表代码 sql语句, 通过bean合理注入由框架统一调度.   
### 用法
表结构请用统一的, 表名  left_right_relation   
配置中的 表名  left_right   
beanName  leftRightGraphDao   
### 后续更新
1.考虑在测试环境 动态创建关系表, 从配置文件中读取相应信息, 避免表结构不统一造成的 bug 。    
2.下一版会简化一些配置方式, 更换命名等。   
3.现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis, 但是目前没这个打算。   
