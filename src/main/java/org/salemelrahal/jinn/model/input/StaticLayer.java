package org.salemelrahal.jinn.model.input;

import java.util.ArrayList;

import org.salemelrahal.jinn.model.Layer;
import org.salemelrahal.jinn.model.Neuron;

public class StaticLayer extends Layer {
	public StaticLayer(int size) {
		neurons = new ArrayList<Neuron>(size);
		for (int i = 0 ; i < size ; i++){
			neurons.add(new StaticActivationNeuron());
		}
	}
}
