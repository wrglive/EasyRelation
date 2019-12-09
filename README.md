## GraphDB

### 简介
    为了简化关系表而设计的, 所有的设计都是为了更好的偷懒...

### 更新
**1.0.4** 现在彻底抛弃mybatis 使用 **原生的jdbc + HikariCP** 连接池    
**1.0.3** 现在将graph整合到spring的 yaml配置文件中,


### 用法
1. 配置,基于springboot- application.yaml文件, 注意目前仅支持这种文件, 后续会考虑动态寻找spring boot的配置
```
sky:  
    graph:    
        ##表名前缀, 现在会动态创建表名为 like_pet_variety_relation的表    
        prefixTableNames: like_pet_variety,like_pet,user_follow    
    datasource:    
      url: jdbc:mysql://12345:3306/sky-graph?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true    
      username: root    
      password: root\    
      driver-class-name: com.mysql.cj.jdbc.Driver   
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

3.目前的API如下

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
                  

* [x] 现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis             
* [ ] 对table进行扩充， 目前字段只是最原始的字段，考虑加几个字段来给各个业务当冗余字段使用
* [ ] 目前任何环境都会自动创建, 后续考虑只有test环境动态创建表
* [ ] 目前就仅支持 resource/application-xx.yaml 里配置参数, 以后考虑跟boot一样 会按顺序寻找 pro, yaml, yml 配置文件去动态读取配置.
   

### 长远计划
1.mybatis被其他框架替代, 例如jdbcTemplate 或者jdbc原生， 亦或者自己搭建一个jdbc 框架。    
**解决方案**: 使用jdbc + HikariCP 来实现, 尽量规避三方库     
2.多数据源配置.      
3.高并发下, 性能问题.    


