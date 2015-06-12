package org.salemelrahal.jinn.train;

import java.math.BigDecimal;
import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.train.api.NetworkTrainer;

public class StochasticTrainer implements NetworkTrainer {

	public void train(Network network, TrainingSuite trainingSuite, BigDecimal learningRate, int batchSize, int epochs) {
		for (int i = 0; i < epochs; i++) {
			List<TrainingSuite> split = trainingSuite.split(batchSize);
			for (TrainingSuite suite : split) {
				this.train(network, suite, learningRate);
			}
		}
	}

	public void train(Network network, TrainingSuite trainingSuite, BigDecimal learningRate) {
		int batchSize = trainingSuite.getTests().size();
		if (batchSize > 0) {
			for (TrainingTest test : trainingSuite.getTests()) {
				network.fire(test.getInput());
				network.backPropagate(test.getExpected());
				network.updateRunningError(learningRate.divide(BigDecimal.valueOf(batchSize)));
			}
			network.learn();
		}
	}
}
