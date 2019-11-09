import java.util.ArrayList;
import java.util.List;

public class Board2 {
	
	private String[][] battleBoard ;
    
    private ArrayList<Integer> ships;
    
    private ArrayList<String> code;
    
    private int rows; //Y
    private int cols; //X
   
    public boolean setShipPosition(Integer length, String iStartPoint, String iEndPoint){
    	
    	try {
    	
    		
    		
    	String x_y_s_Point[] = iStartPoint.split(",");
    	String x_y_e_Point[] = iEndPoint.split(",");
    	
    	Integer x_s = Integer.parseInt(x_y_s_Point[0]);
    	Integer y_s = Integer.parseInt(x_y_s_Point[1]);
    	
    	Integer x_e = Integer.parseInt(x_y_e_Point[0]);
    	Integer y_e = Integer.parseInt(x_y_e_Point[1]);
    	
    	
    	if ((x_s < 0) || (x_s >= cols)){throw new Exception ("OutOfLimits");}
    	if ((y_s < 0) || (y_s >= rows)){throw new Exception ("OutOfLimits");}
    	
    	if ((x_e < 0) || (x_e >=  cols)){throw new Exception ("OutOfLimits");}
    	if ((y_e < 0) || (y_e >=  rows)){throw new Exception ("OutOfLimits");}
    	
    	
    	if (x_s > x_e) {throw new Exception("wrong input coordinates");}
    	if (y_s > y_e) {throw new Exception("wrong input coordinates");}
    	
    	if (battleBoard != null) {
    	
    		String shipCode = String.valueOf(length);
    		int value = length + 64;
    		
    		while (code.contains(shipCode)) {
    			shipCode = Character.toString((char)value);
    			value += 32;
    			if (value > 255) {
    				value = 17;
    			}
    			
    		}
    		
    		code.add(shipCode);
    		
	    	for(int i=y_s; i<=y_e; i++) {
	    		for(int j=x_s; j<=x_e; j++) {
	    			
	    			//battleBoard[i][j] = "+";
	    			battleBoard[i][j] = shipCode;
	    		}
	    	}
    	
    	}
    	
    	}catch(Exception e) {
    		System.out.println("Cannot insert a ship " + e.getMessage());
    		return false;
    	}
    	
        return true;
    }

    public List<Integer> getShipsMissing()
    {
        return ships;
    }

    public Board2(int rows, int cols, List<Integer> shipsMissing ) throws Exception
    {
    	battleBoard = new String[rows][cols];
    	
    	this.rows = rows;
    	this.cols = cols;
    	
    	for(int i=0; i< this.rows; i++) {
    		for(int j=0; j< this.cols; j++) {
    			battleBoard[i][j] = "_";
    		}
    	}
    	
    	code = new ArrayList<String>();
    	
    	ships = new ArrayList<Integer>(shipsMissing);
    	
    	for(Integer size: ships) {
    		if ((size < 0) ||  (size > this.cols) ||  (size > this.rows)) {shipsMissing.remove(size);}
    	}
    	
    	if (ships.isEmpty()) {
    		throw new Exception("Empty Board!");
    	}
    	
    }

    public String[][] getBattleBoard(){return battleBoard;}
    

}
