package birdz.UI;
import java.awt.*;
import javax.swing.*;

public class PixelUI extends JFrame{
	private PixelPanel panel;

	private Color[][] pixels;
	private int frameWidth;
	private int frameHeight;

	public PixelUI(String name, Color[][] pixels) {
		this(name, pixels, 400, 400);
	}

	public PixelUI(String name, Color[][] pixels, int frameWidth, int frameHeight) {
		super(name);
		this.pixels = pixels;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.panel = new PixelPanel(pixels);
		setupWindow();
	}

	private void setupWindow(){
		this.setSize(frameWidth, frameHeight);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void updatePixels(Color[][] pixelsC){
		panel.updateScreen(pixelsC);
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

class PixelPanel extends JComponent {
	private static final long serialVersionUID = 6356966263972731143L;
	private Color[][] pixels;

	public PixelPanel(Color[][] pixelsC) {
		this.pixels = pixelsC;
	}

	public void updateScreen(Color[][] pixelsC) {
		this.pixels = pixelsC;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    int pixelWidth = getWidth() / pixels.length;
	    int pixelHeight = getHeight() / pixels[0].length;
	    int xExtraSpace = getWidth() % pixels.length;
	    int yExtraSpace = getHeight() % pixels[0].length;

	    
	    for (int y = 0; y < pixels.length; y++) {
	        for (int x = 0; x < pixels[0].length; x++) {
	            g.setColor(pixels[x][y]);
				g.fillRect((x * pixelWidth) + xExtraSpace/2 , (y * pixelHeight) + yExtraSpace/2 , pixelWidth, pixelHeight);
	        }
	    }
	}
}
