package org.salemelrahal.jinn.train;

import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.ArrayTrainingSuite;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

/**
 * This trainer will train the network using a stochastic gradient descent algorithm.
 *
 */
public class StochasticTrainer {

	public void train(Network network, TrainingSuite trainingSuite, double learningRate, int batchSize, int epochs) {
		for (int i = 0; i < epochs; i++) {
			List<TrainingSuite> split = trainingSuite.split(batchSize);
			for (TrainingSuite suite : split) {
				this.trainBatch(network, suite, learningRate);
			}
		}
	}
	
	private void trainBatch(Network network, TrainingSuite trainingSuite, double learningRate) {
		boolean trainedAtLeastOnce = false;
		Iterator<TrainingTest> tests = trainingSuite.iterator();

		while (tests.hasNext()) {
			TrainingTest test = tests.next();
			network.fire(test.getInput());
			network.backPropagate(test.getExpected());
			network.updateRunningError(learningRate /trainingSuite.size());
			trainedAtLeastOnce = true;
		}
		
		if (trainedAtLeastOnce) {
			network.learn();
		}
	}
}
