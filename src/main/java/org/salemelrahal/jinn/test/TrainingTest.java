package org.salemelrahal.jinn.test;

import org.salemelrahal.jinn.model.Neuron;
import org.salemelrahal.jinn.model.input.StaticLayer;

public class TrainingTest {
	protected StaticLayer input;
	protected StaticLayer expected;
	public StaticLayer getInput() {
		return input;
	}
	public void setInput(StaticLayer input) {
		this.input = input;
	}
	public StaticLayer getExpected() {
		return expected;
	}
	public void setExpected(StaticLayer expected) {
		this.expected = expected;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("input=[");
		for (Neuron neuron : input.getNeurons()) {
			sb.append(neuron.getNetInput());
			sb.append(",");
		}
		sb.append("]");
		
		sb.append("\nexpected=[");
		for (Neuron neuron : expected.getNeurons()) {
			sb.append(neuron.getNetInput());
			sb.append(",");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}
