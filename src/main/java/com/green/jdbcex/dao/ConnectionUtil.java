package com.green.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {

    INSTANCE;

    private HikariDataSource hikariDataSource;

    ConnectionUtil(){
        HikariConfig config=new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtsCacheSize","250");
        config.addDataSourceProperty("prepStmtsCacheSqlLimit","2048");

        hikariDataSource=new HikariDataSource(config);
    }

    public Connection getConnection() throws Exception{
        return hikariDataSource.getConnection();
    }

}
