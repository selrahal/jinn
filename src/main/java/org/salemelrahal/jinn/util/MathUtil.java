package org.salemelrahal.jinn.util;


public class MathUtil {

	/**
	 * Calculate the result of sigmoid(x) 
	 * @param x
	 * @return 1/(1+e^(-x))
	 */
	public static double sigmoid(double x) {
		double part = Math.pow(Math.E, x * -1);
		if (Double.isInfinite(part)) {
			return 0;
		} else {
			return 1/(1+part);
		}
	}
	
	/**
	 * Calculate the result of sigmoid'(x) 
	 * @param x
	 * @return 1/(1+e^(-x))
	 */
	public static double sigmoidDerivitave(double x) {
		double sigmoid = sigmoid(x);
		return sigmoid * (1 - sigmoid);
	}
	
	/**
	 * Calculate the difference squared
	 * @return (x-y)^2
	 */
	public static double differenceSquared(double x, double y) {
		return Math.pow(x-y,2);
	}
}
