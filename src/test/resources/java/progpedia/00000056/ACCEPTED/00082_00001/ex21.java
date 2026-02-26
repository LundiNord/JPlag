//com ajuda de Tiago Francisco
import java.lang.*;
import java.io.*;
import java.util.*;

class ex21{
    
    static int n_sardas;
    static Node[] sardas;
    static double[][] pesos; // matriz que para o nó i nos diz a distância ao nó j;
    
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	
	n_sardas = stdin.nextInt();
	sardas = new Node[n_sardas + 1];
	pesos = new double[n_sardas + 1][n_sardas + 1];
	double distancia_minima = 0;
	
	for(int i = 1; i <= n_sardas; i++)
	    sardas[i] = new Node(stdin.nextDouble(),stdin.nextDouble());
	
	
	for(int i = 1; i <= n_sardas; i++)
	    for(int j = 1; j <= n_sardas; j++)
		pesos[i][j] = calc_dist(sardas[i],sardas[j]);
	
	MST_Prim(1);
	
	for(int i = 1; i <= n_sardas; i++){
	    distancia_minima += sardas[i].dist;
	}
	
	System.out.printf("%.2f\n",distancia_minima);
    }
    
    static double calc_dist(Node a, Node b){
	double x_dist = (a.x - b.x) * (a.x - b.x);
	double y_dist = (a.y - b.y) * (a.y - b.y);
	return Math.sqrt((x_dist + y_dist));
    }
    
    static void MST_Prim(int r){
	double distancia_min = 999999;
	int no_min = 0;
	int o;
	int d;
	
	sardas[r].dist = 0;
	
	while(true){
	    distancia_min = 999999;
	    no_min = -1;
	    for(o = 1; o <= n_sardas; o++){
		if(sardas[o].dist < distancia_min && !sardas[o].visited){
		    no_min = o;
		    distancia_min = sardas[o].dist;
		}
	    }
	    if (no_min == -1) return;
	    sardas[no_min].visited = true;

	    for(d = 1; d <= n_sardas; d++){
		if(pesos[no_min][d] < sardas[d].dist  && !sardas[d].visited){
		    sardas[d].dist = pesos[no_min][d];
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
	this.dist = 999999;
    }
}
    

    
	
	   
