package org.salemelrahal.jinn.test.impl;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.test.api.NetworkTester;
import org.salemelrahal.jinn.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeanSquaredTester implements NetworkTester {
	private static final Logger LOG = LoggerFactory.getLogger(MeanSquaredTester.class);
	
	public double test(Network network, TrainingSuite suite) {
		double totalError = 0;

		for (TrainingTest test : suite.getTests()) {
			double testScore = this.test(network, test);
			LOG.info("Test score:" + testScore);
			totalError = totalError + testScore;
		}
		
		return totalError;
	}
	
	public double test(Network network, TrainingTest test) {
		network.fire(test.getInput());
		return scoreTest(test.getExpected(), network);
	}
	
	
	/**
	 * Cost function is 1/2*sum((actual-expected)^2)
	 */
	private double scoreTest(Layer expected, Network network) {
		double error = 0;
		Layer actual = network.getOutput();
		if (expected.getNeurons().size() != actual.getNeurons().size()) {
			throw new IllegalArgumentException("Cannot score two layers of different size");
		}
		
		for (int i = 0; i < expected.getNeurons().size(); i++) {
			double expectedActivation = expected.getNeurons().get(i).getActivation();
			double actualActivation = actual.getNeurons().get(i).getActivation();
			
			error = error + MathUtil.differenceSquared(expectedActivation, actualActivation);
		}
		
		return error/2;
	}
	
}
