package org.salemelrahal.jinn.test.impl;

import java.util.List;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.model.Neuron;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.test.api.NetworkTester;

public class OneChoiceTester implements NetworkTester {

	public double test(Network network, TrainingSuite suite) {
		double totalTests = 0;
		double passes = 0;
		
		for (TrainingTest test : suite.getTests()) {
			if (this.test(network, test) == 0) {
				passes = passes + 1;
			}
			
			totalTests = totalTests + 1;
		}
		return passes/totalTests;
	}

	public double test(Network network, TrainingTest test) {
		network.fire(test.getInput());
//		LOG.info("-");
//		LOG.info(network.toString());
//		LOG.info("-");
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
//			LOG.info(actuals.get(bestGuessIndex).getActivation()+">" + actuals.get(i).getActivation() + "("+actuals.get(i).getNetInput()+")");
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
		
//		LOG.info(answerIndex + ":" + bestGuessIndex);
		if (answerIndex == bestGuessIndex) {
			return 0;
		} else {
			return 1;
		}
		
	}

}
