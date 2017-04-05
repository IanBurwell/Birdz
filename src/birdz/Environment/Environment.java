package birdz.Environment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Environment extends JFrame {

	EnvDisplay eDisp = new EnvDisplay();
	
	public Environment(int width, int height){
		this("", width, height);
	}

	public Environment(String title, int width, int height){
		super("title");
		this.add(eDisp);//TODO pass info
		this.setResizable(false);
		this.setSize(width, height);
		this.setVisible(true);
	}

	public void devUpdate(Bird[] birds){
		eDisp.update(birds);
	}
	
	private class EnvDisplay extends JComponent{

		private Bird[] birds;
		public int birdSize = 0;


		EnvDisplay(){
			this(null);
		}

		EnvDisplay(Bird[] birds){
			if(birds != null) update(birds);
		}

		public void update(Bird[] birds){
			this.birds = birds;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.BLUE);//TODO make it have color deffined in constructor

			for(Bird b : birds){
				g.fillOval(b.position.x-birdSize, b.position.y-birdSize, birdSize*2, birdSize*2);
				g.fillPolygon(new int[] {(int)(birdSize*Math.cos(Math.toRadians(b.degRotation))),
								(int)(birdSize*Math.cos((Math.PI/2)+Math.toRadians(b.degRotation))),
								birdSize+(int)(birdSize*Math.cos((Math.PI/4)+Math.toRadians(b.degRotation)))}, 	  	  
							  new int[] {(int)(birdSize*Math.sin(Math.toRadians(b.degRotation))),
								(int)(birdSize*Math.sin((Math.PI/2)+Math.toRadians(b.degRotation))),
								birdSize+(int)(birdSize*Math.cos((Math.PI/4)+Math.toRadians(b.degRotation)))}, 
							  3);
			}
		}



	}
}

