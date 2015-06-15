package org.salemelrahal.jinn.xor;

import org.junit.Assert;
import org.junit.Test;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.stream.RealtimeTrainer;
import org.salemelrahal.jinn.test.impl.MeanSquaredTester;
import org.salemelrahal.jinn.test.impl.MeanSquaredStreamTester;
import org.salemelrahal.jinn.train.StochasticTrainer;
import org.salemelrahal.jinn.train.api.NetworkTrainer;
import org.salemelrahal.jinn.xor.provider.XORTestProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test to make sure we can we tell the difference between a Network trained
 * on the XOR tests and a random Network.
 *
 */
public class XORTest {
	private static final Logger LOG = LoggerFactory.getLogger(XORTest.class);
	private static final double goal = .04;

	/**
	 * Test the trained network to make sure its error is below the goal error.
	 */
	@Test
	public void testTrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		NetworkTrainer trainer = new StochasticTrainer();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		trainer.train(network, xorSuite, 1, 4, 5000);
		
		
		double results = tester.test(network, xorSuite);
		
		
		LOG.info("XOR trained Test results:" + results);
		Assert.assertTrue("XOR trained test failed("+results+")", results < goal);
	}
	
	/**
	 * Test the untrained network to make sure its error is above the goal error. Sometimes
	 * this test will pass if the untrained network is _really_ lucky.
	 */
	@Test
	public void testUntrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		
		double results = tester.test(network, xorSuite);
		
		
		LOG.info("XOR untrained Test results:" + results);
		Assert.assertTrue("XOR untained test failed ("+results+")", results > goal);
	}

	/**
	 * Test the stream-trained network to make sure its error is below the goal error.
	 */
	@Test
	public void testStreamTrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		RealtimeTrainer trainer = new RealtimeTrainer();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		for (int epoch = 0; epoch < 5000; epoch++)
			trainer.train(network, xorSuite.getTests().iterator(), 1);
		
		
		double results = tester.test(network, xorSuite);
		
		
		LOG.info("XOR trained Test results:" + results);
		Assert.assertTrue("XOR trained test failed("+results+")", results  < goal);
	}

	/**
	 * Test the stream-trained network using a stream based tester to make sure its error is below the goal error.
	 */
	@Test
	public void streamTestStreamTrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		RealtimeTrainer trainer = new RealtimeTrainer();
		MeanSquaredStreamTester tester = new MeanSquaredStreamTester();
		
		for (int epoch = 0; epoch < 5000; epoch++)
			trainer.train(network, xorSuite.getTests().iterator(), 1);
		
		
		double results = tester.test(network, xorSuite.getTests().iterator());
		
		
		LOG.info("XOR trained Test results:" + results);
		Assert.assertTrue("XOR trained test failed("+results+")", results < goal);
	}
}
