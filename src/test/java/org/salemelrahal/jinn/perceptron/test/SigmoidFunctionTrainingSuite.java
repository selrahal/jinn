package org.salemelrahal.jinn.perceptron.test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.util.RandomUtil;

public class SigmoidFunctionTrainingSuite implements TrainingSuite {
	private double slope = 0;
	private double intercept = 0;
	
	public SigmoidFunctionTrainingSuite() {
		this(RandomUtil.randomGaussian(), RandomUtil.randomGaussian());
	}
	
	public SigmoidFunctionTrainingSuite(double slope, double intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	public void addTest(TrainingTest test) {
		//This suite is stateless
	}

	public Iterator<TrainingTest> iterator() {
		return new SigmoidFunctionTrainingTestIterator(slope, intercept);
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public List<TrainingSuite> split(int batchSize) {
		return Collections.singletonList((TrainingSuite)this);
	}
	
}
