import java.text.DecimalFormat;


public class FormatdecimalTest {

	public static void main(String[] args) {
		DecimalFormat decimalFormat = new DecimalFormat("#########0.0");
		Double doubleVal = new Double("1111111111.0");
		System.out.println(doubleVal);
		System.out.println("DECIMAL FORMAR........."+decimalFormat.format(doubleVal));

	}

}