package com.marshall.sky.easyrelation.util;

/**
 * @author : livE
 */
public class DefaultPageUtil {

  private DefaultPageUtil() {
  }

  public static int getPageOrDefault(Integer page) {
    return page == null ? 0 : page;
  }

  public static int getCountOrDefault(Integer count) {
    return count == null ? 20 : count;
  }

}
