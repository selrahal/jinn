package org.salemelrahal.jinn.train.api;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;

public interface NetworkTrainer {
	public void train(Network network, TrainingSuite trainingSuite, int batchSize, int epochs);
}
