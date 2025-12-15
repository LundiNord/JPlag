import java.util.*;

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

class Graph {
    int max, min;
    int[] maximus;
    LinkedList<Integer> nosCentrais;
    LinkedList<Integer> nosPerifericos;
    
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    
    Graph(int n) {
        this.n = n;
        nodes = new Node[n + 1]; // +1 se os comecam em 1 ao inves de 0
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }
        
        this.nosCentrais = new LinkedList<Integer>();
        this.nosPerifericos = new LinkedList<Integer>();
        this.maximus = new int[this.n + 1];
    }

    public void addLink(int a, int b) {
        nodes[a].adj.add(b);
        nodes[b].adj.add(a);
    }

    public void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(v);
        nodes[v].visited = true;
        nodes[v].distance = 0;
        while (q.size() > 0) {
            int u = q.removeFirst();
            
            this.setMaxi(v, nodes[u].distance);
            
            for (int w : nodes[u].adj) {
                if (!nodes[w].visited) {
                    q.add(w);
                    nodes[w].visited = true;
                    nodes[w].distance = nodes[u].distance + 1;
                }
            }
        }
    }

    public void setMaxi(int no, int distancia) {
        if (this.maximus[no] < distancia)
            this.maximus[no]=distancia;
    }

    public void clearBfs() {
        for (int i = 1; i < this.n; i++) {
            nodes[i].distance = -1;
            nodes[i].visited = false;
        }
    }
    
    public void calcMaxMin(){
        this.max = this.min = this.maximus[1];
        for (int i=1; i<this.n+1; i++){
            if (this.max < this.maximus[i])     this.max = this.maximus[i];
            if (this.min > this.maximus[i])     this.min = this.maximus[i];
        }
    }
    
    public void calcNosCentraisPerifericos(){
        for (int i=0; i<this.n+1; i++){
            if (this.maximus[i] == this.min)    this.nosCentrais.add(i);
            if (this.maximus[i] == this.max)    this.nosPerifericos.add(i);
        }
    }
    
    public void printMax(){
        System.out.println(this.max);
    }
    
    public void printMin(){
        System.out.println(this.min);
    }
    
    public void printNosCentrais(){
        for (int i=0; i<this.nosCentrais.size(); i++){
            if (i == this.nosCentrais.size()-1)     System.out.println(this.nosCentrais.get(i));
            else                                    System.out.print(this.nosCentrais.get(i) + " ");
        }
    }
    
    public void printNosPerifericos(){
        for (int i=0; i<this.nosPerifericos.size(); i++){
            if (i == this.nosPerifericos.size()-1)     System.out.println(this.nosPerifericos.get(i));
            else                                    System.out.print(this.nosPerifericos.get(i) + " ");
        }
    }
}

public class BFS {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        int nos = in.nextInt();
	Graph g = new Graph(nos);
	int vertices = in.nextInt();
	for (int i=0; i<vertices; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
        
        
        /*int nos = 8;
        int arestas = 9;
        Graph g = new Graph(nos);
        for (int i = 0; i < arestas; i++) {
            g.addLink(1, 3);
            g.addLink(2, 3);
            g.addLink(3, 5);
            g.addLink(3, 6);
            g.addLink(6, 4);
            g.addLink(6, 8);
            g.addLink(4, 7);
            g.addLink(1, 2);
            g.addLink(8, 7);
        }
        */
        for (int i = 1; i <= nos; i++) {
            g.bfs(i);
            g.clearBfs();
        }
        g.calcMaxMin();
        g.calcNosCentraisPerifericos();
        g.printMax();
        g.printMin();
        g.printNosCentrais();
        g.printNosPerifericos();
    }
}
