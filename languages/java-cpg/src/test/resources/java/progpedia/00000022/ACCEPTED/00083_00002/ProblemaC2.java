import java.util.*;

class VerticesAdj{

    int vertice;
    VerticesAdj prox;

    VerticesAdj(int v,VerticesAdj p){
	vertice=v;
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

    void inserirLigacao(int v1,int v2){
	v[v1]=new VerticesAdj(v2,v[v1]);
	v[v2]=new VerticesAdj(v1,v[v2]);
    }

    void ntrocos(){
	int ntrocos=0;

	for(int i=1;i<=nverts;i++)
	    for(VerticesAdj p=v[i];p!=null;p=p.prox)
		ntrocos++;

	System.out.println(ntrocos/2);
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

	    if(almax>=lmin && acmax>=cmin && ahmax>=hmin)
		g.inserirLigacao(v1,v2);

	    v1=sc.nextInt();
	}

	g.ntrocos();
    }
}