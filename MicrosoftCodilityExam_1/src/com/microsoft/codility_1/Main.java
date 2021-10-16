package com.microsoft.codility_1;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	
	public Main() {
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Main m = new Main();
		
		int result = m.solution("abccbd", new int[] {0,1,2,3,4,5});
		assert result == 2 : String.format("No expected result %d, expected %d",result,2); 
		System.out.println("result ::= " + result);
		
		result = m.solution("aabbcc", new int[] {1,2,1,2,1,2});
		assert result == 3 : String.format("No expected result %d, expected %d",result,3);
		System.out.println("result ::= " + result);
		
		result = m.solution("aaaa", new int[] {3,4,5,6});
		assert result == 12 : String.format("No expected result %d, expected %d",result,12);
		System.out.println("result ::= " + result);
		
		result = m.solution("ababa", new int[] {10,5,10,5,10});
		assert result == 0 : String.format("No expected result %d, expected %d",result,0);
		System.out.println("result ::= " + result);
		
		result = m.solution("aaababbbaabb", new int[] {1,2,3,1,1,1,2,3,1,2,1,2});
		assert result == 8 : String.format("No expected result %d, expected %d",result,8);
		System.out.println("result ::= " + result);
		
		

	}
	
	
	public int solution(String S, int[] C) throws Exception {
		int result = 0;
		int back_char = -1;
		
		ArrayList<Integer> remotevalue = new ArrayList<Integer>();
		
		if (S.length() != C.length) {
			throw new Exception ("not same size");
		}
		
		for (int i = 0; i< S.length(); i++) {
			char charS = S.charAt(i);
			
			int current_char = (int)charS;
			
			if (back_char != current_char) {
				
				if (!remotevalue.isEmpty()) {
					
					if (remotevalue.size() > 1) {
						
						result += remotevalue.subList(0, remotevalue.size()-1).stream().mapToInt(Integer::intValue).sum();
						remotevalue.clear();
					} else {
						remotevalue.clear();
					}
					
					remotevalue.add(C[i]);
					
				} else {
					remotevalue.add(C[i]);
				}
				
				
				
			}else {
				remotevalue.add(C[i]);
				Collections.sort(remotevalue);
			}
			
			back_char = current_char;
			
		}
		
		if (remotevalue.size() > 1) {
			result += remotevalue.subList(0, remotevalue.size()-1).stream().mapToInt(Integer::intValue).sum();
	     	remotevalue.clear();		
		}
		
		
		return result;
	}

}
