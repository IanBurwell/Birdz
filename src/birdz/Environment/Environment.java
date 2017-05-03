package birdz.Environment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class Environment extends JFrame {

	private static final long serialVersionUID = -5347268065302433404L;
	
	EnvDisplay eDisp = new EnvDisplay();
	
	public Environment(int width, int height){
		this("", width, height);
	}

	public Environment(String title, int width, int height){
		super(title);
		this.add(eDisp);//TODO pass info
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);
	}

	public void devUpdate(Bird[] birds){
		eDisp.update(birds);
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
	
	
	private class EnvDisplay extends JComponent{

		private Bird[] birds;
		public int birdSize = 20;


		EnvDisplay(){
			this(null);
		}

		EnvDisplay(Bird[] birds){
			if(birds != null) update(birds);
		}

		public void update(Bird[] birds){
			this.birds = birds;
			this.repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			
			for(Bird b : birds){
				g.setColor(Color.BLUE);//TODO make it have color defined in constructor
				g.fillOval(b.position.x-birdSize, b.position.y-birdSize, birdSize*2, birdSize*2);
				
				g.fillPolygon(new int[] {b.position.x+(int)(birdSize*Math.cos(Math.toRadians(b.degRotation))),
								b.position.x+(int)(birdSize*Math.cos((Math.PI/2)+Math.toRadians(b.degRotation))),
								b.position.x+(int)(2*birdSize*Math.cos((Math.PI/4)+Math.toRadians(b.degRotation)))}, 	  	  
							  new int[] {b.position.y+(int)(birdSize*Math.sin(Math.toRadians(b.degRotation))),
								b.position.y+(int)(birdSize*Math.sin((Math.PI/2)+Math.toRadians(b.degRotation))),
								b.position.y+(int)(2*birdSize*Math.sin((Math.PI/4)+Math.toRadians(b.degRotation)))}, 
							  3);
			}
		}



	}

}

