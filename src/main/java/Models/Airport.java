package Models;

/**
 * Model for Airport instances
 */
public class Airport extends DefaultModel {
    /**
     * Class constructor
     */
    public Airport() {
        super("Airport", "id", false);

        String[] modelProperties = {"id", "name", "altitude", "iataCode", "icaoCode", "longitude"};
        this.setModelProperties(modelProperties);
    }
}
