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
    static Point[] pontos;
    static double[][] pesos;
    static double[][] dist;
    static int np;
    static boolean[] visited;

    static void prim (int r) {
	for (int i = 0; i < np; i++) {
	    dist[r][i] = 999999;
	}
	dist[r][r] = 0;
	int vis = 1;
	while (vis != np) {
	    /* extrair-minimo */
	    int u = -1;
	    double distmax = Double.MAX_VALUE;
	    for (int i = 0; i < np; i++) {
		if (dist[r][i] < distmax && !visited[i]) {
		    distmax = dist[r][i];
		    u = i;
		}
	    }
	    if (u == -1) break;
	    visited[u] = true;
	    vis++;
	    /* end extrair-minimo */
	    for (int i = 0; i < np; i++) {
		if (!visited[i] && pesos[u][i] < dist[r][i]) {
		    dist[r][i] = pesos[u][i];
		}
	    }
	}
    }

    public static void main (String[] args) {
	Scanner stdin = new Scanner (System.in);
        np = stdin.nextInt();
	stdin.nextLine();
	pontos = new Point[np];
	pesos = new double[np][np];
	dist = new double[np][np];
	visited = new boolean[np];
	for (int i = 0; i < np; i++) {
	    String line = stdin.nextLine();
	    String[] nums = line.split(" ");
	    pontos[i] = new Point(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]));
	}
	for (int i = 0; i < np; i++) {
	    for (int j = 0; j < np; j++) {
		pesos[i][j] = Math.sqrt((pontos[j].x - pontos[i].x) * (pontos[j].x - pontos[i].x) + (pontos[j].y - pontos[i].y) * (pontos[j].y - pontos[i].y));
	    }
	}
	prim(0);
	double sum = 0;
	for (int i = 0; i < np; i++) {
	    sum += dist[0][i];
	}
	System.out.printf("%.2f\n", sum);
    }
}
