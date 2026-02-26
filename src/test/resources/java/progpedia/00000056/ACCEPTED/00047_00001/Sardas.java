import java.util.*;
import java.io.*;

class Sarda{
    double x;
    double y;
    int n;

    public Sarda(double x, double y, int n){
	this.x = x;
	this.y = y;
	this.n = n;
    }
}

//class para as arestas com dois nos a e b e a distancia entre eles 
class Edge implements Comparable<Edge>{
    Sarda a;
    Sarda b;
    double dist;
    
    //Construtor das arestas
    Edge(Sarda a, Sarda b, double dist){
	this.a    = a;
	this.b    = b;
	this.dist = dist;
    }

    //Implementaçao do comparable para ordenar as arestas da menor para a maior distancia
    @Override
	public int compareTo(Edge e){
	if (dist > e.dist)
	    return 1;
	else 
	    return -1;
    }
}

class Kruskall{
    static LinkedList<Edge> EdgeList = new LinkedList<Edge>();

    //Formar todas a arestas possiveis calculando a distancia entre os dois nos de cada aresta
    public static void setEdges(int N, Sarda[] sarda){
	
	for(int i = 0; i < N ; i++){
	    for(int j = i+1; j < N ; j++){
		double dist1 = (sarda[i].x-sarda[j].x)*(sarda[i].x-sarda[j].x);
		double dist2 = (sarda[i].y-sarda[j].y)*(sarda[i].y-sarda[j].y);
		double dist  = Math.sqrt(dist1+dist2);

		Edge e = new Edge(sarda[i], sarda[j], dist);
		
		EdgeList.addLast(e);
	    }
	}

	//Depois de formar todas as arestas vai ordenalas por ordem crescente da disancia
	Collections.sort(EdgeList);
    }

    public static double kruskall(int N){
	double custo_minimo = 0;

	UnionFind union = new UnionFind(N);

	for(int i = 0; i < EdgeList.size() ; i++){
	    Edge removed_edge = EdgeList.removeFirst();

	    if(!union.isSameSet(removed_edge.a.n, removed_edge.b.n))
		custo_minimo += removed_edge.dist;

	    //Juntar as duas  arvores (podem ser apenas nos)
	    union.unionSet(removed_edge.a.n, removed_edge.b.n);
	}
	
	return custo_minimo;

    }
}

class UnionFind{
    int[] p;
    int[] rank;
    int numSets;

    //Inicializar as variaveis
    public UnionFind(int N){
        p       = new int[N];
	rank    = new int[N];
	numSets = N;

	for(int i = 0; i < N; i++){
	    rank[i] = 0;
	    p[i] = i;
	}
    }

    public boolean isSameSet(int i, int j){
	return ( findSet(i) == findSet(j) );
    }

    public int findSet(int i){
	if (p[i] == i)
	    return i;
	else
	    return findSet(p[i]);
    }

    //unir duas arvores disjuntas
    public void unionSet(int i, int j){
	//SE estao em arvores diferentes
	if(!isSameSet(i,j)){
	    numSets--;
	    int x = findSet(i);
	    int y = findSet(j);
	    if(rank[x] > rank[y]){
		p[y] = x;
	    }
	    else{
		p[x] = y;
		if(rank[x] == rank[y])
		    rank[y] ++;
	    }
	}
    }
}   
    


class Sardas{
    public static void main(String[] args){
	
	Scanner in = new Scanner(System.in);
	
	int N = in.nextInt();
	Sarda[] sarda = new Sarda[N];
	
	//Colocar no vetor sarda todas as sardas dadas pelo input
	for(int i = 0; i < N ; i++)
	    sarda[i] = new Sarda(in.nextDouble(), in.nextDouble(), i);
	//formar todas as arestas
	Kruskall.setEdges(N, sarda);
	double distancia_minima = Kruskall.kruskall(N);
	System.out.printf("%.2f\n", distancia_minima);
    }
}

