package com.sqli.run.ninja;

import java.util.*;

public class Ninja {
    
    private boolean knowSwimming = false;
    private int posNinja = 0;
    private MineField mineField;
    
    private static final int LIMIT_JUMP = 4;
    
    

	public void startIn(MineField mineField) {
        this.mineField = mineField;
        mineField.setPosNinja(posNinja);
    }

    public void cross() throws CannotPassException{
        
        this.posNinja = mineField.getPosNinja();
        List<String> path = mineField.getMineField();
        
        String element = path.get(this.posNinja); 
        
        //ITS A MINE?
        if (element.indexOf(MineFieldBuilder.MINE)!=-1){
            if (element.length() < LIMIT_JUMP){
                this.posNinja = this.posNinja +1;
                mineField.setPosNinja(this.posNinja);
            }else{
                throw new CannotPassException();
            }
            
        }
        //ITS A RIVER?
        else if (element.indexOf(MineFieldBuilder.RIVER)!=-1){
            if (knowSwimming){
                this.posNinja = this.posNinja+1;
                mineField.setPosNinja(this.posNinja);
            }else{
                throw new CannotPassException();
            }
        }
        
        
    }

    public void learnSwimming() {
        knowSwimming = true;
    }

}