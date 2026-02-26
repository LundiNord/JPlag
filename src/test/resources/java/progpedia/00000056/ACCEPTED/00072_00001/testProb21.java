import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	double t;
	int ant; 
	int suc; 

	Node(double t, int ant, int suc){
		this.t = t;
		this.ant = ant;
		this.suc = suc;
	}

	public int compareTo(Node o) {
		return Double.compare(this.t, o.t);
	}

}

public class testProb21{
	static List<Node> nodelist;
	static int n;
	static int p[];
	static int r[];
	
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);

		n = stdin.nextInt();
		double x[] = new double[n]; 
		double y[] = new double[n]; 
		nodelist   = new ArrayList<Node>();

		for(int i = 0; i < n; ++i){
			x[i] = stdin.nextDouble();
			y[i] = stdin.nextDouble();
		}

		for (int i = 0; i < n-1; ++i){
			for(int j = i+1; j < n; ++j){
				double dx = x[i] - x[j];
				double dy = y[i] - y[j];
				double d = Math.sqrt(dx*dx + dy*dy);

				nodelist.add(new Node(d,i,j));
				nodelist.add(new Node(d,j,i));
			}
		}
		System.out.println(String.format("%.2f", algkruskall()));
	}
	
	static double algkruskall(){
		double cost = 0;
		set(n);
		Collections.sort(nodelist);
		for(int i=0; i < nodelist.size(); ++i){
			Node node = nodelist.get(i);
			if(!igual(node.ant, node.suc)) {
				Uniao(node.ant, node.suc);
				cost += node.t;
			}
		}
		return cost;
	}

	static void set(int n1){
		p = new int[n1];
		r = new int[n1];
		for(int i = 0; i < n1; i++){
			p[i] = i;
			r[i] = 0;
		}
	}

	static int proc(int i){
		if(i!=p[i]) {
			p[i] = proc(p[i]);
		}
		return p[i];
	}

	static boolean igual(int i, int j){
		return proc(i) == proc(j);
	}

	static void Uniao(int i, int j){
		int x = proc(i);
		int y = proc(j);

		if(r[x] > r[y])
			p[y] = x;
		else {
			p[x] = y;
			if(r[x] == r[y])
				++r[y];
		}
	}
}
