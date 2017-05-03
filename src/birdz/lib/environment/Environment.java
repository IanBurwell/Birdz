package birdz.lib.environment;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

public class Environment extends JComponent {

	private static final long serialVersionUID = 8786961387046987953L;
	
	private ArrayList<EnvObject> objects;
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).paint(g);
	}
	
}
