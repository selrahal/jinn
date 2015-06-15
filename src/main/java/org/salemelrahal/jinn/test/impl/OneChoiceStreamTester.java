package org.salemelrahal.jinn.test.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Iterator;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.model.Neuron;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.test.api.NetworkTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneChoiceStreamTester {
	private static final Logger LOG = LoggerFactory.getLogger(OneChoiceTester.class);

	public BigDecimal test(Network network, Iterator<TrainingTest> tests) {
		BigDecimal totalTests = BigDecimal.ZERO;
		BigDecimal passes = BigDecimal.ZERO;
		
		while (tests.hasNext()){
			TrainingTest test = tests.next();
			if (this.test(network, test).equals(BigDecimal.ZERO)) {
				passes = passes.add(BigDecimal.ONE);
			}
			
			totalTests = totalTests.add(BigDecimal.ONE);
		}
		return passes.divide(totalTests);
	}

	public BigDecimal test(Network network, Iterator<TrainingTest> tests, int limit) {
		int count = 0;
		BigDecimal totalTests = BigDecimal.ZERO;
		BigDecimal passes = BigDecimal.ZERO;
		
		while (count < limit && tests.hasNext()){
			count++;
			TrainingTest test = tests.next();
			if (this.test(network, test).equals(BigDecimal.ZERO)) {
				passes = passes.add(BigDecimal.ONE);
			}
			
			totalTests = totalTests.add(BigDecimal.ONE);
		}
		return passes.divide(totalTests);
	}

	public BigDecimal test(Network network, TrainingTest test) {
		network.fire(test.getInput());
//		LOG.info("-");
//		LOG.info(network.toString());
//		LOG.info("-");
		return scoreTest(test.getExpected(), network);
	}

	/**
	 * Cost function is 1/2*sum((actual-expected)^2)
	 */
	private BigDecimal scoreTest(Layer expected, Network network) {
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
			if (actuals.get(bestGuessIndex).getActivation().compareTo(actuals.get(i).getActivation()) < 0) {
				bestGuessIndex = i;
			}
		}

		int answerIndex = 0;
		List<Neuron> answers = expected.getNeurons();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(answerIndex).getActivation().compareTo(answers.get(i).getActivation()) < 0) {
				answerIndex = i;
			}
		}
		
		LOG.info(answerIndex + ":" + bestGuessIndex);
		if (answerIndex == bestGuessIndex) {
			return BigDecimal.ZERO;
		} else {
			return BigDecimal.ONE;
		}
		
	}

}
