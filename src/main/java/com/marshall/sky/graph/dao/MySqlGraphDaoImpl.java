package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.dao.mapper.RelationMapper;
import com.marshall.sky.graph.model.RelationDTO;
import com.marshall.sky.graph.util.CheckNullUtil;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySqlGraphDaoImpl implements MySqlGraphDao {

  @Autowired
  RelationMapper relationMapper;

  @Override
  public boolean insert(String tableName, RelationDTO relationDTO) {
    if (CheckNullUtil.hasNull(relationDTO)) {
      return false;
    }
    String sql = GraphProvider.insert(relationDTO, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return relationMapper.insert(sql) > 0;
  }

  @Override
  public boolean remove(String tableName, RelationDTO relationDTO) {
    if (CheckNullUtil.hasNull(relationDTO)) {
      return false;
    }
    String sql = GraphProvider.remove(relationDTO, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return relationMapper.remove(sql) > 0;
  }

  @Override
  public boolean batchInsert(String tableName, Collection<RelationDTO> relationDTOCollection) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return false;
    }
    String sql = GraphProvider.batchInsert(relationDTOCollection, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);
    return relationMapper.insert(sql) > 0;
  }

  @Override
  public boolean batchRemove(String tableName, Collection<RelationDTO> relationDTOCollection) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return false;
    }
    String sql = GraphProvider.batchRemove(relationDTOCollection, tableName);
    if (StringUtils.isBlank(sql)) {
      return false;
    }
    System.out.println(sql);

    return relationMapper.remove(sql) > 0;
  }

}
