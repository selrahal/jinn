package org.salemelrahal.jinn.train;

import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.train.api.NetworkTrainer;

public class StochasticTrainer implements NetworkTrainer{

	public void train(Network network, TrainingSuite trainingSuite, int epochs) {
		for (int i = 0; i < epochs; i++) {
			List<TrainingSuite> split = trainingSuite.randomSplit();
			for (TrainingSuite suite : split) {
				if (suite.getTests().size() > 0) {
					for (TrainingTest test : suite.getTests()) {
						network.fire(test.getInput());
						network.backPropagate(test.getExpected());
					}
					network.learn();
				}
			}
		}
	}
}
