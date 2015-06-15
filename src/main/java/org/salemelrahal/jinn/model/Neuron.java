package org.salemelrahal.jinn.model;

import java.math.BigDecimal;

import org.salemelrahal.jinn.util.MathUtil;
import org.salemelrahal.jinn.util.RandomUtil;

public class Neuron {
	protected BigDecimal netInput = BigDecimal.ZERO;
	private BigDecimal bias = RandomUtil.randomBigDecimal();
	private BigDecimal error = BigDecimal.ZERO;
	private BigDecimal runningError = BigDecimal.ZERO;
	
	private boolean cachedActivationStale = true;
	private BigDecimal cachedActivation = BigDecimal.ZERO;
	
	private boolean cachedActivationDerivitiveStale = true;
	private BigDecimal cachedActivationDerivitave = BigDecimal.ZERO;
	
	public Neuron() {
	}
	
	public BigDecimal getActivation() {
		if (cachedActivationStale) {
			cachedActivation = MathUtil.sigmoid(netInput.add(bias));
			cachedActivationStale = false;
		}
		return cachedActivation;
	}
	
	public BigDecimal getActivationDerivative() {
		if (cachedActivationDerivitiveStale) {
			cachedActivationDerivitave = MathUtil.sigmoidDerivitave(netInput.add(bias));;
			cachedActivationDerivitiveStale = false;
		}
		return cachedActivationDerivitave;
	}
	
	/**
	 * only in charge of making sure the running delta is up to date
	 * @param learningRateFactor 
	 */
	public void updateRunningError(BigDecimal learningRateFactor) {
		runningError = runningError.add(error.multiply(learningRateFactor));
	}
	
	public void learn() {
//		runningError = runningError.multiply(learningRate).divide(BigDecimal.valueOf(numberOfTests),5,RoundingMode.HALF_DOWN);
		bias = bias.subtract(runningError);
		runningError = BigDecimal.ZERO;
	}
	
	public void setNetInput(BigDecimal activation) {
		cachedActivationDerivitiveStale = true;
		cachedActivationStale = true;
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

	public BigDecimal hashed() {
		return bias;
	}

}
