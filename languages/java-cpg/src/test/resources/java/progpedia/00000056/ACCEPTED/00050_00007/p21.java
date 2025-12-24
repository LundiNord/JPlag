import java.io.*;
import java.util.*;

class Node{
    public boolean visited;
    public double x;
    public double y;
    public double distance;
    
    Node(double x, double y, boolean visited, double distance){
	this.x= x;
	this.y = y;
	this.visited= visited;
	this.distance= distance;
    }
    Node(double x, double y){
	this.x = x;
	this.y = y;
	this.visited = false;
	this.distance = 99999;
    
    }
    
}



public class p21{
    static int n;
    static Node g[];
    static double matriz [][];
    
    static double distancia(Node a, Node b){
	double p1= (a.x-b.x);
	double p2= (a.y-b.y);
	p1=p1*p1;
	p2=p2*p2;
	return Math.sqrt((p1+p2));
    }
    
   static void Prim(int no){
	double minimum=99999;
	int nm=0;

	g[no].distance=0;

	while(true){
	    nm=-1;
	    minimum= 10000;
	    for(int i=1;i<=n;i++)
		if(g[i].distance< minimum && !g[i].visited){
		    minimum = g[i].distance;
		    nm= i;
		}
	    if(nm==-1)
		return;

	    g[nm].visited=true;
	    
	    for(int j=1;j<=n;j++)
		if(matriz[nm][j]<g[j].distance && !g[j].visited)
		    g[j].distance= matriz[nm][j];
	}
	
	
   }


    public static  void main(String args []){
	Scanner stdin = new Scanner (System.in);
        n = stdin.nextInt();
	g= new Node[n+1];
	double ink=0;
	
	for(int i=1;i<=n;i++)
	    g[i]=new Node(stdin.nextDouble(),stdin.nextDouble());
	   
        

	matriz = new double[n+1][n+1];


	for(int i=1;i<=n;i++)
	    for(int j=1;j<=n;j++)
		matriz[i][j]=distancia(g[i], g[j]);

	Prim(1);
	

	for(int i=1;i<=n;i++)
	    ink = ink + g[i].distance;
	
	System.out.printf("%.2f\n",ink);
	
	
    }
}
