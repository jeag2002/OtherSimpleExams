
public class Main {
	
	public Main() {
		
	}
	
	
	public int solution(int A, int B) {
        
        // write your code in Java SE 8
        double limit_bef = Math.sqrt(A);
        double limit_af = Math.sqrt(B);
        
        int i = 0;
        
        int limit_bef_int = (int)limit_bef;
        int limit_af_int = (int)limit_af;
        
        i = (limit_af_int - limit_bef_int)+1;
        
        return i;        
    }
	
	public static void main(String[] args) {
		
		Main main = new Main();
		int result = main.solution(4, 17);
		System.out.println("Result " +  result);
	}

}
