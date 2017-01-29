package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Model for Country instances
 */
public class Country extends DefaultModel {
    /**
     * Class constructor
     */
    public Country() {
        super("Country", "code", true);

        String[] modelProperties = {"code", "name", "isoCode3"};
        this.setModelProperties(modelProperties);
    }

    /**
     * Get list of countries
     * @return List of countries
     */
    public Map<String, String> getCountryList() {
        //Prepare query
        this.queryText = new SparqlQueryTemplate("country/country_list");

        //Make query
        TupleQueryResult result = this.repository.makeQuery(queryText.getQuery());
        Map<String, String> countryList = null;

        //Get properties from instance
        if (result != null) {
            countryList = new HashMap<String, String>();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                countryList.put(bindingSet.getValue("code").stringValue(), bindingSet.getValue("name").stringValue());
            }
        }

        //Close the repository connection
        this.repository.closeRepository();
        return countryList;
    }
}
