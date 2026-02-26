
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GrafoLista {
    public class Vertice {
        String nome;
        List<Aresta> adj;
        Boolean visit;

        Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        void addAdj(Aresta e) {
            adj.add(e);
        }
    }

    public class Aresta {
        Vertice origem;
        Vertice destino;

        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }
    }

    List<Vertice> vertices;
    List<Aresta> arestas;

    public GrafoLista() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        Aresta f = new Aresta(destino, origem);
        destino.addAdj(f);
        arestas.add(e);
        return e;
    }
    
    Vertice dfs(Vertice v){
    	v.visit = true;
    	for(Aresta w:v.adj){
    		if (w.destino.visit == false)
    			dfs(w.destino);
    	}
    	return v;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public static void main(String[] args) {
        GrafoLista g = new GrafoLista();
        Scanner inp = new Scanner(System.in);

	int n = inp.nextInt();
	int l = inp.nextInt();
	
	for(int i=0; i<n; i++){
		g.addVertice("" + (i+1));
	}
	
	for(int i=0; i<l; i++){
		Vertice x = g.vertices.get(inp.nextInt() - 1);
		Vertice y = g.vertices.get(inp.nextInt() - 1);
		g.addAresta(x, y);		
	}
	
	int c = 0;
	for(Vertice x:g.vertices){
		x.visit = false;
	}
	for(Vertice x:g.vertices){
		if(x.visit == false){
			c++;
			g.dfs(x);
		}
	}
	
	System.out.println(c);
	inp.close();
    }
}