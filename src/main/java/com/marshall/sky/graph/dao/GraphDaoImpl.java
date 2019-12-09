package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.model.Relation;
import com.marshall.sky.graph.model.RelationDTO;
import com.marshall.sky.graph.model.StateEnum;
import com.marshall.sky.graph.util.CheckNullUtil;
import com.marshall.sky.graph.util.DefaultPageUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class GraphDaoImpl implements GraphDao {

  private String tableName;

  @Override
  public boolean insert(RelationDTO relationDTO) {
    if (CheckNullUtil.hasNull(relationDTO)) {
      return false;
    }
    String sql = GraphProvider.insert(relationDTO, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return RelationMapper.execute(sql) > 0;
  }

  @Override
  public boolean remove(RelationDTO relationDTO) {
    if (CheckNullUtil.hasNull(relationDTO)) {
      return false;
    }
    String sql = GraphProvider.remove(relationDTO, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return RelationMapper.execute(sql) > 0;
  }

  @Override
  public boolean batchInsert(Collection<RelationDTO> relationDTOCollection) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return false;
    }
    String sql = GraphProvider.batchInsert(relationDTOCollection, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return RelationMapper.execute(sql) > 0;
  }

  @Override
  public boolean batchRemove(Collection<RelationDTO> relationDTOCollection) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return false;
    }
    String sql = GraphProvider.batchRemove(relationDTOCollection, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);

    return RelationMapper.execute(sql) > 0;
  }

  @Override
  public List<Relation> listByLeftId(Long leftId, StateEnum state, Integer page, Integer count) {
    if (leftId == null) {
      return new ArrayList<>();
    }

    page = DefaultPageUtil.getPageOrDefault(page);
    count = DefaultPageUtil.getCountOrDefault(count);
    String sql = GraphProvider.listByLeftId(leftId, state, tableName, page, count);
    System.out.println(sql);

    return RelationMapper.select(sql);
  }

  @Override
  public List<Relation> listByLeftIdAndRightIds(Long leftId,
      Collection<Long> rightIds, StateEnum state, Integer page,
      Integer count) {
    if (leftId == null) {
      return new ArrayList<>();
    }
    if (rightIds == null || rightIds.size() == 0) {
      return listByLeftId(leftId, state, page, count);
    }
    page = DefaultPageUtil.getPageOrDefault(page);
    count = DefaultPageUtil.getCountOrDefault(count);
    String sql = GraphProvider.listByLeftIdAndRightIds(leftId, state, tableName, rightIds, page, count);
    System.out.println(sql);
    return RelationMapper.select(sql);
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
}
