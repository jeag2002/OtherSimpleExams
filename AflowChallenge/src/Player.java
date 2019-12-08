import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;




public class Player implements PlayerInterface
{
	
	private static final Integer VERT=0;
	
	
	private enum Strategy{SPIR_STRATEGY,LIN_STRATEGY,PAR_STRATEGY};
	
	
	
    private String name;
    private Board2 myBoard;
    private Board2 enemyBoard;
    
    private Integer enemyBoardX;
    private Integer enemyBoardY;
    
    private String shoot;
    
    private Integer shoot_x;
    private Integer shoot_y;
    
    
    
    private boolean findShip;
    private boolean findShipPar;
    
    private ArrayList<String> previousRoot;
    
    private LinkedList<String> nextMovements;
    
    private Integer[] spiral;
    private int indexSpiral;
    private Strategy strategy;
    
    

    //empty board to set the ships
    public Player(String name, Board2 board) {
        
    	this.myBoard = board;
        this.name = name;
        
        
        enemyBoardY = myBoard.getBattleBoard().length;
        enemyBoardX = myBoard.getBattleBoard()[0].length;
        
        nextMovements = new LinkedList<String>();
        findShipPar = false;
        
        createReverseSpiral(enemyBoardY,enemyBoardX);
        
        Random r = new Random();
        int i = r.nextInt(3);
        
       
        if (i==0) {
        	strategy = Strategy.SPIR_STRATEGY;
        }else if (i==1) {
        	strategy = Strategy.LIN_STRATEGY;
        }else {
        	strategy = Strategy.PAR_STRATEGY;
        }
        
        
        //strategy = Strategy.PAR_STRATEGY;
        
        System.out.println("Strategy player (" + name + ") is " + strategy.toString());
        
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
    
    
    //build spiral;
    void createReverseSpiral(int enemyBoardY, int enemyBoardX ) {
    	
    	spiral = new Integer[enemyBoardX * enemyBoardY];
    	indexSpiral = 0;
    	
    	ArrayList<String> places = new ArrayList<String>();
    	
    	for(int i=0; i<spiral.length; i++) {spiral[i]=-1;}
    	
    	boolean DONE = false;
    	int index = enemyBoardX* enemyBoardY -1;
    	
    	int value = 0;
    	
    	int x_inf = 0;
    	int y_inf = 0;
    	
    	int x_sup = enemyBoardX-1;
    	int y_sup = enemyBoardY-1;
    	
    	
    	
    	while(!DONE) {
    	
    		//->x++
    		
    		if (!DONE) {
	    		for(int i=x_inf; i<=x_sup; i++) {
	    			
	    			if (!places.contains(i+","+y_inf)) {
	    				spiral[index] = i + (y_inf * enemyBoardX);
	        			index--;
	        			places.add(i+","+y_inf);
	    			}
	    		}
	    		
	    		y_inf ++;
	    		
    		}
    		
    		if (!DONE) {
    			
    			for(int i = y_inf; i<=y_sup; i++) {
    				
	    			if (!places.contains(x_sup+","+i)) {
	    				spiral[index] = x_sup + (i*enemyBoardX);
	        			index--;
	        			places.add(x_sup+","+ i);
	    			}
    				
    			}
    			
    			x_sup --;
    		}
    		
    		//->x--
    		
    		if (!DONE) {
    			
    			for(int i=x_sup; i>=x_inf; i--) {
    				
    				if (!places.contains(i+","+y_sup)) {
	    				spiral[index] = i + (y_sup * enemyBoardX);
	        			index--;
	        			value++;
	        			places.add(i+","+y_sup);
	    			}
    			}
    			
    			y_sup --;
    			
    		}
    		
    		//->y--
    		
    		if (!DONE) {
    			
    			for(int i=y_sup; i>=y_inf; i--) {
    				
    				if (!places.contains(x_inf+","+i)) {
	    				spiral[index] = x_inf + (i*enemyBoardX);
	        			index--;
	        			value++;
	        			places.add(x_inf+","+i);
	    			}
    			}
    			
    			x_inf++;
    		}
    		
    		
    		DONE = !(new ArrayList<Integer>(Arrays.asList(spiral))).contains(-1);
    		
    		
    		
    	}
    	
    	
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
    		
    		
    		
			x_ini = 0;
			y_ini = 0;
		
			x_final = 0;
			y_final = 0;
			
			int limit = 0;
    		
    		
    		do {
    		
    			
	    		do {
	    			
	    			orientation = r.nextInt(2);
	    			
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
	    				
	    				
	    				if ((x_final < sizeBoardX) && (y_ini < sizeBoardY)) {
	    					
	    					if (shipLength == 1)  {
	    						DONE = !myBoard.getBattleBoard()[y_ini][x_ini].equalsIgnoreCase("_");
	    					}else {
		    					for(int x=x_ini; x<=x_final; x++) {
		    						if (!myBoard.getBattleBoard()[y_ini][x].equalsIgnoreCase("_")) {
		    							DONE = true;
		    							break;
		    						}
		    					}
	    					}
	    					
	    				}
	    				
	    			}else {
	    				y_final = y_ini + (shipLength-1);
	    				x_final = x_ini;
	    				
	    				
	    				if ((x_ini < sizeBoardX) && (y_final < sizeBoardY)) {
	    					
	    					if (shipLength == 1)  {
	    						DONE = !myBoard.getBattleBoard()[y_ini][x_ini].equalsIgnoreCase("_");
	    					}else {
	    					
		    					for(int y=y_ini; y<=y_final; y++) {
		    						if (!myBoard.getBattleBoard()[y][x_ini].equalsIgnoreCase("_")) {
		    							DONE = true;
		    							break;
		    						}
		    					}
	    					}
	    				}	    				
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
    
    
    @Override
    public String getNextShot() {
    	
    	if (strategy.equals(Strategy.SPIR_STRATEGY)) {
    		return getNextShotSpiral();
    	}else if (strategy.equals(Strategy.LIN_STRATEGY)){
    		return getNextShotLin();
    	}else {
    		return getNextShotParity();
    	}
    	
    }
    
    
    //process next shot
    //STRATEGY: RUN PARITY STRATEGY
    private String getNextShotParity() {
    	
    	if (!findShipPar) {
    		
    		boolean STOP = false;
    		
    		int x=0;
    		int y=0;
    		int index = 0;
    		
    		do {
    			
    			if (previousRoot.contains(x+","+y)) {
    				x+=2;
    				if (x >= enemyBoardX) {
    					
    					y += 1;
    					x = index==0? (y%2): (y%2)==0?1:0; //<-- SHAME
    					
    					
    					if (y >= enemyBoardY) {
    						index = (index == 0?1:0); //<-- SHAME
    						x = index;
    						y = 0;
    					}
    				}
    				
    			}else {
    				STOP = true;
    			}
    			
    		}while(!STOP);
    		
    		shoot_x = x;
    		shoot_y = y;
    		
    		shoot = shoot_x + "," + shoot_y;
    		
    	}else {
    		
    		if (!nextMovements.isEmpty()) {
    			shoot = nextMovements.getFirst();
    			
    			String coords[] = shoot.split(",");
    			
    			shoot_x = Integer.parseInt(coords[0]);
    			shoot_y = Integer.parseInt(coords[1]);
    			
    			
    			nextMovements.removeFirst();
    			findShipPar = true;
    		}else {
    			findShipPar = false;
    		}
    			
    	}
    	
    	return shoot;
    }
    
    
    
    //process next shot
    //STRATEGY: RUN A REVERSE SPIRAL OF MAP FROM CENTER TO THE OUTSIDE.
    private String getNextShotSpiral() {
    	
    	
    	
    	if (!findShip) {
    		
    		boolean STOP = false;
    		
    		while (!STOP) {
    			
    			int value = spiral[indexSpiral];
    			
    			shoot_y = value / enemyBoardX; // --> num rows
    			shoot_x = value % enemyBoardX; // --> num cols
    			
    			if (previousRoot.contains(shoot_x+","+shoot_y)){
    				indexSpiral++;
    			}else {
    				STOP = true;
    			}
    		}
    		
    		shoot = shoot_x + "," + shoot_y;
    		indexSpiral++;
    		
    	}else {
    		
    		if (shoot_x < (enemyBoardX-1)) {
				shoot_x+=1;
			}
				
			shoot = shoot_x+","+shoot_y;	
    		
    	}
    	
    	return shoot;
    }
    
    
    

    //process next shot
    //STRATEGY: RUNS BY COLUMNS 0->N-1; IF DETECT A PART OF A SHIP GET HORIZONTAL PART ==> OK
    //CONST: FAILS ONE TURN BECAUSE YOU PRESUPOSE ALWAYS A SHIP IN A HORIZONTAL POSITION.
    
	public String getNextShotLin() {
		
		if (!findShip) {
			
			shoot_x = 0;
			shoot_y = 0;
			
			boolean FIN = false;
			boolean STOP = false;
			
			do {
				
				FIN = previousRoot.contains(shoot_x+","+shoot_y);
				
				if (FIN) {
					shoot_y += 1;
					
					if (shoot_y >= enemyBoardY) {
						shoot_y = 0;
						shoot_x += 1;
						if (shoot_x >= enemyBoardX) {
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
			
			if (shoot_x < (enemyBoardX-1)) {
				shoot_x+=1;
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
		
		//if (previousShot.equalsIgnoreCase(shoot) && wasOk) {
		if (wasOk) {	
			//FOUND
			if (enemyBoard.getBattleBoard()[shoot_y][shoot_x].equalsIgnoreCase("_")) {
				enemyBoard.getBattleBoard()[shoot_y][shoot_x] = "@";
				previousRoot.add(shoot_x+","+shoot_y);
			}
			
			
			if ((!findShipPar) && (strategy.equals(Strategy.PAR_STRATEGY))) {
				
				
				for(int i=0; i<enemyBoardX; i++) {
				//for(int i=shoot_x+1; i<enemyBoardX; i++) {
					if (!previousRoot.contains(i+","+shoot_y)) {
						nextMovements.add(i+","+shoot_y);
					}
				}
				
				for(int j=0; j<enemyBoardY; j++) {
				//for(int j=shoot_y+1; j<enemyBoardY; j++) {
					if (!previousRoot.contains(shoot_x+","+j)) {
						nextMovements.add(shoot_x+","+j);
					}
				}
			}
			
			
			findShipPar = true;
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
		    
		    if (!findShipPar) {findShipPar=false;}
		    
		    findShip = false;
			
		}
		
	
	}
}
