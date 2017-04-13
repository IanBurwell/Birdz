package birdz.Environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BirdzUI extends JFrame {

	EnvDisplay eDisp = new EnvDisplay();

	public BirdzUI(String title, int width, int height){
		super("title");
		this.add(eDisp);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, height);
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

	//only for dev use, you shouldnt be able to directly modify birds from outside class
	public void devUpdate(Bird[] birds){
		eDisp.update(birds);
	}



	class EnvDisplay extends JComponent{

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
				g.setColor(Color.BLUE);
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
