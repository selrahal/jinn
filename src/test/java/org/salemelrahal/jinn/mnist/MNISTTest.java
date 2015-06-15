package org.salemelrahal.jinn.mnist;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.impl.OneChoiceStreamTester;
import org.salemelrahal.jinn.test.stream.RealtimeTrainer;

public class MNISTTest {
	private static final double GOAL = .75;
	private static final String DIRECTORY = "src/test/resources/";
	private static final String TRAINING_IMAGE_FILE = DIRECTORY + "train-images-idx3-ubyte";
	private static final String TRAINING_LABEL_FILE = DIRECTORY + "train-labels-idx1-ubyte";
	private static final String TESTING_IMAGE_FILE = DIRECTORY + "t10k-images-idx3-ubyte";
	private static final String TESTING_LABEL_FILE = DIRECTORY + "t10k-labels-idx1-ubyte";

	@Test
	public void testTrainedHandwritingNetwork() throws IOException {
		Network network = new Network(784, 10, 30);

		// Training
		RealtimeTrainer trainer = new RealtimeTrainer();
		MNISTTrainingTestStream tests = new MNISTTrainingTestStream(TRAINING_IMAGE_FILE, TRAINING_LABEL_FILE);
		while (tests.hasNext()) {
			trainer.train(network, tests, .3, 10);
		}

		// Testing
		OneChoiceStreamTester tester = new OneChoiceStreamTester();
		MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(TESTING_IMAGE_FILE, TESTING_LABEL_FILE);
		double score = tester.test(network, testStream);
		Assert.assertTrue("Trained network failed MNIST test:" + score, score > GOAL);
	}

	@Test
	public void testUntrainedHandwritingNetwork() throws IOException {
		Network network = new Network(784, 10, 30);

		// Testing
		OneChoiceStreamTester tester = new OneChoiceStreamTester();
		MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(TESTING_IMAGE_FILE, TESTING_LABEL_FILE);
		double score = tester.test(network, testStream);
		Assert.assertTrue("Untrained network passed MNIST test:" + score, score < GOAL);

	}
}
