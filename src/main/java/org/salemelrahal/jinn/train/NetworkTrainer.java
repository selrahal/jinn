package org.salemelrahal.jinn.train;

import java.util.Iterator;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

public class NetworkTrainer {
	
	/**
	 * Train the network using the provided suite.
	 */
	public void train(final Network network, final TrainingSuite suite, final double learningRate){
		Iterator<TrainingTest> tests = suite.iterator();
		while (tests.hasNext()) {
			final TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}
	
	/**
	 * Train the network using the provided test.
	 */
	protected void train(final Network network, final TrainingTest test, final double learningRate) {
		network.fire(test.getInput());
		network.backPropagate(test.getExpected());
		network.updateRunningError(learningRate);
	}
}
