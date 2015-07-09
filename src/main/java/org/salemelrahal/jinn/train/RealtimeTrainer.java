package org.salemelrahal.jinn.train;

import java.util.Iterator;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

/**
 * This Trainer will train a network using a stream of tests.
 *
 */
public class RealtimeTrainer extends NetworkTrainer {
	
	//TODO: implement epochs and reset method on trainingsuite

	/**
	 * Train the network using the provided stream of tests. This will use
	 * up to the limit and will use only one batch.
	 */
	public void train(final Network network, final TrainingSuite suite, final double learningRate, final int limit){
		Iterator<TrainingTest> tests = suite.tests();
		int count = 0;
		while (count < limit && tests.hasNext()){
			count++;
			final TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}
}
