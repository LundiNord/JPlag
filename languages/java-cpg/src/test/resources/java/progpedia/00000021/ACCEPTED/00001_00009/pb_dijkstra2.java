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
  int N,src,dst;
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
    src = find_node(kb.nextInt());
    dst = find_node(kb.nextInt());
    while(true){
      int t = kb.nextInt();
      if(t<0) break;
      int u = find_node(t);
      int v = find_node(kb.nextInt());
      int l = kb.nextInt();
      int c = kb.nextInt();
      int a = kb.nextInt();
      if(l>=Lmin && c>=Cmin && a>=Amin){
        if(c>Cmax) c = Cmax;
        adj.get(u).add(new edge(v,c));
        adj.get(v).add(new edge(u,c));        
      }
    }
    N = map.size();
  }
  void solve(){
    boolean visited[] = new boolean[N];
    int max[] = new int[N];
    for(int i = 0; i < N; i++){
      max[i] = i!=src ? 0 : Integer.MAX_VALUE;
    }
    int count = 0;
    while(count < N){
      int u = -1;
      for(int i = 0; i < N; i++){
        if(!visited[i] && (u<0 || max[i]>max[u])) u = i;
      }
      if(u==dst) break;
      count++;
      visited[u] = true;
      for(edge e : adj.get(u)){
        int v = e.dest;
        if(visited[v]) continue;
        int m = Math.min(max[u],e.cap);
        if(m>max[v]){
          max[v] = m;
        }
      }
    }
    System.out.printf("%d\n",max[dst]);
  }
}

public class pb_dijkstra2{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.read();
    s.solve();
  }
}
