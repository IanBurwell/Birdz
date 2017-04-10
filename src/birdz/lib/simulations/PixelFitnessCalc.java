package birdz.lib.simulations;

import java.awt.Color;
import java.awt.Point;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class PixelFitnessCalc implements FitnessCalc{

	final int FRAMES = 50;
	int[][] pixels = new int[500][500];//1=wall, 2=guy, 3=goal
	int guyX = 5, guyY = 5;

	public PixelFitnessCalc(){

		for(int col = 0; col < 500; col++)
			for(int row = 0; row < 500; row++){
				if(row == 0 || col == 0 || row == 499 || col == 499)
					pixels[col][row] = 1;//wall
				else if(row == 5 && col == 5)
					pixels[col][row] = 2;//guy
				else if(row == 495 && col == 495)
					pixels[col][row] = 3;//goal
				else if(row == 10 && col < 20)
					pixels[col][row] = 1;//wall
				else
					pixels[col][row] = 0;//space
			}
	}

	@Override
	public double getFitness(Individual individual) {//TODO if it doesnt move in like 5 moves then abort
		for(int i = 0; i < FRAMES; i++){

			//TODO check if ontop of the point
			double[] outputs = individual.fire(getSurroundingWalls(guyX, guyY));

			int dX = (outputs[0] > 1/3) ? ((outputs[0] > 2/3) ? 1 : 0): -1;
			int dY = (outputs[1] > 1/3) ? ((outputs[1] > 2/3) ? 1 : 0): -1;

			if(dX == 0 && dY == 0)
				break;
			
			if(pixels[guyX+dX][guyY+dY] == 0){
				pixels[guyX][guyY] = 0;
				pixels[guyX+dX][guyY+dY] = 2;
			}else
				break;
			
		}
		return -Math.sqrt(Math.pow(495-guyX, 2)+Math.pow(495-guyY, 2));
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
		return 10;
	}

	@Override
	public int getLayerSize() {
		return 10;
	}

	@Override
	public String displayFitness(Individual i) {
		return String.valueOf(i.getFitness());
	}

}
