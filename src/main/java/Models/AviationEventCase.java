package Models;

/**
 * Model for AviationEventCase instances
 */
public class AviationEventCase extends DefaultModel {
    /**
     * Class constructor
     */
    public AviationEventCase() {
        super("AviationEventCase", "caseId", true);

        String[] modelProperties = {"caseId", "scheduledFlight"};
        this.setModelProperties(modelProperties);
    }
}
