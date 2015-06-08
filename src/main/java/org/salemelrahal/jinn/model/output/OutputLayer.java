package org.salemelrahal.jinn.model.output;

import java.util.ArrayList;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Link;
import org.salemelrahal.jinn.model.Neuron;

public class OutputLayer extends Layer {
	public OutputLayer(int size) {
		neurons = new ArrayList<Neuron>(size);
		for (int i = 0 ; i < size ; i++){
			neurons.add(new OutputNeuron());
		}
	}
	
	/**
	 * No-op because output layers never have links.
	 */
	@Override
	public void fireLinks() {
	}
}
