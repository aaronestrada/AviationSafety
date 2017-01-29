package AppServlets;

import Models.AviationEventCase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation to view information of aviation event
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
            /**
             * Send list of properties to the view
             */
            String[] eventProperties = aviationEvent.getModelProperties();
            String propertyName;
            for (int propertyIndex = 0; propertyIndex < eventProperties.length; propertyIndex++) {
                propertyName = eventProperties[propertyIndex];
                this.setRequestAttribute(propertyName, aviationEvent.getProperty(propertyName));
            }

            //Get list of aviation cases for the event
            List<String> aviationCases = aviationEvent.getAviationEventCases(eventId);

            if (aviationCases.size() > 0) {
                //Retrieve information for each aviation case
                AviationEventCase aviationEventCaseInstance = new AviationEventCase();

                //List of properties for event case
                String[] eventCaseProperties = aviationEventCaseInstance.getModelProperties();

                //List of events to send to view
                Map<String, Map<String, String>> eventCases = new HashMap<String, Map<String, String>>();

                //Iterate over each event case to send properties in view
                String eventCaseProperty;
                String aviationCaseId;
                for (int aviationCaseIndex = 0; aviationCaseIndex < aviationCases.size(); aviationCaseIndex++) {
                    aviationCaseId = aviationCases.get(aviationCaseIndex);
                    if (aviationEventCaseInstance.getInstance(aviationCaseId)) {
                        //New variable for storing event data
                        Map<String, String> eventData = new HashMap<String, String>();
                        for (int propertyIndex = 0; propertyIndex < eventCaseProperties.length; propertyIndex++) {
                            eventCaseProperty = eventCaseProperties[propertyIndex];
                            eventData.put(eventCaseProperty, aviationEventCaseInstance.getProperty(eventCaseProperty));
                        }

                        //Store data in list of events
                        eventCases.put(aviationCaseId, eventData);
                    }
                }

                //Send event cases to view
                this.setRequestAttribute("eventCases", eventCases);
            }
        }
    }
}
