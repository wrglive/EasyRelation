package com.marshall.sky.graph.util;

import com.marshall.sky.graph.model.RelationDTO;

/**
 * @author : livE
 */
public class CheckNullUtil {

  private CheckNullUtil() {
  }

  public static boolean hasNull(RelationDTO relationDTO) {
    return relationDTO == null || relationDTO.getRightId() == null
        || relationDTO.getLeftId() == null;
  }

}
