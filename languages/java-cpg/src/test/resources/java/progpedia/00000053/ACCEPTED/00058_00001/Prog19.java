

// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)









//ALTERACOES AO CODIGO DO PROFESSOR EM CAPS









import java.util.*;

// Classe que representa um no
class Node {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
	public int distance;            // Distancia do no origem da pesquisa

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	}
}

// Classe que representa um grafo
class Graph {
	int n;           // Numero de nos do grafo
	Node nodes[];    // Array para conter os nos
	
	
	
	//
	int eccentricity; //VALOR DE ECCENTRICITY DO NO S DO GRAFO
	//
	
	
	
	
	Graph(int n) {
		this.n = n;
		nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
		for (int i=1; i<=n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	// Algoritmo de pesquisa em largura
	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
			int u = q.removeFirst();
			//System.out.println(u + " [dist=" + nodes[u].distance + "]");
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1; 
				}	    
		}
		
		
		
		
		
		
		
		//CALCULOS DISTANCIAS DO NO FEITAS
		//CALCULAR ECCENTRICITY
		eccentricity = -1; //caso nao seja a primeira vez que se corre bfs
		for(int i=1; i<=n; i++) {
			if(nodes[i].distance  > eccentricity)
				eccentricity = nodes[i].distance;
		}
		
	}
	
	//RETORNAR ECCENTRICITY
	public int eccentricity() {
		return eccentricity;
	}


	//ADICIONADO UNVISIT PARA CORRER BFS VARIAS VEZES, EXISTE MANEIRA MELHOR?
	public void unvisit() {
		for(int i = 1; i<=n; i++) {
			nodes[i].visited = false;
		}
	}
	
	
	
	
	
	


}

public class Prog19 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		Graph g = new Graph(in.nextInt());
		int   e = in.nextInt();
		for (int i=0; i<e; i++) 
			g.addLink(in.nextInt(), in.nextInt());

		
		
		
		
		//AGORA VEM AI GRANDE MAGIA
		//CALCULAR EXCENTRICIDADE POR NO DO GRAFO
		int[] excentricidade = new int[g.n+1];
		
		for(int i = 1; i<=g.n;i++) {
			g.bfs(i);
			excentricidade[i] = g.eccentricity();
			g.unvisit();
		}
		
		//CALCULAR RADIUS DIAMETER CENTER PERIFERAL
		int diameter = 0, radius = Integer.MAX_VALUE;
		

		for(int i = 1; i<=g.n;i++) {
			if (diameter < excentricidade[i]) {
				diameter = excentricidade[i];
			}
			if (radius > excentricidade[i]) { // && 0<excentricidade[i]
				radius = excentricidade[i];
			}
		}
		
		//OUTPUT TODO BONITO PRA MENINA MOOSHAK
		System.out.println(diameter);
		System.out.println(radius);
		
		String center = new String();
		for(int i = 1; i<=g.n; i++) {
			if(excentricidade[i] == radius) center += i + " ";
		}
		if (center.endsWith(" ")) {
			  center = center.substring(0, center.length() - 1);
		}
		System.out.println(center);
		String periferal = new String();
		for(int i = 1; i<=g.n; i++) {
			if(excentricidade[i] == diameter) periferal += i + " ";
		}
		if (periferal.endsWith(" ")) {
			periferal = periferal.substring(0, periferal.length() - 1);
		}
		System.out.println(periferal);
		
		
	}
}