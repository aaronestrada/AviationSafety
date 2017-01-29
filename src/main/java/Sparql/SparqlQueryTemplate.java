package Sparql;

import General.ConfigPropertyReader;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Class to read queries from text files and parse them in case they need
 * specific parameters
 */
public class SparqlQueryTemplate {
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
    public SparqlQueryTemplate(String queryName) {
        this.initQueryTemplate(queryName, true);
    }

    /**
     * Class constructor
     *
     * @param queryName Name of the query (no extension)
     * @param usePrefix If prefix are needed to read query
     */
    public SparqlQueryTemplate(String queryName, Boolean usePrefix) {
        this.initQueryTemplate(queryName, usePrefix);
    }

    /**
     * Initialize query template
     *
     * @param queryName Name of the query (no extension)
     * @param usePrefix If prefix are needed to read query
     */
    private void initQueryTemplate(String queryName, Boolean usePrefix) {
        try {
            ConfigPropertyReader properties = new ConfigPropertyReader();
            String queryPath = properties.getProperty("queries_path");
            String queryExtension = properties.getProperty("queries_extension");
            String queryPrefix = properties.getProperty("prefix_base_query");

            //Construct relative path for query file
            ClassLoader classLoader = getClass().getClassLoader();
            String relativePath = queryPath + queryName + "." + queryExtension;

            //Get content from query
            String queryContent = IOUtils.toString(classLoader.getResourceAsStream(relativePath));

            //Get content for prefixes
            String prefixContent = "";
            if (usePrefix) {
                String prefixRelativePath = queryPath + queryPrefix + "." + queryExtension;
                prefixContent = IOUtils.toString(classLoader.getResourceAsStream(prefixRelativePath));
            }

            // Create String template
            this.queryTemplate = new StringTemplate(prefixContent + queryContent);
        } catch (IOException e) {
        }
    }

}
