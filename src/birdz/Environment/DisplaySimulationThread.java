package birdz.Environment;

import birdz.Environment.Environment.EnvDisplay;

public class DisplaySimulationThread extends Thread {

	private EnvDisplay envDisplay;

	public DisplaySimulationThread(EnvDisplay eDisp) {
		this.envDisplay = eDisp;
	}

	@Override
	public void run() {
		//TODO simulation stuff with birds
		//TODO use if(this.interrupted()) break; alot to let thread be stoppable
	}
	

}
