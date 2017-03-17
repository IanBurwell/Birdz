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
		private Perceptron pElement = null;
		private int padding = 2;

		NNUIElement(Input element, int padding){
			this(element);
			this.padding = padding;
		}
		
		NNUIElement(Input element){//TODO make this display the type of element(Input/perceptron etc) and connections
			this.element = element;
			if(element instanceof Perceptron) pElement = (Perceptron) element;
			
			//TODO add for inputs (and somehow outputs)

		}

		private void drawPerceptron(Graphics g){
			//TODO make the padding only happen to smaller of height and width, or onlu if it needs to
			g.setColor(Color.BLACK);
			g.fillOval(padding, padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 24));//TODO make font scale
			
			String text = "" + (double)((int)(pElement.weights[0] * 100))/100;
			int stringWidth = g.getFontMetrics().stringWidth(text);
			int stringHeight = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
			g.drawString(text, (Math.min(this.getHeight(), this.getWidth())/2 + padding) - stringWidth/2, (Math.min(this.getHeight(), this.getWidth())/2 + padding) + stringHeight/4);
		
		}

		private void drawOther(Graphics g){
			//TODO make the padding only happen to smaller of height and width, or onlu if it needs to
			g.setColor(Color.RED);
			g.fillOval(padding, padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding, Math.min(this.getHeight(), this.getWidth()) - 2*padding);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 24));//TODO make font scale
			
			String text = "o";
			int stringWidth = g.getFontMetrics().stringWidth(text);
			int stringHeight = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
			g.drawString(text, (Math.min(this.getHeight(), this.getWidth())/2 + padding) - stringWidth/2, (Math.min(this.getHeight(), this.getWidth())/2 + padding) + stringHeight/4);
		
		}
		
		protected void paintComponent(Graphics g) {
			if(element instanceof Perceptron)
				drawPerceptron(g);
			else{
				drawOther(g);
			}
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

			network = new NNUIElement[nn.network.length + 2][Math.max(Math.max(nn.network[0].length, nn.inputs.length),nn.outputs.length)];
			for(int i = 0; i < nn.inputs.length; i++)
				network[0][i] = new NNUIElement(nn.inputs[i]);
			for(int i = 0; i < nn.outputs.length; i++)
				network[network.length-1][i] = new NNUIElement(nn.outputs[i]);
			for(int row = 0; row < nn.network[0].length; row++)
				for(int col = 0; col < nn.network.length; col++)
					network[col+1][row] = new NNUIElement(nn.network[col][row]);

			layout = new GridLayout(network[0].length, network.length);
			setLayout(layout);

			for(int row = 0; row < network[0].length; row++)
				for(int col = 0; col < network.length; col++)
					this.add((network[col][row] == null) ? new JPanel() : network[col][row]);//TODO make it not add panel


		}
		
	}

}
