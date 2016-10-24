package ua.com.serzh.mysql_connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private Properties properties;
    private static Connection connection;

    // TODO look to Singleton, because this looks strange
    private static ConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            getProperties();
            Class.forName(properties.getProperty("DRIVER_CLASS"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getProperties() {
        try {
            properties = new Properties();
            InputStream is = ConnectionFactory.class.getResourceAsStream("/db.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("DB_URL"),
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
