package birds.UI.NN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class UIPerceptron extends NNUIElement {
	private static final long serialVersionUID = 4187996637372811228L;

	public UIPerceptron(int padding) {
		super(padding);
		// TODO Auto-generated constructor stub
	}

	@Override
	void drawElement(Graphics g) {
	g.setColor(Color.BLACK);
		
		int diameter = Math.min(this.getHeight(), this.getWidth()) - 2*getPadding();
		g.fillOval((this.getWidth() - diameter)/2 + getPadding(),
				(this.getHeight() - diameter)/2 + getPadding(),
				diameter,
				diameter);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.PLAIN, 24));//TODO make font scale

		//String text = "" + (double)((int)(pElement.weights[0] * 100))/100;
		String text = "";
		int stringWidth = g.getFontMetrics().stringWidth(text);
		int stringHeight = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, this.getWidth()/2 - getPadding() - stringWidth/2, this.getHeight()/2 + getPadding() + stringHeight/4);

	}

}
