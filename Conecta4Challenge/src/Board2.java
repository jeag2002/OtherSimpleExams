import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;


public class Board2 {
	
	private String[][] board;
	private Integer connecta;
	
	private ArrayList<String> revisedCoord;
	
	public static final String EmptySpace= " ";
	public static final String NO_WINNER = "NO_WINNER";
	
	public static final Integer MAX_CONNECTA = 4;
	
	private String player_1;
	private String player_2;
	
	public Board2() {
		board = new String[Main.size_y][Main.size_x];
		for(int i=0;i<Main.size_y; i++) {for(int j=0;j<Main.size_x;j++) {board[i][j]=this.EmptySpace;}}
		connecta = MAX_CONNECTA;
		connecta+=1;
	}
	
	public Board2(int size_y, int size_x) {
		board = new String[size_y][size_x];
		for(int i=0;i<size_y; i++) {for(int j=0;j<size_x;j++) {board[i][j]=this.EmptySpace;}}
		connecta = Math.max(board.length, board[0].length) > MAX_CONNECTA? MAX_CONNECTA:Math.max(board.length, board[0].length);
		connecta+=1;
	}
	
	
	public Board2 (Board2 input) {
		String[][] inputValue = input.getBoard();
		board = Arrays.stream(inputValue).map(String[]::clone).toArray(String[][]::new);
		connecta = Math.max(board.length, board[0].length) > MAX_CONNECTA? MAX_CONNECTA:Math.max(board.length, board[0].length);
		connecta+=1;
		
		player_1 = input.getPlayer_1();
		player_2 = input.getPlayer_2();
	}
	
	
	public Board2 clone () {
		return new Board2(this);
	}
	
	
	public String[][] getBoard(){ 
		return board;
	}
	
	
	public boolean isDisponible(int x) {
		boolean isFull = true;
		
		for(int i=0;(i<board.length) && isFull; i++) {
			isFull = !board[i][x].equalsIgnoreCase(this.EmptySpace);
		}
		
		return isFull;
	}
	
	public int setDot(int x, String dot) throws Exception{
		
		boolean isFull = true;
		int i = 0;
		
		
		for(i=0;(i<board.length) && isFull; i++) {
			isFull = !board[i][x].equalsIgnoreCase(this.EmptySpace);
			if (!isFull) {board[i][x]= new String(dot);}
		}
		
		if (isFull) {
			throw new Exception("FULL COLUMN CHOOSE ANOTHER ONE");
		}else {
			return (i-1);
		}
		
	}
	
