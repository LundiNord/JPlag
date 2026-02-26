import java.util.*;

class GNode{
    GNode pai;
    int chave;
    int distancia;
    boolean determinado;
    
    GNode(int valor){
    	pai=null;
    	chave=valor;
    	distancia = Integer.MAX_VALUE;
    	determinado = false;
    }
}

class negocio{
    public static void main(String args[]){
    	Scanner in = new Scanner (System.in);
    	int nlojas = in.nextInt();
    	int id_destino = in.nextInt();
    	int[][] mat = new int[nlojas+1][nlojas+1];
    	GNode[] grafo = new GNode[nlojas+1];
    	
    	for (int i=1; i<=nlojas; i++){
    		grafo[i] = new GNode(i);
    	}
    	
    	grafo[id_destino].distancia = 0;
    	int chave1 = in.nextInt();
    	
    	while (chave1!=-1){
    		int chave2 = in.nextInt();
    		int w = in.nextInt();
    		mat[chave1][chave2] = w;
    		mat[chave2][chave1] = w;
    		chave1 = in.nextInt();
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
    			if (mat[u.chave][m]!=0)
    				relax(u,n,mat);
    		}
    		u.determinado = true;
    		if (fila.isEmpty())
    			System.out.print(u.chave);
    		else
    			System.out.print(u.chave+" ");
    	}
    }


    static GNode extractmin(LinkedList<GNode> fila){
    	GNode min = new GNode(0);
    	LinkedList<GNode> fila2 = new LinkedList<GNode>();
    	
    	while (!fila.isEmpty()){
    		GNode teste = fila.removeFirst();
    		if (teste.distancia < min.distancia)
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
    
    static void relax(GNode u, GNode v, int[][] mat){
    	if (v.distancia > (u.distancia + mat[u.chave][v.chave])){
    		v.distancia = (u.distancia + mat[u.chave][v.chave]);
    		v.pai = u;
    	}
    }
}