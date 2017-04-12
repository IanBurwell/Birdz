package birdz.lib.simulations;

import java.awt.Color;
import java.awt.Point;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class PixelFitnessCalc implements FitnessCalc{

	final int FRAMES = 500;
	int[][] pixels = new int[500][500];//1=wall
	int guyX, guyY;

	public PixelFitnessCalc(){

		initEnvironment();
	}

	public void initEnvironment() {
		for(int col = 0; col < 500; col++)
			for(int row = 0; row < 500; row++){
				if(row == 0 || col == 0 || row == 499 || col == 499 || (row == 10 && col < 20))
					pixels[col][row] = 1;
				else
					pixels[col][row] = 0;
			}
		guyX = 5; guyY = 5;
	}

	@Override
	public double getFitness(Individual individual) {
		initEnvironment();
		boolean stuck = false;
		for(int i = 0; i < FRAMES && !stuck; i++){

			double[] outputs = individual.fire(getSurroundingWalls(guyX, guyY));

			int k = 0;
			for(int j = 0; j < 8; j++)
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
			else
				stuck = true;

			if(pixels[guyX+dX][guyY+dY] == 0){
				guyX += dX;
				guyY += dY;
			} else
				stuck = true;;
			

		}
		return 0 - (Math.sqrt(Math.pow(495-guyX, 2)+Math.pow(495-guyY, 2)));
	}

	private double[] getSurroundingWalls(int x, int y) {
		double[] walls = new double[8];
		/* 701
		 * 6 2
		 * 543
		 */
		walls[0] = (pixels[x][y+1] == 1) ? 1 : 0;
		walls[1] = (pixels[x+1][y+1] == 1) ? 1 : 0;
		walls[2] = (pixels[x+1][y] == 1) ? 1 : 0;
		walls[3] = (pixels[x+1][y-1] == 1) ? 1 : 0;
		walls[4] = (pixels[x][y-1] == 1) ? 1 : 0;
		walls[5] = (pixels[x-1][y-1] == 1) ? 1 : 0;
		walls[6] = (pixels[x-1][y] == 1) ? 1 : 0;
		walls[7] = (pixels[x-1][y+1] == 1) ? 1 : 0;
		return walls;
	}

	@Override
	public double getIdealFitness() {
		return -2;
	}

	@Override
	public int getNumInputs() {
		return 8;
	}

	@Override
	public int getNumLayers() {
		return 2;
	}

	@Override
	public int getLayerSize() {
		return 10;
	}

	@Override
	public String displayFitness(Individual i) {
		return "(" + String.valueOf(guyX) + ", " + String.valueOf(guyY) + "), distance from goal = " + String.valueOf(getFitness(i));
	}

}
