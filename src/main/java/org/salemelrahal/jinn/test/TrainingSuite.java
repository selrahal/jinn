package org.salemelrahal.jinn.test;

import java.util.ArrayList;
import java.util.List;

import org.salemelrahal.jinn.util.RandomUtil;

public class TrainingSuite {
	private List<TrainingTest> tests;
	
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
		List<TrainingSuite> toReturn = new ArrayList<TrainingSuite>(2);
		toReturn.add(new TrainingSuite());
		toReturn.add(new TrainingSuite());
		
		for (TrainingTest test : tests) {
			if (RandomUtil.randomBoolean()) {
				toReturn.get(0).addTest(test);
			} else {
				toReturn.get(1).addTest(test);
			}
		}
		
		return toReturn;
	}
}
