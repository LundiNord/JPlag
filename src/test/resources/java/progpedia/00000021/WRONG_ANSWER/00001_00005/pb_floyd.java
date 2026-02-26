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
      //System.out.printf("$$$%d\n",p);
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
    source = find_node(kb.nextInt());
    dest = find_node(kb.nextInt());
    while(true){
      int t = kb.nextInt();
      if(t==-1) break;
      int u = find_node(t);
      int v = find_node(kb.nextInt());
      //System.out.printf(">%d %d\n",u,v);
      int l = kb.nextInt();
      int c = kb.nextInt();
      int a = kb.nextInt();
      if(l>=Lmin && l<=Lmax && c>=Cmin && c<=Cmax && a>=Amin){
        adj.get(u).add(new edge(v,c));
        adj.get(v).add(new edge(u,c));        
      }
    }
    N = adj.size(); 
    //System.out.printf("%s\n",map);
    //System.out.printf("%d\n",N);
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
    if(source==dest) System.out.printf("%d\n",Cmax);
    else if(source>=N || dest>=N) System.out.printf("%d\n",0);
    else System.out.printf("%d\n",mat[source][dest]);
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
