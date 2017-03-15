package birdz.UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import birdz.NN.NeuralNetwork;
import birdz.NN.NeuralNetwork.Input;
import birdz.NN.NeuralNetwork.Perceptron;

public class NeuralNetworkUI extends JFrame{

	private NeuralNetwork neuralNetwork;
	private NNPanel panel;
	private int frameWidth;
	private int frameHeight;

	public NeuralNetworkUI(NeuralNetwork nn, String name) {
		this(nn, name, 400, 400);
	}

	public NeuralNetworkUI(NeuralNetwork nn, String name, int frameWidth, int frameHeight) {
		super(name);
		this.neuralNetwork = nn;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		panel = new NNPanel(nn);
		setupWindow();
	}

	private void setupWindow(){
		this.setSize(frameWidth, frameHeight);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private static Object lock = new Object();
	public void waitForOpen(){
		synchronized(lock) {
			while (!this.isVisible())
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}



	private class NNUIElement extends JComponent{
		private static final long serialVersionUID = 5238359719425059729L;

		private Input element;
		private int padding = 2;

		NNUIElement(Input element, int padding){
			this(element);
			this.padding = padding;
		}
		
		NNUIElement(Input element){//TODO make this display the type of element(Input/perceptron etc) and connections
			this.element = element;
			//TODO add for inputs (and somehow outputs)

		}

		private void drawPerceptron(Graphics g){
			//TODO make the padding only happen to smaller of height and width, or onlu if it needs to
			g.setColor(Color.BLACK);
			g.fillOval(padding, padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Calkboard",Font.PLAIN, 10));
			g.drawString("TEST", Math.min(this.getHeight(), this.getWidth())/2 + padding, Math.min(this.getHeight(), this.getWidth())/2 + padding);
		
		}

		protected void paintComponent(Graphics g) {
			if(element instanceof Perceptron)
				drawPerceptron(g);
			this.repaint();
		}

	}

	private class NNPanel extends JPanel{
		private static final long serialVersionUID = 924418038820117908L;

		private NeuralNetwork neuralNetwork;		
		private NNUIElement[][] network;

		private GridLayout layout;

		public NNPanel(NeuralNetwork nn){
			this.neuralNetwork = nn;

			network = new NNUIElement[Math.max(Math.max(nn.network[0].length, nn.inputs.length),nn.outputs.length)][nn.network.length + 2];
			for(int i = 0; i < nn.inputs.length; i++)
				network[0][i] = new NNUIElement(nn.inputs[i]);
			for(int i = 0; i < nn.outputs.length; i++)
				network[network.length-1][i] = new NNUIElement(nn.outputs[i]);
			for(int row = 0; row < nn.outputs.length; row++)
				for(int col = 0; col < nn.outputs.length; col++)
					network[col+1][row] = new NNUIElement(nn.network[row][col]);

			layout = new GridLayout(network.length, network[0].length);
			setLayout(layout);

			for(NNUIElement[] nnuiA : network)
				for(NNUIElement nnui : nnuiA)
					this.add((nnui == null) ? new JPanel() : nnui);


		}
		
	}

}
