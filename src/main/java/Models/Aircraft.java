package Models;

/**
 * Model for Aircraft instances
 */
public class Aircraft extends DefaultModel {
    /**
     * Class constructor
     */
    public Aircraft() {
        super("Aircraft", "registrationNumber", false);

        String[] modelProperties = {"registrationNumber"};
        this.setModelProperties(modelProperties);
    }
}
