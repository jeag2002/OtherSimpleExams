package es.snake.engine;

import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import es.snake.beans.Table;
import es.snake.graphics.CGTemplate;
import es.snake.graphics.DrawCanvas;

public class SnakeWorld {
	
	
	private HamiltonianCicle cicle;
	
	private int x;
	private int y;
	
	private CGTemplate cG;
	private DrawCanvas dC;
	
	private Table table_grid;
	private Table screen;
	
	private static final int DELAY = 100;
	
	private long initTime; 
	
	private Random rand;
	
	private int position;
	
	private int row_head_snake;
	private int col_head_snake;
	
	private int row_apple;
	private int col_apple;
	
	private int cycle[];
	
	private int num_segments_snake;
	
	private boolean noapple;
	
	
	public SnakeWorld(int _x, int _y) {
		
		x = _x;
		y = _y;
		
		table_grid = new Table(x,y);
		cicle = new HamiltonianCicle(x,y);
		
		initTime = System.currentTimeMillis();
		
		rand = new Random();
		
		dC = new DrawCanvas();
		dC.setTable(table_grid);
		
		cG = new CGTemplate(dC);
		
		position = -1;
		
		row_head_snake = -1;
		col_head_snake = -1;
		
		row_apple = -1;
		col_apple = -1;
		
		num_segments_snake = 1;
		
		noapple = false;
	
	
	
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
    
    private void refreshCanvas() {
		dC.setTable(table_grid);
		dC.repaint();
	}
    
    private void closeCanvas() {
    	cG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	cG.dispatchEvent(new WindowEvent(cG, WindowEvent.WINDOW_CLOSING));
    }
	
	//--> create World
	public int createWorld() throws Exception {
		
		initializeGraphics();
		int res = cicle.generateHamiltonianCycle();
		cicle.printHamCycle();
		cycle = cicle.getHamiltonianCycle();
		
		return res;
	}
	
	
	private void calculateFirstPositionSnake() {
				
		int position = rand.nextInt(cycle.length);
		
		int pos = cycle[position];
		
		int row = pos / x;
		int col = pos % x;
		
		row_head_snake = row;
		col_head_snake = col;
		
		//System.out.println("pos snake position " + pos + " [" + row + "," + col + "]");
		table_grid.getTable()[row][col] = 1;
		
	}
	
	
	private boolean noValidSituation(int row_apple, int col_apple) {
		
		boolean res = false;
		
		if ((row_apple == row_head_snake) && (col_apple == col_head_snake)) {
			return true;
		} else {
			
			if (num_segments_snake > 1) {
				int def_position = position;
				
				for (int i = num_segments_snake-1; i>0; i--) {
					if (def_position == 0) {def_position = cycle.length-1;}
					else{def_position--;}
					
					int pos = cycle[def_position];
					
					int row = pos / x;
					int col = pos % x;
					
					if ((row == row_apple) && (col == col_apple)) {
						return true;
					}
				}
				
			}
			
		}
		
		
		return res;
		
	}
	
	
	
	
	private boolean calculatePositionApple() {
		
		int data = 0;
		
		boolean res = false;
		
		do {
			
			data = rand.nextInt(cycle.length);
		
			int pos = cycle[data];
		
			row_apple = pos / x;
			col_apple = pos % x;
			
			//data++;
		
			res = noValidSituation(row_apple, col_apple);
			if(!res) {break;}
		
		}while (data <= cycle.length);
		
		return res;
	}
	
	
	
	private void moveSnake() {
		
		position++;
		if (position >= cycle.length) {
			position = 0;
		}
		
		positionHeadSnake(position);
		
		
		if (num_segments_snake > 1) {
			int def_position = position;
			
			for (int i = num_segments_snake-1; i>0; i--) {
				if (def_position == 0) {def_position = cycle.length-1;}
				else{def_position--;}
				positionSegmentSnake(def_position);
			}
		}
	}
	
	private void drawPositionApple() {
		table_grid.getTable()[row_apple][col_apple] = 2;
	}
	
	
	private int processPositionSnake(int position) {
		
		int pos = cycle[position];
		return pos;
		
	}
	
	private void positionSegmentSnake(int position) {
		
		int pos = processPositionSnake(position);
		
		int row = pos / x;
		int col = pos % x;
		
		table_grid.getTable()[row][col] = 1;
	}
	
	
	private void positionHeadSnake(int position) {
		
		int pos = processPositionSnake(position);
		
		int row = pos / x;
		int col = pos % x;
		
		table_grid.getTable()[row][col] = 1;
		
		row_head_snake = row;
		col_head_snake = col;
			
	}
	
	
	private void clean_screen() {
		for(int i=0; i<table_grid.getY(); i++) {for (int j=0; j<table_grid.getX(); j++) {table_grid.getTable()[i][j]=0;}}
	}
	
	
	private boolean processAll() {
		
		boolean DONE = false;
		
		
		clean_screen();
		moveSnake();
		
	    
	    if (!noapple) {drawPositionApple();}
		
		
		
		if ((row_head_snake == row_apple) && (col_head_snake == col_apple)) {
			
			num_segments_snake++;
			
			if (num_segments_snake < cycle.length) {
				table_grid.getTable()[row_apple][col_apple] = 1;
				if (!calculatePositionApple() ) { drawPositionApple(); noapple = false;}
				else {noapple = true; row_apple = -1; col_apple = -1;}
			}
		}
		
		
		DONE = (num_segments_snake >= cycle.length);
		
		return DONE;
	}
	
	
	//--> run process
	public void run() throws Exception{
		
		clean_screen();
		calculateFirstPositionSnake();
		calculatePositionApple();
		drawPositionApple();
		
		boolean DONE = false;
		
		while(!DONE) {
			DONE = processAll();
			Thread.sleep(DELAY);
			refreshCanvas();
		}
		
		closeCanvas();
	}
	
	
	
	

}
