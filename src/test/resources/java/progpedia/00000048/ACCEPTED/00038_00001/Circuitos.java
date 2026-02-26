import java.util.*;


class Circuitos{
    static int V;
    static int E;
    static boolean visited[];
    static boolean arestas[][];

    static void dfs(int v){

	visited[v] = true;
	for (int i=1; i<=V; i++)
	    if (arestas[v][i] && !visited[i])
		dfs(i);

    }

    public static void main(String args[]){

	Scanner input = new Scanner(System.in);
        V = input.nextInt();
        E = input.nextInt();
	arestas = new boolean[V+1][V+1];
	visited = new boolean[V+1];
	for(int i=0; i<E; i++){
	    int a = input.nextInt();
	    int b = input.nextInt();
	    arestas[a][b] = arestas[b][a] = true;
	}

	int counter = 0;

	for(int i=1; i<=V; i++){
	    if(visited[i] == false){
		counter++;
		dfs(i);
	    }
	}

	System.out.println(counter);
    }



}
