package com.marshall.sky.graph.model;

import com.marshall.sky.graph.config.YamlConfiguration;

import java.util.Properties;

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

    public static MySQLBean getInstanceByYaml() {
        Properties properties = YamlConfiguration.getProperties();
        String username = properties.getProperty("sky.graph.datasource.username");
        String password = properties.getProperty("sky.graph.datasource.password");
        String driver = properties.getProperty("sky.graph.datasource.driver-class-name");
        String url = properties.getProperty("sky.graph.datasource.url");
        return MySQLBean.newInstance(username, password, driver, url);
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
