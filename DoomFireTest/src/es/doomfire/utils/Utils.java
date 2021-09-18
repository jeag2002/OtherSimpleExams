package es.doomfire.utils;

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
	
	
	public static Color ColorByIndex(int color_index) {
	    
		Color color = Color.WHITE;
		
		switch(color_index) {
			case 1: color = Color.WHITE; break;
			case 2: color = Color.YELLOW; break;
			case 3: color = new Color(0xFF, 0xD7, 0x00); break;
			case 4: color = Color.ORANGE; break;
			case 5: color = new Color(0xFF, 0x8C, 0x00); break;
			case 6: color = new Color(0xFF, 0x45, 0x00); break;
			case 7: color = Color.RED; break;
			case 8: color = new Color(0xDC, 0x14, 0x3C); break;
			case 9: color = new Color(0xA5, 0x2A, 0x2A); break; //--> BROWN
			case 10: color = new Color(0x80,0x00,0x00); break;
			default: color = Color.BLACK; break;
		}
		
		return color;
	
		
	}
	
	
	public static final int MAX_RGB_VALUE = 255;

    public static Color invert(Color c) {
        //  TODO : improve
        int a = c.getAlpha();
        int r = MAX_RGB_VALUE - c.getRed();
        int g = MAX_RGB_VALUE - c.getGreen();
        int b = MAX_RGB_VALUE - c.getBlue();

        // if the resulting color is to light (e.g. initial color is black, resulting color is white...)
        if ((r + g + b > 740) || (r + g + b < 20)) {
            // return a standard yellow
            return new Color(MAX_RGB_VALUE, MAX_RGB_VALUE, 40, a);
        } else {
            return new Color(r, g, b, a);
        }
    }

}
