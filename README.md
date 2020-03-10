## EasyRelation

### 简介
    顾名思义, 简化关系表的存在, 试用与个人开发者在设计关注， 点赞, 帖子话题等各种关系的场景。    
             
### 特点
1.一键式配置关系表名称， 自动生成关系表结构， 告别重复的crud, 详见快速开始        
2.整合到springboot的配置文件中, 不需要再冗余其他配置文件            

### 快速开始    
#### 配置文件       
```
sky:       
  easy-relation: 
    #表名生成规则， ex: like_pet_variety_relation          
    prefixTableNames: like_pet_variety,like_pet,follow_user,user_follow
    datasource:
      url: jdbc:mysql://122222:3306/sky?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
      username: root
      password: root
      driver-class-name: com.mysql.cj.jdbc.Driver
```

#### 代码
```
@Resource(name = "user_follow") 
private EasyRelationDao user_follow;

//查询关注列表fromUserId关注列表
List<Relation> followUidList = userFollow.listByLeftIdAndRightIds(fromUserId, StateEnum.ONLINE, 0, 1);

```

#### 目前的API如下

```
public interface EasyRelationDao {

  boolean insert(RelationDTO relationDTO);

  boolean remove(RelationDTO relationDTO);

  boolean batchInsert(Collection<RelationDTO> relationDTOCollection);

  boolean batchRemove(Collection<RelationDTO> relationDTOCollection);

  List<Relation> listByLeftId(Long leftId, StateEnum state, Integer page, Integer count);

  List<Relation> listByLeftIdAndRightIds(Long leftId, Collection<Long> rightIds, StateEnum state, Integer page,
      Integer count);

}
```

#### 表结构
```
CREATE TABLE `like_pet_relation` (
  `left_id` bigint(20) NOT NULL,
  `right_id` bigint(20) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` tinyint(1) NOT NULL,
  `ext_params` varchar(255) DEFAULT NULL,
  UNIQUE KEY `ux_left_right` (`left_id`,`right_id`) USING BTREE,
  KEY `idx_right_id` (`right_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
```

### 未来计划
* [x] 现在对mybatis是强依赖， 以后可能考虑引入jdbcTemplate 来替换 mybatis             
* [ ] 对table进行扩充， 目前字段只是最原始的字段，考虑加几个字段来给各个业务当冗余字段使用
* [ ] 目前任何环境都会自动创建, 后续考虑只有test环境动态创建表
* [x] mybatis被其他框架替代, 例如jdbcTemplate 或者jdbc原生， 亦或者自己搭建一个jdbc 框架。    
* [ ] 多数据源配置.      
* [ ] 高并发下, 性能问题.    


