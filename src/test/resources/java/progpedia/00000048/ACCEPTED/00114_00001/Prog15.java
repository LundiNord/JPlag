//Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido

/*
 * Logica do programa:
 * Inicializar a rede no no 1 do grafo, correr o a lista de adjacencias do 1
 * descobre que 1 esta ligado a 3, entao a rede R1 = {N1, N3}, o um nao tem mais ligacoes, o 3 liga-se ao no 5, entao
 * R1 = {N1, N3, N5}, nao existem mais ligacoes, entao vou para o proximo node unvisited, N2, etcetc
 * entao contar redes no sempre que iniciar uma pesquisa por uma rede nova!
 */

import java.util.*;


public class Prog15 {
 static int n;                	 // Numero de nos do grafo
 static boolean adj[][];		 // Matriz de adjacencias
 static boolean visited[]; 		 // Que nos ja foram visitados?

 static void dfs(int v) {
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
		dfs(i);
 }
 
 static int contaredes (int no) {
	 int counter = 0;
	 for(int i =1;i<=n;i++) {
		 if(!visited[i]) {		//se nao foi visitado e uma rede nova
			 counter++;
			 dfs(i);
		 }
		 
	 }
	 return counter;
 }
 
 
 //LISTA REDES E DFS PRINT PARA IMPRIMIR AS REDES
 static void listaredes (int no) {
	 for(int i =1;i<=n;i++) {
		 if(!visited[i]) {				//se nao foi visitado e uma rede nova
			 dfsprint(i);
			 System.out.println();		//linha dicionado para formatar output, as redes por uma linha
		 }
		 
	 }
 }
 
 static void dfsprint(int v) {
	System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
		dfs(i);
 }
 
 public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	n = in.nextInt();;						//n de pontos
	adj     = new boolean[n+1][n+1];
	visited = new boolean[n+1];	
	int edges = in.nextInt();				//n ligacoes
	for (int i=0; i<edges; i++) {
	    int a = in.nextInt();
	    int b = in.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	

	// Pesquisa em profundidade a partir do no 1
	//listaredes(1);               					//Se quiser imprimir as redes basta fazer isto, atm uma por linha
	System.out.println(contaredes(1));				//as duas funcoes estao a usar a mesma variavel counter, por isso sao mutualmente excluivas
													//se quiser usar as duas, mudar uma para uma nova variavel counter
 }
}