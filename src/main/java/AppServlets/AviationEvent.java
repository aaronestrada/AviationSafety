package AppServlets;

import General.IntegerEuroFormat;
import Models.AircraftModel;
import Models.AviationEventCase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Servlet implementation class Index
 */
@WebServlet("/aviationevent.jsp")
public class AviationEvent extends DefaultServlet {
    public AviationEvent() {
        super("aviationevent");
    }

    protected void processServlet(HttpServletRequest request) {
        //Get aviation event properties
        String eventId = request.getParameter("id");
        Models.AviationEvent aviationEvent = new Models.AviationEvent();

        //Verify if there is an instance to show in view
        if (aviationEvent.getInstance(eventId)) {

            //Get list of aviation cases for the event
            List<String> aviationCases = aviationEvent.getAviationEventCases(eventId);
            this.setRequestAttribute("aviationCases", aviationCases.toString());

            //Retrieve information for each aviation case
            String aviationCaseId;
            AviationEventCase aviationEventCaseInstance = new AviationEventCase();

            //List of properties for event case
            String[] eventCaseProperties = aviationEventCaseInstance.getModelProperties();

            for (int aviationCaseIndex = 0; aviationCaseIndex < aviationCases.size(); aviationCaseIndex++) {
                aviationCaseId = aviationCases.get(aviationCaseIndex);
                if (aviationEventCaseInstance.getInstance(aviationCaseId)) {
                    this.setRequestAttribute("aircraft", aviationEventCaseInstance.getProperty("Aircraft_modelName"));
                }
            }

            /**
             * Send list of properties to the view
             */
            String[] eventProperties = {
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
                    "eventTypeName"
            };

            String propertyName;
            for (int propertyIndex = 0; propertyIndex < eventProperties.length; propertyIndex++) {
                propertyName = eventProperties[propertyIndex];
                this.setRequestAttribute(propertyName, aviationEvent.getProperty(propertyName));
            }
        }
    }
}
