import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result_1 {

    /*
     * Complete the 'countSubarrays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER k
     */
    /*
    Propuesta del examen. Funciona pero es muy lento :(
    public static long countSubarrays(List<Integer> numbers, int k) {
    // Write your code here
       long results = 0;
       for(int min_size=2; min_size <=(numbers.size()+1); min_size++) {
           for (int i = min_size-1; i<=numbers.size(); i++) {
               List<Integer> subList = numbers.subList(i-min_size+1, i);
               BigInteger mult = subList.stream().map(a->BigInteger.valueOf(a)).reduce(BigInteger.ONE, BigInteger::multiply);
               if ((mult.equals(BigInteger.valueOf(k))) || (mult.compareTo(BigInteger.valueOf(k)) == -1)) {results++;}    
            } 
       }
       return results;
    }
    */
    
    public static long countSubarrays(List<Integer> numbers, int k) {
	  
	 long results = 0;
	 for(int i = 0; i < numbers.size(); i++){ 
	    
	    List<Integer> back = new ArrayList<Integer>();
	    int j = 0;
	    back.add(numbers.get(i));
	    
	    //System.out.println(back);
	    
	    BigInteger mult = back.stream().map(a->BigInteger.valueOf(a)).reduce(BigInteger.ONE, BigInteger::multiply);
            if ((mult.equals(BigInteger.valueOf(k))) || (mult.compareTo(BigInteger.valueOf(k)) == -1)) {results++;}    

	    j=i+1;
	    
	    List<Integer> arr = new ArrayList<Integer>();
	    arr.add(numbers.get(i));
	    
	    while(j< numbers.size()){

	        arr.add(numbers.get(j));
	        //System.out.println(arr);
	        mult = arr.stream().map(a->BigInteger.valueOf(a)).reduce(BigInteger.ONE, BigInteger::multiply);
	        if ((mult.equals(BigInteger.valueOf(k))) || (mult.compareTo(BigInteger.valueOf(k)) == -1)) {results++;}
	        //is an ordered array?
	        else{break;}
	        j = j+1;
	    }

	}
	return results;
    }
    
    

}


public class Solution {
    public static void main(String[] args) throws IOException {
      
        Integer[] array = {4,5,7,8,8,10,10,13,14,15,15,16,22,23,24,26,26,29,30,31,32,43,43,44,45,48,53,53,59,60,60,62,63,69,70,70,72,81,81,82,84,85,86,88,90,90,90,95,96,98,99,99,99};
	int k = 970300; 
	
	//Integer[] array = new Integer[1000];
	//for(int i=0; i<1000; i++){array[i] = 2*i;}
	//int k = 250;
	
	//Integer[] array = {1,2,3};
	//int k = 4;
        List<Integer> numbers = Arrays.asList(array);

        Long startTime = System.currentTimeMillis();
        long result = Result_1.countSubarrays(numbers, k);
        Long stopTime = System.currentTimeMillis();
        
        Long total = (stopTime - startTime)/1000;
        
        System.out.println("result: " + result + " Time(s) " + total + "s");

    }
}

