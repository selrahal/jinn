package org.salemelrahal.jinn.test.stream;

import java.util.Iterator;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealtimeTrainer {
	public void train(Network network, Iterator<TrainingTest> tests, double learningRate){
		while (tests.hasNext()) {
			TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}

	public void train(Network network, Iterator<TrainingTest> tests, double learningRate, int limit){
		int i = 0;
		while (i < limit && tests.hasNext()){
			i++;
			TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}


	private void train(Network network, TrainingTest test, double learningRate){
			network.fire(test.getInput());
			network.backPropagate(test.getExpected());
			network.updateRunningError(learningRate);
	}
}
