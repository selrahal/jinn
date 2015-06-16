package org.salemelrahal.jinn.xor.test;


import org.salemelrahal.jinn.model.input.StaticLayer;
import org.salemelrahal.jinn.test.ArrayTrainingSuite;
import org.salemelrahal.jinn.test.TrainingSuite;
import org.salemelrahal.jinn.test.TrainingTest;

/**
 * Provides TrainingTests and TrainingSuites modeling the XOR operator.
 *
 */
public class XORTestProvider {

	/**
	 * Test for 1 xor 1 = 0
	 * @return
	 */
	public static TrainingTest getXor1() {
		final TrainingTest test = new TrainingTest();
		
		final StaticLayer input = new StaticLayer(2);
		input.getNeurons().get(0).setNetInput(1);
		input.getNeurons().get(1).setNetInput(1);
		
		final StaticLayer expected = new StaticLayer(1);
		expected.getNeurons().get(0).setNetInput(0);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 0 xor 0 = 0
	 * @return
	 */
	public static TrainingTest getXor2() {
		final TrainingTest test = new TrainingTest();
		
		final StaticLayer input = new StaticLayer(2);
		input.getNeurons().get(0).setNetInput(0);
		input.getNeurons().get(1).setNetInput(0);
		
		final StaticLayer expected = new StaticLayer(1);
		expected.getNeurons().get(0).setNetInput(0);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 1 xor 0 = 1
	 * @return
	 */
	public static TrainingTest getXor3() {
		final TrainingTest test = new TrainingTest();
		
		final StaticLayer input = new StaticLayer(2);
		input.getNeurons().get(0).setNetInput(1);
		input.getNeurons().get(1).setNetInput(0);
		
		final StaticLayer expected = new StaticLayer(1);
		expected.getNeurons().get(0).setNetInput(1);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * Test for 0 xor 1 = 1
	 * @return
	 */
	public static TrainingTest getXor4() {
		final TrainingTest test = new TrainingTest();
		
		final StaticLayer input = new StaticLayer(2);
		input.getNeurons().get(0).setNetInput(0);
		input.getNeurons().get(1).setNetInput(1);
		
		final StaticLayer expected = new StaticLayer(1);
		expected.getNeurons().get(0).setNetInput(1);
		
		test.setInput(input);
		test.setExpected(expected);
		
		return test;
	}
	
	/**
	 * @return a TrainingSuite with all 4 XOR tests
	 */
	public static TrainingSuite getXorSuite() {
		final TrainingSuite trainingSuite = new ArrayTrainingSuite();
		trainingSuite.addTest(getXor1());
		trainingSuite.addTest(getXor2());
		trainingSuite.addTest(getXor3());
		trainingSuite.addTest(getXor4());
		return trainingSuite;
	}
	
	/**
	 * @return a TrainingSuite with the XOR tests that should output 0
	 */
	public static TrainingSuite getXorSuite0() {
		final TrainingSuite trainingSuite = new ArrayTrainingSuite();
		trainingSuite.addTest(getXor1());
		trainingSuite.addTest(getXor2());
		return trainingSuite;
	}
	
	/**
	 * @return a TrainingSuite with the XOR tests that should output 1
	 */
	public static TrainingSuite getXorSuite1() {
		final TrainingSuite trainingSuite = new ArrayTrainingSuite();
		trainingSuite.addTest(getXor3());
		trainingSuite.addTest(getXor4());
		return trainingSuite;
	}
}
