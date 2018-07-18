package es.test;

public class QRoundTest {
	
	
	  public QRoundTest() {
		  
	  }
	
	
	  public int solution(int Q){
	        int res = 0;
	        
	        double q = Math.sqrt(Q);
	        int limit = (int)q;
	    
	        boolean DONE = false;
	        
	        int i = -limit;
	        int j = limit;
	        
	        while(!DONE){
	                
	            double a = Math.pow(i,2);
	            double b = Math.pow(j,2);
	                
	            int a_int = (int)a;
	            int b_int = (int)b;
	            
	            if ((a_int + b_int) == Q){
	                res++;   
	            }
	                
	            j--;
	                
	            if (j < -limit){
	                j= limit;
	                i++;
	                if (i > limit){

	                    DONE = true;   
	                }
	            }
	        }
	                
	        return res;
	        
	 }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QRoundTest qRTest = new QRoundTest();
		int input = 2;
		int res = qRTest.solution(input);
		System.out.println("Num QRoundTest (" + input + ") res (" + res + ")");

	}

}
