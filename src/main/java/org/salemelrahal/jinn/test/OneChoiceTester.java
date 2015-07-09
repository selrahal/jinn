package org.salemelrahal.jinn.test;

import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.model.Neuron;

public class OneChoiceTester {
	
	public double test(Network network, TrainingSuite suite) {
		double totalTests = 0;
		double passes = 0;
		Iterator<TrainingTest> tests = suite.tests();
		while (tests.hasNext()){
			TrainingTest test = tests.next();
			if (this.test(network, test) == 0) {
				passes = passes + 1;
			}
			
			totalTests = totalTests + 1;
		}
		return passes/totalTests;
	}

	public double test(Network network, TrainingSuite suite, int limit) {
		int count = 0;
		double totalTests = 0;
		double passes = 0;
		Iterator<TrainingTest> tests = suite.tests();
		while (count < limit && tests.hasNext()){
			count++;
			TrainingTest test = tests.next();
			if (this.test(network, test) == 0) {
				passes = passes + 1;
			}
			
			totalTests = totalTests + 1;
		}
		return passes/totalTests;
	}

	private double test(Network network, TrainingTest test) {
		network.fire(test.getInput());
		return scoreTest(test.getExpected(), network);
	}

	/**
	 * Cost function is 1/2*sum((actual-expected)^2)
	 */
	private double scoreTest(Layer expected, Network network) {
		Layer actual = network.getOutput();
		if (expected.getNeurons().size() != actual.getNeurons().size()) {
			throw new IllegalArgumentException(
					"Cannot score two layers of different size, network size " + actual.getNeurons().size()
					+ ", test size " + expected.getNeurons().size());
		}
		
		int bestGuessIndex = 0;
		List<Neuron> actuals = actual.getNeurons();
		for (int i = 0; i < actuals.size(); i++) {
			if (actuals.get(bestGuessIndex).getActivation() < (actuals.get(i).getActivation())) {
				bestGuessIndex = i;
			}
		}

		int answerIndex = 0;
		List<Neuron> answers = expected.getNeurons();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(answerIndex).getActivation() < (answers.get(i).getActivation())) {
				answerIndex = i;
			}
		}
		
		if (answerIndex == bestGuessIndex) {
			return 0;
		} else {
			return 1;
		}
		
	}

}
