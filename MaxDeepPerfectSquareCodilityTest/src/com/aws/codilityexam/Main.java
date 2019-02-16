package com.aws.codilityexam;

//https://stackoverflow.com/questions/295579/fastest-way-to-determine-if-an-integers-square-root-is-an-integer

import java.lang.*;

public class Main {
	
	private long goodMask; 
    {
        for (int i=0; i<64; ++i) goodMask |= Long.MIN_VALUE >>> (i*i);
    }
    
    public boolean isSquare(long x) {
        if (goodMask << x >= 0) return false;
        final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);
        if ((numberOfTrailingZeros & 1) != 0) return false;
        x >>= numberOfTrailingZeros;
        if ((x&7) != 1 | x <= 0) return x == 0;
        final long tst = (long) Math.sqrt(x);
        return tst * tst == x;
    }
    
    //CHECK THE DEEP OF A PERFECT SQUARE
    public int deeper(int A){
        
        if (isSquare((long)A)){
             double A_SQ = Math.sqrt(A);
            return 1 + deeper((int)A_SQ);
        }else{
            return 0;   
        }
    }
	
    /*
     OTHER SOLUTION
     
     public int deeper(int A){
        
        double A_SQ = Math.sqrt(A);
        
        if ((A_SQ % 1) == 0){
            return 1 + deeper((int)A_SQ);
        }else{
            return 0;   
        }
    } 
    */
    
    public int solution(int A, int B) {
        
        int deepSolution = 0;
        
        if (A <= B){
            
            double rootA = Math.sqrt(A);
            double rootB = Math.sqrt(B);
            
            int integerValueA = (int)rootA;
            int integerValueB = (int)rootB;
            
            if ((double)integerValueA < rootA){
                integerValueA += 1;   
            }
            
            //CHECK ALL THE PERFECT SQUARES BETWEEN TWO LIMITS;
            for(int i=integerValueA; i<= integerValueB; i++){
                int perfectSquare = 1 + deeper(i);
                if (perfectSquare > deepSolution){deepSolution = perfectSquare;}
            }
        }
        
        
        return deepSolution;
   }
    
    
	public static void main(String[] args) {
		
		Main main = new Main();
		
		int deepSolution = main.solution(10, 20);
		System.out.println("Deep solution between [10,20] " + deepSolution);
		
		deepSolution = main.solution(6000, 7000);
		System.out.println("Deep solution between [6000, 7000] " + deepSolution);
		

	}

}
