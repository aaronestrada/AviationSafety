package Models;

/**
 * Model for ReportStatus instances
 */
public class ReportStatus extends DefaultModel {
    /**
     * Class constructor
     */
    public ReportStatus() {
        super("ReportStatus", "id", false);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
