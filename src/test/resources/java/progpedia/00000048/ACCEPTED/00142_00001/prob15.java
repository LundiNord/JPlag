import java.util.*;
import java.io.*;

public class prob15{
	static int verts;
    static int matrix[][];
    static int visited[];

    static void dfs(int v){
    	visited[v] = 1;
    	for(int i=1; i<=verts; i++){
    		if(matrix[v][i]==1 && visited[i]==0){
    			dfs(i);
    		}
    	}
    }
    
    public static void main(String args[]){
    	int aux = 0;
		Scanner in = new Scanner(System.in);
		
		verts = in.nextInt();
		int connections = in.nextInt();
		matrix = new int[verts+1][verts+1];
		visited = new int[verts+1];
		

		for(int i=0; i<connections; i++){
			int temp1 = in.nextInt();
		    int temp2 = in.nextInt();
		    matrix[temp1][temp2] = matrix[temp2][temp1] = 1;
		}

		for(int i=1; i<=verts; i++){
			if(visited[i]==0) {
				aux++;
				dfs(i);
			}
		}

		if(connections==0){
			System.out.println(verts); 
		}
		else{System.out.println(aux);}
		 

    }
}