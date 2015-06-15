package org.salemelrahal.jinn.model.input;

import java.util.ArrayList;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Neuron;

/**
 * This layer is comprised solely of StaticActivationNeurons.
 *
 */
public class StaticLayer extends Layer {
	
	/**
	 * Reset neurons to use static activation neurons.
	 */
	public StaticLayer(final int size) {
		super();
		neurons = new ArrayList<Neuron>(size);
		for (int i = 0 ; i < size ; i++){
			neurons.add(new StaticActivationNeuron());
		}
	}
}
