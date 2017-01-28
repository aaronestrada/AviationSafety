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
    protected AviationRepository repository;

    //Query template to read queries
    protected SparqlQueryTemplate queryText;

    //Prefixes and base model prefixes
    protected String propertyPrefix = "";
    protected String resourcesPrefix = "";
    protected String baseModelQuery = "";
    protected String baseModelQueryAll = "";
    protected String baseModelQueryAllCount = "";

    //List of properties for an instance of a model
    protected Map<String, Object> instanceProperties;
    protected ArrayList modelPropertyItems;

    /**
     * For each data model, these attributes store the name of the model,
     * the ID to retrieve instances and a list of properties to retrieve.
     */
    protected String modelName;
    protected String modelId;
    protected String[] modelProperties;
    protected Boolean useQuotes;

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
        String resourcesPrefix;
        try {
            ConfigPropertyReader properties = new ConfigPropertyReader();
            ontologyPrefix = properties.getProperty("ontology_prefix");
            propertyPrefix = properties.getProperty("property_prefix");
            resourcesPrefix = properties.getProperty("resources_prefix");

            //Get base model query
            this.baseModelQuery = properties.getProperty("model_base_query");

            //Get base model query for all
            this.baseModelQueryAll = properties.getProperty("model_base_query_all");

            //Get base model query for all (just IDs)
            this.baseModelQueryAllCount = properties.getProperty("model_base_query_all_id");

            //Store property prefix
            this.propertyPrefix = ontologyPrefix + propertyPrefix;

            //Store resources prefix
            this.resourcesPrefix = ontologyPrefix + resourcesPrefix;
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
            this.modelPropertyItems.add(this.modelProperties[index]);

    }

    /**
     * Retrieve list of properties for the model
     *
     * @return List of properties
     */
    public String[] getModelProperties() {
        return this.modelProperties;
    }

    /**
     * Retrieve property value for instance
     *
     * @param key Key to retrieve
     * @return Value for the key
     */
    public String getProperty(String key) {
        if (this.instanceProperties.containsKey(key))
            return this.instanceProperties.get(key).toString();
        return "";
    }

    /**
     * Search an instance of the model
     *
     * @param id ID to search
     * @return TRUE if instance has been found
     */
    public Boolean getInstance(String id) {
        Boolean foundInstance = false;

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

                //Get property and remove prefix
                property = bindingSet.getValue("property").stringValue();
                property = property.replace(this.propertyPrefix, "");

                //Get value
                value = bindingSet.getValue("value").stringValue();

                //Store property if set in constructor
                if (this.modelPropertyItems.contains(property))
                    this.instanceProperties.put(property, value);
            }

            foundInstance = true;
        }

        //Close the repository connection
        this.repository.closeRepository();

        //Return whether an instance has been found or not
        return foundInstance;
    }

    /**
     * Given a result and a list of properties, set the properties values to the instance
     *
     * @param result       Result of a query
     * @param propertyList List of properties to save in instance
     */
    protected void setInstancePropertiesFromResult(TupleQueryResult result, String[] propertyList) {
        String property;
        String value;
        while (result.hasNext()) {
            BindingSet bindingSet = result.next();
            for (int propertyIndex = 0; propertyIndex < propertyList.length; propertyIndex++) {
                property = propertyList[propertyIndex];
                if (bindingSet.hasBinding(property)) {
                    value = bindingSet.getValue(property).stringValue();
                    this.instanceProperties.put(property, value);
                }
            }
        }
    }

    /**
     * Search all the instances for a model
     */
    public Map<String, Map<String, Object>> getAll() {
        //Prepare query
        this.queryText = new SparqlQueryTemplate(this.baseModelQueryAll);
        this.queryText.setAttribute("model", this.modelName);
        this.queryText.setAttribute("prop_id", this.modelId);

        //Make query
        String queryString = queryText.getQuery();
        TupleQueryResult result = this.repository.makeQuery(queryString);

        //Get properties from instance
        if (result != null) {
            String property;
            String value;
            String instance_id;

            Map<String, Map<String, Object>> allInstances = new HashMap<String, Map<String, Object>>();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();

                //Get instance ID
                instance_id = bindingSet.getValue("id").stringValue();

                //Create instance with properties
                if (!allInstances.containsKey(instance_id))
                    allInstances.put(instance_id, new HashMap<String, Object>());

                //Get property and remove prefix
                property = bindingSet.getValue("property").stringValue();
                property = property.replace(this.propertyPrefix, "");

                //Get value
                value = bindingSet.getValue("value").stringValue();

                //Store property if set in constructor
                if (this.modelPropertyItems.contains(property))
                    allInstances.get(instance_id).put(property, value);
            }

            return allInstances;
        }

        //Close the repository connection
        this.repository.closeRepository();

        return null;
    }


    /**
     * Count results from query
     *
     * @param queryTemplate Query template to use
     * @return Number of items retrieved in query
     */
    private int getQueryCount(String queryTemplate) {
        //Prepare query
        this.queryText = new SparqlQueryTemplate(queryTemplate);
        this.queryText.setAttribute("model", this.modelName);

        //Make query
        String queryString = queryText.getQuery();
        TupleQueryResult result = this.repository.makeQuery(queryString);

        //Get properties from instance
        int countValue = 0;

        if (result != null) {
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                countValue++;
            }
        }

        //Close the repository connection
        this.repository.closeRepository();

        return countValue;
    }

    /**
     * Return count of all instances of a model
     *
     * @return Number of instances
     */
    public int getAllCount() {
        return this.getQueryCount(this.baseModelQueryAllCount);
    }
}
