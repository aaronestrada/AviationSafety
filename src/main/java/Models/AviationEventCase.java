package Models;

import Sparql.SparqlQueryTemplate;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

/**
 * Model for AviationEventCase instances
 */
public class AviationEventCase extends DefaultModel {
    /**
     * Class constructor
     */
    public AviationEventCase() {
        super("AviationEventCase", "caseId", true);

        String[] modelProperties = {
                "caseId",
                "scheduledFlight",
                "damageDesc",
                "flightPhaseDesc",
                "carrierName",
                "federalRegulationDesc",
                "flightPurposeDesc",
                "Aircraft_registrationNumber",
                "Aircraft_modelName",
                "Aircraft_numberEngines",
                "Aircraft_builtByAmateur",
                "Aircraft_categoryName",
                "Aircraft_manufacturerName"
        };
        this.setModelProperties(modelProperties);
    }

    /**
     * Get an instance of event case
     *
     * @param id ID to retrieve values
     * @return TRUE if instance has been found
     */
    public Boolean getInstance(String id) {
        if (super.getInstance(id)) {
            //Get more properties from second query

            //Prepare and make query
            this.queryText = new SparqlQueryTemplate("aviation_event_case/aviation_event_case_base");
            this.queryText.setAttribute("id", id);
            TupleQueryResult result = this.repository.makeQuery(queryText.getQuery());

            //Get properties from instance
            if (result != null)
                this.setInstancePropertiesFromResult(result, this.getModelProperties());

            //Get type of aircraft involved in event
            this.queryText = new SparqlQueryTemplate("aviation_event_case/aviation_event_case_aircraft_type");
            this.queryText.setAttribute("id", id);
            TupleQueryResult aircraftResult = this.repository.makeQuery(queryText.getQuery());

            String aircraftId = "";
            String registrationNumber = "";
            if (aircraftResult != null) {
                while (aircraftResult.hasNext()) {
                    BindingSet bindingSet = aircraftResult.next();

                    //Verify optional values
                    if (bindingSet.hasBinding("aircraft_id"))
                        aircraftId = bindingSet.getValue("aircraft_id").stringValue();

                    if (bindingSet.hasBinding("registration_number"))
                        registrationNumber = bindingSet.getValue("registration_number").stringValue();
                }
            }

            //If aircraft has an ID associated, search instance
            if ((aircraftId != "") || (registrationNumber != "")) {
                //Verify if it is an aircraft or registered aircraft
                Boolean registeredAircraft = false;
                if (registrationNumber != "") {
                    registeredAircraft = true;
                    aircraftId = registrationNumber;
                }

                //Get properties for aircraft
                Aircraft aircraftInstance = new Aircraft();

                if (aircraftInstance.getInstance(aircraftId, registeredAircraft)) {
                    String propertyName;
                    String[] aircraftProperties = aircraftInstance.getModelProperties();

                    for (int propertyIndex = 0; propertyIndex < aircraftProperties.length; propertyIndex++) {
                        propertyName = aircraftProperties[propertyIndex];
                        this.instanceProperties.put("Aircraft_" + propertyName, aircraftInstance.getProperty(propertyName));
                    }
                }
            }

            //Close the repository connection
            this.repository.closeRepository();
            return true;
        }
        return false;
    }
}
