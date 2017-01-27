package AppServlets;

import General.IntegerEuroFormat;
import Models.AircraftModel;
import Models.Country;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index.jsp")
public class Index extends DefaultServlet {
    private static final long serialVersionUID = 1L;

    public Index() {
        super("index");
    }

    protected void processServlet(HttpServletRequest request) {
        //Get aircraft model counter to show in dashboard
        AircraftModel aircraftModels = new AircraftModel();
        int aircraftModelCount = aircraftModels.getAllCount();

        /**
         * Send counter to view.
         * For events and cases, since Ontop does not support aggregation,
         * the values were counted directly on database and displayed in
         * the view
         */
        this.setRequestAttribute("aircraftModelCount", new IntegerEuroFormat().format(aircraftModelCount));
    }

}
