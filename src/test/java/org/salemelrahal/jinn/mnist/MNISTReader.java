package org.salemelrahal.jinn.mnist;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Network;
import org.salemelrahal.jinn.model.Neuron;
import org.salemelrahal.jinn.model.input.StaticLayer;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.test.api.NetworkTester;
import org.salemelrahal.jinn.test.impl.OneChoiceTester;
import org.salemelrahal.jinn.train.StochasticTrainer;
import org.salemelrahal.jinn.train.api.NetworkTrainer;

public class MNISTReader {
	private static final BigDecimal scale = BigDecimal.valueOf(256);
	/**
	   * @param args
	   *          args[0]: label file; args[1]: data file.
	   * @throws IOException
	   */
	  public static void main(String[] args) throws IOException {
		  Network network = new Network(784, 10, 30);
		  StochasticTrainer trainer = new StochasticTrainer();
		  OneChoiceTester tester = new OneChoiceTester();
		  
		  System.out.println("==================READING============");
		  TrainingSuite suite = train(network, tester, trainer, "/home/selrahal/Projects/blog/code/jinn/train-labels-idx1-ubyte", "/home/selrahal/Projects/blog/code/jinn/train-images-idx3-ubyte");
		  System.out.println("==================TRAINING============");
		  trainer.train(network, suite, 10, 10);

//		  trainer.trainOnce(network, suite, 10);
		  System.out.println("==================TESTING============");
		  test(network,tester,  "/home/selrahal/Projects/blog/code/jinn/t10k-labels-idx1-ubyte","/home/selrahal/Projects/blog/code/jinn/t10k-images-idx3-ubyte");
	  }
	  
	  public static TrainingSuite train(Network network, OneChoiceTester tester,StochasticTrainer trainer, String labelFile, String data) throws IOException {
		    DataInputStream labels = new DataInputStream(new FileInputStream(labelFile));
		    DataInputStream images = new DataInputStream(new FileInputStream(data));
		    int magicNumber = labels.readInt();
		    if (magicNumber != 2049) {
		      System.err.println("Label file has wrong magic number: " + magicNumber + " (should be 2049)");
		      System.exit(0);
		    }
		    magicNumber = images.readInt();
		    if (magicNumber != 2051) {
		      System.err.println("Image file has wrong magic number: " + magicNumber + " (should be 2051)");
		      System.exit(0);
		    }
		    int numLabels = labels.readInt();
		    int numImages = images.readInt();
		    int numRows = images.readInt();
		    int numCols = images.readInt();
		    if (numLabels != numImages) {
		      System.err.println("Image file and label file do not contain the same number of entries.");
		      System.err.println("  Label file contains: " + numLabels);
		      System.err.println("  Image file contains: " + numImages);
		      System.exit(0);
		    }
		
		    int numLabelsRead = 0;
		    int numImagesRead = 0;
		    TrainingSuite suite = new TrainingSuite();
		    while (labels.available() > 0 && numLabelsRead < numLabels
		    		) {
		      byte label = labels.readByte();
		      numLabelsRead++;
		      int[][] image = new int[numCols][numRows];
		      for (int colIdx = 0; colIdx < numCols; colIdx++) {
		        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
		          image[colIdx][rowIdx] = images.readUnsignedByte();
		        }
		      }
		      numImagesRead++;
		
		      // At this point, 'label' and 'image' agree and you can do whatever you like with them.
		      suite.addTest(trainImage(network, trainer, label, image));
		

		      if ((numLabelsRead % 800) == 0) {
		    	System.out.println(numLabelsRead);
//		    	System.out.print(tester.test(network, suite));
		      }
		      
		      
		      if (numLabelsRead % 10 == 0) {
		    	  
//		    	  trainer.train(network, suite);
//		    	  suite = new TrainingSuite();
		        System.out.print(".");
		      }
		    }
		  
