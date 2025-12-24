import java.util.*;
import java.io.*;

class Sarda{
	double x;
	double y;
	boolean verify;

	public Sarda(double x, double y){
		this.x=x;
		this.y=y;
	}
}

class Edge implements Comparable<Edge> {
	Sarda a;
	Sarda b;
	double valeu;

	Edge(Sarda a, Sarda b, double valeu){
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

class kruskal{
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

				//System.out.println(res);
				//System.out.println(sarda[i].x + " " +sarda[i].y);
				//System.out.println(sarda[j].x + " " +sarda[j].y);
				Edge e = new Edge(sarda[i],sarda[j],res);

				edge.addLast(e);
				k++;
			}
		}

		Collections.sort(edge);
	}

	public static double output(){
		double soma=0;

		while(edge.size() > 0){
			Edge d = edge.removeFirst();

			if(!d.a.verify || !d.b.verify){
				d.a.verify=true;
				d.b.verify=true;

				double x = d.valeu;
				soma = soma + x;
			}
		}	

		return soma;
	}
}

class p21{
	
	static Sarda[] sarda;
	public static void main(String args[]){
		Locale.setDefault(new Locale("en", "US"));

		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		sarda = new Sarda[n];
		for(int i=0;i<n;i++) { sarda[i] = new Sarda(input.nextDouble(),input.nextDouble() ); }
		
		kruskal.allEdges(n,sarda);
		System.out.printf(Locale.ENGLISH,"%.2f \n" , kruskal.output() );
	}
}
