package org.salemelrahal.jinn.mnist;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.impl.OneChoiceStreamTester;
import org.salemelrahal.jinn.test.stream.RealtimeTrainer;

public class MNISTTest {
	private static final double GOAL = .75;

	@Test
	public void testTrainedHandwritingNetwork() throws IOException {
		Network network = new Network(784, 10, 30);

		String directory = "src/test/resources/";

		String imageFile = directory + "train-images-idx3-ubyte";
		String labelFile = directory + "train-labels-idx1-ubyte";
		RealtimeTrainer trainer = new RealtimeTrainer();

		for (int epoch = 0; epoch < 10; epoch++) {
			MNISTTrainingTestStream tests = new MNISTTrainingTestStream(
					imageFile, labelFile);

			while (tests.hasNext()) {
				trainer.train(network, tests, .3, 10);
			}

			// MeanSquaredStreamTester tester = new MeanSquaredStreamTester();
			OneChoiceStreamTester tester = new OneChoiceStreamTester();

			imageFile = directory + "t10k-images-idx3-ubyte";
			labelFile = directory + "t10k-labels-idx1-ubyte";

			MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(
					imageFile, labelFile);
			double score = tester.test(network, testStream);
			Assert.assertTrue("Trained network failed MNIST test:" + score,
					score > GOAL);

		}
	}

	@Test
	public void testUntrainedHandwritingNetwork() throws IOException {
		Network network = new Network(784, 10, 30);

		String directory = "src/test/resources/";

		String imageFile = directory + "train-images-idx3-ubyte";
		String labelFile = directory + "train-labels-idx1-ubyte";

		// MeanSquaredStreamTester tester = new MeanSquaredStreamTester();
		OneChoiceStreamTester tester = new OneChoiceStreamTester();

		imageFile = directory + "t10k-images-idx3-ubyte";
		labelFile = directory + "t10k-labels-idx1-ubyte";

		MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(
				imageFile, labelFile);
		double score = tester.test(network, testStream);
		Assert.assertTrue("Untrained network passed MNIST test:" + score,
				score < GOAL);

	}
}
