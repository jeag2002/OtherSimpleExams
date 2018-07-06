import java.util.ArrayList;

public class Main {
	
	
	  public int[] solution(int K, int M, int[] A) {
	        
	        int bucket[] = new int [K];
	        ArrayList<Integer> numberChosen = new ArrayList<Integer>();
	        
	        int inf = 0;
	        int sup = K;
	        
	        for(int i=0; i<A.length; i++){
	            
	            for(int j=0; j<K; j++){bucket[j] = 0;}
	            
	            for(int k=i,l=0; (k<i+K) && (k<A.length); k++, l++){
	                bucket[l] = A[k]+1;    
	            }
	            
	            for(int x=0; x<bucket.length; x++){
	                int num_founds = 1;
	                
	                for(int y=x+1; y<bucket.length; y++){
	                    if (bucket[x] == bucket[y]){num_founds += 1;}
	                }
	                
	                int mod = 0;
	                if (bucket.length%2 != 0){mod+=1;}
	                
	                if (num_founds >= (bucket.length/2+mod)){
	                    if  (bucket[x] != 0){
	                        if (!numberChosen.contains(new Integer(bucket[x]))){
	                            numberChosen.add(new Integer(bucket[x]));
	                        }
	                    }
	                }
	            }
	        }
	        
	        int array[] = new int[numberChosen.size()];
	        for(int i=0; i<numberChosen.size(); i++){
	            array[i] = numberChosen.get(i);   
	        }
	        
	        return array;
	 }
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		int A[] = {2,1,3,1,2,2,3};
		int res[] = main.solution(3, 5, A);
		for(int i=0; i<res.length; i++) {
			System.out.print(res[i] + " ");
		}
		System.out.println("\n");

	}

}
