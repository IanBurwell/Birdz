import java.awt.Color;
import javax.swing.JFrame;

public class init {
	
	
	
	public static void main(String[] args){
		//runPixelUiTest();
		runNeuralNetworkUiTest();
	}
	
	private static void runNeuralNetworkUiTest(){
		
		
	}
	
	private static void runPixelUiTest(){
		final Color[][] map = new Color[100][100];
		PixelUI pixelUI;

		
		System.out.println("Started");
		for(int c = 0; c < map.length; c++)
			for(int r = 0; r < map[0].length; r++)
				map[c][r] = ((int)(Math.random() * 2) == 0) ? Color.BLACK : Color.WHITE;
		
		pixelUI = new PixelUI("UI", map);
		pixelUI.waitForOpen();
		System.out.println("Opened");
		
		for(int i = 0; i < 10000; i++){
			for(int c = 0; c < map.length; c++)
				for(int r = 0; r < map[0].length; r++)
					//map[c][r] = ((int)(Math.random() * 2) == 0) ? Color.BLACK : Color.WHITE;
					map[c][r] = new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255));
			pixelUI.updatePixels(map);
		}
	}

}
