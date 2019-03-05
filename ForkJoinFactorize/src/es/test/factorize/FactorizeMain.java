package es.test.factorize;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import es.test.factorize.task.FactorizeTask;

public class FactorizeMain {
	
	
	public String process(Long results) throws Exception{
		
		FactorizeTask fact = new FactorizeTask(results);
		String response = "";
		
	    int nThreads = Runtime.getRuntime().availableProcessors();
		
		final ForkJoinPool pool = new ForkJoinPool(nThreads);
		try {
			response = pool.invoke(fact);
		}finally {
			pool.shutdown();
		}
		return response;
	}
	
	
	

	public static void main(String[] args) throws Exception {
		Long input = 90099099L;
		FactorizeMain factorize = new FactorizeMain();
		String response = factorize.process(input);
		System.out.println("Input (" + input + ") response (" + response + ")");

	}
	
	
	






	

}
