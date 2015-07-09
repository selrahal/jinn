package org.salemelrahal.jinn.test;

import java.util.Iterator;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.util.MathUtil;

public class MeanSquaredTester {
	public double test(Network network, TrainingSuite suite) {
		double totalError = 0;
		Iterator<TrainingTest> tests = suite.tests();
		
		while (tests.hasNext()) {
			TrainingTest test = tests.next();
			double testScore = this.test(network, test);
			totalError = totalError + testScore;
		}
		
		return totalError;
	}

	public double test(Network network, TrainingSuite suite, int limit) {
		double totalError = 0;
		int count = 0;
		Iterator<TrainingTest> tests = suite.tests();
		
		while (count < limit && tests.hasNext()) {
			count++;
			TrainingTest test = tests.next();
			double testScore = this.test(network, test);
			totalError = totalError + testScore;
		}
		
		return totalError;
	}
	
	private double test(Network network, TrainingTest test) {
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
