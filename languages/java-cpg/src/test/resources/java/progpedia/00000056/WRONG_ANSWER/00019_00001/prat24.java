import java.io.*;
import java.util.*;

class Queue {
    Point queue[];
    int size;
    int first;
    int last;
    int cap;

    Queue(int c) {
	cap = c;
	queue = new Point[c];
	size = first = last = 0;
    }

    boolean isEmpty() {
	return (size == 0);
    }

    Point remove() {
	Point r = queue[first];
	first = (first + 1) % cap;
	size--;
	return r;
    }

    void add(Point node) {
	queue[last] = node;
	last = (last + 1) % cap;
	size++;
    }

    boolean exists (Point p) { // O(n)
	for (int i = 0; i < size; i++) {
	    if (queue[i].x == p.x && queue[i].y == p.y) return true;
	}
	return false;
    }
}

class Point {
    double x;
    double y;
    
    Point (double a, double b) {
	x = a;
	y = b;
    }
}

public class prat24 {
    static Queue q;
    static int nv;
    static Point[] pontos;
    static double[][] dist;
    static double inf = 999999;
    static boolean[] tratado;

    static void prim (int r) {
	for (int v = 0; v < nv; v++) {
	    dist[r][v] = inf;
	}
	dist[r][r] = 0;
	for (int j = 0; j < nv; j++) {
	     q.add(pontos[j]);
	 }
	int f = 0;
	while (f != nv) {
	    /* extrair-minimo */
	    double d = Double.MAX_VALUE;
	    int u = -1;
	    for (int i = 0; i < nv; i++) {
		if (dist[r][i] < d && !tratado[i]) {
		    d = dist[r][i];
		    u = i;
		}
	    }
	    if (u == -1) break;
	    f++;
	    tratado[u] = true;
	    /* end extrair-minimo */ 
	    for (int v = 0; v < nv; v++) {
		if (q.exists(pontos[v]) && calc(pontos[u], pontos[v]) < dist[r][u]) {
		    dist[r][v] = calc(pontos[u], pontos[v]);
		}
	    }
	}
    }

    static double calc (Point p1, Point p2) {
	return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void main (String[] args) {
	Scanner stdin = new Scanner(System.in);
	nv = stdin.nextInt();
	pontos = new Point[nv];
	dist = new double[nv][nv];
	tratado = new boolean[nv];
	q = new Queue(nv);
	stdin.nextLine();
	for (int i = 0; i < nv; i++) {
	    String xy = stdin.nextLine();
	    String[] coords = xy.split(" ");
	    double x = Double.parseDouble(coords[0]);
	    double y = Double.parseDouble(coords[1]);
	    pontos[i] = new Point(x,y);
	}
	for (int i = 0; i < nv; i++) {	   
	    prim(i);
	}
	for (int i = 0; i < nv; i++) {
	    for (int j = 0; j < nv; j++) {
		System.out.print(dist[i][j] + " ");
	    }
	    System.out.println();
	}
   }
}
