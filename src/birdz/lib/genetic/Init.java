package birdz.lib.genetic;


import java.awt.Color;

import birdz.UI.PixelUI;
import birdz.lib.simulations.*;

public class Init {

    public static void main(String[] args) {
    	pixelInit();
    //	ianInit();
    }

    
    
	private static void pixelInit() {//TODO make not so inefficient 
		Learner pixelLearner = new Learner(new PixelFitnessCalc(), 50);

		Individual bestI = pixelLearner.learnUntilDone(true);
		Color[][] pixels = new Color[500][500];
		
		for(int col = 0; col < 500; col++)
			for(int row = 0; row < 500; row++){
				if(row == 0 || col == 0 || row == 499 || col == 499)
					pixels[col][row] = Color.BLACK;//wall
				else if(row == 5 && col == 5)
					pixels[col][row] = Color.BLUE;//guy
				else if(row == 495 && col == 495)
					pixels[col][row] = Color.GREEN;//goal
				else if(row == 10 && col < 20)
					pixels[col][row] = Color.BLACK;//wall
				else 
					pixels[col][row] = Color.WHITE;//space
				
			}
		
		PixelUI ui = new PixelUI("", pixels, 600, 600);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		for(int frame = 0; frame < 1000; frame++){
			int guyX = 5, guyY = 5;
			
			for(int row = 0; row < pixels[0].length; row++)
				for(int col = 0; col < pixels.length; col++)
					if(pixels[col][row] == Color.BLUE){
						guyX = col;
						guyY = row;
					}
			
			
			double[] walls = new double[8];
			/* 701
			 * 6 2
			 * 543
			 */
			walls[0] = (pixels[guyX][guyY+1] == Color.BLACK) ? 1 : 0;
			walls[1] = (pixels[guyX+1][guyY+1] == Color.BLACK) ? 1 : 0;
			walls[2] = (pixels[guyX+1][guyY] == Color.BLACK) ? 1 : 0;
			walls[3] = (pixels[guyX+1][guyY-1] == Color.BLACK) ? 1 : 0;
			walls[4] = (pixels[guyX][guyY-1] == Color.BLACK) ? 1 : 0;
			walls[5] = (pixels[guyX-1][guyY-1] == Color.BLACK) ? 1 : 0;
			walls[6] = (pixels[guyX-1][guyY] == Color.BLACK) ? 1 : 0;
			walls[7] = (pixels[guyX-1][guyY+1] == Color.BLACK) ? 1 : 0;
			
			double[] inputs = new double[9];
			for(int j = 0; j < 8; j++)
				inputs[j] = walls[j];
			inputs[8] = 0 - (Math.sqrt(Math.pow(495-guyX, 2)+Math.pow(495-guyY, 2)));
			double[] outputs = bestI.fire(inputs);
			
			int k = 0;
			for(int j = 0; j < outputs.length; j++)
				if(outputs[j] > outputs[k])
					k = j;
			int dX = 0, dY = 0;
			if(outputs[k] > 0.5)
			switch(k) {
			case 0: dX = 0; dY = 1; break;
			case 1: dX = 1; dY = 1; break;
			case 2: dX = 1; dY = 0; break;
			case 3: dX = 1; dY = -1; break;
			case 4: dX = 0; dY = -1; break;
			case 5: dX = -1; dY = -1; break;
			case 6: dX = -1; dY = 0; break;
			case 7: dX = -1; dY = 1; break;
			}

				if(pixels[guyX+dX][guyY+dY] == Color.WHITE){
					pixels[guyX][guyY] = Color.WHITE;
					pixels[guyX+dX][guyY+dY] = Color.BLUE;
				}
			ui.repaint();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	
	private static void ianInit() {
		Learner xorLearner = new Learner(new XorFitnessCalc(), 50);
		xorLearner.learnUntilDone(true);	
	}
}