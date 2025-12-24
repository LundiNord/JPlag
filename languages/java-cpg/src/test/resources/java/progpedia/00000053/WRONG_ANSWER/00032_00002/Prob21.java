import java.util.*;

class Prob21{
    public static void main(String args[]){

	Scanner in = new Scanner(System.in);
	int i;

	int n = in.nextInt();
	int e = in.nextInt();

	boolean visitado[] = new boolean[n+1];

	//Lista de nos ligados
	ArrayList<ArrayList<Integer>> ligacoes = new ArrayList<ArrayList<Integer>>(n+1);

	//colocar na Lista ligaçoes n+1 listas vazias
	for(i = 0; i < n+1; i++){
	    ArrayList<Integer> lista = new ArrayList<Integer>();
		ligacoes.add(lista);
	}

	//preencher a lista ligacoes
	while(in.hasNextInt()){
	    int n1 = in.nextInt();
	    int n2 = in.nextInt();

	    //procurar a lista n1 e adicionar n2
	    ligacoes.get(n1).add(n2);
	    ligacoes.get(n2).add(n1);
	}

	int dist [][] = new int[n+1][n+1];

	//fazer a pesquisa em largura
	for(i = 1; i < n+1; i++){
	    dfs(i,visitado,dist,ligacoes,n); 
	}
	/*

	//imprimir matriz de distancias
	for(i = 0; i < n+1; i++){
	    for(int j = 0; j < n+1; j++)
		System.out.printf("%d ",dist[i][j]);
	    System.out.printf("\n");
	}
	*/

	int excent[] = new int[n+1];
	int max_dist = 0;

	//Calcular matriz excent
	for(i = 1; i < n+1; i++){
	    for(int k = 1; k < n+1; k++){		
		if(max_dist < max(max_dist,dist[i][k]))
		    max_dist = dist[i][k];
	    }		  
	    excent[i] = max_dist;
	    max_dist = 0;	       
    }
	

	/*
	//imprimir vetor excentricidade de cada no
	for(i = 1; i < n+1; i++)
	    System.out.println(i + " " + excent[i]);
	*/

    //calcular o diametro
    int diametro = 0;
    int raio = 10000;

    for(i = 1; i < n+1; i++){
	diametro = max(diametro,excent[i]);
	raio = min(raio,excent[i]);
    }

    int id[] = new int[n];
    int ir[] = new int[n];
    int l = 0;
    int m = 0;

    //calcular os nos com excent igual ao raio e ao diametro
    for(i = 1; i < n+1; i++){
	if(excent[i] == diametro){
	    id[l] = i;
	    l++;
	}
	if(excent[i] == raio){
	    ir[m] = i;
	    m++;
	}
    }

    System.out.println(diametro);
    System.out.println(raio);
    // System.out.println("Iguais ao raio");
    for(i = 0; i < m; i++)
	System.out.printf("%d",ir[i]);
    System.out.printf("\n");
    //System.out.println("Iguais ao diametro");
    for(i = 0; i < l; i++)
	System.out.printf("%d ",id[i]);
    System.out.printf("\n");
    
    }
    


    public static void dfs(int v,boolean visitado[],int distancia[][],ArrayList<ArrayList<Integer>> ligados,int n){
	Queue<Integer> fila = new LinkedList<Integer>();
	fila.add(v);
	distancia[v][v] = 0;  //distancia de v a ele proprio
	visitado[v] = true;

	while(!fila.isEmpty()){
	    int u = fila.poll();
	    for(int w :ligados.get(u)){
		if(visitado[w] != true){
		    fila.add(w);
		    visitado[w] = true;
		    distancia[v][w] = distancia[w][v] = distancia[u][v] + 1;
		}
	    }
	}


	for(int j = 0; j < n+1; j++)
	    visitado[j] = false; 

    }
    public static int max(int a,int b){
	if(a > b)
	    return a;
	else
	    return b;
    }

    public static int min(int a,int b){
	if(a < b)
	    return a;
	else
	    return b;
    }

}