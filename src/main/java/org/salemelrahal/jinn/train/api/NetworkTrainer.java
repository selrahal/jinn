package org.salemelrahal.jinn.train.api;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;

public interface NetworkTrainer {
	
	/**
	 * 
	 * @param network to be trained
	 * @param trainingSuite is the set of TrainingTests used in one epoch
	 * @param learningRate tunable factor for speed<->accuracy trade off
	 * @param batchSize the size of the batches of tests to quicken learning
	 * @param epochs number of times all of the Training Tests in the trainingSuite will be used.
	 */
	public void train(Network network, TrainingSuite trainingSuite, double learningRate, int batchSize, int epochs);
}
