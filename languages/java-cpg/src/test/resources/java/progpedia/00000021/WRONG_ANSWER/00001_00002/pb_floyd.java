import java.util.*;

class edge{
  int dest;
  int cap;
  edge(){this(0,0);}
  edge(int dest, int cap){
    this.dest = dest;
    this.cap = cap;
  }
}

class sol{
  Scanner kb;
  sol(Scanner kb){this.kb = kb;}
  int N,source,dest;
  int Lmin, Lmax, Cmin, Cmax, Amin;
  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
  ArrayList<LinkedList<edge>> adj = new ArrayList<LinkedList<edge>>();
  int find_node(int u){
    if(map.containsKey(u)) return map.get(u);
    else{
      adj.add(new LinkedList<edge>());
      int p = map.size();
      map.put(u,p);
      return p;
    }
  }
  void read(){
    adj.clear();
    map.clear();
    Lmin = kb.nextInt();
    Lmax = kb.nextInt();
    Cmin = kb.nextInt();
    Cmax = kb.nextInt();
    Amin = kb.nextInt();
    source = kb.nextInt()-1;
    dest = kb.nextInt()-1;
    source = find_node(source);
    dest = find_node(dest);
    while(true){
      int u = kb.nextInt()-1;
      if(u<0) break;
      int v = kb.nextInt()-1;
      int l = kb.nextInt();
      int c = kb.nextInt();
      int a = kb.nextInt();
      if(l>=Lmin && l<=Lmax && c>=Cmin && c<=Cmax && a>=Amin){
        int x = find_node(u);
        int y = find_node(v);
        adj.get(x).add(new edge(y,c));
        adj.get(y).add(new edge(x,c));        
      }
    }
    N = map.size();
  }
  void solve(){
    int mat[][] = new int[N][N];
    for(int u = 0; u < N; u++){
      for(edge e : adj.get(u)){
        if(e.cap>mat[u][e.dest]){
          mat[u][e.dest] = e.cap;
        }
      }
    }
    for(int k = 0; k < N; k++){
      for(int u = 0; u < N; u++){
        for(int v = 0; v < N; v++){
          mat[u][v] = Math.max(mat[u][v],Math.min(mat[u][k],mat[k][v]));
        }
      }
    }
    System.out.printf("%d\n",mat[source][dest]);
  }
}

public class pb_floyd{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.read();
    s.solve();
  }
}