		    labels.close();
		    images.close();
		    return suite;
	  }
	
	  @SuppressWarnings("unused")
	  private static TrainingTest trainImage(Network network, NetworkTrainer trainer, byte label, int[][] image) {
		  TrainingTest test = new TrainingTest();
		  StaticLayer input = new StaticLayer(784);
		  for (int vector = 0 ;  vector < image.length; vector++) {
			  for (int cell = 0 ; cell < image[vector].length ; cell++) {
				  
				  input.getNeurons().get((vector * image.length) + cell).setNetInput(standardize(BigDecimal.valueOf(image[vector][cell])));
			  }
		  }
		  
		  StaticLayer expected = new StaticLayer(10);
		  for (Neuron neuron : expected.getNeurons()) {
			  neuron.setNetInput(BigDecimal.ZERO);
		  }
		  
		  test.setInput(input);
		  test.setExpected(expected);
		  
		  expected.getNeurons().get(label).setNetInput(BigDecimal.ONE);
		  
//		  System.out.println("Training with " + test);
//		  TrainingSuite suite = new TrainingSuite();
//		  suite.addTest(test);
//		  
//		  trainer.train(network, suite, 1);
		  return test;
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public static void test(Network network, NetworkTester tester, String labelFile, String data) throws IOException {
		    DataInputStream labels = new DataInputStream(new FileInputStream(labelFile));
		    DataInputStream images = new DataInputStream(new FileInputStream(data));
		    int magicNumber = labels.readInt();
		    if (magicNumber != 2049) {
		      System.err.println("Label file has wrong magic number: " + magicNumber + " (should be 2049)");
		      System.exit(0);
		    }
		    magicNumber = images.readInt();
		    if (magicNumber != 2051) {
		      System.err.println("Image file has wrong magic number: " + magicNumber + " (should be 2051)");
		      System.exit(0);
		    }
		    int numLabels = labels.readInt();
		    int numImages = images.readInt();
		    int numRows = images.readInt();
		    int numCols = images.readInt();
		    if (numLabels != numImages) {
		      System.err.println("Image file and label file do not contain the same number of entries.");
		      System.err.println("  Label file contains: " + numLabels);
		      System.err.println("  Image file contains: " + numImages);
		      System.exit(0);
		    }
		
		    int numLabelsRead = 0;
		    int numImagesRead = 0;
		    BigDecimal score = BigDecimal.ZERO;
		    
		    while (labels.available() > 0 && numLabelsRead < numLabels) {
		      byte label = labels.readByte();
		      numLabelsRead++;
		      int[][] image = new int[numCols][numRows];
		      for (int colIdx = 0; colIdx < numCols; colIdx++) {
		        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
		          image[colIdx][rowIdx] = images.readUnsignedByte();
		        }
		      }
		      numImagesRead++;
		
		      // At this point, 'label' and 'image' agree and you can do whatever you like with them.
		      score = score.add(testImage(network, tester, label, image));
		
		      if (numLabelsRead % 10 == 0) {
		        System.out.print(".");
		      }
		      if ((numLabelsRead % 800) == 0) {
		        System.out.println(numLabelsRead);
		      }
		    }
		  
		    labels.close();
		    images.close();
		    
		    System.out.println("SCore!" + score.divide(BigDecimal.valueOf(10000)));
	  }
	  
	  
	  @SuppressWarnings("unused")
	  private static BigDecimal testImage(Network network, NetworkTester tester, byte label, int[][] image) {
		  TrainingTest test = new TrainingTest();
		  StaticLayer input = new StaticLayer(784);
		  for (int vector = 0 ;  vector < image.length; vector++) {
			  for (int cell = 0 ; cell < image[vector].length ; cell++) {
				  input.getNeurons().get((vector * image.length) + cell).setNetInput(standardize(BigDecimal.valueOf(image[vector][cell])));
			  }
		  }
		  
		  StaticLayer expected = new StaticLayer(10);
		  for (Neuron neuron : expected.getNeurons()) {
			  neuron.setNetInput(BigDecimal.ZERO);
		  }
		  
		  
		  expected.getNeurons().get(label).setNetInput(BigDecimal.ONE);
		  
		  test.setInput(input);
		  test.setExpected(expected);
		  
		  System.out.println("Testing with " + test);
		  TrainingSuite suite = new TrainingSuite();
		  suite.addTest(test);
		  
		  return tester.test(network, suite);
	  }
	  
	  
	private static BigDecimal standardize(BigDecimal value) {
		return value.subtract(BigDecimal.valueOf(128)).divide(scale);
	}
	
}

