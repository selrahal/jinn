package org.salemelrahal.jinn.test;

import org.salemelrahal.jinn.model.Layer;

public class TrainingTest {
	protected Layer input;
	protected Layer expected;
	
	public Layer getInput() {
		return input;
	}
	public void setInput(Layer input) {
		this.input = input;
	}
	public Layer getExpected() {
		return expected;
	}
	public void setExpected(Layer expected) {
		this.expected = expected;
	}
}
