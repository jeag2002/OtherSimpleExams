package es.snake;

import es.snake.engine.SnakeWorld;
import es.snake.utils.Utils;

public class Main {
	
	private static final int DEFAULT_X = 2;
	private static final int DEFAULT_Y = 2;
	

	public static void main(String[] args) throws Exception {
		
		SnakeWorld sW;
		
		int X = 0;
		int Y = 0;
		
		if (args.length >= 2) {
			X =  Utils.StringToInteger(args[0]);
			Y =  Utils.StringToInteger(args[1]);
			if (X < DEFAULT_X) {X = DEFAULT_X;}
			if (Y < DEFAULT_Y) {Y = DEFAULT_Y;}
			
			
		} else { 
			X = DEFAULT_X; 
			Y = DEFAULT_Y; 
		}
		
		System.out.println("[Create SNAKE IA] x::= " + X +  " y::= " + Y );
		
		if ((X <= 1) || (Y <= 1)) {
			throw new Exception("Both numbers have to be up to one");
		} else if (!Utils.isEven(X) && !Utils.isEven(Y)) {
			throw new Exception("At least one of the input values has to be even");
		}
		
		sW = new SnakeWorld(X,Y);
		
		try {
			int res = sW.createWorld();
			if (res != 0) {sW.run();}
		} catch (Exception e) {
			System.out.println("[Create SNAKE IA] error " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
