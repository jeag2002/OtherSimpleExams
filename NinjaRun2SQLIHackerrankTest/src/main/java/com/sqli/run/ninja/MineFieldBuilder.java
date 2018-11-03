package com.sqli.run.ninja;

import java.util.*;

public class MineFieldBuilder {
    
    
    public static final String MINE = "o";
    public static final String RIVER = "~~~";
    
    private List<String> path = new ArrayList<String>();
    

    public MineFieldBuilder addMines(int size) {
        
        String mine = "";
        for(int i=0; i<size; i++){mine+=MINE;}
        path.add(mine);
        
        return this;
    }

    public MineFieldBuilder addRiver() {
        // TODO
        path.add(RIVER);
        return this;
    }

    public MineField build() {
        MineField mine = new MineField(path);
        return mine;
    }

}