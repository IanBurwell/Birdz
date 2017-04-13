package birdz.Environment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class Environment implements FitnessCalc{
	
	Environment(){
		
	}

	@Override
	public double getFitness(Individual i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getIdealFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumInputs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumLayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLayerSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String displayFitness(Individual i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}

