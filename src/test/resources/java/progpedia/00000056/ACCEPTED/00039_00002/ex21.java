import java.util.*;
import java.io.*;

class Sarda{
    float x;
    float y;
    int index;
    Sarda(float x, float y, int index){
	this.x = x;
	this.y = y;
	this.index = index;
    }
}

class Edge implements Comparable<Edge>{
    Sarda a;
    Sarda b;
    double weight;
    Edge(Sarda a, Sarda b, double weight){
	this.a = a;
	this.b = b;
	this.weight = weight;
    }

    @Override
    public int compareTo(Edge n){

	if(weight > n.weight)
	    return 1;
	else
	    return -1;
    }
	
}

class UnionFind{

    int[] pai, rank; //node x -> posicao x de casa array
    int n_edges;

    public UnionFind(int n_edges){

	pai = new int[n_edges];
	rank = new int[n_edges];
	this.n_edges = n_edges;

	for(int i=0; i<n_edges; i++){
	    pai[i] = i;
	    rank[i] = 0; //rank da sub-árvore de um nó
	}
    }
    
    public boolean isSameSet(int x , int y){ //verificar se têm o mesmo representante
	return (findSet(x) == findSet(y));
    }
    
    public int findSet(int x){ //procurar representante do conjunto
	if(pai[x] == x)
	    return x;
	else
	    return findSet(pai[x]);
    }

    public void unionSet(int x, int y){ //unir elementos disjuntos apartir dos seus indices

	if(!isSameSet(x,y)){
	    
	    int xRaiz = findSet(x);
	    int yRaiz = findSet(y);
	    
	    if(rank[xRaiz] > rank[yRaiz])
		pai[yRaiz] = xRaiz;
	    
	    else{
		pai[xRaiz] = yRaiz;
		
		if(rank[xRaiz] == rank[yRaiz])
		    rank[yRaiz]++;
	    }
	}
	
    }
}

class Kruskal{

    static LinkedList<Edge> edges = new LinkedList<Edge>();
    
    public static void weight(int n, Sarda[] s){

	
	for(int i=0; i<n; i++)
	    for(int j=i+1; j<n; j++){
		double c1 = (s[j].x-s[i].x)*(s[j].x-s[i].x);
		double c2 = (s[j].y-s[i].y)*(s[j].y-s[i].y);
		double weight = Math.sqrt(c1+c2);

		Edge add = new Edge(s[i], s[j], weight);
		edges.addLast(add);
		
	    }
	Collections.sort(edges);	
    }
    
    public static void kruskal(int n_edges){

	UnionFind makeUnion = new UnionFind(n_edges);
	double mst_cost = 0;
	
	for(int i=0; i<edges.size(); i++){
	    Edge testEdge = edges.removeFirst(); //analisar a primeira aresta do array

	    /*implementar funções:
	           isSameSet - verificar se as arestas já pertencem a mm arvore
		   findSet - auxiliar da funcao isSameSet
		   unionSet - unir arvores caso sejam disjuntas
	    */
	    
	    if(!makeUnion.isSameSet(testEdge.a.index, testEdge.b.index))
		mst_cost += testEdge.weight;
	    makeUnion.unionSet(testEdge.a.index, testEdge.b.index);
	    
	    
	}
	System.out.printf("%.2f", mst_cost); // -------impressao-------
	System.out.println();
    }
}
class ex21{
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Sarda[] position = new Sarda[n];

	for(int i=0; i<n; i++)
	    position[i] = new Sarda(in.nextFloat(), in.nextFloat(), i);

	//calcular peso das arestas todas!!
	Kruskal.weight(n, position);
	Kruskal.kruskal(n);
    }
}
