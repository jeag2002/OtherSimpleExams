import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static final Integer size_x = 40;
	private static final Integer size_y = 40;
	
	
	private static boolean shootToOtherPlayer(String coordinates, Board2 enemy) throws Exception{
		String coords[] = coordinates.split(",");
		
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		 
		boolean isShoot = !enemy.getBattleBoard()[y][x].equalsIgnoreCase("_");
		if (isShoot) {enemy.getBattleBoard()[y][x]="_";}
		return isShoot;	
	}
	
	
	private static boolean isEmpty(Board2 board) throws Exception{
		
		boolean isEmpty = true;
		
		int x = board.getBattleBoard().length;
		int y = board.getBattleBoard()[0].length;
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if (!board.getBattleBoard()[i][j].equalsIgnoreCase("_")) {
					isEmpty = false;
				}
			}
		}
		
		return isEmpty;
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Choose number of rows: (Y) ");
		
		int limit_x = 0;
		int limit_y = 0;
		
		Integer[] ships_data_p1;
		Integer[] ships_data_p2;
		
		
		try {
		
			limit_y = Integer.parseInt(reader.nextLine());
			
			if (limit_y < 1) {limit_y = size_x;}
			if (limit_y > size_x) {limit_y = size_x;}
		
		}catch(Exception e) {
			limit_y = size_x;
		}
		
		System.out.println("Choose number of cols: (X) ");
		
		try {
			limit_x = Integer.parseInt(reader.nextLine()); 
			if (limit_x < 1) {limit_x = size_y;}
			if (limit_x > size_y) {limit_x = size_y;}
			
		}catch(Exception e) {
			limit_x = size_y;
		}
		
		
		System.out.println("Write ships Player_1 (with this format 1,2,3,4,...):");
		
		try {
			String numbers[] = reader.nextLine().split(",");
			ships_data_p1 = new Integer[numbers.length];
			
			for(int i=0; i<numbers.length; i++) {
				ships_data_p1[i] = Integer.parseInt(numbers[i]);
				
				if (ships_data_p1[i] < 1) {ships_data_p1[i] = 1;}
				if ((ships_data_p1[i] > limit_x) || (ships_data_p1[i] > limit_y)) {ships_data_p1[i] = 1;}
				
			}
			
			
		}catch(Exception e) {
			ships_data_p1 = new Integer[0];
		}
		
		
		
		System.out.println("Write ships Player_2 (with this format 5,6,7,8,...):");
		
		try {
			String numbers[] = reader.nextLine().split(",");
			ships_data_p2 = new Integer[numbers.length];
			
			for(int i=0; i<numbers.length; i++) {
				ships_data_p2[i] = Integer.parseInt(numbers[i]);
				
				if (ships_data_p2[i] < 1) {ships_data_p2[i] = 1;}
				if ((ships_data_p2[i] > limit_x) || (ships_data_p2[i] > limit_y)) {ships_data_p2[i] = 1;}
			}
			
			
		}catch(Exception e) {
			ships_data_p2 = new Integer[0];
		}
		
		
		ArrayList<Integer> ships_1 = new ArrayList<Integer>(Arrays.asList(ships_data_p1));
		ArrayList<Integer> ships_2 = new ArrayList<Integer>(Arrays.asList(ships_data_p2));
		
		Board2 board_P1 = new Board2(limit_y, limit_x, ships_1);
		Board2 board_P2 = new Board2(limit_y, limit_x, ships_2);
		
		
		Player p1 = new Player("Player1", board_P1);
		Player p2 = new Player("Player2", board_P2);
		
		
		p1.setShips();
		System.out.println("Ships PLAYER 1 placed");
		p2.setShips();
		System.out.println("Ships PLAYER 2 placed");
		
		
		
		//COMIENZA EL JUEGO!!!!
		System.out.println("INIT GAME!!!");
		
		
		boolean DONE = false;
		
		int index = 0;
		
		do {
			
			System.out.println("\n");
			System.out.println("*****************   STEP (" + index + ") *******************************************");
			
			System.out.println("PLAYER 1 (" + p1.getName() + ")");
			
			System.out.print("OWN BOARD P1"); for(int i=0; i<50-"OWN BOARD P1".length(); i++) {System.out.print(" ");} System.out.print("REMOTE BOARD P2\n");
			
			//print screen PLAYER_1;
			for(int i=0; i<limit_y; i++ ) {
				
				for(int j=0; j<limit_x; j++) {
					System.out.print(p1.getBoard().getBattleBoard()[i][j]);
				}
				
				for(int z=0; z<50-limit_x; z++) {
					System.out.print(" ");
				}
				
				for(int j=0; j<limit_x; j++) {
					System.out.print(p1.getEnemyBoard().getBattleBoard()[i][j]);
				}
				
				System.out.print("\n");
			
			}
			
			System.out.println("\n");
			
			System.out.println("PLAYER 2 (" + p2.getName() + ")");
			
			System.out.print("OWN BOARD P2"); for(int i=0; i<50-"OWN BOARD P2".length(); i++) {System.out.print(" ");} System.out.print("REMOTE BOARD P1\n");
			
			
			//print screen PLAYER_2;
			for(int i=0; i<limit_y; i++ ) {
				
				for(int j=0; j<limit_x; j++) {
					System.out.print(p2.getBoard().getBattleBoard()[i][j]);
				}
				
				for(int z=0; z<50-limit_x; z++) {
					System.out.print(" ");
				}
				
				for(int j=0; j<limit_x; j++) {
					System.out.print(p2.getEnemyBoard().getBattleBoard()[i][j]);
				}
				
				System.out.print("\n");
			}
			
			System.out.println("\n");
			
			
			String shoot_P1 = p1.getNextShot();
			boolean isShoot = Main.shootToOtherPlayer(shoot_P1, p2.getBoard());
			System.out.println("P1 shoot (" + shoot_P1 + ") to P2 is " +  (isShoot? "WIN":"FAIL"));
			p1.wasShotOk(shoot_P1, isShoot);
			
			
			String shoot_P2 = p2.getNextShot();
			isShoot = Main.shootToOtherPlayer(shoot_P2, p1.getBoard());
			System.out.println("P2 shoot (" + shoot_P2 + ") to P1 is " +  (isShoot? "WIN":"FAIL"));
			p2.wasShotOk(shoot_P2, isShoot);
			
			
			if (Main.isEmpty(p1.getBoard())) {
				System.out.println("PLAYER2 WINS!!!");
				DONE = true;
			}else if (Main.isEmpty(p2.getBoard())) {
				System.out.println("PLAYER1 WINS!!!");
				DONE = true;
			}else {
				Thread.sleep(200);
			}
			
			System.out.println("*****************   STEP (" + index + ") *******************************************");
			System.out.println("\n");
			
			index++;
			
		}while(!DONE);
		
		
		System.out.println("************************* END *****************************************");
		
		System.out.println("PLAYER 1 (" + p1.getName() + ")");
		
		System.out.print("OWN BOARD P1"); for(int i=0; i<50-"OWN BOARD P1".length(); i++) {System.out.print(" ");} System.out.print("REMOTE BOARD P2\n");
		
		//print screen PLAYER_1;
		for(int i=0; i<limit_y; i++ ) {
			
			for(int j=0; j<limit_x; j++) {
				System.out.print(p1.getBoard().getBattleBoard()[i][j]);
			}
			
			for(int z=0; z<50-limit_x; z++) {
				System.out.print(" ");
			}
			
			for(int j=0; j<limit_x; j++) {
				System.out.print(p1.getEnemyBoard().getBattleBoard()[i][j]);
			}
			
			System.out.print("\n");
		
		}
		
		System.out.println("\n");
		
		System.out.println("PLAYER 2 (" + p2.getName() + ")");
		
		System.out.print("OWN BOARD P2"); for(int i=0; i<50-"OWN BOARD P2".length(); i++) {System.out.print(" ");} System.out.print("REMOTE BOARD P1\n");
		
		
		//print screen PLAYER_2;
		for(int i=0; i<limit_y; i++ ) {
			
			for(int j=0; j<limit_x; j++) {
				System.out.print(p2.getBoard().getBattleBoard()[i][j]);
			}
			
			for(int z=0; z<50-limit_x; z++) {
				System.out.print(" ");
			}
			
			for(int j=0; j<limit_x; j++) {
				System.out.print(p2.getEnemyBoard().getBattleBoard()[i][j]);
			}
			
			System.out.print("\n");
		}
		
		System.out.println("\n");
		
		System.out.println("************************* END *****************************************");
		
		System.out.println("END GAME!!!!");
		
		
	}

}
