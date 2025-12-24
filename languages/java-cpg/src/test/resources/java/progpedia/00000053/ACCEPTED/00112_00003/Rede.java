
//Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
//(codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;
import java.util.ArrayList;


//Classe que representa um no
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

//Classe que representa um grafo
class Graph {
int n;           // Numero de nos do grafo
Node nodes[];    // Array para conter os nos

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

public void Initialize(int size){
	for(int i=1;i<size+1;i++){
		nodes[i].visited=false;
		nodes[i].distance=0;
	}
}

// Algoritmo de pesquisa em largura
public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
}


}

public class Rede {
public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int size=in.nextInt();
	Graph g = new Graph(size);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	int j=1,m=0;
	ArrayList<Integer>lista=new ArrayList<Integer>();
	while(j<size+1){
		g.bfs(j);
		
		int maior=0;
		
		//descobrir o diametro e o raio e as suas listas de valores
		for(int i=1;i<size+1;i++){
			if(g.nodes[i].distance>maior)
				maior=g.nodes[i].distance;
			
			
		}
		lista.add(m,maior);
		j++;
		m++;
		g.Initialize(size);
	}
	int maior=0,menor=1000,index=0,index1=0;
	ArrayList<Integer>printMaior=new ArrayList<Integer>();
	ArrayList<Integer>printMenor=new ArrayList<Integer>();
	for(int i=0;i<lista.size();i++){
		if(lista.get(i)>maior){
			maior=lista.get(i);
			if(printMaior.isEmpty()){
				printMaior.add(index,i+1);
				index++;
			}
			else{
				printMaior.clear();
				index=0;
				printMaior.add(index,i+1);
			}		
		}	
		else if(lista.get(i)==maior){
			printMaior.add(index,i+1);
			index++;
		}
		
		if(lista.get(i)<menor){
			menor=lista.get(i);
			if(printMenor.isEmpty()){
				printMenor.add(index1,i+1);
				index1++;
			}
			else{
				printMenor.clear();
				index1=0;
				printMenor.add(index1,i+1);
			}		
		}
		else if(lista.get(i)==menor){
			printMenor.add(index1,i+1);
			index1++;
		}
	}
	System.out.println(maior);
	System.out.println(menor);
	
	int copia1[]=new int[printMenor.size()];
	int copia2[]=new int[printMaior.size()];
	for(int i=0;i<printMenor.size();i++)
		copia1[i]=printMenor.get(i);
	for(int i=0;i<printMaior.size();i++)
		copia2[i]=printMaior.get(i);
	
	Arrays.sort(copia1);
	Arrays.sort(copia2);
	for(int i=0;i<printMenor.size();i++){
		if(i<printMenor.size()-1)
			System.out.print(copia1[i]+" ");
		else
			System.out.println(copia1[i]);
	}
	
	for(int i=0;i<printMaior.size();i++){
		if(i<printMaior.size()-1)
			System.out.print(copia2[i]+" ");
		else
			System.out.println(copia2[i]);
	}



}
}
