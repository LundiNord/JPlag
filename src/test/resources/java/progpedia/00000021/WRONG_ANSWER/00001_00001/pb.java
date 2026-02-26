import java.util.*;

class heap_node{
  int pri;
  int key;
  heap_node(){this(0,0);}
  heap_node(int key, int pri){this.key = key; this.pri = pri;}
}

class heap{
  int size;
  int msize;
  int where_is[];
  heap_node data[];
  heap(int size){
    this.size = 0;
    this.msize = size;
    data = new heap_node[size+1];
    where_is = new int[size];//keys [0..size-1]
    for(int i = 0; i < size; i++) where_is[i] = -1;
  }
  void swap(int i, int k){
    heap_node t = data[i];
    data[i] = data[k];
    data[k] = t;
    where_is[data[i].key] = i;
    where_is[data[k].key] = k;
  }
  int max_child(int p){
    int l = p<<1, r = (p<<1)+1;
    if(l > size) return -1;
    if(r > size) return l;
    return (data[l].pri > data[r].pri) ? l : r;
  }
  void up_heap(int p){
    while(p>1 && data[p].pri>data[p>>1].pri){
      swap(p,p>>1);
      p>>=1;
    }
  }
  void down_heap(int p){
    while(p<=size){
      int m = max_child(p);
      if(m<0 || data[m].pri<=data[p].pri) break;
      swap(p,m);
      p = m;
    }
  }
  void make_heap(int size){
    this.size = size;
    int p = size>>1;
    while(p>0){
      down_heap(p);
      p--;
    }
  }
  void push(heap_node hn){
    size++;
    data[size] = hn;
    where_is[hn.key] = size;
    up_heap(size);
  }
  heap_node front(){
    return data[1];
  }
  heap_node pop(){
    where_is[data[1].key] = -1;
    data[0] = data[1];
    data[1] = data[size];
    where_is[data[1].key] = 1;
    size--;
    down_heap(1);
    return data[0];
  }
  void increase_key(int key, int pri){
    int p = where_is[key];
    if(p<0) return;
    data[p].pri = pri;
    up_heap(p);
  }
  int size(){ return this.size; }
  boolean empty(){ return this.size==0; }
}

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
        u = find_node(u);
        v = find_node(v);
        adj.get(u).add(new edge(v,c));
        adj.get(v).add(new edge(u,c));        
      }
    }
    N = map.size();
  }
  void solve(){
    boolean visited[] = new boolean[N];
    heap H = new heap(N);
    int max[] = new int[N];
    for(int i = 0; i < N; i++){
      max[i] = i!=source ? 0 : Integer.MAX_VALUE;
      H.push(new heap_node(i,max[i]));
    }
    while(!H.empty()){
      int u = H.pop().key;
      if(u==dest) break;
      visited[u] = true;
      for(edge e : adj.get(u)){
        int v = e.dest;
        if(visited[v]) continue;
        int m = Math.min(max[u],e.cap);
        if(m>max[v]){
          H.increase_key(v,m);
          max[v] = m;
        }
      }
    }
    System.out.printf("%d\n",max[dest]);
  }
}

public class pb{
  public static void main(String args[]){
    Scanner kb = new Scanner(System.in);
    sol s = new sol(kb);
    s.read();
    s.solve();
  }
}
