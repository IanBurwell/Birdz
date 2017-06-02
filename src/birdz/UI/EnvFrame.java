package birdz.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import birdz.lib.environment.*;

public class EnvFrame extends JFrame {

	private static final long serialVersionUID = -8011140655139414918L;
	private Environment env;
	public JButton startButton = new JButton();
	
	public EnvFrame(String title, Environment env){
		super(title);
		
		this.setLayout(new FlowLayout());	
		
		this.env = env;
		env.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(env);
		
		startButton.setText("Start");
		//startButton.setEnabled(false);
		this.add(startButton);
		
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//TODO add listener to stop threads when window closed
		this.setSize(env.getPreferredSize().width, env.getPreferredSize().height+40);
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
