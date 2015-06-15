package org.salemelrahal.jinn.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.input.StaticLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Network {
	private static final Logger LOG = LoggerFactory.getLogger(Network.class);
	private List<Layer> layers;
	
	public Network(int inputSize, int outputSize, int... hiddenSizes) {
		layers = new ArrayList<Layer>(3);
		layers.add(new StaticLayer(inputSize));
		for (int i : hiddenSizes) {
			layers.add(new Layer(i));
		}
		layers.add(new Layer(outputSize));
		
		for (int i = 0 ; i + 1 < layers.size(); i++ ){
			layers.get(i).before(layers.get(i+1));
		}
	}
	
	public void fire(Layer input) {
		this.resetErrors();
		this.resetNetInput();
		
		
		//Set input layer values
		Iterator<Neuron> fromSet = input.neuronIterator();
		for (Neuron to : layers.get(0).getNeurons()) {
			if (!fromSet.hasNext()) {
				break;
			} else {
				Neuron from = fromSet.next();
				to.setNetInput(from.getNetInput());
			}
		}
		
		//Fire all links
		for (Layer layer : layers) {
			layer.fireLinks();
		}
	}
	
	public void backPropagate(Layer expected){
		Iterator<Neuron> expectedNeurons = expected.neuronIterator();
		this.resetErrors();
		
		//Set the error on the output layer
		for (Neuron neuron : getOutput().getNeurons()) {
			if (expectedNeurons.hasNext()) {
				Neuron expectedNeuron = expectedNeurons.next();
				double expectedActivation = expectedNeuron.getActivation();
				double actualActivation = neuron.getActivation();
				double cost = actualActivation - expectedActivation;
				double error = cost * neuron.getActivationDerivative();
//				LOG.info("E:" + expectedActivation + ", A:" + actualActivation + ", AD:" + neuron.getActivationDerivative()+ ", Error " + error);
				neuron.setError(error);
			}
		}
		
		//Back propagate error through the hidden layers
		for (int i = layers.size() - 1; i >= 0; i--) {
			layers.get(i).backpropagate();
		}
	}
	
	public void updateRunningError(double learningRateFactor) {
		for (Layer layer : layers) {
			layer.updateRunningError(learningRateFactor);
		}
	}

	
	public void learn() {
		for (Layer layer : layers) {
			layer.learn();
		}
	}
	
	private void resetErrors() {
		for (Layer layer : layers) {
			layer.resetErrors();
		}
	}
	
	private void resetNetInput() {
		for (Layer layer : layers) {
			layer.resetNetInput();
		}
	}

	public Layer getOutput(){
		return layers.get(layers.size()-1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Network \n");
		for (Layer layer : layers) {
			sb.append(layer.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public double hashed() {
		double count = 0;
		for (Layer layer : layers) {
			count = count + layer.hashed();
		}
		return count;
	}
}
