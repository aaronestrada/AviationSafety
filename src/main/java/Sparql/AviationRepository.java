package Sparql;

import Project.PropertyReader;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Connection to aviation repository
 * Repository URL must be configured in config.properties file in the resources folder.
 *
 * View config.example.properties file to check the correct use of the property in
 * the config.example file.
 */
public class AviationRepository extends RemoteRepositoryEndpoint {

    public AviationRepository() {
        super();
        try {
            //Create property reader and read endpoint URL
            PropertyReader properties = new PropertyReader();
            String sparqlEndpoint = properties.getProperty("aviation_endpoint");

            //set URL endpoint to reader
            if (sparqlEndpoint != "")
                this.setEndpoint(sparqlEndpoint.trim());

        } catch (IOException e) {

        }


    }
}