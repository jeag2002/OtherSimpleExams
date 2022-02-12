package es.test.aparapi;

import com.aparapi.Kernel;
import com.aparapi.Range;

public class Main {
	
	private static final int SIZE = 1000;

	public static void main(String[] args) {
		
		final float result[] = new float[SIZE];
		final float inA[] = new float[SIZE];
		final float inB[] = new float[SIZE];
		
		
		for (int i =0; i<SIZE; i++) {
			result[i] = 0;
			inA[i] = (float)i;
			inB[i] = (float)SIZE - (float)i;
		}
		
		
		Kernel kernel = new Kernel() {
		    @Override
		    public void run() {
		        int i = getGlobalId();
		        result[i] = inA[i] + inB[i];
		    }
		};
		

		Range range = Range.create(result.length);
		
		Long start = System.currentTimeMillis();
		kernel.execute(range);
		Long time = System.currentTimeMillis() -start;
		System.out.println("Time Parallel " +  time);
		kernel.dispose();
		
		for (int i =0; i<SIZE; i++) {
			result[i] = 0;
			inA[i] = (float)i;
			inB[i] = (float)SIZE - (float)i;
		}
		
		
		start = System.currentTimeMillis();
		for (int i =0; i<SIZE; i++) {
			result[i] = inA[i] + inB[i];
		}
		time = System.currentTimeMillis() -start;
		System.out.println("Time seq " +  time);
		
		
		
		

	}

}
