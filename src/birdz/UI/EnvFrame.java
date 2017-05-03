package birdz.UI;

import javax.swing.JFrame;
import birdz.lib.environment.*;

public class EnvFrame extends JFrame {

	private static final long serialVersionUID = -8011140655139414918L;

	public EnvFrame(String title, int width, int height, Environment env){
		super(title);
		this.add(env);
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
	
}
