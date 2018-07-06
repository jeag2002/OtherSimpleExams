package es.test;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	
	public static final int DEEP = 2;
	
	public int buildStrings(int A[], int length){

	  int[] indexes = new int[length];
	 
	  int pMax = A.length;  
	  int max_value = 0;
	  while (indexes[0] < pMax){ 
		 
		
		int index_x = indexes[0];
		int index_y = indexes[1];
		
		
		if (index_x <= index_y) {
			
			int value = A[index_x]  + A[index_y] + (index_y -index_x);
			System.out.println("combination (" + index_x + "," + index_y + ") VALUE " + value);
			
			if (value > max_value) {
				max_value = value;
			}
			
		}
		  
		indexes[length-1]++; 
	    for (int i = length-1; indexes[i] == pMax && i > 0; i--){  
	      indexes[i-1]++;  
	      indexes[i]=0;     
	    }     
	  }
	  
	  return max_value;
	}
	
	

	public Main() {
		
	}
	
	
	public static void main(String[] args) {
		
		//int A[] = {1,3,-3};
		int A[] = {-8,4,0,5,-3,6};
		Main main = new Main();
		int max_result = main.buildStrings(A, 2);
		System.out.println("max_result ::= " + max_result);
		
	}

}

/*
https://stackoverflow.com/questions/31175503/iteratively-find-all-combinations-of-size-k-of-an-array-of-characters-n-choose
char[] pool = new char[]{'1', '2', '3'};
*/

/*
Recursive Combination
public void recursiveCombination(int A[], ArrayList<Integer> indexes, ArrayList<Integer> results, int index) {
	int result = 0;
	
	if (index < DEEP) {
		for (int i=0; i<A.length; i++) {
			
			indexes.add(i);
			recursiveCombination(A,indexes,results,index+1);		
			indexes.remove(indexes.size()-1);
			
		}
		
		
	}else {
		
		int indexI = indexes.get(0);
		int indexJ = indexes.get(1);
		
		if (indexI <= indexJ) {
			result = A[indexI] + A[indexJ] + (indexJ - indexI);
			System.out.println(" combination (" + indexI + "," + indexJ+ ") RESULT (" + result + ")");	
			results.add(result);
		}
	}
	
	
}

	
public int calculateMaxDistance(int A[]) {
	
	int max_result = 0;
	
	ArrayList<Integer> indexes = new ArrayList<Integer>();
	ArrayList<Integer> results = new ArrayList<Integer>();
	
	recursiveCombination(A,indexes, results, 0);
	
	Collections.sort(results);
	
	max_result = results.get(results.size()-1);
	
	
	return max_result;
}
*/

