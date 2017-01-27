package Models;

/**
 * Model for Country instances
 */
public class Country extends DefaultModel {
    /**
     * Class constructor
     */
    public Country() {
        super("Country", "code", true);

        String[] modelProperties = {"code", "name"};
        this.setModelProperties(modelProperties);
    }
}
