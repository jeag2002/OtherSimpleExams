package es.snake.utils;

import java.awt.Color;

public class Utils {
	
	public static int StringToInteger(String data) {
		int return_value = -1;
		
		try {
			return_value = Integer.parseInt(data);
		}catch(Exception e){
			return_value = -1;
		}
		
		return return_value;
	}
	
	
	public static boolean isOdd(int value) {
		return (value % 2 != 0);
	}
	

	public static boolean isEven(int value) {
		return (value % 2 == 0);
	}
	
	
	public static Color ColorByIndex(int color_index) {
	    
		Color color = Color.WHITE;
		
		switch(color_index) {
			case 0: color = Color.BLACK; break;
			case 1: color = Color.GREEN; break;
			case 2: color = Color.RED; break;
			default: color = Color.WHITE; break;
		}
		
		return color;
	}

	
	
	


}
