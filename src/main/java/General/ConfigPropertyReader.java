package General;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Read config.properties file for project configuration
 */
public class ConfigPropertyReader extends java.util.Properties {

    public ConfigPropertyReader() throws FileNotFoundException {
        super();

        String propFileName = "config.properties";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            this.load(inputStream);
        } catch (IOException e) {
            throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
        }
    }
}
