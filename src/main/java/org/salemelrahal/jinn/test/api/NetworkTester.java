package org.salemelrahal.jinn.test.api;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

public interface NetworkTester {
	public BigDecimal test(Network network, TrainingSuite suite);
	public BigDecimal test(Network network, TrainingTest test);
}
