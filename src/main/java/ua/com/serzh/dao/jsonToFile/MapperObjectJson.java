package ua.com.serzh.dao.jsonToFile;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Serzh on 11/4/16.
 */
public class MapperObjectJson {

    // Convert JSON string from file to Object
    public static Object getObjectFromFile(String pathName, String className) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(pathName);

            Object object = mapper.readValue(file, Class.forName(className));

            return object;
        } catch (EOFException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static void writeJsonToFile(Object object, String pathName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(pathName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, object);

            //Convert object to JSON string and pretty print
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

            // 2nd variant - not pretty but compact
//            String jsonInString = mapper.writeValueAsString(object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}