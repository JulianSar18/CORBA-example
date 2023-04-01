import CalculatorApp.CalculatorPOA;

import java.text.NumberFormat;
import java.util.Locale;

public class EchoServer  extends CalculatorPOA {

    @Override
    public String calcMonthlyFee(double purchase, double MonthlyFee) {
        double pow = Math.pow(1.032160, MonthlyFee);
        double mFee = (purchase*(0.032160 * pow))/((pow)-1);
        NumberFormat formatC = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        return (formatC.format(mFee));
    }
}
