package Models;

/**
 * Model for Location instances
 */
public class Location extends DefaultModel {
    /**
     * Class constructor
     */
    public Location() {
        super("Location", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
