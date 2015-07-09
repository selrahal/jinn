package org.salemelrahal.jinn.train;

import java.util.List;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;

/**
 * This trainer will train the network using a stochastic gradient descent algorithm.
 *
 */
public class StochasticTrainer extends NetworkTrainer {

	public void train(Network network, TrainingSuite trainingSuite, double learningRate, int batchSize, int epochs) {
		for (int i = 0; i < epochs; i++) {
			List<TrainingSuite> split = trainingSuite.split(batchSize);
			for (TrainingSuite suite : split) {
				this.train(network, suite, learningRate);
			}
		}
	}
}
