package org.salemelrahal.jinn.perceptron.test;

import java.util.Iterator;

import org.salemelrahal.jinn.model.input.StaticLayer;
import org.salemelrahal.jinn.test.TrainingTest;
import org.salemelrahal.jinn.util.MathUtil;
import org.salemelrahal.jinn.util.RandomUtil;

public class SigmoidFunctionTrainingTestIterator implements Iterator<TrainingTest>{
	private double slope = 0;
	private double intercept = 0;
	
	public SigmoidFunctionTrainingTestIterator() {
		this(RandomUtil.randomGaussian(),RandomUtil.randomGaussian());
	}
	
	public SigmoidFunctionTrainingTestIterator(final double slope, final double intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public TrainingTest next() {
		double x = RandomUtil.randomGaussian();
		
		final TrainingTest test = new TrainingTest();
		
		final StaticLayer input = new StaticLayer(1);
		input.getNeurons().get(0).setNetInput(x);
		
		final StaticLayer expected = new StaticLayer(1);
		expected.getNeurons().get(0).setNetInput(MathUtil.sigmoid(x*slope + intercept));
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
