## GraphDB

### 简介
为了简化关系表的存在, 以往关系表其实都是大同小异, 重复代码繁多, 冗余, 所以graphdb的开发就是为了偷懒, 本版基于spring boot开发.

### 更新
1.0.3 现在将graph整合到spring的 yaml配置文件中, 数据源应用spring.datasource的数据源  
1.0.2 现在可以配置sky-graphdb.properties配置文件进行数据库表的自动生成，规则为user_feed 生成的表名为user_feed_relation beanName: userFeedGraphDao         

### 用法
1. 配置,基于springboot- application.yaml文件, 注意目前仅支持这种文件, 后续会考虑动态寻找spring boot的配置
```
sky:  
graph:    
##表名前缀, 现在会动态创建表名为 like_pet_variety_graph的表
prefixTableNames: like_pet_variety,like_pet,user_follow

```

2.注入使用即可
```
//注入的时候使用prefixTableNames的驼峰+GraphDao
//例如 user_follow 对应 userFollowGraphDao
@Resource(name = "userFollowGraphDao")
private GraphDao userFollow;

//具体api使用, 就跟正常调用方法一样
List<Relation> relations = userFollow.listByLeftIdAndRightIds(fromUserId, 
Lists.newArrayList(toUserId), StateEnum.ONLINE, 0, 1);

```

3.提供一下api吧

```
public interface GraphDao {

  boolean insert(RelationDTO relationDTO);

  boolean remove(RelationDTO relationDTO);

  boolean batchInsert(Collection<RelationDTO> relationDTOCollection);

  boolean batchRemove(Collection<RelationDTO> relationDTOCollection);

  List<Relation> listByLeftId(Long leftId, StateEnum state, Integer page, Integer count);

  List<Relation> listByLeftIdAndRightIds(Long leftId, Collection<Long> rightIds, StateEnum state, Integer page,
      Integer count);

}
```


### 后续更新
                  
1.现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis, 但是目前没这个打算。              
             
2.对table进行扩充， 目前字段只是最原始的字段，考虑加几个字段来给各个业务当冗余字段使用

3.目前任何环境都会自动创建, 后续考虑只有test环境动态创建表

4.目前就仅支持 resource/application-xx.yaml 里配置参数, 以后考虑跟boot一样 会按顺序寻找 pro, yaml, yml 配置文件去动态读取配置.
       
### 长远计划
1.mybatis被其他框架替代, 例如jdbcTemplate 或者jdbc原生， 亦或者自己搭建一个jdbc 框架。    
2.如果用单独的数据源或者原生jdbc, 是否有性能问题, 这个值得斟酌.           


