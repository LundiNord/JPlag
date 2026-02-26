import java.util.*;

class No
{
    int id;
    Troço [] adj;
    No(){}
    No(int id, int n)
    {
	this.id = id;
	adj = new Troço [n+1];
	for (int i =1; i<=n; i++)
	    {
		adj[i] = new Troço();
		adj[i].dest = -1; 
	    }
    }
    public void addTroço(Troço tNvo)
    {
	adj[tNvo.dest]=tNvo;
    }
}
class Troço
{
    int org, dest, lg, cmp, alt;
    Troço(){}
    Troço(int org, int dest, int lg, int cmp,int alt)
    {
	this.dest = dest;
	this.org = org;
	this.lg = lg;
	this.cmp = cmp;
	this.alt = alt;
    }
} 
class Jaula
{
    int  lgMin,lgMax, cmpMin, cmpMax, altMin;
    Jaula(){}
    Jaula (int lgMin,int lgMax, int cmpMin,int cmpMax,int altMin)
    {
	this.lgMin = lgMin;
	this.cmpMin = cmpMin;
	this.altMin = altMin;
	this.lgMax = lgMax;
	this.cmpMax = cmpMax;
    }
    
    public boolean passa(Troço n)
    {
	if(n.lg>= lgMin){
	    if(n.cmp>= cmpMin){
		if(n.alt >= altMin ){
		    return true;
		}
	    }
	}

	return false;
    }
}
    
class InfraEstrutura{
    
   public static void main (String args[]){
	int nLoc;
	Scanner stdin = new Scanner(System.in);
	nLoc = stdin.nextInt();
	int lgMin = stdin.nextInt();
	int lgMax = stdin.nextInt();
	int cmpMin = stdin.nextInt();
	int cmpMax = stdin.nextInt();
	int altMin = stdin.nextInt();
	Jaula jaula = new Jaula(lgMin,lgMax,cmpMin,cmpMax,altMin);
	int origem =  stdin.nextInt();
	int dest = stdin.nextInt();
	
	/*--------------------------------------------------------------------*/
	/*----------------------------Cria Grafo------------------------------*/
	/*--------------------------------------------------------------------*/
	No [] grafo = new No [nLoc+1];
	for(int i=1; i<=nLoc; i++){grafo[i] = new No (i,nLoc);}
	boolean tmpHa = true;
	while(tmpHa)
	    {
		tmpHa = false;
		int nOrig = stdin.nextInt();
	        if(nOrig != -1)
		    {
			tmpHa = true;
			int nDest = stdin.nextInt();
			int l = stdin.nextInt();
			int c = stdin.nextInt();
			int a = stdin.nextInt();
			Troço nvoTr = new Troço (nOrig,nDest,l,c,a); 
			grafo[nOrig].addTroço(nvoTr);
	        
		    } 
	}
	/*--------------------------------------------------------------------*/
	/*--------------------------------------------------------------------*/
	/*--------------------------------------------------------------------*/
	int n = numeroTroços(jaula, grafo, nLoc);
	System.out.println(n );
    }
    
    public static  int numeroTroços(Jaula ja, No[]gr, int n)
    {
	int conta =0;
	for (int i=1; i <= n; i++)
	    {
		for(int j =1; j<=n; j++)
		    {
		    if(gr[i].adj[j].dest != -1)
			{
			    if(ja.passa(gr[i].adj[j]))
				conta ++;
			}
		    }
	    }
	return conta;
    }
}
