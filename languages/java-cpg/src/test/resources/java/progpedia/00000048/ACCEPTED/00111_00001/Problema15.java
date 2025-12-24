import java.io.*;
import java.util.*;

/*
  Aqui vai uma solução mal cozinhada!
  Pode ser que funcione!
 */

public class Problema15 {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);

    Graph g = new Graph(in.nextInt());
    int e = in.nextInt();
    for (int i = 0; i < e; i++)
      g.addLink(in.nextInt(), in.nextInt());

    // Pesquisa em largura a partir do no 1
    for (int i = 1; i <= g.n; i++)
      if (!g.nodes[i].visited) {
	g.compNum++;
	g.dfs(i);
      }
      
    System.out.println(g.con());
  }
}

// Classe que representa um no
class Node {
  public LinkedList<Integer> adj;
  public boolean visited;
  public int distance;

  Node() {
    adj = new LinkedList<Integer>();
    visited = false;
    distance = -1;
  }
}

// Classe que representa um grafo
class Graph {
  int n;        // Numero de nos do grafo
  Node nodes[]; // Array para conter os nos
  int compNum = 0;

  Graph(int n) {
    this.n = n;
    nodes = new Node[n + 1]; // +1 se os comecam em 1 ao inves de 0
    for (int i = 1; i <= n; i++)
      nodes[i] = new Node();
  }

  // Algoritmo de pesquisa em profundidade
  public void dfs(int v) {
    nodes[v].visited = true;
    for (int w : nodes[v].adj) {
      if (!nodes[w].visited) {
	nodes[w].visited = true;
	dfs(w);
      }
    }
  }

  public int con() { return compNum; }

  // Algoritmo de pesquisa em largura
  public void bfs(int v) {
    LinkedList<Integer> q = new LinkedList<Integer>();

    q.add(v);
    nodes[v].visited = true;
    nodes[v].distance = 0;

    while (q.size() > 0) {
      int u = q.removeFirst();
      System.out.println(u + " [dist=" + nodes[u].distance + "]");
      for (int w : nodes[u].adj)
        if (!nodes[w].visited) {
          q.add(w);
          nodes[w].visited = true;
          nodes[w].distance = nodes[u].distance + 1;
        }
    }
  }

  public void addLink(int a, int b) {
    nodes[a].adj.add(b);
    nodes[b].adj.add(a);
  }
}
