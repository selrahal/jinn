package org.salemelrahal.jinn.model;


import org.salemelrahal.jinn.util.RandomUtil;



public class Link {
	private Neuron from;
	private Neuron to;
	private double weight;
	private double runningError = 0;
	
	public Link(Neuron from, Neuron to) {
		this(from, to, RandomUtil.randomGaussian());
	}
	
	public Link(Neuron from, Neuron to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public void fire() {
		double netInput = to.getNetInput();
		double source = from.getActivation();
		double delta = source * weight;
		to.setNetInput(netInput + delta);
	}
	
	public void backPropagate() {
		
		double source = to.getError();
		double delta = source*weight*from.getActivationDerivative();
		
		double error = from.getError();
		from.setError(error + delta);
	}
	
	
	/**
	 * only in charge of making sure the running delta is up to date
	 * @param learningRateFactor 
	 */
	public void updateRunningError(double learningRateFactor) {
		double activation = from.getActivation();
		double error = to.getError();
		
		runningError = runningError + (activation * (error) * (learningRateFactor));
	}
	
	public void learn() {
//		runningError = runningError.multiply(learningRate).divide(BigDecimal.valueOf(numberOfTests),5,RoundingMode.HALF_DOWN);
		weight = weight - runningError;
		runningError = 0;
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		long temp;
		temp = Double.doubleToLongBits(runningError);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(runningError) != Double
				.doubleToLongBits(other.runningError))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}

	public double hashed() {
		return weight;
	}

}
