import java.util.Scanner;

public class Player implements PlayerInterface {

	private String dot;
	private Board2 board;
	private Scanner reader;
	
	
	public Player(Board2 board, String dot, Scanner reader) {
		this.dot = dot;
		this.board = board;
		this.reader = reader;
	}
	
	@Override
	public String getDot() {
		return dot;
	}
	
	@Override
	public void setColumn() {
		
		System.out.println("SET COLUMN (1) AND (" + board.getBoard()[0].length + ")");
		
		boolean DONE = false;
		int column = 0;
		
		do {
		
			try {
				
				column = Integer.parseInt(reader.nextLine());
				column-=1;
				board.setColumn(column, this.dot);
				DONE = true;
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				DONE = false;
			}
		
		}while(!DONE);
		
	}

}
