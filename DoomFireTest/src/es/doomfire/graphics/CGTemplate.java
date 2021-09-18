package es.doomfire.graphics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CGTemplate extends JFrame{
	
	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;
	
	private DrawCanvas canvas;
	
	public CGTemplate(DrawCanvas _canvas) {
		canvas = _canvas;
	}
	
	
	public void start() {
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		
		//set Frame in the middle of the screen
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - canvas.getWidth()) / 2;
        int iCoordY = (objDimension.height - canvas.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY);
        
        Container cp = getContentPane();
        cp.add(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("DOOM FIRE");
        this.setState(NORMAL);
        setVisible(true);
	}
	

}
