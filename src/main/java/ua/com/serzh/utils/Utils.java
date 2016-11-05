package ua.com.serzh.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Serzh on 11/5/16.
 */
public class Utils {
    private static Properties props;

    public static Properties getProperties() throws IOException {
        if (props != null) {
            return props;
        } else {
            Resource resource = new ClassPathResource("/db.properties");
            props = PropertiesLoaderUtils.loadProperties(resource);
            return props;
        }
    }
}
