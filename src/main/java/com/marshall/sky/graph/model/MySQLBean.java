package com.marshall.sky.graph.model;

/**
 * @author : livE
 */
public class MySQLBean {

  private String username;
  private String password;
  private String jdbcDriver;
  private String jdbcUrl;

  private MySQLBean() {
  }

  private MySQLBean(String username, String password, String jdbcDriver, String jdbcUrl) {
    this.username = username;
    this.password = password;
    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
  }

  public static MySQLBean newInstance(String username, String password, String jdbcDriver,
      String jdbcUrl) {
    return new MySQLBean(username, password, jdbcDriver, jdbcUrl);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getJdbcDriver() {
    return jdbcDriver;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }
}
