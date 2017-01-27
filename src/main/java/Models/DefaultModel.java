package Models;

import General.ConfigPropertyReader;
import Sparql.AviationRepository;
import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Model abstraction to retrieve instances from data
 */
public class DefaultModel {
    //Repository to retrieve data
    private AviationRepository repository;

    //Query template to read queries
    private SparqlQueryTemplate queryText;

    //Prefixes and base model prefixes
    private String propertyPrefix = "";
    private String baseModelQuery = "";

    //List of properties for an instance of a model
    private Map<String, Object> instanceProperties;
    private ArrayList modelPropertyItems;

    /**
     * For each data model, these attributes store the name of the model,
     * the ID to retrieve instances and a list of properties to retrieve.
     */
    private String modelName;
    private String modelId;
    private String[] modelProperties;
    private Boolean useQuotes;

    /**
     * Class constructor
     *
     * @param modelName Name of model
     * @param modelId   ID to retrieve instances
     * @param useQuotes If ID needs quotes, set this flag to True
     */
    public DefaultModel(String modelName, String modelId, Boolean useQuotes) {
        this.modelName = modelName;
        this.modelId = modelId;
        this.useQuotes = useQuotes;

        //Initialize aviation repository
        this.repository = new AviationRepository();

        //Initialize properties for instance
        this.instanceProperties = new HashMap<String, Object>();

        // Get data and properties prefixes
        String ontologyPrefix;
        String propertyPrefix;
        try {
            ConfigPropertyReader properties = new ConfigPropertyReader();
            ontologyPrefix = properties.getProperty("ontology_prefix");
            propertyPrefix = properties.getProperty("property_prefix");

            //Get base model query
            this.baseModelQuery = properties.getProperty("model_base_query");

            //Store property prefix
            this.propertyPrefix = ontologyPrefix + propertyPrefix;
        } catch (IOException e) {
        }

    }

    /**
     * Set properties for model based on a list
     *
     * @param modelProperties List of properties
     */
    public void setModelProperties(String[] modelProperties) {
        this.modelProperties = modelProperties;

        //Set property items for model
        this.modelPropertyItems = new ArrayList();
        for (int index = 0; index < this.modelProperties.length; index++)
            this.modelPropertyItems.add(this.propertyPrefix + this.modelProperties[index]);

    }

    /**
     * Retrieve property value for instance
     *
     * @param key Key to retrieve
     * @return Value for the key
     */
    public String getProperty(String key) {
        String propertyPrefix = this.propertyPrefix + key;

        if (this.instanceProperties.containsKey(propertyPrefix))
            return this.instanceProperties.get(propertyPrefix).toString();
        return "";
    }

    /**
     * Search an instance of the model
     *
     * @param id ID to search
     */
    public void getInstance(String id) {
        //Prepare query
        this.queryText = new SparqlQueryTemplate(this.baseModelQuery);
        this.queryText.setAttribute("model", this.modelName);
        this.queryText.setAttribute("prop_id", this.modelId);

        // Add quotes to ID if necessary
        if (this.useQuotes) id = "\"" + id + "\"";
        this.queryText.setAttribute("id", id);

        //Make query
        String queryString = queryText.getQuery();
        TupleQueryResult result = this.repository.makeQuery(queryString);

        //Get properties from instance
        if (result != null) {
            String property;
            String value;

            while (result.hasNext()) {
                BindingSet bindingSet = result.next();

                property = bindingSet.getValue("property").stringValue();
                value = bindingSet.getValue("value").stringValue();

                //Store property if set in constructor
                if (this.modelPropertyItems.contains(property))
                    this.instanceProperties.put(property, value);
            }
        }

        //Close the repository connection
        this.repository.closeRepository();

    }
}
