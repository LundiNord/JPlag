import java.lang.*;
import java.io.*;
import java.util.*;

class Prob21{
    
    static int n_pontos;
    static Node[] pontos;
    static double[][] dist; // matriz que para o nó i nos diz a distância ao nó j;
    
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	
	n_pontos = stdin.nextInt();
	pontos = new Node[n_pontos + 1];
	dist = new double[n_pontos + 1][n_pontos + 1];
	double distancia_minima = 0;
	
	for(int i = 1; i <= n_pontos; i++)
	    pontos[i] = new Node(stdin.nextDouble(),stdin.nextDouble());
	
	
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
    
    static void MST_Prim(int r){
	double dist_min = 1500;
	int no_min = 0;
	int o;
	int d;
	
	pontos[r].dist = 0;
	
	while(true){
	    dist_min = 1500;
	    no_min = -1;
	    for(o = 1; o <= n_pontos; o++){
		if(pontos[o].dist < dist_min && !pontos[o].visited){
		    no_min = o;
		    dist_min = pontos[o].dist;
		}
	    }
	    if (no_min == -1) return;
	    
	    pontos[no_min].visited = true;
	    
	    for(d = 1; d <= n_pontos; d++){
		if(dist[no_min][d] < pontos[d].dist  && !pontos[d].visited){
		    pontos[d].dist = dist[no_min][d];
		}
	    }
	}
    }
}

class Node{
    double x;
    double y;
    boolean visited;
    double dist;
    
    
    Node(double x,double y, boolean v, double d){
	this.x = x;
	this.y = y;
	this.visited = v;
	this.dist = d;
    }
    
    Node(double x,double y){
	this.x =   x;
	this.y =   y;
	this.visited = false;
	this.dist = 1500;
    }
}
    

    
	
	   
