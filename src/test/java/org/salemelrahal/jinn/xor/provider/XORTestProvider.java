package org.salemelrahal.jinn.xor.provider;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.input.InputLayer;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

public class XORTestProvider {

	/**
	 * Test for 1 xor 1 = 0
	 * @return
	 */
	public static TrainingTest getXor1() {
		TrainingTest test = new TrainingTest();
		
		Layer input = new InputLayer(2);
		input.getNeurons().get(0).setNetInput(BigDecimal.ONE);
		input.getNeurons().get(1).setNetInput(BigDecimal.ONE);
		
		Layer expected = new InputLayer(1);
		expected.getNeurons().get(0).setNetInput(BigDecimal.ZERO);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 0 xor 0 = 0
	 * @return
	 */
	public static TrainingTest getXor2() {
		TrainingTest test = new TrainingTest();
		
		Layer input = new InputLayer(2);
		input.getNeurons().get(0).setNetInput(BigDecimal.ZERO);
		input.getNeurons().get(1).setNetInput(BigDecimal.ZERO);
		
		Layer expected = new InputLayer(1);
		expected.getNeurons().get(0).setNetInput(BigDecimal.ZERO);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 1 xor 0 = 1
	 * @return
	 */
	public static TrainingTest getXor3() {
		TrainingTest test = new TrainingTest();
		
		Layer input = new InputLayer(2);
		input.getNeurons().get(0).setNetInput(BigDecimal.ONE);
		input.getNeurons().get(1).setNetInput(BigDecimal.ZERO);
		
		Layer expected = new InputLayer(1);
		expected.getNeurons().get(0).setNetInput(BigDecimal.ONE);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 0 xor 1 = 1
	 * @return
	 */
	public static TrainingTest getXor4() {
		TrainingTest test = new TrainingTest();
		
		Layer input = new InputLayer(2);
		input.getNeurons().get(0).setNetInput(BigDecimal.ZERO);
		input.getNeurons().get(1).setNetInput(BigDecimal.ONE);
		
		Layer expected = new InputLayer(1);
		expected.getNeurons().get(0).setNetInput(BigDecimal.ONE);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	public static TrainingSuite getXorSuite() {
		TrainingSuite trainingSuite = new TrainingSuite();
		trainingSuite.addTest(getXor1());
		trainingSuite.addTest(getXor2());
		trainingSuite.addTest(getXor3());
		trainingSuite.addTest(getXor4());
		return trainingSuite;
	}
	
	public static TrainingSuite getXorSuite0() {
		TrainingSuite trainingSuite = new TrainingSuite();
		trainingSuite.addTest(getXor1());
		trainingSuite.addTest(getXor2());
		return trainingSuite;
	}
	
	public static TrainingSuite getXorSuite1() {
		TrainingSuite trainingSuite = new TrainingSuite();
		trainingSuite.addTest(getXor3());
		trainingSuite.addTest(getXor4());
		return trainingSuite;
	}
}
