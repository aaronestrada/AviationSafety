package AppServlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet default class.
 * This class is used for all the servlets in the application, controlling the response calls
 * and the request parameters to the views.
 * <p>
 * Views are stored in WEB-INF/views folder.
 */
public class DefaultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //Stores the name of the view, stored in WEB-INF/views folder
    private String jspView;

    //Request list will be managed using a map that will be sent before doing the request to the view
    private Map<String, Object> requestAttributes;

    //Redirect page to go after processing a servlet
    private String redirectPage;

    /**
     * Set redirect page to process before launching JSP
     *
     * @param redirectPage Redirect URL
     */
    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }

    /**
     * Class constructor
     */
    public DefaultServlet() {
        super();
        this.initServlet();
    }

    /**
     * Class constructor
     *
     * @param jspView View to use
     */
    public DefaultServlet(String jspView) {
        super();
        this.initServlet();
        this.setView(jspView);
    }

    /**
     * Initialize servlet and process
     */
    public void initServlet() {
        this.requestAttributes = new HashMap<String, Object>();
        this.redirectPage = null;
    }

    /**
     * Set name of view to load in servlet
     *
     * @param jspView Name of view, stored in WEB-INF/views/
     */
    public void setView(String jspView) {
        this.jspView = "WEB-INF/views/" + jspView + ".jsp";
    }

    /**
     * Store values in request to send them in GET or POST response
     *
     * @param key   Key to store
     * @param value Value to store
     */
    public void setRequestAttribute(String key, Object value) {
        this.requestAttributes.put(key, value);
    }

    /**
     * Function processed before sending response to client
     */
    protected void processServlet(HttpServletRequest request) {
    }

    /**
     * GET execution for servlet
     *
     * @param request  Request value
     * @param response Response to send to servlet
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processServlet(request);

        if (this.redirectPage != null) {
            response.sendRedirect(this.redirectPage);
        } else {
            //Send all attributes from list to request
            for (Map.Entry<String, Object> e : this.requestAttributes.entrySet())
                request.setAttribute(e.getKey(), e.getValue());

            request.getRequestDispatcher(this.jspView).forward(request, response);
        }
    }

    /**
     * POST execution for servlet
     *
     * @param request  Request value
     * @param response Response to send to servlet
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
