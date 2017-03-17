import java.awt.Color;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import birdz.NN.NeuralNetwork;
import birdz.NN.NeuralNetwork.Input;
import birdz.UI.NeuralNetworkUI;
import birdz.UI.PixelUI;

public class init {
	
	
	
	public static void main(String[] args){
		//runPixelUiTest();
		runNeuralNetworkUiTest();
	}
	
	private static void runNeuralNetworkUiTest(){
		Input input = new Input(){
			@Override
			public Double fire(){
				return (double)((int)(Math.random() * 5));
			}
		};
		
		NeuralNetworkUI nnUI = new NeuralNetworkUI(new NeuralNetwork(3, 6, 2, input, input), "UI");//TODO make new input
		
	}
	
	private static void runPixelUiTest(){
		final Color[][] map = new Color[100][100];
		PixelUI pixelUI;

		
		for(int c = 0; c < map.length; c++)
			for(int r = 0; r < map[0].length; r++)
				map[c][r] = ((int)(Math.random() * 2) == 0) ? Color.BLACK : Color.WHITE;
		
		pixelUI = new PixelUI("UI", map);
		pixelUI.waitForOpen();
		
		
		for(int i = 0; i < 10000; i++){
			for(int c = 0; c < map.length; c++)
				for(int r = 0; r < map[0].length; r++)
					//map[c][r] = ((int)(Math.random() * 2) == 0) ? Color.BLACK : Color.WHITE;
					map[c][r] = new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255));
			pixelUI.updatePixels(map);
		}
	}

}
