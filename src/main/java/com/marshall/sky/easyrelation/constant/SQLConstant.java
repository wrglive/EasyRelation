package com.marshall.sky.easyrelation.constant;

public class SQLConstant {

  protected static final String INSERT_INTO = "insert into ";

  protected static final String VALUES = " values ";

  protected static final String UPDATE = "update ";

  protected static final String SET = " set ";

  protected static final String ON_DUPLICATE_KEY_UPDATE = " ON DUPLICATE KEY UPDATE ";

  protected static final String INSERT_COLUMN = " (left_id, right_id, create_time, state, ext_params) ";

  protected static final String TABLE_SUFFIX = "_relation ";

  protected static final String WHERE = " where ";

  protected static final String AND = " and ";

  protected static final String SELECT = " select ";

  protected static final String FROM = " from ";

  protected static final String QUERY_COLUMN = "left_id, right_id, create_time, state, ext_params, update_time";

  protected static final String LIMIT = " limit ";

}
