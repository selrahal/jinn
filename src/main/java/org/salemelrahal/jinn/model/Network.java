package org.salemelrahal.jinn.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.salemelrahal.jinn.model.input.InputLayer;
import org.salemelrahal.jinn.model.output.OutputLayer;

public class Network {
	private InputLayer inputLayer;
	private List<Layer> hiddenLayers;
	private OutputLayer outputLayer;
	
	public Network() {
		//Only 3 layers for now (input, hidden, output)
		hiddenLayers = new ArrayList<Layer>(1);
		
		inputLayer = new InputLayer(2);
		hiddenLayers.add(new Layer(4));
		outputLayer = new OutputLayer(1);
		
		inputLayer.before(hiddenLayers.get(0));
		for (int i = 0 ; i + 1 < hiddenLayers.size(); i++ ){
			hiddenLayers.get(i).before(hiddenLayers.get(i+1));
		}
		hiddenLayers.get(hiddenLayers.size() - 1).before(outputLayer);
		
	}
	
	public void fire(Layer input) {
		this.resetAllNodes();
		
		
		//Set input layer values
		Iterator<Neuron> fromSet = input.neuronIterator();
		for (Neuron to : inputLayer.getNeurons()) {
			if (!fromSet.hasNext()) {
				break;
			} else {
				to.setNetInput(fromSet.next().getNetInput());
			}
		}
		
		//Fire all links
		for (Link link : inputLayer.getLinks()) {
			link.fire();
		}
		for (int i = 0 ; i < hiddenLayers.size() ; i++) {
			for (Link link : hiddenLayers.get(i).getLinks()) {
				link.fire();
			}
		}
	}
	
	public void postTest() {
		
	}
	
	public void preTest() {
		
	}

	
	public void backPropagate(Layer expected){
		Iterator<Neuron> expectedNeurons = expected.neuronIterator();
		this.resetErrors();
		
		//Set the error on the output layer
		for (Neuron neuron : outputLayer.getNeurons()) {
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
		
		//Back propagate error through the hidden layers
		for (int i = hiddenLayers.size() - 1; i >= 0; i--) {
			Layer current = hiddenLayers.get(i);
			
			for (Link link : current.getLinks()) {
				link.backPropagate();
			}
		}
		
		for (Link link : inputLayer.getLinks()) {
			link.backPropagate();
		}
		
		//update running deltas
		updateRunningDeltas();
	}
	
	public void updateRunningDeltas() {
		for (Link link : inputLayer.getLinks()) {
			link.updateTest();
		}
		
		for (Layer hiddenLayer : hiddenLayers) {
			for (Link link : hiddenLayer.getLinks()) {
				link.updateTest();
			}
		}
		
		for (Layer hiddenLayer : hiddenLayers) {
			for (Neuron neuron : hiddenLayer.getNeurons()){
				neuron.updateTest();
			}
		}
		
		for (Neuron neuron : outputLayer.getNeurons()) {
			neuron.updateTest();
		}
	}

	
	public void learn() {
		//update the weights
		for (Link link : inputLayer.getLinks()) {
			link.learn();
		}
		
		for (Layer hiddenLayer : hiddenLayers) {
			for (Link link : hiddenLayer.getLinks()) {
				link.learn();
			}
		}
		
		for (Layer hiddenLayer : hiddenLayers) {
			for (Neuron neuron : hiddenLayer.getNeurons()){
				neuron.learn();
			}
		}
		
		for (Neuron neuron : outputLayer.getNeurons()) {
			neuron.learn();
		}
	}
	
	private void resetErrors() {
		//Reset input layer nodes
		inputLayer.resetErrors();
		
		
		//Reset Hidden 
		for (Layer layer : hiddenLayers) {
			layer.resetErrors();
		}
		
		//Reset output
		outputLayer.resetErrors();
	}
	
	private void resetAllNodes() {
		//Reset input layer nodes
		inputLayer.resetNeurons();
		
		
		//Reset Hidden 
		for (Layer layer : hiddenLayers) {
			layer.resetNeurons();
		}
		
		//Reset output
		outputLayer.resetNeurons();
	}

	public Layer getOutput(){
		return outputLayer;
	}

	@Override
	public String toString() {
		return "Network\n inputLayer=" + inputLayer + ",\n hiddenLayers="
				+ hiddenLayers + ",\n outputLayer=" + outputLayer;
	}
}
