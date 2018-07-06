import java.util.ArrayList;

public class Main {
	
	  private class Seat{
	      public int[] left = {0,0,0};
	      public int[] middle = {0,0,0,0}; 
	      public int[] right = {0,0,0};
	    }
	    
	    
	    private Integer StringToNumeric(String num){
	        int return_value = -1;
	        try{
	            return_value = Integer.parseInt(num);
	        }catch(Exception e){
	          return_value = -1;   
	        }
	        return return_value;
	    }
	    
	    
	    private ArrayList<Seat> availableSeats = new ArrayList<Seat>();
	    
	    public int solution(int N, String S) {
	        int return_value = 0;
	        
	        for(int i=0; i<N; i++){
	            Seat seat = new Seat();
	            availableSeats.add(seat);
	        }
	        
	        
	        if (!S.trim().equalsIgnoreCase("")){
	        
	            String seatfull[] = S.split(" ");
	            
	            
	            for(int i=0; i<seatfull.length; i++){
	                
	                String rowStr = seatfull[i].substring(0,1);
	                String colStr = seatfull[i].substring(1,seatfull[i].length());
	                
	                int rowInt = StringToNumeric(rowStr);
	                
	                if (colStr.equalsIgnoreCase("A") || colStr.equalsIgnoreCase("B") || colStr.equalsIgnoreCase("C")){
	                    if (colStr.equalsIgnoreCase("A")){
	                        availableSeats.get(rowInt-1).left[0] = 1; 
	                    }else if (colStr.equalsIgnoreCase("B")){
	                        availableSeats.get(rowInt-1).left[1] = 1;   
	                    }else if (colStr.equalsIgnoreCase("C")){
	                        availableSeats.get(rowInt-1).left[2] = 1;
	                    }
	                }else if (colStr.equalsIgnoreCase("H") || colStr.equalsIgnoreCase("J") || colStr.equalsIgnoreCase("K")){
	                    
	                    if (colStr.equalsIgnoreCase("H")){
	                        availableSeats.get(rowInt-1).right[0] = 1; 
	                    }else if (colStr.equalsIgnoreCase("J")){
	                        availableSeats.get(rowInt-1).right[1] = 1;   
	                    }else if (colStr.equalsIgnoreCase("K")){
	                        availableSeats.get(rowInt-1).right[2] = 1;
	                    }
	                    
	                    
	                }else{
	                    
	                    if (colStr.equalsIgnoreCase("D")){
	                        availableSeats.get(rowInt-1).middle[0] = 1; 
	                    }else if (colStr.equalsIgnoreCase("E")){
	                        availableSeats.get(rowInt-1).middle[1] = 1;   
	                    }else if (colStr.equalsIgnoreCase("F")){
	                        availableSeats.get(rowInt-1).middle[2] = 1;
	                    }else if (colStr.equalsIgnoreCase("G")){
	                        availableSeats.get(rowInt-1).middle[3] = 1;
	                    }
	                }
	                
	            }
	        }
	    
	        for(int i = 0; i<N; i++){
	            Seat data = availableSeats.get(i);
	            
	            if ((data.left[0]==0) && (data.left[1]==0) && (data.left[2]==0)){
	                return_value += 1;  
	            }
	            
	            if  ((data.right[0]==0) && (data.right[1]==0) && (data.right[2]==0)){
	                return_value += 1;
	            }
	            
	            if ((data.middle[0]==0) && (data.middle[1]==0) && (data.middle[2]==0) && (data.middle[3]==0)){
	                return_value += 1;
	            }else if ((data.middle[0]==0) && (data.middle[1]==0) && (data.middle[2]==0)){
	                return_value += 1;        
	            }else if ((data.middle[1]==0) && (data.middle[2]==0) && (data.middle[3]==0)){
	                return_value += 1;   
	           }
	        }
	        return return_value;
	    }
	    
	    
	    public static void main(String[] args) {
	    	
	    	Main main = new Main();
	    	int sol = main.solution(2, "1A 2F 1C");
	    	System.out.println("solution " +  sol);
	    	
	    }
	    
}
	
	

