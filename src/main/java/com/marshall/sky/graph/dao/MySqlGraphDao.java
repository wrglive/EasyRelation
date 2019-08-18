package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.model.Relation;
import com.marshall.sky.graph.model.RelationDTO;
import java.util.Collection;
import java.util.List;

public interface MySqlGraphDao {

  boolean insert(RelationDTO relationDTO);

  boolean remove(RelationDTO relationDTO);

  boolean batchInsert(Collection<RelationDTO> relationDTOCollection);

  boolean batchRemove(Collection<RelationDTO> relationDTOCollection);

  List<Relation> listByLeftId(Long leftId, Integer page, Integer count);

  List<Relation> listByLeftIdAndRightIds(Long leftId, Collection<Long> rightIds, Integer page,
      Integer count);

}
