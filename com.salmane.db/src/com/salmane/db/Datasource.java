package com.salmane.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Datasource {
    private final String url;
    private final String user;
    private final String password;
    private final String database;
    private Connection connection;

    private static Datasource instance;

    private Datasource(String url, String database, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public static Datasource getInstance(String url, String database, String user, String password) {
        instance = instance == null ? new Datasource(url, database, user, password) : instance;
        return instance;
    }

    public static Datasource getInstance() throws SQLException {
        if (instance == null) {
            throw new SQLException("Database not initialized");
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            final String connectionUrl = this.url + this.database;
            Properties properties = new Properties();
            properties.setProperty("user", this.user);
            properties.setProperty("password", this.password);
            properties.setProperty("database", this.database);

            connection = DriverManager.getConnection(connectionUrl, properties);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
