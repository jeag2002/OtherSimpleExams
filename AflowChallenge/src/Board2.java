import java.util.ArrayList;
import java.util.List;

public class Board2 {
	
	private String[][] battleBoard ;
    
    private ArrayList<Integer> ships;
    
    private ArrayList<String> code;
    
    private int _length;
    private int _weight;
   
    public boolean setShipPosition(Integer length, String iStartPoint, String iEndPoint){
    	
    	try {
    	
    		
    		
    	String x_y_s_Point[] = iStartPoint.split(",");
    	String x_y_e_Point[] = iEndPoint.split(",");
    	
    	Integer x_s = Integer.parseInt(x_y_s_Point[0]);
    	Integer y_s = Integer.parseInt(x_y_s_Point[1]);
    	
    	Integer x_e = Integer.parseInt(x_y_e_Point[0]);
    	Integer y_e = Integer.parseInt(x_y_e_Point[1]);
    	
    	
    	if ((x_s < 0) || (x_s >= _weight)){throw new Exception ("OutOfLimits");}
    	if ((y_s < 0) || (y_s >= _length)){throw new Exception ("OutOfLimits");}
    	
    	if ((x_e < 0) || (x_e >= _weight)){throw new Exception ("OutOfLimits");}
    	if ((y_e < 0) || (y_e >= _length)){throw new Exception ("OutOfLimits");}
    	
    	
    	if (x_s > x_e) {throw new Exception("wrong input coordinates");}
    	if (y_s > y_e) {throw new Exception("wrong input coordinates");}
    	
    	if (battleBoard != null) {
    	
    		String shipCode = String.valueOf(length);
    		int value = length + 63;
    		
    		while (code.contains(shipCode)) {
    			shipCode = Character.toString((char)value);
    			value += 32;
    			if (value > 255) {
    				value = 17;
    			}
    			
    		}
    		
    		code.add(shipCode);
    		
	    	for(int i=x_s; i<=x_e; i++) {
	    		for(int j=y_s; j<=y_e; j++) {
	    			
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

    public Board2(int lenght, int weigth, List<Integer> shipsMissing ) throws Exception
    {
    	battleBoard = new String[weigth][lenght];
    	
    	this._length = lenght;
    	this._weight = weigth;
    	
    	for(int i=0; i<_weight; i++) {
    		for(int j=0; j<_length; j++) {
    			battleBoard[i][j] = "_";
    		}
    	}
    	
    	code = new ArrayList<String>();
    	
    	ships = new ArrayList<Integer>(shipsMissing);
    	
    	for(Integer size: ships) {
    		if ((size < 0) ||  (size > this._length) ||  (size > this._weight)) {shipsMissing.remove(size);}
    	}
    	
    	if (ships.isEmpty()) {
    		throw new Exception("Empty Board!");
    	}
    	
    }

    public String[][] getBattleBoard(){return battleBoard;}
    

}
