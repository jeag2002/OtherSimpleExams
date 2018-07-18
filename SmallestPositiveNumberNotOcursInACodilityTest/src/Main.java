import java.util.HashMap;
import java.util.Map;

public class Main {
	
	
	public Main() {
	}
	
	 public int solution(int[] A) {
	        // write your code in Java SE 8
	        Map<Integer, Integer>map = new HashMap<Integer, Integer>();
	        
	        for(int j=0; j<A.length; j++){
	            map.put(new Integer(A[j]), new Integer(1));    
	        }
	        
	        int i = 1;
	        
	        for(i=1; i<100000; i++){
	            if (!map.containsKey(new Integer(i))){
	                break;
	            }    
	        }
	        
	        if (i == 100000) {
	        	if (!map.containsKey(new Integer(i))){
	                return i;
	            }else {
	            	i = i+1;
	            	if (!map.containsKey(new Integer(i))){
		                return i;
		            }
	            }
	        }
	        
	        
	        return i;   
	   }
	
	

	public static void main(String[] args) {
			
		Main main = new Main();
		
		int A[] = {1, 3, 6, 4, 1, 2};
		int result = main.solution(A);
		System.out.println("result ::= " +  result);
		
	}

}
