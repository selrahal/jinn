package org.salemelrahal.jinn.model.input;

import org.salemelrahal.jinn.model.Neuron;

/**
 * This Neuron's activation is the input on it. It has no bias
 * and cannot learn.
 *
 */
public class StaticActivationNeuron extends Neuron {
	
	public StaticActivationNeuron() {
		super();
		
		//Static activation neurons don't have biases.
		this.bias = 0;
	}
	
	/**
	 * The activation function for input neurons is the identity function.
	 * f(x) = x
	 */
	@Override
	public double getActivation() {
		return this.netInput;
	}
	
	/**
	 * Input neurons have a fixed activation and bias, thus they cannot learn.
	 */
	@Override
	public void learn() {
		//No-op
	}
	
	/**
	 * Input neurons have a fixed activation and bias,
	 *  so they do not keep track of error.
	 */
	@Override
	public void updateRunningError(double learningRateFactor) {
		//No-op
	}
	
}
