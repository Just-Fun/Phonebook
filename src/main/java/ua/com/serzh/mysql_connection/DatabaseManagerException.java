package ua.com.serzh.mysql_connection;

import java.sql.SQLException;

/**
 * Created by Serzh on 10/24/16.
 */
public class DatabaseManagerException extends RuntimeException {
    DatabaseManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
