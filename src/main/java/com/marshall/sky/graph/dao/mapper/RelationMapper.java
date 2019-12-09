package com.marshall.sky.graph.dao.mapper;

import com.marshall.sky.graph.model.Relation;

import java.util.List;

public interface RelationMapper {

    int insert(String sql);

    int remove(String sql);

    List<Relation> select(String sql);
}
