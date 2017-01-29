package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Model for InjurySeverity instances
 */
public class InjurySeverity extends DefaultModel {
    /**
     * Class constructor
     */
    public InjurySeverity() {
        super("InjurySeverity", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }

    /**
     * Get list of injury types
     *
     * @return List of injury types
     */
    public Map<String, String> getInjuryList() {
        //Prepare query
        this.queryText = new SparqlQueryTemplate("injury_severity/injury_severity_list");

        //Make query
        TupleQueryResult result = this.repository.makeQuery(queryText.getQuery());
        Map<String, String> injuryList = null;

        //Get properties from instance
        if (result != null) {
            injuryList = new HashMap<String, String>();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                injuryList.put(bindingSet.getValue("id").stringValue(), bindingSet.getValue("description").stringValue());
            }
        }

        //Close the repository connection
        this.repository.closeRepository();
        return injuryList;
    }
}
