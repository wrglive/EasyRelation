package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.constant.SQLConstant;
import com.marshall.sky.graph.model.RelationDTO;
import com.marshall.sky.graph.util.CheckNullUtil;
import java.util.Collection;

/**
 * @author : livE
 */
public class GraphProvider extends SQLConstant {


  public static String insert(RelationDTO dto, String tableName) {
    StringBuilder sql = new StringBuilder();
    sql.append(INSERT_INTO)
        .append(getTableName(tableName))
        .append(INSERT_COLUMN)
        .append(VALUES)
        .append("(")
        .append(dto.getLeftId())
        .append(", ")
        .append(dto.getRightId())
        .append(", ")
        .append(System.currentTimeMillis())
        .append(", ")
        .append("1")
        .append(", ")
        .append("'{}'")
        .append(") ")
        .append(ON_DUPLICATE_KEY_UPDATE)
        .append("state = 1");
    return sql.toString();
  }

  public static String remove(RelationDTO dto, String tableName) {
    StringBuilder sql = new StringBuilder();
    sql.append(UPDATE)
        .append(getTableName(tableName))
        .append(SET)
        .append("state = 0")
        .append(WHERE)
        .append("left_id = ")
        .append(dto.getLeftId())
        .append(AND)
        .append("right_id = ")
        .append(dto.getRightId());
    return sql.toString();
  }

  public static String batchInsert(Collection<RelationDTO> relationDTOCollection,
      String tableName) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return null;
    }
    int insertSize = 0;
    StringBuilder sql = new StringBuilder();
    sql.append(INSERT_INTO)
        .append(getTableName(tableName))
        .append(INSERT_COLUMN)
        .append(VALUES);

    for (RelationDTO relationDTO : relationDTOCollection) {
      if (CheckNullUtil.hasNull(relationDTO)) {
        continue;
      }
      if (insertSize > 0) {
        sql.append(" , ");
      }
      sql.append("(")
          .append(relationDTO.getLeftId())
          .append(", ")
          .append(relationDTO.getRightId())
          .append(", ")
          .append(System.currentTimeMillis())
          .append(", ")
          .append("1")
          .append(", ")
          .append("'{}'")
          .append(") ");
      insertSize++;
    }
    if (insertSize == 0) {
      return null;
    }
    sql.append(ON_DUPLICATE_KEY_UPDATE)
        .append("state = 1");
    return sql.toString();
  }

  public static String batchRemove(Collection<RelationDTO> relationDTOCollection,
      String tableName) {
    if (relationDTOCollection == null || relationDTOCollection.size() == 0) {
      return null;
    }
    int whereSize = 0;
    StringBuilder sql = new StringBuilder();
    sql.append(UPDATE)
        .append(getTableName(tableName))
        .append(SET)
        .append("state = 0")
        .append(WHERE)
        .append("(left_id, right_id) in ")
        .append(" ( ");

    for (RelationDTO relationDTO : relationDTOCollection) {
      if (CheckNullUtil.hasNull(relationDTO)) {
        continue;
      }
      if (whereSize > 0) {
        sql.append(" , ");
      }
      sql.append(" ( ")
          .append(relationDTO.getLeftId())
          .append(" , ")
          .append(relationDTO.getRightId())
          .append(" ) ");
      whereSize++;
    }

    if (whereSize == 0) {
      return null;
    }
    sql.append(" ) ");
    return sql.toString();
  }

  private static String getTableName(String tableName) {
    return tableName + TABLE_SUFFIX;
  }

}
