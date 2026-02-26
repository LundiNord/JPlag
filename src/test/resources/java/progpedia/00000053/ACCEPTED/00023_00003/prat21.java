import java.util.*;
import java.io.*;

class Queue {
    int queue[];
    int size;
    int first;
    int last;
    int cap;

    Queue (int c) {
	cap = c;
	queue = new int[c];
	size = first = last = 0;
    }

    boolean isEmpty() {
	return (size == 0);
    }

    int remove() {
	int r = queue[first];
	first = (first+1) % cap;
	size--;
	return r;
    }

    void add (int node) {
	queue[last] = node;
	last = (last+1) % cap;
	size++;
    }
}

public class prat21 {
    static ArrayList<LinkedList<Integer>> grafo = new ArrayList<LinkedList<Integer>>();
    static boolean[] visited;
    static int[][] dist;
    static int nv,ne;
    static int diametro = 0;
    
    static int excentricidade (int node) {
	int max = 0;
	for (int i = 0; i < nv+1; i++) {
	    if (dist[node][i] > max) max = dist[node][i];
	}
	return max;
    }

    static void bfs (int node) {
	Queue q = new Queue(nv+1);
	q.add(node);
	dist[node][node] = 0;
	visited[node] = true;
	while (!q.isEmpty()) {
	    int u = q.remove();
	    for(int x : grafo.get(u)) {
		if (!visited[x]) {
		    q.add(x);
		    visited[x] = true;
		    dist[node][x] = dist[x][node] = dist[node][u] + 1; 
		    if (dist[node][x] > diametro) diametro = dist[node][x];
		}
	    }
	}
    }
    
    public static void main (String[] args) {
	Scanner stdin = new Scanner (System.in);
        nv = stdin.nextInt();
	ne = stdin.nextInt();
	for (int i = 0; i < nv+1; i++) {
	    grafo.add(new LinkedList<Integer>());
	}
	dist = new int[nv+1][nv+1];
	visited = new boolean[nv+1];
	for (int i = 0; i < ne; i++) {
	    int a,b;
	    a = stdin.nextInt();
	    b = stdin.nextInt();
	    grafo.get(a).add(b);
	    grafo.get(b).add(a);
	}
	/*	for (int i = 1; i < nv+1; i++) {
	    System.out.print(i + ": ");
	    for(int x : grafo.get(i))
		{
		    System.out.print(x + " ");
		}
	    System.out.println();
	    }*/
	for (int i = 1; i < nv+1; i++) {
	    Arrays.fill(visited,false);
	    bfs(i);
	}
	/*for (int i = 0; i < nv+1; i++) {
	    System.out.println(Arrays.toString(dist[i]));
	    }*/
	System.out.println(diametro);
	int raio = Integer.MAX_VALUE;
	for (int i = 0; i < nv+1; i++) {
	    if (excentricidade(i) < raio && excentricidade(i) != 0) 
		raio = excentricidade(i);
	}
	System.out.println(raio);
	int conta = 0;
	for (int i = 0; i < nv+1; i++) {
	    if (excentricidade(i) == raio) { 
		if (conta != 0) System.out.print(" ");
		System.out.print(i);
		conta++;
	    }
	}
	System.out.println();
	conta = 0;
	for (int i = 0; i < nv+1; i++) {
	    if (excentricidade(i) == diametro) { 
		if (conta != 0) System.out.print(" ");
		System.out.print(i);
		conta++;
	    }  
	}
	System.out.println();		
    }
}
