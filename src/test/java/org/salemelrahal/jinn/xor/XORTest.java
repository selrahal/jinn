package org.salemelrahal.jinn.xor;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.impl.MeanSquaredTester;
import org.salemelrahal.jinn.train.StochasticTrainer;
import org.salemelrahal.jinn.train.api.NetworkTrainer;
import org.salemelrahal.jinn.xor.provider.XORTestProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XORTest {
	private static final Logger LOG = LoggerFactory.getLogger(XORTest.class);
	private static final BigDecimal goal = BigDecimal.valueOf(.1);

	@Test
	public void testTrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		NetworkTrainer trainer = new StochasticTrainer();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		trainer.train(network, xorSuite, 3000);
		
		
		BigDecimal results = tester.test(network, xorSuite);
		
		
		LOG.info("XOR trained Test results:" + results);
		Assert.assertTrue("XOR trained test failed", results.compareTo(goal) < 0);
	}
	
	@Test
	public void testUntrained() {
		Network network = new Network(2,1,4);
		TrainingSuite xorSuite = XORTestProvider.getXorSuite();
		MeanSquaredTester tester = new MeanSquaredTester();
		
		
		BigDecimal results = tester.test(network, xorSuite);
		
		
		LOG.info("XOR untrained Test results:" + results);
		Assert.assertTrue("XOR untained test failed", results.compareTo(goal) > 0);
	}
}
