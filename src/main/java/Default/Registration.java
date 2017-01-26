package Default;

import Project.QueryTemplate;
import Sparql.AviationRepository;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

    private int abc() {
        //Step 1: connect to remote repository

        AviationRepository repository = new AviationRepository();

        QueryTemplate queryText = new QueryTemplate("query1");
        String queryString = queryText.getQuery();

        //Step 2: execute query
        TupleQueryResult result = repository.makeQuery(queryString);

        int count = 0;
        //Step 3: show results
        if (result != null) {
            while (result.hasNext()) {
                //Iterate over the result
                count++;
                BindingSet bindingSet = result.next();
                //resultStr += bindingSet;
                //System.out.println(bindingSet);
            }
        }

        //Finally, close the repository
        repository.closeRepository();

        return count;
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int result = this.abc();

        //List of parameters obtained from the form
        List<String> listOfString = new ArrayList<String>(Arrays.asList(
                "firstname",
                "lastname",
                "email",
                "phone",
                "organization",
                "address",
                "urlpage",
                "city",
                "zipcode",
                "paymentmode",
                "total",
                "startdate",
                "favoritecolor"
        ));

        //Set parameters into view
        String parameterName;
        for (int i = 0; i < listOfString.size(); i++) {
            parameterName = listOfString.get(i);
            request.setAttribute(parameterName, request.getParameter(parameterName));
        }

        request.setAttribute("abc", result);

        //call to registration.jsp to show parameters sent on form
        request.getRequestDispatcher("pages/registration.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
