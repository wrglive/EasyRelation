package com.marshall.sky.graph.dao.mapper;

import com.marshall.sky.graph.model.MySQLBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * pool
 */
public class SqlPoolFactory {
    protected SqlPoolFactory(){}
    private static HikariDataSource hikariDataSource = null;

    protected static final synchronized HikariDataSource getPool(){
        if (hikariDataSource != null) {
            return hikariDataSource;
        }
        SqlPoolFactory.init();
        return hikariDataSource;
    }

    private static void init() {
        final MySQLBean mySQLBean = MySQLBean.getInstanceByYaml();

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mySQLBean.getJdbcUrl());
        hikariConfig.setDriverClassName(mySQLBean.getJdbcDriver());
        hikariConfig.setUsername(mySQLBean.getUsername());
        hikariConfig.setPassword(mySQLBean.getPassword());
        hikariConfig.setMaximumPoolSize(20);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariDataSource = new HikariDataSource(hikariConfig);
    }
}
