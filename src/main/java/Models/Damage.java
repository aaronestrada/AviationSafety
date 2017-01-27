package Models;

/**
 * Model for Damage instances
 */
public class Damage extends DefaultModel {
    /**
     * Class constructor
     */
    public Damage() {
        super("Damage", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
