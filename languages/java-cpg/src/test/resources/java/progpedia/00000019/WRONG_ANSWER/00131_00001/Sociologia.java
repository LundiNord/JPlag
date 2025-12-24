import java.util.*;

class Sol{
    Scanner input;
    Sol(Scanner input){this.input=input;}
    int nNodes;
    ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
    Deque<Integer> q = new LinkedList<Integer>(); 
    int[] flood;
    
    void read(){
	nNodes = input.nextInt();
	for(int i=0; i<nNodes+1; i++)
	    g.add(new ArrayList<Integer>());
	for(int i=0; i<nNodes; i++){
	    int n      = input.nextInt();
	    int nEdges = input.nextInt();
	    for(int j=0; j<nEdges; j++){
		int l = input.nextInt();
		g.get(n).add(l);
	    }
	}
    }
    
    void solve(){
	dfs();
	transpose();
	dfs2();
	printSol();
    }
    
    void dfs(){
	int v[] = new int[g.size()];
	for(int i=1; i<g.size(); i++)
	    runDFS(i,v);
    }
    
    void runDFS(int n, int[] v){
	v[n]=1;
	for(int j=0; j<g.get(n).size(); j++)
	    if(v[g.get(n).get(j)]==0)
		runDFS(g.get(n).get(j),v);
	if(!q.contains(n))
	    q.addLast(n);
    }
    
    void dfs2(){
	flood = new int[g.size()];
	int v[] = new int[g.size()];
	int i=0;
	while(!q.isEmpty()){
	    int n = q.removeFirst();
	    if(v[n]==0)
		runDFS2(n,v,flood,i);
	    i++;
	}
    }
    
    void printSol(){
	Arrays.sort(flood);
	int nGroups = 0;
	int nPeople = 0;
	int peopleG = 0;
	for(int i=0; i<flood.length; i++){
	    nPeople=0;
	    for(int j=1; j<flood.length; j++)
		if(flood[j]==i)
		    nPeople++;
	    if(nPeople>=4){
		nGroups++;
		peopleG+=nPeople;
	    }
	}
	System.out.println(nGroups+" "+(nNodes-peopleG));
    }
    
    void runDFS2(int n, int[] v, int[] flood,int i){
	v[n]=1;
	flood[n]=i;
	for(int j=0; j<g.get(n).size(); j++)
	    if(v[g.get(n).get(j)]==0)
		runDFS2(g.get(n).get(j),v,flood,i);
    }

    void transpose(){
	ArrayList<ArrayList<Integer>> g2 = new ArrayList<ArrayList<Integer>>();
	for(int i=0; i<g.size(); i++)
	    g2.add(new ArrayList<Integer>());
	for(int i=0; i<g.size(); i++)
	    for(int j=0; j<g.get(i).size(); j++)
		g2.get(g.get(i).get(j)).add(i);
	g=g2;
    }
//
//    void printGraph(){
//	for(int i=1; i<g.size(); i++){
//	    System.out.print(i+"-> ");
//	    for(int j=0; j<g.get(i).size(); j++)
//		System.out.print(g.get(i).get(j)+" ");
//	    System.out.println();
//	}
//    }
}

class Sociologia{
     public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	int nCases = input.nextInt();
	for(int k=0; k<nCases; k++){
	    System.out.println("Caso #"+(k+1));
	    Sol s = new Sol(input);
	    s.read();
	    s.solve();
	}
    }
}