import java.io.*;
import java.util.*;

class Node{
    public boolean visited;
    public double x;
    public double y;
    public double distance;
    
    Node(){
	visited= false;
	distance= 10000;
    }
    
}



public class p21{
    static int n;
    static Node g[];
    static double matriz [][];
    
    static double distancia(Node a, Node b){
	double p1= a.x-b.x;
	double p2= a.y-b.y;
	p1=p1*p1;
	p2=p2*p2;
	return Math.sqrt(p1+p2);
    }
    
   static void Prim(int no){
	double minimum=1000000;
	
	g[no].distance=0;

	while(true){
	    int nm=0;
	    for(int i=1;i<=n;i++)
		if(g[i].distance< minimum){
		    minimum = g[i].distance;
		    nm= i;
		}
	    if(nm==0)
		return;

	    g[nm].visited=true;
	    
	    for(int i=1;i<=n;i++)
		if(matriz[nm][i]<g[i].distance && !g[i].visited)
		    g[i].distance= matriz[nm][i];
	}
	
	
   }


    public static  void main(String args []){
	Scanner stdin = new Scanner (System.in);
        n = stdin.nextInt();
	g= new Node[n+1];
	double ink=0;
	
	for(int i=1;i<=n;i++){
	    g[i]=new Node();
	    g[i].x= stdin.nextDouble();
	    g[i].y = stdin.nextDouble();
        }
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
