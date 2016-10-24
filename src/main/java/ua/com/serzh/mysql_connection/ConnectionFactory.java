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

    String driver_class;
    String db_url;
    String user_name;
    String user_password;

    // TODO look to Singleton, because this looks strange
    private static ConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            getProperties();
            Class.forName(driver_class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getProperties() {
            properties = new Properties();
        try (InputStream is = ConnectionFactory.class.getResourceAsStream("/db.properties")) {
//        try (FileInputStream is = new FileInputStream("/db.properties")) { // dont work...
            properties.load(is);

            driver_class = properties.getProperty("DRIVER_CLASS");
            db_url = properties.getProperty("DB_URL");
            user_name = properties.getProperty("USER_NAME");
            user_password = properties.getProperty("USER_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("Some trouble with loading properties: " + e.getCause());
        }
    }

    private Connection createConnection() {
        try {
            connection = DriverManager.getConnection(db_url, user_name, user_password);
        } catch (SQLException e) {
            connection = null;
            throw new DatabaseManagerException(
                    String.format("Cant get connection for db:%s user:%s", db_url, user_name), e);
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
