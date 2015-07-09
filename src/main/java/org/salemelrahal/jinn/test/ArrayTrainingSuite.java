package org.salemelrahal.jinn.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayTrainingSuite implements TrainingSuite {
	protected List<TrainingTest> tests;
	
	public ArrayTrainingSuite() {
		tests = new ArrayList<TrainingTest>();
	}
	
	public void addTest(TrainingTest test) {
		tests.add(test);
	}
	
	public Iterator<TrainingTest> tests() {
		return tests.iterator();	
	}
	
	public int size() {
		return tests.size();
	}
	
	public List<TrainingSuite> split(int batchSize) {
		double size = tests.size();
		double batch = batchSize;
		double buckets = size/batch;
		Collections.shuffle(tests);
		List<TrainingSuite> toReturn = new ArrayList<TrainingSuite>((int)buckets);
		
		for (int i = 0 ; i < buckets; i++) {
			ArrayTrainingSuite suite = new ArrayTrainingSuite();
			for (int j = 0; j < batchSize; j++) {
				suite.addTest(tests.get(i * batchSize + j));
			}
			toReturn.add(suite);
		}

		return toReturn;
	}
}
