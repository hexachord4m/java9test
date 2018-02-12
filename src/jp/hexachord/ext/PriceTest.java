/**
 *
 */
package jp.hexachord.ext;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 *
 */
public class PriceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriceTest test = new PriceTest();
		System.out.println("売値: " + test.calcPrice(1000, "0.07"));
	}

	public int calcPrice(int l, String d) {
		BigDecimal discount = new BigDecimal(d);
		int p = BigDecimal.ONE.subtract(discount).multiply(BigDecimal.valueOf(l))
				.setScale(0, RoundingMode.FLOOR).intValue();
		return p;

	}

}
