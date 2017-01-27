package Models;

/**
 * Model for FlightPhase instances
 */
public class FlightPhase extends DefaultModel {
    /**
     * Class constructor
     */
    public FlightPhase() {
        super("FlightPhase", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
