import java.io.*;
import java.util.*;

public class prat16 {
    static int nv, ne;
    static ArrayList<LinkedList<Integer>> grafo = new ArrayList<LinkedList<Integer>>();
    static boolean[] visited;
    
    static void dfs (int node) {
	visited[node] = true;
	for (int i : grafo.get(node)) {
	    if (!visited[i]) dfs(i);
	}
    }

    public static void main (String[] args) {
	Scanner stdin = new Scanner (System.in);
	nv = stdin.nextInt()+1;
	ne = stdin.nextInt();
	visited = new boolean[nv];
	for (int i = 0; i < nv; i++) {
	    grafo.add(new LinkedList<Integer>());
	}
	for (int i = 0; i < ne; i++) {
	    int a,b;
	    a = stdin.nextInt();
	    b = stdin.nextInt();
	    grafo.get(a).add(b);
	    grafo.get(b).add(a);
	}
	int conexos = 0;
	for (int i = 1; i < nv; i++) {
	    if (!visited[i]) {
		dfs(i);
		conexos++;
	    }
	}
	System.out.println(conexos);
    }
}
