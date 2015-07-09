Java Implemented Neural Networks
================================

## Intention ##

The usage of neural networks in computing is increasing as the collective academics progress. This is an effort to make the theory and capabilities of neural networks widely available.

## Graphs & Networks ##

A graph is just another data structure. Specifically it is a collection of vertices and edges, where edges connect vertices. This is useful to represent data as relationships as opposed to focusing on the schema of the data. We can easily represent friends in a social group by their connections instead of worrying about what kind of data makes up a person. A network is a type of graph where the edges are assigned a value. This value might be a cost of traversal, as in the Traveling Salesman problem, or a weight like the document similarity in web pages.

## Neurons ##

We introduce a specific type of vertex here, namely the neuron. A bit of biological background first. In a simplified model of the brain, neurons are connected with synapses (think edges) to other neurons. A neuron might fire, in which case it will send electromagnetic pulses along its outgoing synapses to other neurons. Each neuron has a particular threshold, which if reached by incoming pulses, will cause it to fire. We will create an analogous model and use this to process complex input.

## Neural Network ##

A neural network is a network of neurons (vertices) and links (edges). The network is divided into layers (collections of neurons). In a feed forward neural network all of the layers are chained and every neuron in a layer has an output link to each neuron in the next layer. The first layer is typically called the `input layer`, the last layer is called the `output layer`, and any layer in between is referred to as a `hidden layer`. 

## Perceptron ##

The simplest neural network worthy of inspection is a perceptron. A perceptron is simply one neuron. In order to affect the neuron we will introduce a static input neuron to the network. This neurons only function is to set the input of the perceptron. We can then read the activation on the perceptron. Inspect the `SimplePerceptronTest` to see how we create, train, and test a perceptron that will learn to calculate a random sigmoid function.

