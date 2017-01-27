package Models;

/**
 * Model for FlightPurpose instances
 */
public class FlightPurpose extends DefaultModel {
    /**
     * Class constructor
     */
    public FlightPurpose() {
        super("FlightPurpose", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
