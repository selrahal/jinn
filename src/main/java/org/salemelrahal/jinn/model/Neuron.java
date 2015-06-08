package org.salemelrahal.jinn.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.salemelrahal.jinn.util.MathUtil;
import org.salemelrahal.jinn.util.RandomUtil;

public class Neuron {
	protected BigDecimal netInput = BigDecimal.ZERO;
	private BigDecimal bias = BigDecimal.ZERO;
	private BigDecimal error = BigDecimal.ZERO;
	private BigDecimal totalError = BigDecimal.ZERO;
	private BigDecimal learningRate = BigDecimal.valueOf(1);
	private int numberOfTests = 0;
	
	public Neuron() {
		bias = RandomUtil.randomBigDecimal();
	}
	
	public BigDecimal getActivation() {
		return MathUtil.sigmoid(netInput.add(bias));
	}
	
	public BigDecimal getActivationDerivative() {
		return MathUtil.sigmoidDerivitave(netInput.add(bias));
	}
	
	/**
	 * only in charge of making sure the running delta is up to date
	 */
	public void updateTest() {
		totalError = totalError.add(error);
		numberOfTests++;
	}
	
	public void learn() {
		totalError = totalError.multiply(learningRate).divide(BigDecimal.valueOf(numberOfTests),5,RoundingMode.HALF_DOWN);
		bias = bias.subtract(totalError);
		totalError = BigDecimal.ZERO;
		numberOfTests = 0;
	}
	
	public void setNetInput(BigDecimal activation) {
		this.netInput = activation;
	}
	
	public BigDecimal getNetInput() {
		return netInput;
	}

	public BigDecimal getBias() {
		return bias;
	}

	public void setBias(BigDecimal bias) {
		this.bias = bias;
	}
	
	public BigDecimal getError() {
		return error;
	}

	public void setError(BigDecimal error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Neuron [netInput=" + netInput + ", bias=" + bias + ", activation="
				+ getActivation() + "]";
	}

}
