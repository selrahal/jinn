package org.salemelrahal.jinn.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.salemelrahal.jinn.util.RandomUtil;



public class Link {
	private Neuron from;
	private Neuron to;
	private BigDecimal weight;
	private BigDecimal runningError = BigDecimal.ZERO;
	private BigDecimal learningRate = BigDecimal.valueOf(1);
	private int numberOfTests = 0;
	
	public Link(Neuron from, Neuron to) {
		this(from, to, RandomUtil.randomBigDecimal());
	}
	
	public Link(Neuron from, Neuron to, BigDecimal weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public void fire() {
		BigDecimal netInput = to.getNetInput();
		BigDecimal source = from.getActivation();
		BigDecimal delta = source.multiply(weight);
		to.setNetInput(netInput.add(delta));
	}
	
	public void backPropagate() {
		
		BigDecimal source = to.getError();
		BigDecimal delta = source.multiply(weight).multiply(from.getActivationDerivative());
		
		BigDecimal error = from.getError();
		from.setError(error.add(delta));
	}
	
	
	/**
	 * only in charge of making sure the running delta is up to date
	 */
	public void updateRunningError() {
		BigDecimal activation = from.getActivation();
		BigDecimal error = to.getError();
		
		runningError = runningError.add(activation.multiply(error));
		numberOfTests++;
	}
	
	public void learn() {
		runningError = runningError.multiply(learningRate).divide(BigDecimal.valueOf(numberOfTests),5,RoundingMode.HALF_DOWN);
		weight = weight.subtract(runningError);
		runningError = BigDecimal.ZERO;
		numberOfTests = 0;
	}
	
	public Neuron getFrom() {
		return from;
	}

	public void setFrom(Neuron from) {
		this.from = from;
	}

	public Neuron getTo() {
		return to;
	}

	public void setTo(Neuron to) {
		this.to = to;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}

}
