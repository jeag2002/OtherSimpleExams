package es.snake.engine.ia;

import es.snake.beans.Table;

public class HamiltonianCicle {
	
	public int x;
	public int y;
	
	public int vertex;
	
	public int path[];
	
	private static final int[] path_4x4 = {0,1,2,3,7,6,5,9,10,11,15,14,13,12,8,4}; 
	private static final int[] path_6x6 = {0,1,2,3,4,5,11,10,9,8,7,13,14,15,16,17,23,22,21,20,19,25,26,27,28,29,35,34,33,32,31,30,24,18,12,6};
	private static final int[] path_8x8 = {0,1,2,3,4,5,6,7,15,14,13,12,11,10,9,17,18,19,20,21,22,23,31,30,29,28,27,26,25,33,34,35,36,37,38,39,47,46,45,44,43,42,41,49,50,51,52,53,54,55,63,62,61,60,59,58,57,56,48,40,32,24,16,8}; 
	private static final int[] path_10x10 = {
			0,1,2,3,4,5,6,7,8,9,
			19,18,17,16,15,14,13,12,11,
			21,22,23,24,25,26,27,28,29,
			39,38,37,36,35,34,33,32,31,
			41,42,43,44,45,46,47,48,49,
			59,58,57,56,55,54,53,52,51,
			61,62,63,64,65,66,67,68,69,
			79,78,77,76,75,74,73,72,71,
			81,82,83,84,85,86,87,88,89,
			99,98,97,96,95,94,93,92,91,
			90,80,70,60,50,40,30,20,10
	};
	
	public Table table;
	
	public HamiltonianCicle(int _x, int _y) {
		x = _x;
		y = _y;
		
		vertex = x *y;
		
		path = new int[vertex];
		
		table = new Table(vertex,vertex);
		generateGrid();
		
		printGridDebug();
	}
	
	
	public int generateHamiltonianCycle() {
		
		int res = -1;
		
		if ((x == 4) && (y == 4)) {
			path = path_4x4;
			res = 1;
		}else if ((x == 6) && (y == 6)) {
			path = path_6x6;
			res = 1;
		}else if ((x == 8) && (y== 8)) {
			path = path_8x8;
			res = 1;
		}else if ((x == 10) && (y == 10)) {
			path = path_10x10;
			res = 1;
		}else {
			res = hamCycle(table.getTable());
		}
		return res;
	}
	
	
    public int[] getHamiltonianCycle() {
    	return path;
    }
	
	private void generateGrid() {
		
		for (int i=0; i<vertex; i++) {
			if ((i+1) < vertex) {table.getTable()[i][i+1] = 1;}
			if ((i+x) < vertex) {table.getTable()[i][i+x] = 1;}
			if ((i-1) >= 0) {table.getTable()[i][i-1] = 1;}
			if ((i-x) >= 0) {table.getTable()[i][i-x] = 1;}
		}
	}
	
	
	private boolean isSafe(int v, int graph[][], int path[], int pos) {

        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }
 
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
 
        return true;
    }
	
	private void printGridDebug() {
		
		System.out.println("*******************************************");
		
		for(int i=0; i<vertex; i++) {
			for(int j=0; j<vertex; j++) {
				System.out.print(table.getTable()[i][j]);
			}
			System.out.print("\n");
		}
		
		System.out.println("*******************************************");
	}
	
	public void printHamCycle() {
		System.out.println("*******************************************");
		
		for(int i=0; i < path.length; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.print("\n");
		
		System.out.println("*******************************************");
	}
	
    private int hamCycle(int graph[][]) {
       
        for (int i = 0; i < vertex; i++) {
            path[i] = -1;
        }
 
        path[0] = 0;
        
        if (hamCycleUtil(graph, path, 1) == false) {
            return 0;
        }
 
        return 1;
    }
	
    private boolean hamCycleUtil(int graph[][], int path[], int pos)
    {
       
        if (pos == vertex) {
            if (graph[path[pos - 1]][path[0]] == 1) {
                return true;
            } else {
                return false;
            }
        }
 
      
        for (int v = 1; v < vertex; v++)  {
        	
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
 
                if (hamCycleUtil(graph, path, pos + 1) == true) {
                    return true;
                }
 
                path[pos] = -1;
            }
        }
        return false;
    }

}
