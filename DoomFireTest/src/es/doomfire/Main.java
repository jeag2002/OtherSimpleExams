package es.doomfire;

import es.doomfire.engine.DoomFireWorld;
import es.doomfire.utils.Utils;

public class Main {
	
	private static final int DEFAULT_X = 550;
	private static final int DEFAULT_Y = 100;

	public static void main(String[] args) {
		
		DoomFireWorld dFW;
		
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
		
		System.out.println("[Create DOOMFIRE] x::= " + X +  " y::= " + Y );
		
		dFW = new DoomFireWorld(X,Y);
		
		try {
			dFW.createWorld();
			dFW.run();
		} catch (Exception e) {
			System.out.println("[Create DOOMFIRE] error " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
