package org.salemelrahal.jinn.util;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUtil {
	private static final Random INSTANCE = new Random();
	
	public static BigDecimal randomBigDecimal(){
		return BigDecimal.valueOf(INSTANCE.nextGaussian());
	}
	
	public static boolean randomBoolean() {
		return INSTANCE.nextBoolean();
	}
}
