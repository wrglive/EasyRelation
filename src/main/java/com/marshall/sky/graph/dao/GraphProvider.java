package com.marshall.sky.graph.dao;

import com.marshall.sky.graph.constant.SQLConstant;
import com.marshall.sky.graph.model.RelationDTO;
import com.marshall.sky.graph.util.CheckNullUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.lang.NonNull;

/**
 * @author : livE
 */
public class GraphProvider extends SQLConstant {


  protected static String insert(RelationDTO dto, String tableName) {
    List<RelationDTO> relationDTOS = new ArrayList<>();
    relationDTOS.add(dto);
    return batchInsert(relationDTOS, tableName);
  }

  protected static String remove(RelationDTO dto, String tableName) {
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

  protected static String batchInsert(Collection<RelationDTO> relationDTOCollection,
      String tableName) {
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

  protected static String batchRemove(Collection<RelationDTO> relationDTOCollection,
      String tableName) {
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

  protected static String listByLeftId(long leftId, String tableName, int page, int count) {
    StringBuilder sql = buildSelectSQL(tableName);

    sql.append(WHERE)
        .append(" left_id = ")
        .append(leftId)
        .append(LIMIT)
        .append(getLimit(page, count));

    return sql.toString();
  }

  protected static String listByLeftIdAndRightIds(Long leftId, String tableName,
      Collection<Long> rightIds, Integer page,
      Integer count) {
    if (rightIds == null || rightIds.size() == 0) {
      return listByLeftId(leftId, tableName, page, count);
    }

    StringBuilder sql = buildSelectSQL(tableName);
    sql.append(WHERE)
        .append(" left_id = ")
        .append(leftId)
        .append(AND)
        .append(" right_id IN ")
        .append(getInSQL(rightIds))
        .append(LIMIT)
        .append(getLimit(page, count));

    return sql.toString();
  }

  private static StringBuilder buildSelectSQL(String tableName) {
    return new StringBuilder().append(SELECT).append(QUERY_COLUMN).append(FROM)
        .append(getTableName(tableName));
  }

  private static String getInSQL(@NonNull Collection<Long> ids) {
    StringBuilder sql = new StringBuilder();
    sql.append(" ( ");
    int idCount = 0;

    for (Long id : ids) {
      if (idCount > 0) {
        sql.append(" , ");
      }
      sql.append(id);
      idCount++;
    }

    sql.append(" ) ");
    return sql.toString();
  }


  private static String getTableName(String tableName) {
    return tableName + TABLE_SUFFIX;
  }

  private static String getLimit(int page, int count) {
    int limit = page * count;
    return limit + " , " + count;
  }

}
