//com a ajuda de Catarina e Carlos Ribeiro
import java.io.*;
import java.util.*;

//Classe que representa um no
class Node {

    public LinkedList<Integer> adj; // Lista de adjacencias
    public double distance; // Distancia de um ponto(x,y) a outro
    public double x;
    public double y;
    public int noPai;

    Node(double x, double y) {
        adj = new LinkedList<Integer>();
        distance = 0.0;
        noPai = 0;
        this.x = x;
        this.y = y;
    }
}

// Classe que representa um grafo
class Graph {
    int n; // Numero de nos do grafo
    Node nodes[]; // Array para conter os nos

    Graph(int n) {
        this.n = n;
        nodes = new Node[n + 1]; // +1 se os comecam em 1 ao inves de 0
    }
    // calcular distancia
    public double distancia(int x, int y) {
        return Math
                .sqrt(((nodes[x].x - nodes[y].x) * (nodes[x].x - nodes[y].x))
                        + ((nodes[x].y - nodes[y].y) * (nodes[x].y - nodes[y].y)));
    }
    // algoritmo de Prim
    public void Prim(int v) {
        LinkedList<Integer> q = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            nodes[i].distance = Double.MAX_VALUE;
            q.add(i);
        }
        nodes[v].distance = 0;
        double minimum = Double.MAX_VALUE;
        int z = 0;
        int menor = 0;
        while (q.size() > 0) {
            minimum = Double.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (q.contains(i) && nodes[i].distance < minimum) {
                    minimum = nodes[i].distance;
                    menor = i;
                }
            }
            q.removeFirstOccurrence(menor);
            z = menor;

            for (int w : nodes[z].adj) {
                double dist = distancia(z, w);
                if (q.contains(w) && dist < nodes[w].distance) {
                    nodes[w].noPai = z;
                    nodes[w].distance = dist;
                }
            }
        }
    }
}
public class Sardas{
    static int n;
    static double dist = 0.0;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        Graph g = new Graph(n);
        for (int i = 1; i <= n; i++) {
            g.nodes[i] = new Node(in.nextDouble(), in.nextDouble());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g.nodes[i].adj.add(j);//adiciono o nó à lista de adjacentes
            }
        }
        //fazer a pesquisa em largura a partir do primeiro no
        g.Prim(1);
        for (int i = 1; i <= n; i++) {
            dist += g.nodes[i].distance;
        }
        System.out.printf("%.2f\n", dist);
    }
}
