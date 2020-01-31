
//https://es.slideshare.net/jokerm/minimax-paraconecta4-19881676

import java.util.Arrays;
import java.lang.Math;

public class IAPlayer implements PlayerInterface  {
	
	private String dot;
	private Board2 board;
	
	private static final Integer HEUR_POSITIVE = 10000;
	private static final Integer HEUR_NEGATIVE = -10000;
	
	
	public IAPlayer(Board2 board, String dot) {
		this.dot = dot;
		this.board = board;
	}
	
	
	@Override
	public String getDot() {
		return dot;
	}

	@Override
	public void setColumn() {
		
		System.out.println("IA set position automatically: ");
		
		try {
			board.setColumn(bestMove(), dot);
		}catch(Exception e) {
			//Exception never reached in this case.
		}	
	}
	
	/*
	 * MINIMAX ALGORITHM WITH ALPHA-BETA CUT OPTIMIZATION
	 */
	
	public int bestMove() {
		int best_move = -1;
		
		
		int max, max_actual;
		max = Integer.MIN_VALUE;
		max_actual = 0;
		
		Board2 clone = board.clone();
		String anotherPlayer = clone.getPlayer_1().equalsIgnoreCase(dot)?clone.getPlayer_2():clone.getPlayer_1();
		
		for(int j=0; j<clone.getBoard()[0].length; j++) {
			
			try {
				//insert seed value
				int i = clone.setColumn(j, dot);
				
				max_actual = valorMin(clone,anotherPlayer,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
				
				//remove previous seed value
				clone.getBoard()[i][j] = new String(Board2.EmptySpace); 
				
				
				if (max_actual > max) {
					max = max_actual;
					best_move = j;
				}
				
			}catch(Exception e) {}
			
		}
		
		clone = null;
		return best_move;
	}
	
	//MIN
	public int valorMin(Board2 board, String anotherPlayer, int prof, int alfa, int beta) {
		
		
		if (board.isWinner(dot) || board.isWinner(anotherPlayer)) {
			return heuristica(board, anotherPlayer);
		}else if (board.isFull()) {
			return heuristica(board, anotherPlayer);
		}else if (prof > board.getConnecta()) {
			return heuristica(board, anotherPlayer);
		}else {
			
			for(int j=0; j<board.getBoard()[0].length; j++) {
				
				try {
					
					int i = board.setColumn(j, dot);
					
					alfa = Math.min(alfa, valorMax(board, anotherPlayer, prof+1, alfa, beta));
					
					board.getBoard()[i][j] = new String(Board2.EmptySpace); 
					
					if (alfa >= beta) {
						return alfa;
					}
					
					
					
				}catch(Exception e) {}
				
			}
			
			return beta;
		}
		
	}
	
	
	
	
	//MAX
	
	public int valorMax(Board2 board, String anotherPlayer, int prof, int alfa, int beta) {
		

		
		if (board.isWinner(dot) || board.isWinner(anotherPlayer)) {
			return heuristica(board, anotherPlayer);
		}else if (board.isFull()) {
			return heuristica(board, anotherPlayer);
		}else if (prof > board.getConnecta()) {
			return heuristica(board, anotherPlayer);
		}else {
			
			for(int j=0; j<board.getBoard()[0].length; j++) {
				
				try {
					
					int i = board.setColumn(j, dot);
					
					alfa = Math.max(alfa, valorMin(board, anotherPlayer, prof+1, alfa, beta));
					
					board.getBoard()[i][j] = new String(Board2.EmptySpace); 
					
					if (alfa >= beta) {
						return beta;
					}
					
					
				}catch(Exception e) {}
				
			}
			
			return alfa;
		}
		
	}
	
	
	
	public int heuristica(Board2 data, String anotherPlayer) {
		int costo = 0;
		
		if (data.isWinner(dot)) {
			return  HEUR_POSITIVE;
		}else if (data.isWinner(anotherPlayer)) {
			return HEUR_NEGATIVE;
		}else {
			return data.cost(dot)-data.cost(anotherPlayer);
		}
	}
	
	

}
