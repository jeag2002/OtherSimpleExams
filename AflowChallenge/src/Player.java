import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class Player implements PlayerInterface
{
	
	private static final Integer VERT=0;
	
	
    private String name;
    private Board2 myBoard;
    private Board2 enemyBoard;
    
    private Integer enemyBoardX;
    private Integer enemyBoardY;
    
    private String shoot;
    
    private Integer shoot_x;
    private Integer shoot_y;
    
    
    private boolean findShip;
    private boolean findOrientation;
    
    private ArrayList<String> previousRoot;
    
    
    

    //empty board to set the ships
    public Player(String name, Board2 board) {
        
    	this.myBoard = board;
        this.name = name;
        
        
        enemyBoardY = myBoard.getBattleBoard().length;
        enemyBoardX = myBoard.getBattleBoard()[0].length;
        
        try {
        	ArrayList<Integer> enemy = new ArrayList<Integer>();
        	enemy.add(1);
			enemyBoard = new Board2(enemyBoardY, enemyBoardX, enemy);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        previousRoot = new ArrayList<String>();
        
        shoot_x = 0;
        shoot_y = 0;
        
        findShip = false;
        
        
    }
    
    
    //define ships
    public boolean setShips() {
    	
    	
    	Random r = new Random();
		int sizeBoardX = myBoard.getBattleBoard()[0].length;
		int sizeBoardY = myBoard.getBattleBoard().length;
    	
    	
    	for(Integer shipLength: myBoard.getShipsMissing()) {
    		
    		//1-define orientation (1-VERT; 2-HORIZ)
    		
    		
    		boolean DONE = false;
    		
    		int orientation = 0;
    		int x_ini = 0;
    		int y_ini = 0;
    		
    		int x_final = 0;
    		int y_final = 0;
    		
    		orientation = r.nextInt(2);
    		
			x_ini = 0;
			y_ini = 0;
		
			x_final = 0;
			y_final = 0;
			
			int limit = 0;
    		
    		
    		do {
    		
    			
	    		do {
	    			//2-define first point; end point
	    			if (!DONE) {
	    				x_ini = r.nextInt(sizeBoardX);
	    				y_ini = r.nextInt(sizeBoardY);
	    			}else {

		    			if (orientation == VERT) {
		    				x_ini += 1;
		    				if (x_ini >= sizeBoardX) {x_ini = 0;}
		    			}else {
		    				y_ini += 1;
		    				if (y_ini >= sizeBoardY) {y_ini = 0;}
		    			}
	    			}
	    			
	    			x_final = 0;
	    			y_final = 0;
	    			
	    			//3-define end point
	    			if (orientation == VERT) {
	    				x_final = x_ini + (shipLength-1);
	    				y_final = y_ini;
	    				
	    				
	    				if (x_final < sizeBoardX) {
	    					
	    					for(int x=x_ini; x<x_final; x++) {
	    						if (!myBoard.getBattleBoard()[x][y_ini].equalsIgnoreCase("_")) {
	    							DONE = true;
	    							break;
	    						}
	    					}
	    					
	    				}
	    				
	    				System.out.println("(" + x_ini + "," + y_ini +") (" + x_final + "," + y_final + ") COLLISION " + (DONE?"YES":"NO"));
	    				
	    				
	    			}else {
	    				y_final = y_ini + (shipLength-1);
	    				x_final = x_ini;
	    				
	    				
	    				if (y_final < sizeBoardY) {
	    					
	    					for(int y=y_ini; y<y_final; y++) {
	    						if (!myBoard.getBattleBoard()[x_ini][y].equalsIgnoreCase("_")) {
	    							DONE = true;
	    							break;
	    						}
	    					}
	    				}
	    				
	    				System.out.println("(" + x_ini + "," + y_ini +") (" + x_final + "," + y_final + ") COLLISION " + (DONE?"YES":"NO"));
	    				
	    			}
	    		//out of limits?
	    		}while((x_final >= sizeBoardY) || (y_final >= sizeBoardX));
	    		    		
	    		
	    		limit++;
	    		
    		
    		}while((DONE) && (limit <= 100));
	    	
    		if (!DONE) {
    			myBoard.setShipPosition(shipLength, x_ini+","+y_ini, x_final+","+y_final);
    		}
    	
    	}
    	
    	
    	
    	return true;
    }
    

    //process next shot
	@Override
	public String getNextShot() {
		
		if (!findShip) {
			
			shoot_x = 0;
			shoot_y = 0;
			
			boolean FIN = false;
			boolean STOP = false;
			
			do {
				
				FIN = previousRoot.contains(shoot_x+","+shoot_y);
				
				if (FIN) {
					shoot_x += 1;
					
					if (shoot_x >= enemyBoardX) {
						shoot_x = 0;
						shoot_y += 1;
						if (shoot_y >= enemyBoardY) {
							STOP = true;
						}
					}
					
					STOP = true;
					
				}else {
					STOP = false;
				}
				
				
				
			}while(STOP);
			
			shoot = shoot_x+","+shoot_y;
		}else {
			
			if (shoot_y < (enemyBoardY-1)) {
				shoot_y+=1;
			}
				
			shoot = shoot_x+","+shoot_y;	
		
			
			
		}
		
		
		return shoot;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Board2 getBoard() {
		return myBoard;
	}
	
	
	public Board2 getEnemyBoard() {
		return enemyBoard;
	}
	

	@Override
	public void wasShotOk(String previousShot, boolean wasOk) {
		
		if (previousShot.equalsIgnoreCase(shoot) && wasOk) {
			
			//FOUND
			if (enemyBoard.getBattleBoard()[shoot_y][shoot_x].equalsIgnoreCase("_")) {
				enemyBoard.getBattleBoard()[shoot_y][shoot_x] = "@";
				previousRoot.add(shoot_x+","+shoot_y);
			}
			
			findShip = true;
			
		}else {
			
			//FAIL
			String x_y_Point[] = previousShot.split(",");
			
		    Integer x_e_board = Integer.parseInt(x_y_Point[0]);
		    Integer y_e_board = Integer.parseInt(x_y_Point[1]);
		    
		    if (enemyBoard.getBattleBoard()[y_e_board][x_e_board].equalsIgnoreCase("_")) {
		    	enemyBoard.getBattleBoard()[y_e_board][x_e_board] = "x";
		    	previousRoot.add(x_e_board+","+y_e_board);
		    }
		    
		    findShip = false;
			
		}
		
	
	}
}
