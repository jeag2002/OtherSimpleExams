package com.sqli.run.ninja;


	import java.util.*;

	public class MineField {
	    
	    private List<String> mines = new ArrayList<String>();
	    
	    private static final String NINJA = "P";
	    private static final String LEVEL_COMPLETE = "Level completed";
	    
	    private int pos_ninja = -1;
	    
	    public MineField(List<String> _mines){
	        this.mines = _mines;
	    }
	    
	    public List<String> getMineField(){
	        return this.mines;
	    }
	    
	    public int getPosNinja(){return pos_ninja;}
	    
	    public void setPosNinja(int newPosNinja){pos_ninja = newPosNinja;}
	    
	    public String print() {
	        
	        String path = "";
	        for(int i=0; i<mines.size(); i++){
	            if (pos_ninja == i){
	                path += NINJA;
	            }
	            path += "-"+mines.get(i)+"-";
	        }
	        
	        if (pos_ninja >= mines.size()){
	            path = LEVEL_COMPLETE;
	        }
	        
	        return path;
	    }

	}

