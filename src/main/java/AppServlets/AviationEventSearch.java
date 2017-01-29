package AppServlets;

import Models.Country;
import Models.InjurySeverity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Servlet implementation to search for aviation events
 */
@WebServlet("/search.jsp")
public class AviationEventSearch extends DefaultServlet {
    public AviationEventSearch() {
        super("search");
    }

    protected void processServlet(HttpServletRequest request) {
        Models.AviationEvent aviationEvent = new Models.AviationEvent();

        Country countries = new Country();
        Map<String, String> countryList = countries.getCountryList();

        InjurySeverity injurySeverity = new InjurySeverity();
        Map<String, String> injuryList = injurySeverity.getInjuryList();

        String countryCode = request.getParameter("country");
        if (countryCode == null)
            countryCode = "";

        String injurySeverityId = request.getParameter("injury_severity");
        if (injurySeverityId == null)
            injurySeverityId = "";

        Map<String, Map<String, String>> eventList = aviationEvent.searchAviationEvent(countryCode, injurySeverityId);

        //Send event cases to view
        this.setRequestAttribute("eventList", eventList);
        this.setRequestAttribute("countryList", countryList);
        this.setRequestAttribute("injuryList", injuryList);
        this.setRequestAttribute("selectedCountryCode", countryCode);
        this.setRequestAttribute("selectedInjurySeverity", injurySeverityId);
    }
}