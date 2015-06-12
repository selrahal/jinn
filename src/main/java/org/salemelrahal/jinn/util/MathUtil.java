package org.salemelrahal.jinn.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MathUtil {
	private static Map<BigDecimal, BigDecimal> sigmoidCache = new HashMap<BigDecimal, BigDecimal>(256);
	static {
		for (int i = 0; i < 256; i++) {
			sigmoidCache.put(BigDecimal.valueOf(i), sigmoid(BigDecimal.valueOf(i)));
		}
	}

	/**
	 * Calculate the result of sigmoid(x) 
	 * @param x
	 * @return 1/(1+e^(-x))
	 */
	public static BigDecimal sigmoid(BigDecimal x) {
		if (sigmoidCache.containsKey(x)) {
			return sigmoidCache.get(x);
		}
		double part = Math.pow(Math.E, x.doubleValue() * -1);
		return BigDecimal.ONE.divide(BigDecimal.ONE.add(BigDecimal.valueOf(part)), 5, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * Calculate the result of sigmoid'(x) 
	 * @param x
	 * @return 1/(1+e^(-x))
	 */
	public static BigDecimal sigmoidDerivitave(BigDecimal x) {
		BigDecimal sigmoid = sigmoid(x);
		return sigmoid.multiply(BigDecimal.ONE.subtract(sigmoid));
	}
	
	/**
	 * Calculate the difference squared
	 * @return (x-y)^2
	 */
	public static BigDecimal differenceSquared(BigDecimal x, BigDecimal y) {
		return x.subtract(y).pow(2);
	}
}
