import java.util.*;
import java.io.*;


class Node{
	public LinkedList<Integer> adj;
	public boolean visited;
	public int distance;

	Node(){
		adj = new LinkedList<Integer>();
		visited = false;
		distance = 0;
	}
}
class Graph{
	int n; //number of nodes
	Node node[]; //array of nodes
	int[][] matriz;


	//GRAFO TEM APENAS NODES E CADA NODE TEM UMA LISTA DE ADJ
	Graph( int n){
		this.n = n;
		node = new Node[n+1];
		matriz = new int[n+1][n+1];

		for(int i=1; i<=n; i++)
			node[i] = new Node();
	}

	public void addLink(int i, int j){
		node[i].adj.addLast(j);
		node[j].adj.addLast(i);
	}

	public void bfs(int v){
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.addLast(v);
		node[v].visited 	= true;
        node[v].distance	= 0;

		while(q.size()>0){

			int u = q.removeFirst();

			//System.out.println( u + " distance = " + node[u].distance);
			matriz[v][u] = node[u].distance;

			//PARA CADA ELEMENTO DA LISTA W FICA COM O VALOR
			for(int w : node[u].adj)
				if(!node[w].visited){
					node[w].visited = true;
					node[w].distance = node[u].distance + 1;
					q.addLast(w);
				}	
		}

		for(int i=1; i<=n; i++){
		    node[i].distance	=0;
		    node[i].visited		=false;
	    }
	}
}

class bfs_1{

	static int[] excentricidades;

	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int numberNodes = input.nextInt();
		Graph g = new Graph(numberNodes);

		int numberEdge = input.nextInt();

		for(int i =0; i<numberEdge;i++){
			int a = input.nextInt();
			int b = input.nextInt();

			g.addLink(a,b);
		}

		for(int i=1;i<=numberNodes;i++){
			g.bfs(i);
		}

		//PRINT DA MATRIZ DE LIGAÇOES DOS NOS 
		/*for(int i = 1; i<=numberNodes; i++){
			for(int j = 1; j<numberNodes;j++){
				System.out.print(g.matriz[i][j]);
			}
			System.out.println();
		}*/

		excentricidades = new int[numberNodes+1];

		//DIAMETRO - EXCENTRICIDADE MAX
		int diametro = diametro (g.matriz , numberNodes) ;
		System.out.println(diametro);

		//RAIO - MENOR EXCENTRICIDADE
		int raio = raio(g.matriz , numberNodes);
		System.out.println( raio );

		//TODOS OS NOS CENTRAIS-RAIO
		nosCentrais(raio,excentricidades);

		//TODOS OS NOS PERIFERICOS-DIAMETRO
		nosPerifericos(diametro,excentricidades);

		System.out.println();
	}

	public static int diametro(int[][] matriz, int n){
		int max = 0;
		int maximu = 0;

		for(int i = 1; i<=n;i++){
			for(int j = 1;j<=n;j++){

				if(matriz[i][j] >= maximu)
					maximu = matriz[i][j];

				if(matriz[i][j] >= max)
					max = matriz[i][j];
			}

			excentricidades[i] = max;
			max = 0;
		}

		return maximu;
	}

	public static int raio(int[][] matriz , int n){

		int min[]= new int[n+1];
		int max = 0;

		for(int i = 1; i<=n;i++){
			for(int j = 1;j<=n;j++){
				if(matriz[i][j] > max )
					max = matriz[i][j];
			}
			min[i] = max;
			max = 0; 
		}

		Arrays.sort(min);

		return min[1];
	}

	public static void nosCentrais(int raio, int[] exc){
		LinkedList<Integer> q = new LinkedList<Integer>();

		for(int i = 1; i<= exc.length-1;i++){
			if(exc[i] == raio)
				q.addLast(i);
		}

		while(q.size() > 0){

			if(q.size() == 1)
			System.out.print(q.removeFirst());

			else
			System.out.println(q.removeFirst() + " ");
		}

		System.out.println();
	}

	public static void nosPerifericos(int diametro , int[] exc){
		LinkedList<Integer> q = new LinkedList<Integer>();

		for(int i = 1; i<= exc.length-1;i++){
			if(exc[i] == diametro)
				q.addLast(i);
		}

		while(q.size() > 0){
			if(q.size() == 1)
			System.out.print(q.removeFirst());

			else
			System.out.print(q.removeFirst() + " ");
		}
	}
}