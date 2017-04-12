package birds.UI.NN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

abstract class NNUIElement extends JComponent{
	private static final long serialVersionUID = 5238359719425059729L;

	private int padding = 2;
	private double outputStrength = 0.0; //TODO make outputStrength be updated by element being triggered
	
	NNUIElement(int padding){
		this.padding = padding;
	}

	public double getOutputStrength() {
		return outputStrength;
	}

	public void setOutputStrength(double outputStrength) {
		if(outputStrength <= 1.0 && outputStrength >= 0.0) this.outputStrength = outputStrength;
		else if(outputStrength > 1.0) this.outputStrength = 1.0;
		else this.outputStrength = 0.0;
	}
	
	//TODO make into class
	private void drawOther(Graphics g){		
		int diameter = Math.min(this.getHeight(), this.getWidth()) - 2*padding;
		g.fillOval((this.getWidth() - diameter)/2 + padding,
				(this.getHeight() - diameter)/2 + padding,
				diameter,
				diameter);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.PLAIN, 24));//TODO make font scale

		String text = "o";
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, this.getWidth()/2 - padding - stringWidth/2, this.getHeight()/2 + padding + stringHeight/4);
	}

	abstract void drawElement(Graphics g);
	
	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	protected void paintComponent(Graphics g) {
		drawElement(g);
		this.repaint();
	}

	
	
	// TODO When it is mousing over and mouse click i want dialog to pop up
	// http://stackoverflow.com/questions/1439022/get-mouse-position 
	
	
	
	
}

