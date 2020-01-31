
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static final Integer size_x = 40;
	public static final Integer size_y = 40;
	
	private static final Integer min_size = 3;

	public static final Integer defPlayer = 1;
	
	
	private Board2 board;
	private Integer _numPlayer;
	
	private PlayerInterface player_1;
	private PlayerInterface player_2;
	
	
	public Main(int sizeBoardX, int sizeBoardY, int numPlayer, Scanner reader) {
		
		System.out.println("CREATING BOARD " + sizeBoardY + " ROWS " + sizeBoardY + " COLS " + numPlayer + " PLAYERS ");
		
		this.board = new Board2(sizeBoardY,sizeBoardX);
		this._numPlayer = numPlayer;
		
		if (_numPlayer == 0) {
			System.out.println("CHOSED IA VS IA");
			player_1 = new IAPlayer(board,"@");
			player_2 = new IAPlayer(board,"X");
		}else if (_numPlayer == 1) {
			System.out.println("CHOSED PLAYER VS IA");
			player_1 = new Player(board,"@",reader);
			player_2 = new IAPlayer(board,"X");
		}else {
			System.out.println("CHOSED PLAYER VS PLAYER");
			player_1 = new Player(board,"@",reader);
			player_2 = new Player(board,"X",reader);
		}	
		
		this.board.setPlayer_1(player_1.getDot());
		this.board.setPlayer_2(player_2.getDot());
	}
	
	
	
	public void Run() {
		
		boolean DONE = false;
		
		System.out.println("RUN");
		
		do {
			board.printBoard();
			
			//-->FIRST PLAYER
			System.out.print("TURN PLAYER_1: "); player_1.setColumn();
			DONE = board.isWinner(player_1.getDot());
			
			
			
			if (!DONE) {
				board.printBoard();
				
				//-->SECOND PLAYER
				System.out.print("TURN PLAYER_2: "); player_2.setColumn();
				DONE = board.isWinner(player_2.getDot());
				
				if (DONE) {
					System.out.println("PLAYER_2 IS WINNER");
				}

			}else {
				System.out.println("PLAYER_1 IS WINNER"); 
			}
			
			
			if (!DONE) {
				//NO MORE MOVEMENTS
				DONE = board.isFull();
				
				if(DONE) {
					System.out.println("TIED! NO WINNERS");
				}
				
			}
			
		}while(!DONE);
		
		board.printBoard();
		
	}

	
	public static void main(String[] args) throws Exception{
		
		
		System.out.println("*****************************************************");
		System.out.println("CONECTA 4 BEGIN!!!");
		System.out.println("*****************************************************");
		
		Scanner reader = new Scanner(System.in);
		
		int limit_y = 0;
		int limit_x = 0;
		
		int numPlayers = 1;
		
		System.out.println("NUM ROWS: (Y) ");
		
		try {
			
			limit_y = Integer.parseInt(reader.nextLine());
			
			if (limit_y < min_size) {limit_y = size_x;}
			if (limit_y > size_x) {limit_y = size_x;}
		
		}catch(Exception e) {
			limit_y = size_x;
		}
		
		System.out.println("NUM COLS: (X) ");
		
		try {
			limit_x = Integer.parseInt(reader.nextLine()); 
			if (limit_x < min_size) {limit_x = size_y;}
			if (limit_x > size_y) {limit_x = size_y;}
			
		}catch(Exception e) {
			limit_x = size_y;
		}
		
		
		System.out.println("CHOOSE NUM OF PLAYERS: (0) IA VS IA (1) PLAYER VS IA (2) PLAYER VS PLAYER");
		try {
			
			numPlayers = Integer.parseInt(reader.nextLine()); 
			
			if ((numPlayers != 0) && (numPlayers != 1) && (numPlayers != 2)) {
				numPlayers = defPlayer;
			}
			
		}catch(Exception e) {
			numPlayers = defPlayer;
		}
		
		Main main = new Main(limit_x, limit_y, numPlayers, reader);
		main.Run();
		
		System.out.println("*****************************************************");
		System.out.println("CONECTA 4 END!!!");
		System.out.println("*****************************************************");
		
		System.exit(0);
		
	}
	

}
