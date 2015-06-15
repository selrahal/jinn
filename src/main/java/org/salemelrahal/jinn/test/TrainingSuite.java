package org.salemelrahal.jinn.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingSuite {
	protected List<TrainingTest> tests;
	
	public TrainingSuite() {
		tests = new ArrayList<TrainingTest>();
	}
	
	public void addTest(TrainingTest test) {
		tests.add(test);
	}
	
	public List<TrainingTest> getTests() {
		return tests;	
	}
	
	public int size() {
		return tests.size();
	}
	
	public List<TrainingSuite> randomSplit() {
		return this.split(2);
	}
	
	public List<TrainingSuite> split(int batchSize) {
		double size = tests.size();
		double batch = batchSize;
		double buckets = size/batch;
		Collections.shuffle(tests);
		List<TrainingSuite> toReturn = new ArrayList<TrainingSuite>((int)buckets);
		
		for (int i = 0 ; i < buckets; i++) {
			TrainingSuite suite = new TrainingSuite();
			for (int j = 0; j < batchSize; j++) {
				suite.addTest(tests.get(i * batchSize + j));
			}
			toReturn.add(suite);
		}

		return toReturn;
	}
}
