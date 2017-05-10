package birdz.UI;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import birdz.lib.environment.*;

public class EnvFrame extends JFrame {

	private static final long serialVersionUID = -8011140655139414918L;
	private Environment env;
	
	public EnvFrame(String title, int width, int height, Environment env){
		super(title);
		this.env = env;
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
