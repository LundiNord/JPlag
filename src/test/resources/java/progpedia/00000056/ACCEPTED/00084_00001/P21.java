// Com ajuda de Tiago Francisco

import java.util.*;
import java.io.*;
import java.lang.*;

  class No{
	double x;
	double y;
	boolean visited;
	double dist;
    
    
	No(double x1,double y1,boolean v,double d){
	    x=x1;
	    y=y1;
	    visited=v;
	    dist=d;
	}
    
	No(double x2,double y2){
	    x=x2;
	    y=y2;
	    this.visited = false;
	    this.dist = 999999;
	}
    }

class P21 {
    public static int N;
    public static No sardas[];
    public static double pesos[][];

    public static double distancia(No a1, No a2){
	double x= (a1.x-a2.x)*(a1.x-a2.x);
	double y= (a1.y-a2.y)*(a1.y-a2.y);
	return Math.sqrt(x+y);
    }
    public static void Arvore_Custo_Minimo(int r){
	double dismin = 999999;
	int min = 0;
	sardas[r].dist = 0;
	while(true){
	    dismin=999999;
	    min=-1;
	    for(int k=1;k<=N;k++){
		if(sardas[k].dist<dismin && !sardas[k].visited){
		    min=k;
		    dismin=sardas[k].dist;
		}
	    }
	    if (min==-1) return;
	    sardas[min].visited = true;
	    for(int j=1;j<=N;j++){
		if(pesos[min][j]<sardas[j].dist  && !sardas[j].visited){
		    sardas[j].dist = pesos[min][j];
		}
	    }
	}
    }

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);	
	N=in.nextInt();
	sardas=new No[N+1];
	pesos=new double[N+1][N+1];
	double dismin=0;
	for(int i=1;i<=N;i++){
	    sardas[i] =new No(in.nextDouble(),in.nextDouble());
	}
	for(int i=1;i<=N;i++){
	    for(int j=1;j<=N;j++){
		pesos[i][j]=distancia(sardas[i],sardas[j]);
	    }
	}
	Arvore_Custo_Minimo(1);
	for(int i=1;i<=N;i++){
	    dismin=dismin+sardas[i].dist;
	}
	
	System.out.printf("%.2f\n",dismin);
    }
}
   
  




    
   
