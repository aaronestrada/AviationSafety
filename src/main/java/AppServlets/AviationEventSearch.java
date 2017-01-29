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

        //Check page
        int currentPage;
        String pageValue = request.getParameter("page");
        if (pageValue != null) {
            try {
                currentPage = Integer.valueOf(pageValue);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }

            if (currentPage < 1)
                currentPage = 1;
        } else
            currentPage = 1;

        //Get results from search
        Object[] searchResults = aviationEvent.searchAviationEvent(countryCode, injurySeverityId, currentPage);
        int eventCount = (int) searchResults[1];
        Map<String, Map<String, String>> eventList = (Map<String, Map<String, String>>) searchResults[0];

        //Set next and previous page values
        int nextPage = 0;
        int previousPage = currentPage - 1;

        if (currentPage == 1)
            previousPage = 0;

        if (eventCount > 100)
            nextPage = currentPage + 1;

        //Send event cases to view
        this.setRequestAttribute("eventList", eventList);
        this.setRequestAttribute("countryList", countryList);
        this.setRequestAttribute("injuryList", injuryList);
        this.setRequestAttribute("selectedCountryCode", countryCode);
        this.setRequestAttribute("selectedInjurySeverity", injurySeverityId);
        this.setRequestAttribute("eventListCount", eventList.size());
        this.setRequestAttribute("previousPage", previousPage);
        this.setRequestAttribute("nextPage", nextPage);
        this.setRequestAttribute("currentPage", currentPage);
    }
}