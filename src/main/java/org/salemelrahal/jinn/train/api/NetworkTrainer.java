package org.salemelrahal.jinn.train.api;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;

public interface NetworkTrainer {
	public void train(Network network, TrainingSuite trainingSuite, BigDecimal learningRate, int batchSize, int epochs);
}
