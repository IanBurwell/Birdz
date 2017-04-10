package birds.UI.NN;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import birdz.NN.NeuralNetwork;
import birdz.NN.NeuralNetwork.Input;
import birdz.NN.NeuralNetwork.Perceptron;
import birdz.lib.genetic.Individual;

public class NeuralNetworkUI extends JFrame{

	private Individual individual;
	private NNUIElement[][] uiNetwork;
	private NNPanel panel;
	private int frameWidth;
	private int frameHeight;
	
	public NeuralNetworkUI(Individual individual, String name) {
		this(individual, name, 400, 400);
	}

	public NeuralNetworkUI(Individual individual, String name, int frameWidth, int frameHeight) {
		super(name);
		this.individual = individual;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		panel = new NNPanel(individual);
		uiNetwork = panel.network;
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

	public void setOutputStrength(int collum, int row, double strength) {
		uiNetwork[collum][row].setOutputStrength(strength);
	}

	
	
	
	

	
	
	private class NNPanel extends JPanel{
		private static final long serialVersionUID = 924418038820117908L;

		private Individual individual;		
		NNUIElement[][] network;

		private GridLayout layout;

		public NNPanel(Individual individual){
			this.individual = individual;


			for(int i = 0; i < individual.getFitnessCalc().getNumInputs(); i++)
				network[0][i] = new NNUIElement(ElementType.Input);
			
			
			//TODO make network fill
//			network = new NNUIElement[nn.network.length + 2][Math.max(Math.max(nn.network[0].length, nn.inputs.length),nn.outputs.length)];
//			for(int i = 0; i < nn.inputs.length; i++)
//				network[0][i] = new NNUIElement(nn.inputs[i]);
//			for(int i = 0; i < nn.outputs.length; i++)
//				network[network.length-1][i] = new NNUIElement(nn.outputs[i]);
//			for(int row = 0; row < nn.network[0].length; row++)
//				for(int col = 0; col < nn.network.length; col++)
//					network[col+1][row] = new NNUIElement(nn.network[col][row]);

			layout = new GridLayout(network[0].length, network.length);
			setLayout(layout);

			for(int row = 0; row < network[0].length; row++)
				for(int col = 0; col < network.length; col++)
					this.add((network[col][row] == null) ? new JComponent(){
																private static final long serialVersionUID = 1L; 
															} : network[col][row]);

		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for(int col = 0; col < network.length-1; col++)
				for(int row = 0; row < network[0].length; row++)
					if(network[col][row] != null)
						for(int selectedRow = 0; selectedRow < network[0].length; selectedRow++)
							if(network[col+1][selectedRow] != null){
								g.setColor(new Color((int)(network[col][row].getOutputStrength()*255), 0, 0));
								g.drawLine(network[col][row].getX() + (network[col][row].getWidth()/2), 
										   network[col][row].getY() + (network[col][row].getHeight()/2),
										   network[col+1][selectedRow].getX() + (network[col+1][selectedRow].getWidth()/2),
										   network[col+1][selectedRow].getY() + (network[col+1][selectedRow].getHeight()/2));
							}
											
								
								
		}
	}

}
