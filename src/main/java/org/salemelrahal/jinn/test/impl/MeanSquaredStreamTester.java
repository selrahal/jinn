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


import java.util.Iterator;

public class MeanSquaredStreamTester {
	private static final Logger LOG = LoggerFactory.getLogger(MeanSquaredStreamTester.class);
	
	public BigDecimal test(Network network, Iterator<TrainingTest> tests) {
		BigDecimal totalError = BigDecimal.ZERO;
		while (tests.hasNext()) {
			TrainingTest test = tests.next();
			BigDecimal testScore = this.test(network, test);
//			LOG.info("Test score:" + testScore);
			totalError = totalError.add(testScore);
		}
		
		return totalError;
	}

	public BigDecimal test(Network network, Iterator<TrainingTest> tests, int limit) {
		BigDecimal totalError = BigDecimal.ZERO;
		int count = 0;
		while (count < limit && tests.hasNext()) {
			count++;
			TrainingTest test = tests.next();
			BigDecimal testScore = this.test(network, test);
//			LOG.info("Test score:" + testScore);
			totalError = totalError.add(testScore);
		}
		
		return totalError;
	}
	
	public BigDecimal test(Network network, TrainingTest test) {
		network.fire(test.getInput());
		return scoreTest(test.getExpected(), network);
	}
	
	
	/**
	 * Cost function is 1/2*sum((actual-expected)^2)
	 */
	private BigDecimal scoreTest(Layer expected, Network network) {
		BigDecimal error = BigDecimal.ZERO;
		Layer actual = network.getOutput();
		if (expected.getNeurons().size() != actual.getNeurons().size()) {
			throw new IllegalArgumentException("Cannot score two layers of different size");
		}
		
		for (int i = 0; i < expected.getNeurons().size(); i++) {
			BigDecimal expectedActivation = expected.getNeurons().get(i).getActivation();
			BigDecimal actualActivation = actual.getNeurons().get(i).getActivation();
//			LOG.info("e:" + expectedActivation + "," + " a:" + actualActivation);
			error = error.add(MathUtil.differenceSquared(expectedActivation, actualActivation));
		}
		
		BigDecimal half = new BigDecimal(.5);
		return error.multiply(half);
	}
	
}
