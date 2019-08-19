# graphdb
## 1.0
### 特色
不需要你再去创建 单一 重复的关系表代码 sql语句, 通过bean合理注入由框架统一调度.
### 用法
表结构请用统一的, 表名  left_right_relation
配置中的 表名  left_right
beanName  leftRightGraphDao
### 后续更新
考虑在测试环境 动态创建关系表, 从配置文件中读取相应信息, 避免表结构不统一造成的 bug 。
