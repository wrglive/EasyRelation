package com.marshall.sky.easyrelation.dao;

import com.marshall.sky.easyrelation.model.Relation;
import com.marshall.sky.easyrelation.model.RelationDTO;
import com.marshall.sky.easyrelation.model.StateEnum;
import java.util.Collection;
import java.util.List;

public interface EasyRelationDao {

  boolean insert(RelationDTO relationDTO);

  boolean remove(RelationDTO relationDTO);

  boolean batchInsert(Collection<RelationDTO> relationDTOCollection);

  boolean batchRemove(Collection<RelationDTO> relationDTOCollection);

  List<Relation> listByLeftId(Long leftId, StateEnum state, Integer page, Integer count);

  List<Relation> listByLeftIdAndRightIds(Long leftId, Collection<Long> rightIds, StateEnum state, Integer page,
      Integer count);

}
