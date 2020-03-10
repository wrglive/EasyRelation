package com.marshall.sky.easyrelation.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * pool
 */
@Configuration
public class JdbcPoolFactory implements EnvironmentAware {

    protected static Environment environment;
    private static HikariDataSource hikariDataSource = null;

    protected JdbcPoolFactory() {
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    protected static final synchronized HikariDataSource getPool() {
        if (hikariDataSource != null) {
            return hikariDataSource;
        }
        JdbcPoolFactory.init();
        return hikariDataSource;
    }

    private static void init() {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("sky.easy-relation.datasource.url"));
        hikariConfig.setDriverClassName(environment.getProperty("sky.easy-relation.datasource.driver-class-name"));
        hikariConfig.setUsername(environment.getProperty("sky.easy-relation.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("sky.easy-relation.datasource.password"));
        hikariConfig.setMaximumPoolSize(20);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariDataSource = new HikariDataSource(hikariConfig);
    }
}
