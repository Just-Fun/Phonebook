package ua.com.serzh.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Serzh on 11/5/16.
 */
public class Utils implements InitializingBean {

    public Properties getProperties() throws IOException {
            Resource resource = new ClassPathResource("/db.properties");
           return PropertiesLoaderUtils.loadProperties(resource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
