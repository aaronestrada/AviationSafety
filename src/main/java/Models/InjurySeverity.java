package Models;

/**
 * Model for InjurySeverity instances
 */
public class InjurySeverity extends DefaultModel {
    /**
     * Class constructor
     */
    public InjurySeverity() {
        super("InjurySeverity", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
