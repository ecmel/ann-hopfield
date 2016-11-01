# Hopfield Artificial Neural Network Example

An old example of Hopfield ANN

## Screenshot

![screenshot](https://cloud.githubusercontent.com/assets/1858678/19889325/f2bc897a-a03c-11e6-8207-ced7cb3a10e5.png)

## Help File

Train MLP network to learn the selected patterns. In addition to predefined patterns, you may define also your own patterns.The network has 80 neurons since the pattern defined on 8x10 grid are to be trained to the network. The number of neurons at the output layer depends on the number of patterns that you have selected to be trained. In the network there are two hidden layers and you can adjust the number of neurons at each layer. Furthermore you can adjust the number of iterations and learning rate. You may observe the value of the error as training progresses.

After you have trained the network, load the patterns that you want on the grid, you may distort them by adding noise or by directly changing the elements on the grid by clicking them. Then ask the network to recognize the pattern. The charts at the bottom right of the applett are shows the value of each neuron output. In the ideal case the output of the neuron corresponding to the pattern will be 100% and all the others will be 0%. 

Observe what happens as the amount of noiseis increased, and also as the number of iterations are increased.

### Here are some useful tips:

- Select the patterns to be trained by clicking on the thickbox under them. 
- Click on the "Train Selected Pattern(s)" button to train the network. You can adjust the learning rate, training iteration number and the number of neurons in the hidden layer. 
- Load one of the patterns by clicking on it. 
- Add random noise to the pattern by using "Add Noise" button. (You can adjust the level of noise) 
- You can edit the loaded pattern. 
- Click on "Recall" button to see how the Backpropagation algorithm gives the results. The charts at the bottom right of the applett are shows the value of each neuron output. In the ideal case the output of the neuron corresponding to the pattern will be 100% and all the others will be 0%. 
- You can clear the grids by clicking on "Clear Pattern" buttons 
- You can create your own pattern on the loaded pattern section, then you can simply save it by clicking the thickbox belonging to pattern that you want to save it to, and then clicking "Save" button.
