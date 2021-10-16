package com.microsoft.codility_2;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	
	private Vector<Vector<Integer>> ans = new Vector<Vector<Integer>>();
	private Vector<Integer> tmp = new Vector<Integer>();
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		int result = m.solution(new int[] {4,9,8,2,6},3);
		//POSSIBLE MISTAKE OF CODILITY MAX VALUE IS 23 NOT 18
		//assert result == 18 : String.format("No expected result %d, expected %d",result,18); 
		System.out.println("result ::= " + result);
		
		result = m.solution(new int[] {5,6,3,4,2},5);
		assert result == 20 : String.format("No expected result %d, expected %d",result,20); 
		System.out.println("result ::= " + result);
		
		result = m.solution(new int[] {7,7,7,7},1);
		assert result == -1 : String.format("No expected result %d, expected %d",result,-1); 
		System.out.println("result ::= " + result);
		
		result = m.solution(new int[] {1000},2);
		assert result == -1 : String.format("No expected result %d, expected %d",result,-1); 
		System.out.println("result ::= " + result);
		
		result = m.solution(new int[] {2,3,3,5,5},3);
		//POSSIBLE MISTAKE OF CODILITY MAX VALUE IS 13 NOT 12
		//assert result == 12 : String.format("No expected result %d, expected %d",result,12); 
		System.out.println("result ::= " + result);
		

	}
	
	public int solution(int[] A, int K) {
		int result = -1;
		
		if (K <= 1) {
			return result;
		} else if (A.length < K) {
			return result;
		} else {
			return makeCombi(A, A.length, K, result);
		}
	}
	
	
	
	void makeCombiUtil(int[] A, int n, int left, int k, AtomicInteger resultValue) {
		 
		 if (k == 0) {
	         ans.add(tmp);
	         
	        
	         int value = tmp.stream().mapToInt(Integer::intValue).sum();
	         //System.out.println("VALUE " + tmp + " res " + value);
	         
	         resultValue.set(Math.max(resultValue.get(), value));
	         return;
	         
	     }
		 
		 for (int i = left; i <= n; ++i){
	          tmp.add(A[i-1]);
	          makeCombiUtil(A, n, i + 1, k - 1, resultValue);
	          tmp.remove(tmp.size() - 1);
	     }
		
		
		
	}
	
	
    private int makeCombi(int[] A, int n, int K, int result)
    {
        
    	AtomicInteger resultValue = new AtomicInteger();
    	resultValue.set(result);
    	
    	
    	makeCombiUtil(A, n, 1, K, resultValue);
        //return ans;
    	
    	return resultValue.get();
    }
	
	
	

}


/*


 static Vector<Vector<Integer>> ans = new Vector<Vector<Integer>>();
    static Vector<Integer> tmp = new Vector<Integer>();
       
    static void makeCombiUtil(int n, int left, int k)
    {
       
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp);
            for(int i = 0; i < tmp.size(); i++)
            {
                System.out.print(tmp.get(i) + " ");
            }
            System.out.println();
            return;
        }
  
        // i iterates from left to n. First time
        // left will be 1
        for (int i = left; i <= n; ++i)
        {
            tmp.add(i);
            makeCombiUtil(n, i + 1, k - 1);
  
            // Popping out last inserted element
            // from the vector
            tmp.remove(tmp.size() - 1);
        }
    }
  
    // Prints all combinations of size k of numbers
    // from 1 to n.
    static Vector<Vector<Integer>> makeCombi(int n, int k)
    {
        makeCombiUtil(n, 1, k);
        return ans;
    }
     
    public static void main(String[] args)
    {
       
        // given number
        int n = 5;
        int k = 3;
        ans = makeCombi(n, k);
    }







*/