package org.salemelrahal.jinn.test.api;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

/**
 * Used to determine the error of a network for a given TrainingTest or TrainingSuite.
 */
public interface NetworkTester {
	public double test(Network network, TrainingSuite suite);
	public double test(Network network, TrainingTest test);
}
