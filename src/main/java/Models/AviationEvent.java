package Models;

/**
 * Model for AviationEvent instances
 */
public class AviationEvent extends DefaultModel {
    /**
     * Class constructor
     */
    public AviationEvent() {
        super("Airport", "id", true);

        String[] modelProperties = {
                "id",
                "date",
                "latitude",
                "longitude",
                "totalFatalInjuries",
                "totalSeriousInjuries",
                "totalMinorInjuries",
                "totalNoInjuries"
        };
        this.setModelProperties(modelProperties);
    }
}
