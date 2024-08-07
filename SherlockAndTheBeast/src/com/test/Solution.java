package com.test;

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

class Result {

    /*
     * Complete the 'decentNumber' function below.
     *
     * The function accepts INTEGER n as parameter.
     */
    //chatGPT solution, improvement over my solution.
    public static void decentNumber(int n) {
        // Edge cases to handle the impossible configurations
        if (n < 3 || n == 4) {
            System.out.println("-1");
            return;
        }
        
        // Find the maximum number of 5's that can be used (it must be a multiple of 3)
        int fivesCount = n; // Start with all digits as 5
        while (fivesCount % 3 != 0) {
            fivesCount -= 5; // Decrease by 5 to ensure we can have a multiple of 3
        }

        // If after deducing we have a negative count of 5's, then it's not possible
        if (fivesCount < 0) {
            System.out.println("-1");
            return;
        }

        int threesCount = n - fivesCount; // The rest are 3's

        // Construct the decent number as a string
        StringBuilder decentNumber = new StringBuilder();
        if (fivesCount > 0) {
        decentNumber.append(String.format("%"+fivesCount+"s","")
                .replace(" ","5")); // Append fives
        }
        if (threesCount > 0) {
        decentNumber.append(String.format("%"+threesCount+"s","")
                .replace(" ","3")); // Append threes
        }

        // Print the result
        System.out.println(decentNumber.toString());
    }

//my suboptimal solution
/*
    public static void decentNumber(int n) {
    // Write your code here
       if ((n < 3) || (n == 4))
          System.out.println("\n-1");
       else if ( n == 3)
          System.out.println("\n555");
       else if (n == 5)
          System.out.println("\n33333");
       else {
           
           
           String concatnumber = "";
           
           if (n%3 == 0) {
               concatnumber = concatnumber
               .concat(String.format("%"+n+"s","")
               .replace(" ","5"));
               System.out.println("\n"+concatnumber);
           } else  {
           
           BigInteger max_number = BigInteger.valueOf(-1L);
           for(int number=n; number>=0; number=number-5) {
                  concatnumber = "";
                  int five = number;
                  int three = n-number;
                  if (five > 0) {
                      if (five%3 == 0) {
                          concatnumber = concatnumber
                         .concat(String.format("%"+five+"s","")
                         .replace(" ","5"));
                      }
                  }
                  
                  if (three > 0) {
                      if (three%5 == 0) {
                        concatnumber = concatnumber
                         .concat(String.format("%"+three+"s","")
                         .replace(" ","3"));
                      }
                  }
              
              
              if (!concatnumber.equals(""))
                  if (concatnumber.length() == n)
                      if (max_number.compareTo(new BigInteger(concatnumber)) <= 0)
                          max_number = new BigInteger(concatnumber);
              
           	}
           
            System.out.println(max_number.toString());
                      
           }
                  
           }
           
       }
*/









    }


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                Result.decentNumber(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
