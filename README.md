# graphdb
## 1.0
### 1.0.2更新
现在可以配置sky-graphdb.properties配置文件进行数据库表的自动生成，规则为user_feed 生成的表名为user_feed_relation beanName: userFeedGraphDao         
### 特色
以往关系表的sql, 以及业务代码都是大量重复代码, 每次都得创建一系列的方法, 体验很差   
现在你可以通过注解去注入不同的关系表来操作相应的关系表.
### 表结构
`已经不需要了, 目前全局自动生成table来强制达成一致性, 以后考虑只对测试环境生效。`
   
### 用法
1.请根据表结构创建表, 但是请改名 left_id, right_id对应 表名对应的左右两个表.  
2.sky-graphdb.properties 配置文件 配置 prefixTableName "["user_channel","b"]"   
3.注入@resource(name = "userChannelGraphDao")
4.GraphDao.java 是具体调用的方法.
### 后续更新
1.考虑在测试环境 动态创建关系表, 从配置文件中读取相应信息, 避免表结构不统一造成的 bug 。        
目前可以自动创建表, 创建到配置中的数据库, 后续考虑判断出是否是测试环境 只创建测试环境， 避免线上数据库污染.       
           
2.下一版会简化一些配置方式, 更换命名等。       
已经完成。      
         
3.现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis, 但是目前没这个打算。              
目前 初始化数据库表使用的jdbc原生完成的, 因为不想把一些方法暴露给外边。             
        
4.目前数据库还是用的yaml里的数据源, 以后考虑跟sky-garphdb里的配置源用一套。        
5.对table进行扩充， 目前字段只是最原始的字段，考虑加几个字段来给各个业务当冗余字段使用
       
### 长远计划
1.mybatis被其他框架替代, 例如jdbcTemplate 或者jdbc原生， 亦或者自己搭建一个jdbc 框架。    
2.如果用单独的数据源或者原生jdbc, 是否有性能问题, 这个值得考量.           


