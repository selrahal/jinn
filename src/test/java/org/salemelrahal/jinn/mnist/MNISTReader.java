package org.salemelrahal.jinn.mnist;

import java.io.IOException;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.test.impl.OneChoiceStreamTester;
import org.salemelrahal.jinn.test.stream.RealtimeTrainer;

public class MNISTReader {
	/**
	   * @param args
	   *          args[0]: label file; args[1]: data file.
	   * @throws IOException
	   */
	  public static void main(String[] args) throws IOException {
		Network network = new Network(784, 10, 30);

		String directory = "src/test/resources/";


		String imageFile = directory + "train-images-idx3-ubyte";
		String labelFile = directory + "train-labels-idx1-ubyte";
		RealtimeTrainer trainer = new RealtimeTrainer();
		
		for (int epoch = 0; epoch < 10;  epoch++){
			 System.out.println("==================EPOCH:"+epoch+"============");
			MNISTTrainingTestStream tests = new MNISTTrainingTestStream(imageFile, labelFile);

			long start = System.currentTimeMillis();
		   while (tests.hasNext()) {
					trainer.train(network, tests, .3, 10);
		   }
		   System.out.println("Time to train:" + (System.currentTimeMillis() - start));
		   
		   
		  
//			MeanSquaredStreamTester tester = new MeanSquaredStreamTester();
			OneChoiceStreamTester tester = new OneChoiceStreamTester();

			imageFile = directory + "t10k-images-idx3-ubyte";
			labelFile = directory + "t10k-labels-idx1-ubyte";
			
			MNISTTrainingTestStream testStream = new MNISTTrainingTestStream(imageFile, labelFile);
			System.out.println("Score:"+tester.test(network,testStream));
			
			long time = System.currentTimeMillis();
			 System.out.println("Time to test:" + (System.currentTimeMillis() - time));
		
			
		}
	  }
}
