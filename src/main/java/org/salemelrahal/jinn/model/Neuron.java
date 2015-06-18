package org.salemelrahal.jinn.model;

import org.salemelrahal.jinn.util.MathUtil;
import org.salemelrahal.jinn.util.RandomUtil;

public class Neuron {
	protected double netInput = 0;
	protected double bias = RandomUtil.randomGaussian();
	private double error = 0;
	private double runningError = 0;
	
	private boolean cachedActivationStale = true;
	private double cachedActivation = 0;
	
	private boolean cachedActivationDerivitiveStale = true;
	private double cachedActivationDerivitave = 0;
	
	public Neuron() {
	}
	
	public double getActivation() {
		if (cachedActivationStale) {
			cachedActivation = MathUtil.sigmoid(netInput+bias);
			cachedActivationStale = false;
		}
		return cachedActivation;
	}
	
	public double getActivationDerivative() {
		if (cachedActivationDerivitiveStale) {
			cachedActivationDerivitave = MathUtil.sigmoidDerivitave(netInput+bias);
			cachedActivationDerivitiveStale = false;
		}
		return cachedActivationDerivitave;
	}
	
	/**
	 * only in charge of making sure the running delta is up to date
	 * @param learningRateFactor 
	 */
	public void updateRunningError(double learningRateFactor) {
		runningError = runningError + (error * learningRateFactor);
	}
	
	public void learn() {
//		runningError = runningError.multiply(learningRate).divide(BigDecimal.valueOf(numberOfTests),5,RoundingMode.HALF_DOWN);
		bias = bias - runningError;
		runningError = 0;
	}
	
	public void setNetInput(double activation) {
		cachedActivationDerivitiveStale = true;
		cachedActivationStale = true;
		this.netInput = activation;
	}
	
	public double getNetInput() {
		return netInput;
	}
	
	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Neuron [netInput=" + netInput + ", bias=" + bias + ", activation="
				+ getActivation() + "]";
	}
}