	public int setColumn(int x, String dot) throws Exception {
		if (x < 0 || x >= board[0].length) {
			throw new Exception("NO VALID POSITION CHOOSE ANOTHER ONE");
		}else { 
			return setDot(x,dot);
			
		}
	}
	
	
	public boolean isFull() {
		boolean DONE = false;
		
		int i = 0;
		int j = 0;
		
		do {
			DONE = board[i][j].equalsIgnoreCase(EmptySpace);
			if (!DONE) {
				i += 1;
				if (i >= board.length) {
					i = 0;
					j += 1;
					if (j >= board[0].length) {
						DONE = true;
					}
				}	
		   }
			
		}while(!DONE);
		
		return (j >= board[0].length);
		
		
	}
	
	
	public void printBoard() {
		
		//1-> Superior
		//**************************************************
		System.out.print("\n");
		for(int i=0; i<board[0].length; i++) {System.out.print(" _");}
		System.out.print("\n");
		//**************************************************
		
		for(int i=board.length-1; i>=0; i--) {
			
			for(int j=0; j<board[0].length; j++) {
				System.out.print("|");
				System.out.print(board[i][j]);
			}
			System.out.print("|\n");
			
			for(int j=0; j<board[0].length; j++) {System.out.print(" _");}
			System.out.print("\n");
			
		}
	
	}
	
	
	public boolean processDot(int row, int col, String dot, int connecta) {
		boolean isConnecta4=false;
		
		//horizontal
		
		int j = 0;
		int i = 0;
		boolean found = false;
		int count = 0;
		
		for(j=col; j<=(connecta+col) && j < board[0].length && !found ; j++) {
			//System.out.println("HORIZONTAL  connecta ("+connecta+") board[" + row + "][" + j + "] = (" + board[row][j] + ")" );
			if (!board[row][j].equalsIgnoreCase(dot)) {found = true;}
			else {count++;}
		}
		
		if (count >= (connecta-1)) {
			isConnecta4= true;
		}else {
			isConnecta4 = false;
		}
		
		//System.out.println("HORIZONTAL? " + isConnecta4); 
		
		//vertical
		
		if (!isConnecta4) {
			i=0;
			found = false;
			count = 0;
			
			for(i=row; i<=(connecta+row) && i < board.length && !found ; i++) {
				
				//System.out.println("VERTICAL  connecta ("+connecta+") board[" + i + "][" + col + "] = (" + board[i][col] + ")" );
				if (!board[i][col].equalsIgnoreCase(dot)) {found = true;}
				else {count++;}
			}
			
			if (count >= (connecta-1)) {
				isConnecta4= true;
			}else {
				isConnecta4 = false;
			}
			
			//System.out.println("VERTICAL? " + isConnecta4);
		}
		
		//diagonal \
		
		if (!isConnecta4) {
			i = 0;
			j = 0;
			count = 0;
			
			found = false;
			
			for (i=row, j=col; i<=(connecta+row) && i< board.length && j<=(connecta + col) && j < board[0].length && !found; i++, j++) {
				//System.out.println("DIAGONAL  connecta ("+connecta+") board[" + i + "][" + j + "] = (" + board[i][j] + ")" );
				if (!board[i][j].equalsIgnoreCase(dot)) {found = true;}
				else {count++;}
			}
			
			if (count >= (connecta-1)) {
				isConnecta4= true;
			}else {
				isConnecta4 = false;
			}
			
			//System.out.println("DIAGONAL? " + isConnecta4);
			
		}
		
		//diagonal /
		
		if (!isConnecta4) {
			
			i = 0;
			j = 0;
			count = 0;
			
			found = false;
			
			for (i=row, j=col; i<=(connecta+row) && i< board.length && j >= (col-connecta) && j >= 0 && !found; i++, j--) {
				
				//System.out.println("DIAGONAL INVERSA connecta ("+connecta+") board[" + i + "][" + j + "] = (" + board[i][j] + ")" );
				if (!board[i][j].equalsIgnoreCase(dot)) {found = true;}
				else {count++;}
			}
			
			
			
			if (count >= (connecta-1)) {
				isConnecta4= true;
			}else {
				isConnecta4 = false;
			}
			
	
		}
				
		return isConnecta4;
	}
	
	
	
	public int cost(String dot_p) {
		
		boolean DONE = false;
		
		int i = 0;
		int j = 0;
		int numResults = 0;
		
		do {
			if (board[i][j].equalsIgnoreCase(dot_p)) {
				if (processDot(i,j,dot_p, connecta-1)) {
					numResults ++;
				}
				
			}
			
			//--> Coordinates
			
			i += 1;
			if (i >= board.length) {
				i = 0;
				j += 1;
				if (j >= board[0].length) {
					DONE = true;
				}
			}	

			
		}while(!DONE);
		
		return numResults;
		
	}
	
	
	
	
	
	
	
	
	

	public boolean isWinner(String dot_p) {
		boolean isWinner = false;
		boolean DONE = false;
		
		
		int i = 0;
		int j = 0;
		
		do {
			if (board[i][j].equalsIgnoreCase(dot_p)) {
				isWinner = processDot(i,j,dot_p, connecta);
				DONE = isWinner;
			}
			
			//--> Coordinates
			if (!DONE) {
				i += 1;
				if (i >= board.length) {
					i = 0;
					j += 1;
					if (j >= board[0].length) {
						DONE = true;
					}
				}	
		   }
			
		}while(!DONE);
		
		return isWinner;
	}
	
	
	
	/*
	public String isWinner() {
		
		boolean is_winner_p1 = isWinner(this.player_1);
		boolean is_winner_p2 = isWinner(this.player_2);
		
		if (is_winner_p1) {System.out.println("PLAYER_1 IS WINNER"); return player_1;}
		else if (is_winner_p2) {System.out.println("PLAYER_2 IS WINNER"); return player_2;}
		else {
			//no winner :(
			return this.NO_WINNER;
		}
	}
	*/
	
	public String getPlayer_1() {
		return player_1;
	}

	public void setPlayer_1(String player_1) {
		this.player_1 = player_1;
	}

	public String getPlayer_2() {
		return player_2;
	}

	public void setPlayer_2(String player_2) {
		this.player_2 = player_2;
	}
	
	public Integer getConnecta() {
		return connecta;
	}

	public void setConnecta(Integer connecta) {
		this.connecta = connecta;
	}
	

}
