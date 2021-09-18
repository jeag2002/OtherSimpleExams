package es.doomfire.engine;

import java.util.Random;

import javax.swing.SwingUtilities;

import es.doomfire.beans.Table;
import es.doomfire.graphics.CGTemplate;
import es.doomfire.graphics.DrawCanvas;

public class DoomFireWorld {
	
	private int x;
	private int y;
	
	private CGTemplate cG;
	private DrawCanvas dC;
	
	private Table table;
	
	private static final int DELAY = 100;
	
	private Random rand;
	
	public DoomFireWorld(int _x, int _y) {
		x = _x;
		y = _y;
		
		table = new Table(x,y);
		
		rand = new Random();
		
		dC = new DrawCanvas();
		dC.setTable(table);
		
		cG = new CGTemplate(dC);
		
	}
	
	//--> initialize Graphics
    private void initializeGraphics() throws Exception{
			
		SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					cG.start();
				}
		});	
	}
    
	//--> initialize global parameters
	private void initializeWorld() throws Exception{
		
		int limit_Y = table.getY();
		
		for(int x = table.getX()-1; x > 0; x--) {
			for (int y = table.getY()-1; y > 0; y--) {
				
				if (y < table.getY() && y >= table.getY()-2) {
					table.getTable()[y][x] = 1; 
				} else if (y < table.getY()-2 && y >= table.getY()-6) {
					table.getTable()[y][x] = 2; 
				} else if (y < table.getY()-6 && y >= table.getY()-10) {
					table.getTable()[y][x] = 3; 
				} else if (y < table.getY()-10 && y >= table.getY()-14) {
					table.getTable()[y][x] = 4; 
				} else if (y < table.getY()-14 && y >= table.getY()-18) {
					table.getTable()[y][x] = 5; 
				} else if (y < table.getY()-18 && y >= table.getY()-22) {
					table.getTable()[y][x] = 6; 
				} else if (y < table.getY()-22 && y >= table.getY()-26) {
					table.getTable()[y][x] = 7; 
				} else if (y < table.getY()-26 && y >= table.getY()-30) {
					table.getTable()[y][x] = 8; 
				} else if (y < table.getY()-30 && y >= table.getY()-34) {
					table.getTable()[y][x] = 9;
				} else if (y < table.getY()-34 && y >= table.getY()-38) {
					table.getTable()[y][x] = 10;
				} else {
					table.getTable()[y][x] = 0;
				}
			}
		}
		
		
	}
	
	private void spreadFire(int _x_from, int _y_from) {
		
		
		
		int pixel = table.getTable()[_y_from][_x_from];
		
		int randIndx = rand.nextInt(3);
		
		
		
		int y_to = _y_from - randIndx +1;
		int x_to = _x_from - randIndx;
		//int x_to = _x_from;
		
		
		if (((y_to >= 0) && (y_to < table.getY() )) && 
		((x_to >= 0) && (x_to < table.getX() ))) { 
			int new_pixel = pixel- (randIndx & 1);
			if (new_pixel == 1) {new_pixel = 0;}
			table.getTable()[y_to][x_to] = new_pixel;
		}
		
		
		/*
		if (pixel == 0) {
			
			table.getTable()[y_from][_x] = 5;
		
		} else {
	        int randIdx = (int)Math.round(Math.random() * 3.0);// & 3;
	        int y_to = y_from - randIdx + 1;
			table.getTable()[y_to][_x] = pixel - (randIdx & 1);
		
		}
		*/
		
	}
	
	
	//-->generate fire
	private void generateFire() throws Exception {
		
		for(int x = table.getX()-1; x > 0; x--) {
			for (int y = table.getY()-1; y > 0; y--) {
		/*for(int x = 0; x < table.getX(); x++) {
			for (int y = 1; y < table.getY(); y++) {*/
				spreadFire(x, y);
			}
		}
		
	}
	
	
	private void refreshCanvas() {
		dC.setTable(table);
		dC.repaint();
	}
	
	//--> create World
	public void createWorld() throws Exception {
		initializeWorld();
		initializeGraphics();
	}
	
	//--> run process
	public void run() throws Exception{
		while(true) {
			generateFire();
			Thread.sleep(DELAY);
			refreshCanvas();
		}
	}
	

}
