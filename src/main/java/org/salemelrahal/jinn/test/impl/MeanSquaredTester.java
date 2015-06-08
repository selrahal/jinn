package org.salemelrahal.jinn.test.impl;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.test.api.NetworkTester;
import org.salemelrahal.jinn.util.MathUtil;

public class MeanSquaredTester implements NetworkTester {
	public BigDecimal test(Network network, TrainingSuite suite) {
		BigDecimal totalError = BigDecimal.ZERO;

		for (TrainingTest test : suite.getTests()) {
			totalError = totalError.add(this.test(network, test));
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
			
			error = error.add(MathUtil.differenceSquared(expectedActivation, actualActivation));
		}
		
		BigDecimal half = new BigDecimal(.5);
		return error.multiply(half);
	}
	
}
