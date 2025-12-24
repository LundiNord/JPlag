import java.io.*;
import java.util.*;

public class Problema21 {

  public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);
    int N = stdin.nextInt(); // N sardas.

    Graph g = new Graph(N);

    // Input
    for (int i = 1; i <= N; i++) {
      double x = stdin.nextDouble();
      double y = stdin.nextDouble();

      g.nodes[i].par = new Par(x, y);
    }

    // Generate adjacency lists
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        g.nodes[i].adj.add(g.nodes[j]);
      }
      g.nodes[i].adj.remove(g.nodes[i]);
    }

    // Calculate MST.
    g.prim(1);

    // Output
    double res = 0;

    /*    for (int i = 1; i <= N; i++) {
      res += g.nodes[i].dist;
      }*/

    /*System.out.printf("%.2f\n", res);*/
  }
}

// Classe que representa um grafo
class Graph {
  int N;           // Numero de nós do grafo.
  MSTNode nodes[]; // Array para conter os nós.

  Graph(int n) {
    N = n;
    nodes = new MSTNode[n + 1]; // +1 se os nós comecam em 1.

    for (int i = 1; i <= n; i++)
      nodes[i] = new MSTNode(i);
  }

  // MST - Algoritmo de Prim
  public void prim(int r) {
    PriorityQueue<MSTNode> q = new PriorityQueue<MSTNode>(N + 1);
    boolean visited[] = new boolean[N + 1];
    nodes[r].dist = 0.0;

    for (int i = 1; i <= N; i++) {
      q.add(nodes[i]);
      visited[i] = false;
    }

    double cost = 0;
    while (q.size() > 0) {
      MSTNode u = q.poll();

      if (!visited[u.index]) {
	//System.out.printf("%d %.2f\n", u.index, u.dist);
	visited[u.index] = true;
	cost += u.dist;
	
	ListIterator<MSTNode> it = u.adj.listIterator();
	while (it.hasNext()) {
	  MSTNode v = it.next();
	  
	  double peso = u.par.cost(v.par);
	  if (!visited[v.index] && peso < v.dist) {
	    MSTNode aux = new MSTNode(v.index);
	    aux.adj  = v.adj;
	    aux.dist = peso;
	    aux.par  = v.par;
	    q.add(aux);
	    
	  }
	}
      }
    }

    System.out.printf("%.2f\n", cost);
    
  }
}

// Classe que representa um MST no
class MSTNode implements Comparable<MSTNode> {
  public List<MSTNode> adj;
  public double dist;
  public MSTNode pai;
  public Par par;
  public int index;

  MSTNode(int i) {
    adj = new LinkedList<MSTNode>();
    dist = Double.MAX_VALUE;
    index = i;
  }

  public int compareTo(MSTNode that) {
    return Double.compare(this.dist, that.dist);
  }
}

class Par {
  double x, y;

  Par(double a, double b) {
    x = a;
    y = b;
  }

  public double cost(Par p2) {
    double xx = this.x - p2.x;
    double yy = this.y - p2.y;
    return Math.sqrt(xx * xx + yy * yy);
  }
}
