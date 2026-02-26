//Joana Silva up200908475

import java.util.*;
import java.io.*;

public class prob15{
	static int ver;
    static int matrix[][];
    static int visited[];

    static void dfs(int v){
    	visited[v] = 1;
    	for(int i=1; i<=ver; i++){
    		if(matrix[v][i]==1 && visited[i]==0){
    			dfs(i);
    		}
    	}
    }
    
    public static void main(String args[]){
    		int aux = 0;
		Scanner in = new Scanner(System.in);
		
		ver = in.nextInt();
		int links = in.nextInt();
		matrix = new int[ver+1][ver+1];
		visited = new int[ver+1];
		

		for(int i=0; i<links; i++){
		    int a = in.nextInt();
		    int b = in.nextInt();
		    matrix[a][b] = matrix[b][a] = 1;
		}

		for(int i=1; i<=ver; i++){
			if(visited[i]==0) {
				aux++;
				dfs(i);
			}
		}

		if(links==0){
			System.out.println(ver); 
		}
		else{System.out.println(aux);}
		 

    }
}
