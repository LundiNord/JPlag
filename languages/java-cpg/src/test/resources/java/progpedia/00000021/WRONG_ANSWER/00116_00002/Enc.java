import java.util.*;

class Dimension{
    public int w;
    public int l;
    public int h;
    Dimension(int w, int l, int h){
	this.w=w;
	this.l=l;
	this.h=h;
    }
}

class Edge{
    int e;
    Dimension d;
    Edge(int e, Dimension d){
	this.e=e;
	this.d=d;
    }
}

class Sol{
    Scanner input;
    int index=-1;
    int wMax;
    int wMin;
    int lMax;
    int lMin;
    int hMin;
    int src;
    int des;
    ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>();
    Hashtable<Integer,Integer> t = new Hashtable<Integer,Integer>();
    Sol(Scanner input){this.input=input;}
    
    void read(){
	wMin = input.nextInt();
	wMax = input.nextInt();
	lMin = input.nextInt();
	lMax = input.nextInt();
	hMin = input.nextInt();
	src  = input.nextInt();
	des  = input.nextInt();
	
	while(input.hasNext()){
	    int s = input.nextInt();
	    if(s==-1)
		break;
	    s=handles(s);
	    int e = input.nextInt();
	    e=handles(e);
	    int w = input.nextInt();
	    int l = input.nextInt();
	    int h = input.nextInt();
	    if(wMin<=w && wMax>=w && lMin<=l && lMax>=l && hMin<=h){
		Dimension d = new Dimension(w,l,h);
		Edge e1 = new Edge(e,d);
		Edge e2 = new Edge(s,d);
		g.get(s).add(e1);
		g.get(e).add(e2);
	    }
	}
    }
    
    int handles(int s){
	if(t.containsKey(s))
	    return t.get(s);
	else{
	    index++;
	    g.add(new ArrayList<Edge>());
	    t.put(s,index);
	    return index;
	}
    }
	

    void solve(){
	//printGraph();
	int[] pred=dijkstra();
	/*for(int i=0; i<pred.length; i++)
	    System.out.print(+" ");*/
	System.out.println(pred[0]);
    }
    
    int[] dijkstra(){
	int[] dist = new int[g.size()];
	int[] visi = new int[g.size()];
	int[] pred = new int[g.size()];
	init(dist);
	
	for(int i=0; i<dist.length; i++){
	    int n = maxEdge(dist,visi);
	    //System.out.println(n);
	    visi[n]=1;
	    
	    for(int j=0; j<g.get(n).size(); j++){
		int f = g.get(n).get(j).e;
		int l = g.get(n).get(j).d.l;
		if(visi[f]==1)
		    continue;
		relax(dist, visi, pred,n,f,l);
	    }
	}
	return dist;
    }
    
    void relax(int[] dist, int[] visi, int[] pred, int n, int f, int l){
	int aux = Math.min(dist[n],l);
	//System.out.println("AUX: "+aux+" DIST "+dist[f]);
	if(aux>dist[f]){
	    dist[f]=aux;
	    pred[f]=n;
	}
    }
    
    int maxEdge(int[] dist, int[] visi){
	int ind=0;
	int max=0;
	for(int i=0; i<dist.length; i++){
	    //System.out.println(dist[i]);
	    if(visi[i]==0 && dist[i]>=max){
		ind=i;
		max=dist[i];
	    }
	}
	return ind;
    }
    
    void init(int[] dist){
	for(int i=0; i<dist.length; i++)
	    dist[i]=0;
	dist[t.get(src)]=Integer.MAX_VALUE;
    }
    
    void printGraph(){
	for(int i=0; i<g.size(); i++){
	    System.out.print(i+" -> ");
	    for(int j=0; j<g.get(i).size(); j++)
		System.out.print(g.get(i).get(j).e+"/"+g.get(i).get(j).d.l+" ");
	    System.out.println();
	}
    }
}

class Enc{
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	Sol s = new Sol(input);
	s.read();
	s.solve();
    }
}