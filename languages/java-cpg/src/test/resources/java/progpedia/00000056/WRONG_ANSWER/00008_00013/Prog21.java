import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Par implements Comparable<Par> {
    double w;
    int a, b;
    
    Par (double w, int a, int b){
	this.a = a;
	this.b = b;
	this.w = w;
    }
	
    public int compareTo(Par p){
	if (b < p.b)      return -1;
	else if (b > p.b) return +1;
	else              return 0;
    }
}


class Node implements Comparable<Node>{
	double w;
	int from; // no antecessor
	int to; // no sucessor

	Node(double w, int from, int to){
		this.w = w;
		this.from = from;
		this.to = to;
	}

	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Double.compare(this.w, o.w);
	}

}


public class Prog21{
//	static List<Node> nodelist;
	static PriorityQueue<Par> queue;
	static int n;
	static int [] pset;
	static int [] rank;

	//create the set
	static void makeSet(int n1){
		pset = new int[n1];
		rank = new int[n1];
		//desnecessario?
		for(int i = 0; i < n1; i++){
			pset[i] = i;
			rank[i] = 0;
		}
	}

	//search the set
	static int findSet(int i){
		if(i!=pset[i]) {
			pset[i] = findSet(pset[i]);
		}
		return pset[i];
	}

	//is it the same set?

	static boolean isSameSet(int i, int j){
		return findSet(i) == findSet(j);

	}

	//Union.

	static void Union(int i, int j){
		int xRz = findSet(i);
		int yRz = findSet(j);


		if(rank[xRz] > rank[yRz])
			pset[yRz] = xRz;
		else {
			pset[xRz] = yRz;
			if(rank[xRz] == rank[yRz])
				++rank[yRz];
		}


	}

	//algoritmo de kruskall
	static double kruskall(){

		double cost = 0;
		makeSet(n);
		while(queue.size() > 0 ){
			Par p = queue.remove();
			if(!isSameSet(p.a,p.b)){
				Union(p.a,p.b);
				cost+= p.w;
			}
		}
//		for(int i = 0; i < nodelist.size(); ++i){
////			System.out.println(cost);
//			Node node = nodelist.get(i);
//			//SE NAO ESTÁ EMBRICADO CORRE SEMPRE, O QUE É O OPOSTO DO ALGORITMO...............
//			if(!isSameSet(node.from, node.to)) {
//				Union(node.from, node.to);
//				cost += node.w;
//			}
//		}
		return cost;
	}


	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(System.in);

		n = in.nextInt();
		double [] xcord = new double[n]; 
		double [] ycord = new double[n]; 
//		nodelist = new ArrayList<Node>();
		queue = new PriorityQueue<Par>();
		//splits the string and transforms into doubles
		for(int i = 0; i < n; ++i){
			xcord[i]=in.nextDouble();
			ycord[i]=in.nextDouble();
		}

		for (int i = 0; i < n-1; ++i){
			for(int j = i+1; j < n; ++j){
				double dx = xcord[i] - xcord[j];
				double dy = ycord[i] - ycord[j];
				double d = Math.sqrt(dx*dx + dy*dy);

				queue.add(new Par(d,i,j));
				queue.add(new Par(d,i,j));

			}
		}
		
		System.out.println(String.format("%.2f", kruskall()));
	}




}