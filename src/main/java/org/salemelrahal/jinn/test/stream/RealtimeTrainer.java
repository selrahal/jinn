package org.salemelrahal.jinn.test.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.salemelrahal.jinn.model.Network;
import java.math.BigDecimal;
import java.util.Iterator;
import org.salemelrahal.jinn.test.TrainingTest;

public class RealtimeTrainer {
	private static final Logger LOG = LoggerFactory.getLogger(RealtimeTrainer.class);

	public void train(Network network, Iterator<TrainingTest> tests, BigDecimal learningRate){
		while (tests.hasNext()) {
			TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}

	public void train(Network network, Iterator<TrainingTest> tests, BigDecimal learningRate, int limit){
		int i = 0;
		while (i < limit && tests.hasNext()){
			i++;
			TrainingTest test = tests.next();
			train(network, test, learningRate);
		}
		network.learn();
	}


	private void train(Network network, TrainingTest test, BigDecimal learningRate){
			network.fire(test.getInput());
			network.backPropagate(test.getExpected());
			network.updateRunningError(learningRate);
	}
}
