import java.util.*;

class VerticesAdj{

    int vertice;
    boolean troco;
    VerticesAdj prox;

    VerticesAdj(int v,boolean t,VerticesAdj p){
	vertice=v;
	troco=t;
	prox=p;
    }
}

class Grafo{

    VerticesAdj v[];
    int nverts;
    
    Grafo(int nv){
	v=new VerticesAdj[nv+1];
	nverts=nv;

	for(int i=1;i<=nv;i++)
	    v[i]=null;
    }

    void inserirLigacao(int v1,boolean t,int v2){
	v[v1]=new VerticesAdj(v2,t,v[v1]);
	v[v2]=new VerticesAdj(v1,t,v[v2]);
    }

    void ntrocos(int origem,int destino){
	int trocos=0;

	boolean avisitada[][]=new boolean[nverts+1][nverts+1];
	for(int i=1;i<=nverts;i++)
	    for(int j=1;j<=nverts;j++)
		avisitada[i][j]=false;

	LinkedList<Integer>fila=new LinkedList<Integer>();
	fila.addLast(origem);

	while(fila.size()>0){
	    int i=fila.removeFirst();

	    for(VerticesAdj p=v[i];p!=null;p=p.prox)
		if(!avisitada[i][p.vertice]){
		    if(p.troco)
		       trocos++;
		    fila.addLast(p.vertice);
		    avisitada[i][p.vertice]=true;
		    avisitada[p.vertice][i]=true;
		}
	}

	System.out.println(trocos);
    }
}

class ProblemaC2{

    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);

	int nverts=sc.nextInt();
	int lmin=sc.nextInt(),lmax=sc.nextInt(),cmin=sc.nextInt(),cmax=sc.nextInt(),hmin=sc.nextInt();
	int origem=sc.nextInt(),destino=sc.nextInt();

	Grafo g=new Grafo(nverts);

	int v1=sc.nextInt();
	while(v1>0){
	    int v2=sc.nextInt();
	    int almax=sc.nextInt(),acmax=sc.nextInt(),ahmax=sc.nextInt();

	    g.inserirLigacao(v1,almax>=lmin && acmax>=cmin && ahmax>=hmin,v2);

	    v1=sc.nextInt();
	}

	g.ntrocos(origem,destino);
    }
}