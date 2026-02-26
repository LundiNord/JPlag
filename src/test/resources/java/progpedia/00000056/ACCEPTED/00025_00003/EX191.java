import java.util.*;

class edge implements Comparable<edge>{
  int u;
  int v;
  double cost;
  edge(){this(0,0,0);}
  public int compareTo(edge other){
    if(cost==other.cost) return 0;
    else return cost<other.cost ? -1 : 1;
  }
  public String toString(){ return "("+u+","+v+","+cost+")"; }
  edge(int u, int v, double c){this.u=u; this.v=v; this.cost=c;}
}



class disjoint_set{
  int p[];
  int rank[];
  disjoint_set(int N){
    p = new int[N];
    rank = new int[N];
    for(int i = 0; i < N; i++){
      p[i] = i;
      rank[i] = 0;
    }
  }
  int find_set(int x){
    if(p[x]!=x) p[x] = find_set(p[x]);
    return p[x];
  }
  void union(int x, int y){
    link(find_set(x),find_set(y));
  }
  void link(int x, int y){
    if(rank[x]>rank[y]) p[y] = x;
    else{
      p[x] = y;
      if(rank[x]==rank[y]) rank[y]++;
    }
  }
}



class sol{
    
  Scanner kb;
  sol(Scanner kb){this.kb = kb;}
  double points[][];
  
  double distance(int i, int k){
    double dx = points[k][0]-points[i][0];
    double dy = points[k][1]-points[i][1];
    return Math.sqrt(dx*dx+dy*dy);
  }
  
  void solve(){
    int N = kb.nextInt();
    points = new double[N][2];
    for(int i = 0; i < N; i++){
      points[i][0] = kb.nextDouble();
      points[i][1] = kb.nextDouble();
    }
    List<edge> edges = new ArrayList<edge>();
    for(int i = 0; i < N; i++){
      for(int k = i+1; k < N; k++){
        edges.add(new edge(i,k,distance(i,k)));
      }
    }
    Collections.sort(edges);
    disjoint_set S = new disjoint_set(N);
    int count = 0;
    double total = 0;
    for(edge e : edges){
      if(S.find_set(e.u)!=S.find_set(e.v)){
        S.union(e.u,e.v);
        total += e.cost;
        count++;
        if(count==N-1) break;
      }
    }
    System.out.printf("%.2f\n",total);
  }
}

public class EX191{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.solve();
  }
}