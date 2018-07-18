package es.test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	private static final TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	
	 static {
		 map.put(1000, "M");
	     map.put(900, "CM");
	     map.put(500, "D");
	     map.put(400, "CD");
	     map.put(100, "C");
	     map.put(90, "XC");
	     map.put(50, "L");
	     map.put(40, "XL");
	     map.put(10, "X");
	     map.put(9, "IX");
	     map.put(5, "V");
	     map.put(4, "IV");
	     map.put(1, "I");
	    }
	
	 public String toRomanTreeMap(int number) {
	    int l =  map.floorKey(number);
	    if ( number == l ) {
	         return map.get(number);
	    }
	    return map.get(l) + toRomanTreeMap(number-l);
	  }
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 public String toRomanNumeral(int input) {
		    if (input < 1 || input > 3999)
		        return "Invalid Roman Number Value";
		    String s = "";
		    while (input >= 1000) {
		        s += "M";
		        input -= 1000;        }
		    while (input >= 900) {
		        s += "CM";
		        input -= 900;
		    }
		    while (input >= 500) {
		        s += "D";
		        input -= 500;
		    }
		    while (input >= 400) {
		        s += "CD";
		        input -= 400;
		    }
		    while (input >= 100) {
		        s += "C";
		        input -= 100;
		    }
		    while (input >= 90) {
		        s += "XC";
		        input -= 90;
		    }
		    while (input >= 50) {
		        s += "L";
		        input -= 50;
		    }
		    while (input >= 40) {
		        s += "XL";
		        input -= 40;
		    }
		    while (input >= 10) {
		        s += "X";
		        input -= 10;
		    }
		    while (input >= 9) {
		        s += "IX";
		        input -= 9;
		    }
		    while (input >= 5) {
		        s += "V";
		        input -= 5;
		    }
		    while (input >= 4) {
		        s += "IV";
		        input -= 4;
		    }
		    while (input >= 1) {
		        s += "I";
		        input -= 1;
		    }    
		    return s;
		}
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 static final int MIN_VALUE = 1;
	 static final int MAX_VALUE = 3999;
	 static final String[] RN_M = {"", "M", "MM", "MMM"};
	 static final String[] RN_C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	 static final String[] RN_X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	 static final String[] RN_I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

	 
	 
	 public String toRomanIterative(int mInt) {
		    String[] rnChars = { "M",  "CM", "D", "C",  "XC", "L",  "X", "IX", "V", "I" };
		    int[] rnVals = {  1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
		    String retVal = "";

		    for (int i = 0; i < rnVals.length; i++) {
		        int numberInPlace = mInt / rnVals[i];
		        if (numberInPlace == 0) continue;
		        retVal += numberInPlace == 4 && i > 0? rnChars[i] + rnChars[i - 1]:
		            new String(new char[numberInPlace]).replace("\0",rnChars[i]);
		        mInt = mInt % rnVals[i];
		    }
		    return retVal;
		}
	 
	 
	 public String toRomanArray(int number) {
	        if (number < MIN_VALUE || number > MAX_VALUE) {
	            throw new IllegalArgumentException(
	                    String.format(
	                            "The number must be in the range [%d, %d]",
	                            MIN_VALUE,
	                            MAX_VALUE
	                    )
	            );
	        }

	        return new StringBuilder()
	                .append(RN_M[number / 1000])
	                .append(RN_C[number % 1000 / 100])
	                .append(RN_X[number % 100 / 10])
	                .append(RN_I[number % 10])
	                .toString();
	    }
	 
	 /////////////////////////////////////////////////////////////////////////////////////////////// 
	 
	 /////////////////////////////////////////////////////////////////////////////////////////////// 
	 public String toRomanChain(int number) {
		    
		 	String res = "";
		 	return  String.format("%1$-" + number + "s", " ").replaceAll(" ", "I")
		            .replaceAll("IIIII", "V")
		            .replaceAll("IIII", "IV")
		            .replaceAll("VV", "X")
		            .replaceAll("VIV", "IX")
		            .replaceAll("XXXXX", "L")
		            .replaceAll("XXXX", "XL")
		            .replaceAll("LL", "C")
		            .replaceAll("LXL", "XC")
		            .replaceAll("CCCCC", "D")
		            .replaceAll("CCCC", "CD")
		            .replaceAll("DD", "M")
		            .replaceAll("DCD", "CM");
		}
	 
	 
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////
	 
	 public String toRomanLinkedHashMap(int Int) {
		    LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
		    roman_numerals.put("M", 1000);
		    roman_numerals.put("CM", 900);
		    roman_numerals.put("D", 500);
		    roman_numerals.put("CD", 400);
		    roman_numerals.put("C", 100);
		    roman_numerals.put("XC", 90);
		    roman_numerals.put("L", 50);
		    roman_numerals.put("XL", 40);
		    roman_numerals.put("X", 10);
		    roman_numerals.put("IX", 9);
		    roman_numerals.put("V", 5);
		    roman_numerals.put("IV", 4);
		    roman_numerals.put("I", 1);
		    String res = "";
		    for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
		      int matches = Int/entry.getValue();
		      res += repeat(entry.getKey(), matches);
		      Int = Int % entry.getValue();
		    }
		    return res;
		  }
	 
	 private String repeat(String s, int n) {
		    if(s == null) {
		        return null;
		    }
		    final StringBuilder sb = new StringBuilder();
		    for(int i = 0; i < n; i++) {
		        sb.append(s);
		    }
		    return sb.toString();
	 }
	 //////////////////////////////////////////////////////////////////////////////////////////////
	 
	
	public static void main(String[] args) {

		Main main = new Main();
		int value = 3431;
		String RomanValue = main.toRomanTreeMap(value);
		System.out.println("TreeMap (" + value + ") res (" + RomanValue + ")");
		RomanValue = main.toRomanNumeral(value);
		System.out.println("Numeral (" + value + ") res (" + RomanValue + ")");
		RomanValue = main.toRomanIterative(value);
		System.out.println("Iterative (" + value + ") res (" + RomanValue + ")");
		RomanValue = main.toRomanArray(value);
		System.out.println("Array (" + value + ") res (" + RomanValue + ")");
		RomanValue = main.toRomanLinkedHashMap(value);
		System.out.println("LinkedHashMap (" + value + ") res (" + RomanValue + ")");
		RomanValue = main.toRomanChain(value);
		System.out.println("Chain (" + value + ") res (" + RomanValue + ")");
	}

}
