import java.util.*;
import java.io.*;

// Classe que representa um no
class Node {
  public LinkedList<Integer> adj; // Lista de adjacencias
  public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
  public int distance;            // Distancia do no origem da pesquisa
  public int excentri;

  Node() {
    adj = new LinkedList<Integer>();
    visited = false;
    distance = -1;
    excentri = 0;
  }
}

// Classe que representa um grafo
class Graph {
  int n;           // Numero de nos do grafo
  Node nodes[];    // Array para conter os nos

  Graph(int n) {
    this.n = n;
    nodes = new Node[n + 1]; // +1 se os comecam em 1 ao inves de 0
    for (int i = 1; i <= n; i++)
      nodes[i] = new Node();
  }

  public void addLink(int a, int b) {
    nodes[a].adj.add(b);
    nodes[b].adj.add(a);
  }

  // Algoritmo de pesquisa em largura
  public void bfs(int v) {
    LinkedList<Integer> q = new LinkedList<Integer>();

    q.add(v);
    nodes[v].visited = true;
    nodes[v].distance = 0;

    while (q.size() > 0) {
      int u = q.removeFirst();
      System.out.println(u + " [dist=" + nodes[u].distance + "]");
      for (int w : nodes[u].adj) {
        if (!nodes[w].visited) {
          q.add(w);
          nodes[w].visited = true;
          nodes[w].distance = nodes[u].distance + 1;
        }
      }
    }
  }

  public void bfs() {
    int ret, v, u = 0;
    LinkedList<Integer> q;

    for (v = 1; v < nodes.length; v++) {
      int maxdist = 0;

      q = new LinkedList<Integer>();
      q.add(v);
      nodes[v].visited = true;
      nodes[v].distance = 0;

      while (q.size() > 0) {
        u = q.removeFirst();

        for (int w : nodes[u].adj)
          if (!nodes[w].visited) {
            q.add(w);
            nodes[w].visited = true;
            nodes[w].distance = nodes[u].distance + 1;

            maxdist = Math.max(maxdist, nodes[w].distance);
          }

      }

      nodes[v].excentri = maxdist;

      // Limpa visited e distance.
      for (int i = 1; i < nodes.length; i++) {
        nodes[i].visited = false;
        nodes[i].distance = -1;
      }

    }
  }

  public int diam() {
    int d = 0;

    for (int i = 1; i < nodes.length; i++)
      d = Math.max(d, nodes[i].excentri);

    return d;
  }

  public int radius() {
    int r = Integer.MAX_VALUE;

    for (int i = 1; i < nodes.length; i++)
      r = Math.min(r, nodes[i].excentri);

    return r;
  }

  public void cnodes(int r) {
    String str = new String("");

    for (int i = 1; i < nodes.length; i++)
      if (nodes[i].excentri == r)
        str += i + " ";

    System.out.println(str.substring(0, str.length() - 1));
  }

  public void pnodes(int d) {
    String str = new String("");

    for (int i = 1; i < nodes.length; i++)
      if (nodes[i].excentri == d)
        str += i + " ";

    System.out.println(str.substring(0, str.length() - 1));
  }

}

public class Problema19 {

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int N; // N nós
    int E; // E arestas

    N = stdin.nextInt();
    E = stdin.nextInt();

    Graph g = new Graph(N);

    for (int i = 0; i < E; i++)
      g.addLink(stdin.nextInt(), stdin.nextInt());

    // Output
    g.bfs();
    //  Diâmetro
    int d = g.diam();
    System.out.println(d);
    //  Raio
    int r = g.radius();
    System.out.println(r);
    //  Nós centrais.
    g.cnodes(r);
    //  Nós periféricos.
    g.pnodes(d);

  }
}
