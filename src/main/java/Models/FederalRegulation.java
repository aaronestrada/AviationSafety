package Models;

/**
 * Model for FederalRegulation instances
 */
public class FederalRegulation extends DefaultModel {
    /**
     * Class constructor
     */
    public FederalRegulation() {
        super("FederalRegulation", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
