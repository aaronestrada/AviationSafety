package Models;

/**
 * Model for City instances
 */
public class City extends DefaultModel {
    /**
     * Class constructor
     */
    public City() {
        super("City", "id", false);

        String[] modelProperties = {"id", "name"};
        this.setModelProperties(modelProperties);
    }
}
