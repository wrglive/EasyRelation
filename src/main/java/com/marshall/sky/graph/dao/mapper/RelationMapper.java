package com.marshall.sky.graph.dao.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface RelationMapper {

  @InsertProvider(type = SQLHandle.class, method = "execute")
  int insert(String sql);

  @UpdateProvider(type = SQLHandle.class, method = "execute")
  int remove(String sql);
}
