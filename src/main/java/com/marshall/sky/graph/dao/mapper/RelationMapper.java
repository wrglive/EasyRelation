package com.marshall.sky.graph.dao.mapper;

import com.marshall.sky.graph.model.Relation;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface RelationMapper {

  @InsertProvider(type = SQLHandle.class, method = "execute")
  int insert(String sql);

  @UpdateProvider(type = SQLHandle.class, method = "execute")
  int remove(String sql);

  @SelectProvider(type = SQLHandle.class, method = "execute")
  List<Relation> select(String sql);
}
