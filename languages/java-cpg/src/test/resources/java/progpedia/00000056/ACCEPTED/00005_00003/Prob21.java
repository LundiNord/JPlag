import java.util.*;




class Edge implements Comparable<Edge> {
    Edge(No a, No b){
	this.a = a;
	this.b = b;

    }
    No a, b;

    @Override
    public int compareTo(Edge other) {
	return (int) (((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y)) - ((other.a.x-other.b.x)*(other.a.x-other.b.x) + (other.a.y-other.b.y)*(other.a.y-other.b.y)));
    }
}

class No {
    No(double x, double y, int idx) {
	this.x = x;
	this.y = y;
	this.idx = idx;
    }
    double x, y;
    int idx;
}

class UnionFind{
    int n;
    int a[];

    UnionFind(int n){
	this.n=n;
	a = new int[n];
	for (int i = 0; i< n; i++)
	    a[i]=i;
    }

    int FindSet(int i){
	if ( a[i]==i)
	    return i;
	return a[i] = FindSet(a[i]);
    }

    void UnionSet(int l, int r) {
	a[FindSet(l)] = FindSet(r);
    }

}


public class Prob21{
    static int n;


    public static void main(String args[]){

	Scanner in = new Scanner(System.in);
	n = in.nextInt();

	ArrayList<No> nos = new ArrayList<No>();
	ArrayList<Edge> arestas = new ArrayList<Edge>();
	double comp = 0.0;
	
	for (int i=0; i < n; i++)
	    nos.add(new No(in.nextDouble(), in.nextDouble(), i));	    
       
	UnionFind sets = new UnionFind(n);

	for (int i=0; i < n; i++)
 	    for (int j=i+1; j< n; j++)
		arestas.add(new Edge(nos.get(i), nos.get(j)));

	Collections.sort(arestas);
	
	for (Edge e : arestas)
	    if (sets.FindSet(e.a.idx) != sets.FindSet(e.b.idx)){
		sets.UnionSet(e.a.idx, e.b.idx);
		comp += Math.sqrt((e.a.x-e.b.x)*(e.a.x-e.b.x) + (e.a.y-e.b.y)*(e.a.y-e.b.y));
		
	    }
	System.out.printf("%.2f\n", comp);
    }

}
