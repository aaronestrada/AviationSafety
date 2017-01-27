package AppServlets;

import Models.Airport;
import Models.Country;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends DefaultServlet {
    private static final long serialVersionUID = 1L;

    public Registration() {
        super("registration");
    }

    private String abc() {
        //Step 1: connect to remote repository

        Airport countryInstance = new Airport();
        countryInstance.getInstance("1");
        String countryName = countryInstance.getProperty("iataCode");
        return countryName;
    }

    protected void processServlet(HttpServletRequest request) {
        String result = this.abc();

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
            this.setRequestAttribute(parameterName, request.getParameter(parameterName));
        }

        this.setRequestAttribute("abc", result);

    }


}
