package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                "finLocationName",
                "finLocationLongitude",
                "finLocationLatitude",
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
                        "airportCountryName",
                        "finLocationName",
                        "finLocationLongitude",
                        "finLocationLatitude"
                };
                this.setInstancePropertiesFromResult(result, propertyList);
            }

            //Get type of aviation event
            String eventTypeName = this.getAviationEventType(id);
            this.instanceProperties.put("eventTypeName", eventTypeName);

            //Set final location for instance
            String[] location = this.setInstanceLocation(
                    this.getProperty("airportId"),
                    this.getProperty("latitude"),
                    this.getProperty("longitude"),
                    this.getProperty("airportName"),
                    this.getProperty("airportCityName"),
                    this.getProperty("airportCountryName"),
                    this.getProperty("airportLatitude"),
                    this.getProperty("airportLongitude"),
                    this.getProperty("locationName"),
                    this.getProperty("countryName")
            );

            //Send location parameters to view
            this.instanceProperties.put("finLocationName", location[0]);
            this.instanceProperties.put("finLocationLongitude", location[1]);
            this.instanceProperties.put("finLocationLatitude", location[2]);

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
        Boolean result = this.repository.makeBooleanQuery(queryText.getQuery());

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
        return aviationCases;
    }

    /**
     * Set final location for retrieved instance
     */
    private String[] setInstanceLocation(String airportId,
                                         String latitude,
                                         String longitude,
                                         String airportName,
                                         String airportCityName,
                                         String airportCountryName,
                                         String airportLatitude,
                                         String airportLongitude,
                                         String locationName,
                                         String countryName
    ) {
        String locationLatitude = latitude;
        String locationLongitude = longitude;
        String finalLocationName = "";

        //Has airport value, take this as principal location
        if (airportId != "") {
            finalLocationName = airportName +
                    " [" + airportCityName +
                    ", " + airportCountryName + "]";
            locationLatitude = airportLatitude;
            locationLongitude = airportLongitude;
        } else if (locationName != "") {
            finalLocationName = locationName;
        } else if (countryName != "") {
            finalLocationName = countryName;
        }

        String[] location = {finalLocationName, locationLongitude, locationLatitude};
        return location;
    }

    /**
     * Search aviation events
     *
     * @TODO: For the moment, the only filter that can be applied is country. Next version will be complete.
     */
    public Map<String, Map<String, String>> searchAviationEvent(String countryCode, String injurySeverity) {
        this.queryText = new SparqlQueryTemplate("aviation_event/aviation_event_all");

        String filters = "";
        if (countryCode != "") {
            SparqlQueryTemplate filterCountry = new SparqlQueryTemplate("filters/aviation_event_all_country", false);
            filterCountry.setAttribute("country", countryCode);
            filters += filterCountry.getQuery();
        }

        if (injurySeverity != "") {
            SparqlQueryTemplate injuryFilterCountry = new SparqlQueryTemplate("filters/aviation_event_all_injury", false);
            injuryFilterCountry.setAttribute("injury", injurySeverity);
            filters += injuryFilterCountry.getQuery();
        }

        this.queryText.setAttribute("filters", filters);

        TupleQueryResult result = this.repository.makeQuery(queryText.getQuery());

        //List of events
        Map<String, Map<String, String>> eventList = new HashMap<String, Map<String, String>>();

        if (result != null) {
            String[] resultProperties = {
                    "eventId",
                    "date",
                    "totalNoInjuries",
                    "totalSeriousInjuries",
                    "totalMinorInjuries",
                    "totalFatalInjuries",
                    "injurySeverityName",
                    "weatherConditionName",
                    "reportStatusName",
                    "locationName",
                    "countryNameLocation",
                    "countryCode",
                    "airportId",
                    "airportName",
                    "airportIataCode",
                    "airportIcaoCode",
                    "airportLatitude",
                    "airportLongitude",
                    "airportAltitude",
                    "airportCityName",
                    "airportCountryName",
                    "airportCountryCode"
            };

            String value;
            String property;
            String eventId;
            while (result.hasNext()) {
                Map<String, String> eventItem = new HashMap<>();
                BindingSet bindingSet = result.next();

                //Get id of event
                eventId = bindingSet.getValue("eventId").stringValue();

                for (int propertyIndex = 0; propertyIndex < resultProperties.length; propertyIndex++) {
                    property = resultProperties[propertyIndex];
                    if (bindingSet.hasBinding(property))
                        value = bindingSet.getValue(property).stringValue();
                    else
                        value = "";

                    //Store item in list
                    eventItem.put(property, value);
                }


                //Set final location for instance
                String[] location = this.setInstanceLocation(
                        eventItem.get("airportId"),
                        eventItem.get("latitude"),
                        eventItem.get("longitude"),
                        eventItem.get("airportName"),
                        eventItem.get("airportCityName"),
                        eventItem.get("airportCountryName"),
                        eventItem.get("airportLatitude"),
                        eventItem.get("airportLongitude"),
                        eventItem.get("locationName"),
                        eventItem.get("countryName")
                );

                //Send location parameters to view
                eventItem.put("finLocationName", location[0]);
                eventItem.put("finLocationLongitude", location[1]);
                eventItem.put("finLocationLatitude", location[2]);

                //Store event type
                eventItem.put("eventType", this.getAviationEventType(eventId));

                //Add number of cases associated to the event
                List<String> aviationCases = this.getAviationEventCases(eventId);
                eventItem.put("caseCount", Integer.toString(aviationCases.size()));

                //Store tuple in list of events
                eventList.put(bindingSet.getValue("eventId").stringValue(), eventItem);
            }
        }

        //Close the repository connection
        this.repository.closeRepository();

        return eventList;
    }
}
