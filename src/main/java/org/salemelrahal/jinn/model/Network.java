package org.salemelrahal.jinn.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.input.InputLayer;
import org.salemelrahal.jinn.model.output.OutputLayer;

public class Network {
	private List<Layer> layers;
	
	public Network() {
		//Only 3 layers for now (input, hidden, output)
		layers = new ArrayList<Layer>(3);
		
		layers.add(new InputLayer(2));
		layers.add(new Layer(4));
		layers.add(new OutputLayer(1));
		
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
				to.setNetInput(fromSet.next().getNetInput());
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
				BigDecimal expectedActivation = expectedNeuron.getActivation();
				BigDecimal actualActivation = neuron.getActivation();
				BigDecimal cost = actualActivation.subtract(expectedActivation);
				BigDecimal error = cost.multiply(neuron.getActivationDerivative());
//				System.out.println("E:" + expectedActivation + ", A:" + actualActivation + ", AD:" + neuron.getActivationDerivative()+ ", Error " + error);
				neuron.setError(error);
			}
		}
		
		//Back propagate error backwards through the hidden layers
		for (int i = layers.size() - 1; i >= 0; i--) {
			layers.get(i).backpropagate();
		}
		
		//update running deltas
		updateRunningError();
	}
	
	public void updateRunningError() {
		for (Layer layer : layers) {
			layer.updateRunningError();
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
		return "Network layers=" + layers;
	}
}
