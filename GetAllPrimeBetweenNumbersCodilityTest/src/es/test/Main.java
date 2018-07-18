package es.test;

public class Main {
	
	
	public Main() {
	}
	
	 private boolean isPrime(int a){
	        
	        if (a<=3){
	            return true;
	        }else{
	            if ((a%2==0) || (a%3==0)){
	                return false;
	            }else{
	                
	                for(int i=5; i*i<=a; i=i+6){
	                    if (a%i == 0 || a%(i+2) == 0){
	                        return false;
	                    }    
	                }
	                
	            }   
	        }
	        
	        return true;
	    }
	    
	    
	    public int solution(int A, int B) {
	        
	        int res = 0;
	        
	        for (int i=A; i<=B; i++){
	            if (isPrime(i)){
	                res ++;
	            }        
	        }
	        
	        return res;
	        
	    }
	
	

	public static void main(String[] args) {
		Main main = new Main();
		int A = 11;
		int B = 19;
		int res = main.solution(A, B);
		System.out.println(" process(" + A + "," + B + ") res ::= "  + res);

	}

}
