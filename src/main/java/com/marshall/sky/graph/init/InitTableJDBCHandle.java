package com.marshall.sky.graph.init;

import com.marshall.sky.graph.model.MySQLBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : livE
 */
public class InitTableJDBCHandle {


  private Connection connection;
  private PreparedStatement preparedStatement;


  protected InitTableJDBCHandle(String sql, MySQLBean bean) {
    try {
      Class.forName(bean.getJdbcDriver());
      connection = DriverManager
          .getConnection(bean.getJdbcUrl(), bean.getUsername(), bean.getPassword());
      preparedStatement = connection.prepareStatement(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected boolean execute() {
    boolean rst = false;
    try {
      rst = preparedStatement.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      close();
    }
    return rst;
  }

  private void close() {
    try {
      this.connection.close();
      this.preparedStatement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
