import java.util.*;


public class DAA19 {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int diam = 0, raio = Integer.MAX_VALUE;
		Graph grafo = new Graph(scan.nextInt());

		int arestas = scan.nextInt();
		for (int i=0; i<arestas; i++) 
			grafo.addLink(scan.nextInt(), scan.nextInt());

		int[] excentricidade = new int[grafo.n+1];	

		for(int i = 1; i<=grafo.n;i++) {
			grafo.bfs(i);
			excentricidade[i] = grafo.maior_dist_min();
			grafo.unvisit();
		}		

		for(int i = 1; i<=grafo.n;i++){
			if (diam < excentricidade[i]){
				diam = excentricidade[i];
			}
			if (raio > excentricidade[i]){
				raio = excentricidade[i];
			}
		}

		System.out.println(diam);
		System.out.println(raio);

		String no_central = new String();
		for(int i = 1; i<=grafo.n; i++) {
			if(excentricidade[i] == raio) no_central += i + " ";
		}
		if (no_central.endsWith(" ")) {
			no_central = no_central.substring(0, no_central.length() - 1);
		}

		System.out.println(no_central);

		String no_periferico = new String();
		for(int i = 1; i<=grafo.n; i++) {
			if(excentricidade[i] == diam) no_periferico += i + " ";
		}
		if (no_periferico.endsWith(" ")) {
			no_periferico = no_periferico.substring(0, no_periferico.length() - 1);
		}

		System.out.println(no_periferico);				
	}
}

class No {
	public LinkedList<Integer> adj; 
	public boolean visited;         
	public int dist;            // distancia do no de origem ao no de pesquisa

	No() {
		adj = new LinkedList<Integer>();
		visited = false;
		dist = -1;
	}
}

class Graph {
	int n;           // N nos
	int exc;		// maior distância mínima dele próprio a um qualquer outro nó
	No nodes[];    // Array de nos

	Graph(int n) {
		this.n = n;
		nodes  = new No[n+1]; // incrementam se começam em 1
		for (int i=1; i<=n; i++)
			nodes[i] = new No();
	}
	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public int maior_dist_min() {
		return exc;
	}

	public void unvisit() {
		for(int i = 1; i<=n; i++) {
			nodes[i].visited = false;
		}
	}

	//BFS
	public void bfs(int no) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(no);
		nodes[no].visited = true;
		nodes[no].dist = 0;

		while (list.size() > 0) {
			int u = list.removeFirst();
			for (int k : nodes[u].adj)
				if (!nodes[k].visited) {
					list.add(k);
					nodes[k].visited  = true;
					nodes[k].dist = nodes[u].dist + 1; 
				}	    
		}
		exc = -1; 
		for(int i=1; i<=n; i++) {
			if(nodes[i].dist  > exc)
				exc = nodes[i].dist;
		}		
	}
}