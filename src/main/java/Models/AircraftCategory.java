package Models;

/**
 * Model for AircraftCategory instances
 */
public class AircraftCategory extends DefaultModel {
    /**
     * Class constructor
     */
    public AircraftCategory() {
        super("AircraftCategory", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
