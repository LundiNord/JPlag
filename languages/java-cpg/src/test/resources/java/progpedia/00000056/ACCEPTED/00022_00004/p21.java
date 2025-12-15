import java.util.*;
import java.io.*;

class Sarda{
	double x;
	double y;
	int num;

	public Sarda(double x, double y, int num){
		this.x=x;
		this.y=y;
		this.num=num;
	}
}

class Edge implements Comparable<Edge> {
	Sarda a;
	Sarda b;
	double valeu;

	public Edge(Sarda a, Sarda b, double valeu){
		this.a=a;
		this.b=b;
		this.valeu=valeu;
	}

	//ordeno todas as arestas
	@Override
	public int compareTo(Edge p) {

		if(valeu > p.valeu)
			return 1;
		if(valeu < p.valeu)
			return -1;
		else 
			return 0;
	}	
}

class UnionFind{
	int[] p;									//elementos que representam que estao no conjunto
	int[] rank;
	int[] setSize;
	int numSets;								// = n

	public UnionFind(int n) {					//inicializa os vetores e inteiros
		numSets = n;

		setSize=new int[n];
		for(int i=0; i<n;i++) { setSize[i]=1; }

		rank = new int[n];
		for(int i=0;i<n;i++) { rank[i]=0;}

		p=new int[n];
		for(int i=0;i<n;i++){p[i] = i;}
	}
	
	public int findSet(int i){ 					//procurar o representante do conjunto
		if(p[i] == i)
			return i; 
		else
			return findSet(p[i]);  
	}

	public boolean isSameSet(int i, int j){		//verifica se tem o mesmo representante
		return ( findSet(i)==findSet(j) );
	}

	public void unionSet(int i, int j){			//une elementos sozinhos ou dois conjuntos
		
		if(!isSameSet(i,j)){					//verifica se estao em conjuntos diferentes
			numSets--;

			int x = findSet(i);
			int y = findSet(j);

			if(rank[x] > rank[y]){
				p[y]=x;
				setSize[x] += setSize[y];
			}

			else{
				p[x] = y;
				setSize[y]+=setSize[x];

				if(rank[x] == rank[y])
					rank[y]++;
			}
		}
	}
}

class Kruskall{
	static LinkedList<Edge> edge = new LinkedList<Edge>();

	//calcula todas as arestas possiveis e guarda no vetor edge[]
	public static void allEdges(int size, Sarda[] sarda){
		int k=0;

		//calcular todas as arestas e por num vetor Edge
		for(int i=0;i<size;i++){
			for(int j=i+1;j<size;j++){

				double x1= sarda[i].x; 
				double y1=sarda[i].y; 
				double x2=sarda[j].x; 
				double y2=sarda[j].y;

				double sub1=Math.abs(x2-x1);
				double sub2=Math.abs(y2-y1);
				double sqr1=sub1*sub1;
				double sqr2=sub2*sub2;
				double res = Math.sqrt((sqr1+sqr2));

				Edge e = new Edge(sarda[i],sarda[j],res);

				edge.addLast(e);
				k++;
			}
		}

		Collections.sort(edge);
	}

	public static double kruskall(int nSardas){
		double minimalCost = 0;

		UnionFind unionFind = new UnionFind(nSardas);

		while(edge.size() > 0){
			Edge single_edge = edge.removeFirst();

			if(!unionFind.isSameSet(single_edge.a.num,single_edge.b.num))
				minimalCost = minimalCost + single_edge.valeu;
				unionFind.unionSet(single_edge.a.num,single_edge.b.num); 	//juntar os dois conjuntos ou nodes
		}
		
		return minimalCost;
	}
}

class p21{
	
	static Sarda[] sarda;
	public static void main(String args[]){

		Locale.setDefault(new Locale("en", "US"));
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		sarda = new Sarda[n];

		for(int i=0;i<n;i++) {
		 sarda[i] = new Sarda(input.nextDouble(),input.nextDouble(), i ); 
		}
		
		Kruskall.allEdges(n,sarda);
		double d = Kruskall.kruskall(n);
		System.out.printf(Locale.ENGLISH,"%.2f\n" , d );
	}
}
