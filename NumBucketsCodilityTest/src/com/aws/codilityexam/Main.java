package com.aws.codilityexam;

public class Main {
	
	
	public int solution(String S, int K) {
	        
	        int number_of_buckets = -1;
	        
	        if (K > 0){
	            int length = S.length();
	            number_of_buckets = length/K;
	            if (length % K > 0){number_of_buckets ++; }
	        }
	        return number_of_buckets;
	}

	
	
	public static void main(String[] args) {
		Main main = new Main();
	    String SMS = "SMS de prueba too wapa shur un abrazo";
	    int size_of_bucket = 12;
	    int solution = main.solution(SMS, size_of_bucket);
	    System.out.println("SMS (" + SMS + ") size_of_bucket (" + size_of_bucket + ") num buckets (" + solution + ")");
	}

}
