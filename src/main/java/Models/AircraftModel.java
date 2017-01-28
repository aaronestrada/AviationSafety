package Models;

/**
 * Model for AircraftModel instances
 */
public class AircraftModel extends DefaultModel {
    /**
     * Class constructor
     */
    public AircraftModel() {
        super("AircraftModel", "id", false);

        String[] modelProperties = {"id", "name", "numberEngines", "builtByAmateur"};
        this.setModelProperties(modelProperties);
    }
}
