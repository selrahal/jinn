package org.salemelrahal.jinn.train;

import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

/**
 * This trainer will train the network using a stochastic gradient descent algroithm.
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
		int batchSize = trainingSuite.getTests().size();
		if (batchSize > 0) {
			for (TrainingTest test : trainingSuite.getTests()) {
				network.fire(test.getInput());
				network.backPropagate(test.getExpected());
				network.updateRunningError(learningRate /batchSize);
			}
			network.learn();
		}
	}
}
