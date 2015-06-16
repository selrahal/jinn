package org.salemelrahal.jinn.perceptron;

import org.junit.Assert;
import org.junit.Test;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.perceptron.test.SigmoidFunctionTrainingSuite;
import org.salemelrahal.jinn.test.MeanSquaredTester;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.train.RealtimeTrainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePerceptronTest {
	private static final Logger LOG = LoggerFactory.getLogger(SimplePerceptronTest.class);
	private static final double GOAL = .5;

	@Test
	public void testTrained(){
		LOG.info("Testing one node network, perceptron");
		Network perceptron = new Network(1,1);
		TrainingSuite suite = new SigmoidFunctionTrainingSuite();
		RealtimeTrainer trainer = new RealtimeTrainer();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		//TODO: factor epochs out of tests.
		for (int  epoch = 0; epoch < 1000; epoch++)
			trainer.train(perceptron, suite, .01, 1000);
		
		
		double results = tester.test(perceptron, suite, 1);
		
		
		LOG.info("XOR trained Test results:" + results);
		Assert.assertTrue("XOR trained test failed("+results+")", results < GOAL);
	}
}
