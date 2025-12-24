//Pesquisa em profundidade - Grafo não dirigido

import java.util.*;
import java.io.*;

public class Circuitos {

    static int n;                //Nós no grafo
    static boolean madj[][];     //Matriz adjacências
    static boolean nodeV[];    //Nós visitados
    static int conex = 0;

    static void DFS (int node) {

	nodeV[node] = true;
	for (int i = 1; i <= n; i++) {
	    if (madj[node][i] && !nodeV[i]) {
		DFS(i);
	    }
	}
    }

    static int GrafC() {

	for (int i = 1; i<= n; i++)
	    nodeV[i] = false;
	for (int i = 1; i <= n; i++) {
	    if (!nodeV[i]) {
		conex++;
		DFS(i);
	    }
	}
	return conex;
    }

    public static void main (String args[]) {

	Scanner in = new Scanner(System.in);

	n = in.nextInt();
	madj = new boolean[n+1][n+1];
	nodeV = new boolean [n+1];

	int l = in.nextInt();
	for (int i = 0; i < l; i++) {
	    int x = in.nextInt();
	    int y = in.nextInt();
	    madj[x][y] = madj[y][x] = true;
	}
	System.out.println(GrafC());
    }
}
