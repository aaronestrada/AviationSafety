package Models;

/**
 * Model for WeatherCondition instances
 */
public class WeatherCondition extends DefaultModel {
    /**
     * Class constructor
     */
    public WeatherCondition() {
        super("WeatherCondition", "id", true);

        String[] modelProperties = {"id", "description"};
        this.setModelProperties(modelProperties);
    }
}
