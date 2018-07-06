import java.util.Stack;

public class Main {


    public static final String POP = "POP";
    public static final String DUP = "DUP";
    
    
    private boolean isNumeric(String inputStr){
        boolean isNumeric = false;
        try{
            int numericStr = Integer.parseInt(inputStr);
            isNumeric = true;
        }catch(Exception e){
            isNumeric = false;
        }
        return isNumeric;
    }
    
    
    private int setNumeric(String inputStr){
        int value = -1;
        try{
            value = Integer.parseInt(inputStr);
        }catch(Exception e){
            value = -1;    
        }   
        return value;
    }
    
	
    public int solution(String S) {
        
        int result = 0;
        
        Stack<String> stack = new Stack<String>();
        
        // write your code in Java SE 8
        String[] commands = S.split(" ");
        
        for(int i = 0; i< commands.length; i++){
            
            if (isNumeric(commands[i])){
                stack.push(commands[i]);     
            }else if (commands[i].equalsIgnoreCase(DUP)){
                if (stack.empty()){
                    return -1;
                }else{
                    String top = stack.peek();
                    stack.push(top);
                }
                
            }else if (commands[i].equalsIgnoreCase(POP)){
                
                if (stack.empty()){
                    return -1;
                }else{
                    stack.pop();
                }
                
            }else if (commands[i].equalsIgnoreCase("+")){
                
                if (stack.empty()){
                    return -1;   
                }else{
                    int operator_1 = setNumeric(stack.pop());    
                    if (stack.empty()){
                        return -1;                        
                    }else{
                        int operator_2 = setNumeric(stack.pop());
                        operator_1 = operator_1 + operator_2;
                        stack.push(String.valueOf(operator_1));    
                    }
                    
                    
                }
                
            }else if (commands[i].equalsIgnoreCase("-")){
                
                if (stack.empty()){
                    return -1;   
                }else{
                    int operator_1 = setNumeric(stack.pop());    
                    if (stack.empty()){
                        return -1;                        
                    }else{
                        int operator_2 = setNumeric(stack.pop());
                        operator_1 = operator_1 - operator_2;
                        stack.push(String.valueOf(operator_1));    
                    }
                }
            }
        }
        
        result = setNumeric(stack.pop());
        
        
        return result;
    }
    
	
	
	public static void main(String[] args) {
		String command = "13 DUP 4 POP 5 DUP + DUP + -";
		Main main = new Main();
		int data = main.solution(command);
		System.out.println("result " + data);

	}

}
