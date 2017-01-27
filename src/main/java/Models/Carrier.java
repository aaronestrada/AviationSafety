package Models;

/**
 * Model for Carrier instances
 */
public class Carrier extends DefaultModel {
    /**
     * Class constructor
     */
    public Carrier() {
        super("Carrier", "id", false);

        String[] modelProperties = {"id", "name"};
        this.setModelProperties(modelProperties);
    }
}
