package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for AviationEvent instances
 */
public class AviationEvent extends DefaultModel {
    /**
     * Class constructor
     */
    public AviationEvent() {
        super("AviationEvent", "id", true);

        String[] modelProperties = {
                "id",
                "date",
                "latitude",
                "longitude",
                "totalFatalInjuries",
                "totalSeriousInjuries",
                "totalMinorInjuries",
                "totalNoInjuries",
                "injurySeverityName",
                "weatherConditionName",
                "reportStatusName",
                "locationName",
                "countryNameLocation",
                "airportId",
                "airportName",
                "airportIataCode",
                "airportIcaoCode",
                "airportLatitude",
                "airportLongitude",
                "airportAltitude",
                "airportCityName",
                "airportCountryName",
                "eventTypeName"
        };
        this.setModelProperties(modelProperties);
    }

    /**
     * Get an instance of event
     *
     * @param id ID to retrieve values
     * @return TRUE if instance has been found
     */
    public Boolean getInstance(String id) {
        if (super.getInstance(id)) {
            //Get more properties from second query

            //Prepare and make query
            this.queryText = new SparqlQueryTemplate("aviation_event/aviation_event_base");
            this.queryText.setAttribute("id", id);
            String queryString = queryText.getQuery();
            TupleQueryResult result = this.repository.makeQuery(queryString);

            //Get properties from instance
            if (result != null) {
                String[] propertyList = {
                        "injurySeverityName",
                        "weatherConditionName",
                        "reportStatusName",
                        "locationName",
                        "countryNameLocation",
                        "airportId",
                        "airportName",
                        "airportIataCode",
                        "airportIcaoCode",
                        "airportLatitude",
                        "airportLongitude",
                        "airportAltitude",
                        "airportCityName",
                        "airportCountryName"
                };
                this.setInstancePropertiesFromResult(result, propertyList);
            }

            //Get type of aviation event
            String eventTypeName = this.getAviationEventType(id);
            this.instanceProperties.put("eventTypeName", eventTypeName);

            //Close the repository connection
            this.repository.closeRepository();
            return true;
        }
        return false;
    }

    /**
     * Get type for an aviation event
     *
     * @param id ID to check the type of event
     * @return Name of the event
     */
    public String getAviationEventType(String id) {
        String eventTypeName = "Incident";

        this.queryText = new SparqlQueryTemplate("aviation_event/aviation_event_type");
        this.queryText.setAttribute("id", id);
        String queryString = queryText.getQuery();
        Boolean result = this.repository.makeBooleanQuery(queryString);

        if (result)
            eventTypeName = "Accident";
        return eventTypeName;
    }

    /**
     * Get list of cases involved in the aviation event
     *
     * @param id ID to retrieve event cases
     * @return List with aviation case IDs
     */
    public List<String> getAviationEventCases(String id) {
        List<String> aviationCases = new ArrayList<String>();

        //Prepare and make query
        this.queryText = new SparqlQueryTemplate("aviation_event/aviation_event_cases");
        this.queryText.setAttribute("id", id);
        String queryString = queryText.getQuery();
        TupleQueryResult result = this.repository.makeQuery(queryString);

        //Get properties from instance
        if (result != null) {
            String value;

            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                value = bindingSet.getValue("aviation_case_id").stringValue();
                aviationCases.add(value);
            }
        }

        //Close the repository connection
        this.repository.closeRepository();

        return aviationCases;
    }
}
