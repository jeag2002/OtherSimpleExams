
public class Main {

	
	public int process(int A[]) {
		int return_value = 0;
		
		
		boolean DONE = false;
		
		int i = 0;
		int j = 0;
		
		while (!DONE) {
			
			if (A[i] == A[j]) {
				return_value = Math.max(return_value, Math.abs(i-j));
			}
			
			j++;
			if (j >= A.length) {
				j = 0;
				i++;
				if (i >= A.length) {
					DONE = true;
				}
			}
		}
		
		
		/*
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A.length; j++) {
				if (A[i]==A[j]) {
					return_value = Math.max(return_value, Math.abs(i-j));
				}
			}
		}
		*/
		
		return return_value;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {4,2,2,0,6,1,1,6};
		Main main = new Main();
		int return_value = main.process(A);
		System.out.println("return_value ::= " +return_value);
	}

}
