package Project;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Class to read queries from text files and parse them in case they need
 * specific parameters
 */
public class QueryTemplate {
    private StringTemplate queryTemplate;

    /**
     * Set attribute for query
     *
     * @param key   Key to set
     * @param value Value for the key
     */
    public void setAttribute(String key, String value) {
        this.queryTemplate.setAttribute(key, value);
    }

    /**
     * Return parsed query
     *
     * @return String parsed query
     */
    public String getQuery() {
        return this.queryTemplate.toString();
    }

    /**
     * Class constructor
     * Queries are stored in resources/[queries_path] folder, set on config.properties file
     *
     * @param queryName Name of the query (no extension)
     */
    public QueryTemplate(String queryName) {
        try {
            PropertyReader properties = new PropertyReader();
            String queryPath = properties.getProperty("queries_path");
            String queryExtension = properties.getProperty("queries_extension");

            //Construct relative path for query file
            ClassLoader classLoader = getClass().getClassLoader();
            String relativePath = queryPath + queryName + "." + queryExtension;

            //Get content from query
            String queryContent = IOUtils.toString(classLoader.getResourceAsStream(relativePath));

            // Create String template
            this.queryTemplate = new StringTemplate(queryContent);
        } catch (IOException e) {
        }
    }

}
