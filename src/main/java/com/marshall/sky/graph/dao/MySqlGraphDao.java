package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.model.RelationDTO;
import java.util.Collection;

public interface MySqlGraphDao {

  boolean insert(String tableName, RelationDTO relationDTO);

  boolean remove(String tableName, RelationDTO relationDTO);

  boolean batchInsert(String tableName, Collection<RelationDTO> relationDTOCollection);

  boolean batchRemove(String tableName, Collection<RelationDTO> relationDTOCollection);

}
