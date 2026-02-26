import java.util.*;

class GNode{
    boolean determinado;
    int key;
    int dist;
    GNode pai;
    
    GNode(int valor){
	dist = Integer.MAX_VALUE;
	key=valor;
	determinado = false;
	pai = null;
    }
}

class negocio{
    public static void main(String args[]){
	Scanner in = new Scanner (System.in);
	
	int nlojas = in.nextInt();
	int source = in.nextInt();
	int[][] mat = new int[nlojas+1][nlojas+1];
	GNode[] grafo = new GNode[nlojas+1];

	for (int i=1; i<=nlojas; i++){
	    grafo[i] = new GNode(i);
	}

	grafo[source].dist = 0;
	
	int key1 = in.nextInt();
	int key2;
	int w;

	while (key1!=-1){
	    key2 = in.nextInt();
	    w = in.nextInt();
	    mat[key1][key2] = w;
	    mat[key2][key1] = w;

	    key1 = in.nextInt();
	}
	//acaba leitura   
	//dijkstra p ordenar dist lojas
	LinkedList<GNode> fila = new LinkedList<GNode>();
	for(int k=1; k<=nlojas; k++){
	    fila.addFirst(grafo[k]);
	}

	while (fila.isEmpty()!=true){
	    GNode u = extractmin(fila);
	    for (int m=1; m<=nlojas; m++){
		GNode n = grafo[m];
		if (mat[u.key][m]!=0)
		    relax(u,n,mat);
	    }
	    u.determinado = true;

	    if (fila.isEmpty())
		System.out.print(u.key);
	    else
		System.out.print(u.key+" ");
	}
    }


    public static GNode extractmin(LinkedList<GNode> fila){
	GNode min = new GNode(0);
	LinkedList<GNode> fila2 = new LinkedList<GNode>();
	while (!fila.isEmpty()){
	    GNode teste = fila.removeFirst();
	    if (teste.dist < min.dist)
		min = teste;
	    fila2.addLast(teste);
	}

	while (!fila2.isEmpty()){
	    GNode test = fila2.removeFirst();
	    if (test != min)
		fila.addFirst(test);
	}
	return min;
    }

    public static void relax(GNode u, GNode v, int[][] mat){
	if (v.dist > (u.dist + mat[u.key][v.key])){
	    v.dist = (u.dist + mat[u.key][v.key]);
	    v.pai = u;
	}
    }
}