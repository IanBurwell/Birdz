package birdz.Environment;

import java.awt.Point;

public class init {

	public static void main(String[] args) {
		Environment env = new Environment("Actual title", 800, 600);
		env.waitForOpen();

		for(int i = 0; i < 3600; i++){
			env.devUpdate(new Bird[] {new Bird(new Point(400,300), i+90)});
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {e.printStackTrace();}

		}
	}

}
