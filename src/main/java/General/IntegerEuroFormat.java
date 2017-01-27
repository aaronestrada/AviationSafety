package General;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Format integers with euro format
 */
public class IntegerEuroFormat {
    private DecimalFormat formatter;

    /**
     * Class constructor
     */
    public IntegerEuroFormat() {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator(',');
        unusualSymbols.setGroupingSeparator('.');

        this.formatter = new DecimalFormat("###,###.##", unusualSymbols);
        this.formatter.setMaximumFractionDigits(0);
    }

    /**
     * Format number
     *
     * @param Number Number to format
     * @return Formatted number
     */
    public String format(int Number) {
        return this.formatter.format(Number);
    }
}
