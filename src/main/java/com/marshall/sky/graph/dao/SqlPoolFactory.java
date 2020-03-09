package com.marshall.sky.graph.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * pool
 */
@Configuration
public class SqlPoolFactory implements EnvironmentAware {

    protected static Environment environment;
    private static HikariDataSource hikariDataSource = null;

    protected SqlPoolFactory() {
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    protected static final synchronized HikariDataSource getPool() {
        if (hikariDataSource != null) {
            return hikariDataSource;
        }
        SqlPoolFactory.init();
        return hikariDataSource;
    }

    private static void init() {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("sky.graph.datasource.url"));
        hikariConfig.setDriverClassName(environment.getProperty("sky.graph.datasource.driver-class-name"));
        hikariConfig.setUsername(environment.getProperty("sky.graph.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("sky.graph.datasource.password"));
        hikariConfig.setMaximumPoolSize(20);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariDataSource = new HikariDataSource(hikariConfig);
    }
}
