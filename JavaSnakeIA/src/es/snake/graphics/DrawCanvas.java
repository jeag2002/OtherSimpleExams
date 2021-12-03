package es.snake.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import es.snake.beans.Table;
import es.snake.utils.Utils;




public class DrawCanvas extends JPanel{
	
	private Table table;
	
	private static final int OFFSET_X = 20;
	private static final int OFFSET_Y = 20;
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	
	public void printFireDebug() {
		
		System.out.println("**********************************");
		
		for(int x = table.getX()-1; x > 0; x--) {
			for (int y = table.getY()-1; y > 0; y--) {
				System.out.print(table.getTable()[y][x]);
			}
			System.out.print("\n");
		}
		
		System.out.println("**********************************");
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int cols = table.getX();
		int rows = table.getY();
		
		int width = getSize().width - (OFFSET_X);
		int height = getSize().height - (OFFSET_Y);
		
		int wdOfRow = width / cols;
		int htOfRow = height / rows;
	
		
		for(int y=0; y < table.getY(); y++) {
			for (int x=0; x < table.getX(); x++ ) {
				int x_rect = x * wdOfRow;
				int y_rect = y * htOfRow;
				
				g.setColor(Utils
						.ColorByIndex(table.getTable()[y][x]));

				g.fillRect(x_rect, y_rect, wdOfRow, htOfRow);
				
			}
		}
		
	}
	

}
