package org.salemelrahal.jinn.model.input;

import java.math.BigDecimal;

import org.salemelrahal.jinn.model.Neuron;

public class InputNeuron extends Neuron {
	
	/**
	 * The activation function for input neurons is the identity function.
	 * f(x) = x
	 */
	@Override
	public BigDecimal getActivation() {
		return this.netInput;
	}
	
	/**
	 * Input neurons do not have a bias.
	 */
	@Override
	public BigDecimal getBias() {
		return BigDecimal.ZERO;
	}
}
