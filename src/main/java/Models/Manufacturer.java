package Models;

/**
 * Model for Manufacturer instances
 */
public class Manufacturer extends DefaultModel {
    /**
     * Class constructor
     */
    public Manufacturer() {
        super("Manufacturer", "id", false);

        String[] modelProperties = {"id", "name"};
        this.setModelProperties(modelProperties);
    }
}
