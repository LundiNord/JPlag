//Realizado em conjunto com Rui Carvalho, consultando site do Visualgo para ajuda


import java.util.*;
import java.io.*;

//Classe para o no, com a posicao x, posicao y, e o no em questao
class No{
    double x;
    double y;
    int   no;

    public No(double x, double y, int no){
	this.x  = x;
	this.y  = y;
	this.no = no;
    }
}
//Classe para a aresta, com o no a, no b e a distancia a que estao (dis)
class Edge implements Comparable<Edge> {
    No       a;
    No       b;
    double dis;

    public Edge(No a, No b, double dis){
	this.a   = a;
	this.b   = b;
	this.dis = dis;
    }
    
    @Override
    public int compareTo(Edge e) {       //ordenacao das arestas conforme o seu peso/distancia
	if(dis > e.dis)	    return 1;
	else                return -1;
    }	
}
class UnionFind{
    int[] p;	  
    int[] rank;
    //    int[] setSize;
    int numSets;   

    public UnionFind(int n) {		
	//setSize = new int[n];
	p       = new int[n];
	rank    = new int[n];
	numSets = n;
	
	for(int i=0; i<n;i++) {
	    // setSize[i] = 1; 
	    rank[i]    = 0;
	    p[i]       = i;   }
	
    }
    
	
    public int findSet(int i){ 
	if(p[i] == i) return i; 
	else return findSet(p[i]); }

    
    
    public boolean isSameSet(int i, int j){ 
	return ( findSet(i)==findSet(j) );}



    
    public void unionSet(int i, int j){ 
	if(!isSameSet(i,j)){  
	    numSets--;
	    int x = findSet(i);
	    int y = findSet(j);
	    

	    if(rank[x] > rank[y]){
		p[y]        = x;
		//setSize[x] += setSize[y];
	    }
	    else{
		p[x]        = y;
		//setSize[y] += setSize[x];
		if(rank[x] == rank[y]) rank[y]++; }
	}
    }
}


class Kruskall{
    static LinkedList<Edge> edge = new LinkedList<Edge>();
    public static void Distancias(int n, No[] v){
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		
		double p1=(v[j].x-v[i].x)*(v[j].x-v[i].x);
		double p2=(v[j].y-v[i].y)*(v[j].y-v[i].y);
		double res = Math.sqrt((p1+p2));

		Edge e = new Edge(v[i],v[j],res);
		edge.addLast(e);
	    }
	}

	Collections.sort(edge);
    }

    public static double FindingLower(int n){
	double lowercost = 0;
	UnionFind union = new UnionFind(n);
	for (int i=0; i<edge.size(); i++) {
	    Edge aux = edge.removeFirst();
	    if(!union.isSameSet(aux.a.no,aux.b.no))
		lowercost += aux.dis;
	    union.unionSet(aux.a.no, aux.b.no); 
	}
	return lowercost;
    }
}

class ex21{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int      n = in.nextInt();
	No vetor []= new No[n];

	for(int i=0;i<n;i++) {
	    vetor[i] = new No(in.nextDouble(),in.nextDouble(), i); 
	}
		
	Kruskall.Distancias(n,vetor);
	double low = Kruskall.FindingLower(n);
	System.out.printf("%.2f\n" , low);
    }
}

