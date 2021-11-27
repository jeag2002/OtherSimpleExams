package es.snake;

import es.snake.engine.SnakeWorld;
import es.snake.utils.Utils;

public class Main {
	
	private static final int DEFAULT_X = 4;
	

	public static void main(String[] args) throws Exception {
		
		SnakeWorld sW;
		
		int X = 0;
		int Y = 0;
		
		if (args.length >= 1) {
			X =  Utils.StringToInteger(args[0]);
			if (X < DEFAULT_X) {X = DEFAULT_X; Y = DEFAULT_X;}
			else { Y = X; }
			
		} else { X = DEFAULT_X; Y = DEFAULT_X; }
		
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
