package es.test;

public class Main {
	
	  public Main() {
		  
	  }
	
	  public int solution(int[] A) {
	        int res = -1;
	        
	        boolean DONE = false;
	        
	        int i = 0;
	        int j = 1;
	        
	        while(!DONE){
	            
	            if (i < j){
	                int data = Math.abs(A[i]-A[j]);
	                if (res == -1){
	                    res = data;   
	                }else if (res > data){
	                    res = data;   
	                }
	            }
	            
	            j++;
	            if (j >= A.length){
	                i++;
	                
	                if (i >= A.length){
	                    DONE = true;
	                }else if (i <= (A.length -1)){
	                    j = i+1;
	                    if ( j >= A.length){

	                        DONE = true;   
	                    }
	                }
	            }
	        }
	        
	    return res;
	 }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {8,24,3,20,1,17};
		int B[] = {7,21,3,42,3,7};
		
		Main main = new Main();
		int res = main.solution(B);
		System.out.println("result ::= (" + res +")");
		

	}

}
