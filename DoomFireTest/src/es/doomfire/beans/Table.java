package es.doomfire.beans;


public class Table {
	
	private int[][] cells;
	private int length_x;
	private int length_y;

	
	public Table(int _length_x, int _length_y) {
		
		length_x = _length_x;
		length_y = _length_y;
		cells = new int[length_y][length_x];
		for(int x=0; x<length_x; x++) {for(int y=0; y<length_y; y++) {cells[y][x] = 0;}}
	}
	
	
	public int[][] getTable(){
		return cells;
	}
	
	
	public int getX() {
		return length_x;
	}
	
	public int getY() {
		return length_y;
	}

}
