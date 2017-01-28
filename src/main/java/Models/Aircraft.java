package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.TupleQueryResult;

/**
 * Model for Aircraft instances
 */
public class Aircraft extends DefaultModel {
    /**
     * Class constructor
     */
    public Aircraft() {
        super("Aircraft", "registrationNumber", false);

        String[] modelProperties = {"registrationNumber", "modelName", "numberEngines", "builtByAmateur", "categoryName", "manufacturerName"};
        this.setModelProperties(modelProperties);
    }

    /**
     * Get instance of aircraft / aircraft model with model-manufacturer properties
     *
     * @param id                 ID to search
     * @param registeredAircraft Whether the aircraft is registered or not
     * @return TRUE if instance was found
     */
    public Boolean getInstance(String id, Boolean registeredAircraft) {
        Boolean foundInstance = false;

        String queryName = "aircraft_model/aircraft_model_base";
        if (registeredAircraft)
            queryName = "aircraft/aircraft_base";

        //Prepare and make query
        this.queryText = new SparqlQueryTemplate(queryName);
        this.queryText.setAttribute("id", id);
        TupleQueryResult result = this.repository.makeQuery(queryText.getQuery());

        //Get properties from instance
        if (result != null) {
            foundInstance = true;
            this.setInstancePropertiesFromResult(result, this.modelProperties);
        }

        //Close the repository connection
        this.repository.closeRepository();
        return foundInstance;
    }
}
