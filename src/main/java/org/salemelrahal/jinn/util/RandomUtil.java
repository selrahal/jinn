package org.salemelrahal.jinn.util;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUtil {
	private static final Random INSTANCE = new Random();
	
	public static BigDecimal getRandom(int start, int end) {
		double random = INSTANCE.nextDouble();
		int range = end - start;
		double extra = random * range;
		return BigDecimal.valueOf(start + extra);
	}
	
	public static BigDecimal randomBigDecimal(){
		return BigDecimal.valueOf(INSTANCE.nextDouble());
	}
	
	public static boolean randomBoolean() {
		return INSTANCE.nextBoolean();
	}
}
