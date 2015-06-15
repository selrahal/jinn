package org.salemelrahal.jinn.app;

import java.io.IOException;
import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.impl.OneChoiceStreamTester;
import org.salemelrahal.jinn.test.stream.RealtimeTrainer;

public class MNISTReader {
	private static final BigDecimal scale = BigDecimal.valueOf(256);
	/**
	   * @param args
	   *          args[0]: label file; args[1]: data file.
	   * @throws IOException
	   */
	  public static void main(String[] args) throws IOException {
		Network network = new Network(784, 10, 30);

		String directory = "";


		String imageFile = directory + "train-images-idx3-ubyte";
		String labelFile = directory + "train-labels-idx1-ubyte";
		RealtimeTrainer trainer = new RealtimeTrainer();
		long start = System.currentTimeMillis();
		for (int epoch = 0; epoch < 1;  epoch++){
			MNISTTrainingTestStream tests = new MNISTTrainingTestStream(imageFile, labelFile);

		   while (tests.hasNext()) {
					
					
					trainer.train(network, tests, BigDecimal.valueOf(.3), 10);
		   }
		   System.out.println("Time to train:" + (System.currentTimeMillis() - start));
		   
		   System.out.println("==================TESTING============");
//			MeanSquaredStreamTester tester = new MeanSquaredStreamTester();
			OneChoiceStreamTester tester = new OneChoiceStreamTester();

			imageFile = directory + "t10k-images-idx3-ubyte";
			labelFile = directory + "t10k-labels-idx1-ubyte";
			
			MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(imageFile, labelFile);
			System.out.println("Time to train:" + (System.currentTimeMillis() - start));
			System.out.println("Score:"+tester.test(network,testStream) + " epoch:" + epoch);
			
			long time = System.currentTimeMillis();
			 System.out.println("Time to test:" + (System.currentTimeMillis() - time));
		
			
		}
	  }
}
