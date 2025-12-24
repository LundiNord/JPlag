import java.lang.*;
import java.io.*;
import java.util.*;

class Prob21Improved{
    
    static int n_pontos;
    static Node[] pontos;
    static double[][] dist;
    
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	
	n_pontos = stdin.nextInt();
	pontos = new Node[n_pontos + 1];
	dist = new double[n_pontos + 1][n_pontos + 1];
	double distancia_minima = 0;
	
	for(int i = 1; i <= n_pontos; i++)
	    pontos[i] = new Node(stdin.nextDouble(),stdin.nextDouble(),i);
	
	
	for(int i = 1; i <= n_pontos; i++)
	    for(int j = 1; j <= n_pontos; j++)
		dist[i][j] = calc_dist(pontos[i],pontos[j]);
	
	MST_Prim(1);
	
	
	for(int i = 1; i <= n_pontos; i++){
	    distancia_minima += pontos[i].dist;
	}
	
	System.out.printf("%.2f\n",distancia_minima);
    }
    
    static double calc_dist(Node a, Node b){
	double x_dist = (a.x - b.x) * (a.x - b.x);
	double y_dist = (a.y - b.y) * (a.y - b.y);
	return Math.sqrt((x_dist + y_dist));
    }
    
    static void MST_Prim(int c){
	double dist_min = 1500;
	int o;
	int d;
	PriorityQueue<Par> q  = new PriorityQueue<Par>();
	
	pontos[c].dist = 0;
	
	for(int i = 1; i < n_pontos; i++){
	    q.add(new Par(pontos[i].index,pontos[i].dist));
	}
	
	
	
	while(q.size() != 0){
	    
	    Par current = q.poll();
	    pontos[current.index].visited = true;
	    for(d = 1; d <= n_pontos; d++)
		if(dist[current.index][d] < pontos[d].dist  && !pontos[d].visited)
		    pontos[d].dist = dist[current.index][d];
	}
    }
}



class Par implements Comparable<Par> {
    
    int index;
    double dist;

    Par(int index, double distancia){
	this.index = index;
	this.dist = distancia;
    }
	
    
    public int compareTo(Par a){
	return (int)(this.dist - a.dist);
    }
}

class Node{
    
    double x;
    double y;
    boolean visited;
    double dist;
    int index;
    
    
    Node(double x,double y, boolean v, double d, int i){
	this.x = x;
	this.y = y;
	this.visited = v;
	this.dist = d;
	this.index = i;
    }
    
    Node(double x,double y, int i){
	this.x =   x;
	this.y =   y;
	this.visited = false;
	this.dist = 1500;
	this.index = i;
    }

    Node(Node a){
	this.x =   a.x;
	this.y =   a.y;
	this.visited = a.visited;
	this.dist = a.dist;
	this.index = a.index;
    }
}
