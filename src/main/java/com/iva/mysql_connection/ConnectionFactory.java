package com.iva.mysql_connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PROP_PATH = "/db.properties";
    private static Properties properties = new Properties();
    private static ConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            getProperties();
            Class.forName(properties.getProperty("DRIVER_CLASS"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static Properties getProperties() {
        InputStream is = null;

        try {
            is = ConnectionFactory.class.getResourceAsStream("/db.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(properties.getProperty("DB_URL"),
                                                    properties.getProperty("USER_NAME"),
                                                    properties.getProperty("USER_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
