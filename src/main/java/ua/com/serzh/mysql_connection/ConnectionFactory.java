package ua.com.serzh.mysql_connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private /*static*/ final String PROP_PATH = "/db.properties";
    private /*static*/ Properties properties = new Properties();
    private static ConnectionFactory instance = new ConnectionFactory();

    static Connection connection;

    private ConnectionFactory() {
        try {
            getProperties();
            Class.forName(properties.getProperty("DRIVER_CLASS"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private /*static*/ /*Properties*/ void getProperties() {
        try {
            InputStream  is = ConnectionFactory.class.getResourceAsStream(PROP_PATH);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return properties;
    }

    private Connection createConnection() {

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
        if (connection != null) {
            return connection;
        }
        return instance.createConnection();
    }
}
