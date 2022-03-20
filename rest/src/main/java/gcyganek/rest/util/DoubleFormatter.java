package gcyganek.rest.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleFormatter {

    public static double trimDoubleToOneDecimalPlace(double val) {
        BigDecimal bd = new BigDecimal(Double.toString(val));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
