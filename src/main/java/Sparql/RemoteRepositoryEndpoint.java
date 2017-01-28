package Sparql;


import org.eclipse.rdf4j.query.BooleanQuery;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

/**
 * Class to connect to a remote SPARQL repository
 */
public class RemoteRepositoryEndpoint {
    private Repository repo;

    /**
     * Class constructor
     */
    public RemoteRepositoryEndpoint() {
    }

    /**
     * Class constructor
     *
     * @param sparqlEndpoint URL for SPARQL endpoint to access
     */
    public RemoteRepositoryEndpoint(String sparqlEndpoint) {
        this.setEndpoint(sparqlEndpoint);
    }

    /**
     * Set repository endpoint
     *
     * @param sparqlEndpoint URL of the endpoint
     */
    public void setEndpoint(String sparqlEndpoint) {
        this.repo = new SPARQLRepository(sparqlEndpoint);
    }

    /**
     * Query on repository
     *
     * @param queryString Query to execute in repository
     * @return Results
     */
    public TupleQueryResult makeQuery(String queryString) {
        //Verify if repository has been initialized, otherwise start
        if (!this.repo.isInitialized())
            this.repo.initialize();

        try {
            //Retrieve values from SPARQL query
            RepositoryConnection repConn = this.repo.getConnection();
            TupleQuery tupleQuery = repConn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            TupleQueryResult result = tupleQuery.evaluate();
            return result;
        } catch (RepositoryException re) {
            return null;
        }
    }

    /**
     * Query on repository (boolean queries [ASK])
     *
     * @param queryString Query to execute in repository
     * @return Whether the response was True or False
     */
    public Boolean makeBooleanQuery(String queryString) {
        //Verify if repository has been initialized, otherwise start
        if (!this.repo.isInitialized())
            this.repo.initialize();

        try {
            //Retrieve values from SPARQL query
            RepositoryConnection repConn = this.repo.getConnection();
            BooleanQuery booleanQuery = repConn.prepareBooleanQuery(QueryLanguage.SPARQL, queryString);
            Boolean result = booleanQuery.evaluate();
            return result;
        } catch (RepositoryException re) {
            return null;
        }
    }


    /**
     * Close the remote repository connection
     */
    public void closeRepository() {
        this.repo.shutDown();
    }

}
