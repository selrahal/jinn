package org.salemelrahal.jinn.train;

import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.train.api.NetworkTrainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StochasticTrainer implements NetworkTrainer{
	private static final Logger LOG = LoggerFactory.getLogger(StochasticTrainer.class);

	public void train(Network network, TrainingSuite trainingSuite, int epochs) {
		LOG.info("Training " + network + "with " + trainingSuite + " for " + epochs + " epochs");
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
