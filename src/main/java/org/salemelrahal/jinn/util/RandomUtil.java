package org.salemelrahal.jinn.util;

import java.util.Random;

public class RandomUtil {
	private static final Random INSTANCE = new Random();
	
	public static double randomGaussian(){
		return INSTANCE.nextGaussian();
	}
	
	public static boolean randomBoolean() {
		return INSTANCE.nextBoolean();
	}
}
