package com.microsoft.codility_3;

import java.util.ArrayList;

public class Main {
	
	class Tree {
		int x;
		Tree l;
		Tree r;
	}
	
	
	public Tree tree_1;
	public Tree tree_2;
	public Tree tree_3;
	
	
	
	public Main() {
		createTree_1();
		createTree_2();
		createTree_3();
	}
	
	
	public void createTree_1() {
		
		Tree node_20 = new Tree();
		node_20.x = 20;
		node_20.l = null;
		node_20.r = null;
		
		Tree node_21 = new Tree();
		node_21.x = 21;
		node_21.l = null;
		node_21.r = null;
		
		Tree node_1 = new Tree();
		node_1.x = 1;
		node_1.l = null;
		node_1.r = null;
		
		
		Tree node_3 = new Tree();
		node_3.x = 3;
		node_3.l = node_20;
		node_3.r = node_21;
		
		Tree node_10 = new Tree();
		node_10.x = 10;
		node_10.l = node_1;
		node_10.r = null;
		
		tree_1 = new Tree();
		tree_1.x = 5;
		tree_1.l = node_3;
		tree_1.r = node_10;
		
		
	}
	
	public void createTree_2() {
		
		Tree node_3 = new Tree();
		node_3.x = 3;
		node_3.l = null;
		node_3.r = null;
		
	
		Tree node_8 = new Tree();
		node_8.x = 8;
		node_8.l = null;
		node_8.r = null;
		
		
		Tree node_4 = new Tree();
		node_4.x = 4;
		node_4.l = node_3;
		node_4.r = node_8;
		
		Tree node_6 = new Tree();
		node_6.x = 6;
		node_6.l = null;
		node_6.r = null;
		
		
		tree_2 = new Tree();
		tree_2.x = 5;
		tree_2.l = node_4;
		tree_2.r = node_6;
		
		
		
		
		
		
		
		
	}
	
	public void createTree_3() {
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main m = new Main();
		int result = m.solution(m.tree_1);
		assert result == 4 : String.format("No expected result %d, expected %d",result,4); 
		System.out.println("RESULT " + result);
		
		result = m.solution(m.tree_2);
		assert result == 3 : String.format("No expected result %d, expected %d",result,3); 
		System.out.println("RESULT " + result);
		
		
	
	
	}
	
	
	public int solution(Tree T) {
		int result = 0;
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		
		data.add(T.x);
		
		result += 1 + recursive_solution(T.l,data) +  recursive_solution(T.r, data);
 		
		return result;
	}
	
	
	public int recursive_solution(Tree T, ArrayList<Integer> data) {
		
		if (T == null) {
			return 0;
		}else {
			
			int max = data.stream().max(Integer::compare).get();
			
			data.add(T.x);
			
			int value = 0;
			
			if (max > T.x)  {
		
				value = recursive_solution(T.l,data) +  recursive_solution(T.r, data);
			}else {
				
				value =  1 + recursive_solution(T.l,data) +  recursive_solution(T.r, data);
			}
			
			data.remove(data.size()-1);
			return value;
			
		}
		
	}
	
	
	

}
