import java.io.*;
import java.util.*;

class Node{
    double x;
    double y;
    boolean visited;
    double dis;
    Node(){
	visited=false;
	dis=1000000;
    }
    
    
    
}

public class mst{
    static int ns;
    static Node g[];
    static double custo[][];
    static double tinta;

    public static void main(String []args){
	Scanner in=new Scanner(System.in);
	ns=in.nextInt();
	g= new Node[ns+1];
	custo=new double[ns+1][ns+1];
	tinta=0;
	for(int i=1; i<=ns; i++){
	    g[i]=new Node();
	    g[i].x=in.nextDouble();
	    g[i].y=in.nextDouble();
	}
	
	for(int i=1; i<=ns; i++)
	    for(int j=1;j<=ns; j++){
		custo[i][j]=distance(g[i], g[j]);
	    }
	
	prim(1);
	
	for(int i=1; i<=ns; i++)
	    tinta+=g[i].dis;

	System.out.printf("%.2f", tinta);
	
	
	
    }


    static double distance(Node p, Node q){
	double s1=p.x-q.x;
	double s2=p.y-q.y;
	s1=s1*s1;
	s2=s2*s2;
	return Math.sqrt(s1+s2);
	
    }

    static void prim(int v){
	
	int no_m=0;
	double dis_m;
	g[v].dis=0;
	boolean flag=true;
	while(true){
	    no_m=-1;
	    dis_m=1000000;

	    for(int i=1; i<=ns; i++)
		if(g[i].dis<dis_m && !g[i].visited){
		    no_m=i;
		    dis_m=g[i].dis;
		}
	    
	    if(no_m==-1)
		return;
	    
	    g[no_m].visited=true;
	    
	    for(int j=1; j<=ns; j++)
		if(custo[no_m][j]<g[j].dis && !g[j].visited)
		    g[j].dis=custo[no_m][j];
	    
	    
	    
	    
	}
	
	
	
	
    }




}

