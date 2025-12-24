import java.util.*;
import java.lang.*;



class Arco {
    int no_final;
    
    Arco(int fim){
	no_final = fim;
    }

}


class No {
    int label;
    LinkedList<Arco> adjs;
    
    No(int n) {
	label = n;
	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;
    LinkedList<No> stack;
    boolean visitados[];
			
    public Grafo(int n) {
	nvs = n;
	narcos = 0;
	verts  = new No[n+1];
	stack = new LinkedList<No>();
	visitados = new boolean[n+1];
	for (int i = 1 ; i <= n ; i++) {
	    visitados[i] = false;
	    verts[i] = new No(i);
	}
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    

    public void insert_new_arc(int i, int j){
	verts[i].adjs.addFirst(new Arco(j));
        narcos++;
    }
    
    public Grafo transposeGraph() {
	Grafo g = new Grafo(nvs);
	for (int i=1; i<=nvs; i++)
	    for (Arco e: verts[i].adjs)
		g.insert_new_arc(e.no_final,i);
	return g;
    }
    
    public void dfs1(int node) {
	int aux;
	
	visitados[node] = true;
	for (Arco e: verts[node].adjs)
	    if (visitados[e.no_final] == false)
		dfs1(e.no_final);
	stack.addFirst(new No(node));
    }
    
    
    public int dfs2_visit(int node,int c) {
	visitados[node] = true;
	for (Arco e:verts[node].adjs)
	    if (visitados[e.no_final] == false)
		c = dfs2_visit(e.no_final, c);
	c++;
	return c;
    }

    public void dfs2(LinkedList<No> times) {
	int c,nGrupos = 0,nFora = 0,aux;
	while (times.size() > 0) {
	    aux = times.removeFirst().label;
	    c = 0;
	    if (visitados[aux] == false) {
		c = dfs2_visit(aux,c);
		if (c >= 4)
		    nGrupos++;
		else
		    nFora = nFora + c;
	    }
	}
	System.out.printf("%d %d\n",nGrupos,nFora);
    }

    public void calculateSSC() {
	Grafo transposto;
	for (int i = 1; i<=nvs; i++)
	    if (visitados[i] == false)
		dfs1(i);
	
	transposto = transposeGraph();
	transposto.dfs2(stack);
    }

}

class Sociologia {
    static Scanner in = new Scanner(System.in);

    public static void main(String args[]) {
	int c, ncases, n, connections;
	int a,b;
	Grafo g;
	
	ncases = in.nextInt();
	for (c = 0; c<ncases; c++) {
	    n = in.nextInt();
	    g = new Grafo(n);
	    for (int j = 0; j<n; j++) {
		a = in.nextInt();
		connections = in.nextInt();
		for (int i=0; i<connections; i++) {
		    b = in.nextInt();
		    g.insert_new_arc(a,b);
		}
	    }
	    System.out.printf("Caso#%d\n",c);
	    g.calculateSSC();
	}
    }
}
	    