package es.test.factorize.task;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorizeTask extends RecursiveTask<String> {
	
	
	private volatile Long number;
	private volatile String response;
	
	
	public FactorizeTask() {
		number = 0L;
	}
	
	
	public FactorizeTask(Long _number) {
		number = _number;
	}
	
	
	private boolean isPrime(long n) {
		
	   for(int i = 2; i <= n/2; i++){
	       if(n % i == 0 ){
	          return false;
	       }
	    }
		
	   return true;
	   
		
	}
	
	
	public String getResponse() {
		return response;
	}
	
		
	@Override
	protected String compute() {
		
		if (isPrime(number)) {
			return String.valueOf(number);
		}else {
			
			long divider = 2;
            boolean found = false;
            while(!found){
                if(number % divider ==0){
                	
                	FactorizeTask f1 = new FactorizeTask(divider);
                	FactorizeTask f2 = new FactorizeTask(number / divider);
                	
                	f1.fork();
                	
                	String f2S = f2.compute();
                	String f1S = f1.join();
                	
                	response = f1S+","+f2S;
                	
                    found = true;
                }
                else divider++;
            }
			
			
			
			return response;
			
		}
		
	}
	
}
