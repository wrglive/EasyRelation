package com.marshall.sky.easyrelation.util;

import com.marshall.sky.easyrelation.model.RelationDTO;

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
