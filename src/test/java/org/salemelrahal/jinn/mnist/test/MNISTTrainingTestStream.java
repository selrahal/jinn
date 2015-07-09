package org.salemelrahal.jinn.mnist.test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.Neuron;
import org.salemelrahal.jinn.model.input.StaticLayer;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

public class MNISTTrainingTestStream implements TrainingSuite, Iterator<TrainingTest> {
	private static final double SCALE = 256;
	int numLabels = 0;
	int numImages = 0;
	int numRows = 0;
	int numCols = 0;
	int numLabelsRead = 0;
	int numImagesRead = 0;
	DataInputStream labels;
	DataInputStream images;

	public MNISTTrainingTestStream(String imageFile, String labelFile) {
	try {
		labels = new DataInputStream(new FileInputStream(labelFile));
		images = new DataInputStream(new FileInputStream(imageFile));
		//Make sure these files are the MNIST datas
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

		//read file metadata
		numLabels = labels.readInt();
		numImages = images.readInt();
		numRows = images.readInt();
		numCols = images.readInt();

		//More basic validation
		if (numLabels != numImages) {
			System.err.println("Image file and label file do not contain the same number of entries.");
			System.err.println("  Label file contains: " + numLabels);
			System.err.println("  Image file contains: " + numImages);
			System.exit(0);
		}
	} catch (IOException e){
		System.out.println("Exception!!"+e.getMessage());
	}
	}

	public boolean hasNext() {
try {
		if (labels.available() > 0 && numLabelsRead < numLabels) {
			return true;
		} else {
			labels.close();
			images.close();
			return false;
		}
} catch (IOException e){
	System.out.println("ERROR"+e.getMessage());
}
return false;
	}

	public TrainingTest next() {
try {
		byte label = labels.readByte();
		numLabelsRead++;
		int[][] image = new int[numCols][numRows];
		for (int colIdx = 0; colIdx < numCols; colIdx++) {
		        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
		        	image[colIdx][rowIdx] = images.readUnsignedByte();
			}
		}
		numImagesRead++;
		return generateTest(label, image);	
} catch (IOException e){
	System.out.println("ERRROR"+e);
}
return null;
	}
public void remove(){
}


	private TrainingTest generateTest(byte label, int[][] image) {
		TrainingTest test = new TrainingTest();
		StaticLayer input = new StaticLayer(784);
		for (int vector = 0 ;  vector < image.length; vector++) {
			for (int cell = 0 ; cell < image[vector].length ; cell++) {
				input.getNeurons().get((vector * image.length) + cell).setNetInput(standardize(image[vector][cell]));
			}
		}
		  
		StaticLayer expected = new StaticLayer(10);
		for (Neuron neuron : expected.getNeurons()) {
			neuron.setNetInput(0);
		}
		  
		  
		expected.getNeurons().get(label).setNetInput(1);
		  
		test.setInput(input);
		test.setExpected(expected);
		return test;
	}
	  
	  
	private double standardize(double value) {
		//return value.subtract(BigDecimal.valueOf(128)).divide(scale);
		return value / SCALE;
	}

	public void addTest(TrainingTest test) {
		throw new UnsupportedOperationException();
	}

	public Iterator<TrainingTest> iterator() {
		return this;
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public List<TrainingSuite> split(int batchSize) {
		throw new UnsupportedOperationException();
	}
	
}

