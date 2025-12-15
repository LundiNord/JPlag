import java.util.*;

class VerticesAdj { //os objectos desta classe serao guardados em listas (cada um e no duma lista). Cada uma das listas tera todos vertices que tem em comum um vertice que que lhes esta ligado no grafo e tambem sera guardado o custo dessas ligacoes (arestas) nos vertices correspondentes.

    int vertice; 
    int custo;   
    VerticesAdj prox;

    VerticesAdj(int v,int c,VerticesAdj p){
	vertice=v;
	custo=c;
	prox=p;
    }

}

/****************Algoritmo de Kruskal****************/
class Aresta { //os objectos desta classe serao guardados num vector que contem todas as arestas do grafo por ordem crescente de custo.

    int vertice1;
    int vertice2;
    int custo;

    Aresta(int v1,int v2,int c){
	vertice1=v1;
	vertice2=v2;
	custo=c;
    }
}
/****************Algoritmo de Kruskal****************/

class Grafo {

    VerticesAdj v[]; //v[i] tem todos os vertices que estao ligados ao vertice i
    int nverts; 
    int narestas;

    private boolean vvisitado[]; //vvisitado[i] e um vector auxiliar para a pesquisa em profundidade para ser possivel verificar se o grafo esta ou nao dividido em subgrafos (ver se tem conectividade).

    /****************Algoritmo de Kruskal****************/
    private int k; //indica quantas arestas ja foram adicionadas no grafo quando este ainda esta em construcao
    private Aresta custoDecres[]; //e um vector que contem todas as arestas do grafo por ordem decrescente de custo
    private Aresta a[]; //vector auxiliar para ajudar a ordenar o vector custoDrecres[] por ordem decrescente de custo
    
    private int paiConj[]; //e um vector que contem todas as arvores (conjuntos), por outras palavras, e a floresta. Cada conjunto e representado como o primeiro elemento que o conjunto tem. O primeiro elemento de um conjunto sera considerado o pai de todos os elementos desse mesmo conjunto. Os conjuntos serao sempre todos disjuntos, logo nunca terao um elemento em comum e nunca havera incoerencia. Cada elemento e um vertice do grafo.
    /****************Algoritmo de Kruskal****************/

    Grafo(int nv,int na){
	nverts=nv;
	narestas=na;

	v=new VerticesAdj[nv+1];
	for(int i=1;i<=nv;i++)
	    v[i]=null;

	vvisitado=new boolean[nv+1];

	k=0;
	custoDecres=new Aresta[na];
	a=new Aresta[na];
	paiConj=new int[nv+1];
    }

    void inserirLigacao(int v1,int v2,int c){
	v[v1]=new VerticesAdj(v2,c,v[v1]);
	v[v2]=new VerticesAdj(v1,c,v[v2]);

	custoDecres[k]=new Aresta(v1,v2,c);
	k++;
    }

    boolean haConectividade(){
	for(int i=1;i<=nverts;i++)
	    vvisitado[i]=false;
	pesquisaEmProfundidade(1);

	for(int i=1;i<=nverts;i++)
	    if(!vvisitado[i])
		return false;
	return true;
    }
    
    private void pesquisaEmProfundidade(int i){
	vvisitado[i]=true;
	for(VerticesAdj p=v[i];p!=null;p=p.prox)
	    if(!vvisitado[p.vertice])
		pesquisaEmProfundidade(p.vertice);
    }

    /****************Algoritmo de Kruskal****************/
    private void mergesortCustoDecres(int esq,int dir){
	int meio;

	if(esq<dir){
	    meio=(esq+dir)/2;
	    mergesortCustoDecres(esq,meio);
	    mergesortCustoDecres(meio+1,dir);
	    juntar(esq,meio,dir);
	}
    }
    private void juntar(int esq,int meio,int extremidadeD){
	int extremidadeE=meio;
	int cursor=esq;
	int cursorE=esq;
	int cursorD=meio+1;

	while(cursorE<=extremidadeE && cursorD<=extremidadeD){
	    if(custoDecres[cursorE].custo>custoDecres[cursorD].custo){
		a[cursor]=custoDecres[cursorE];
		cursorE++;
	    }
	    else{
		a[cursor]=custoDecres[cursorD];
		cursorD++;
	    }
	    cursor++;
	}

	while(cursorE<=extremidadeE){
	    a[cursor]=custoDecres[cursorE];
	    cursorE++;
	    cursor++;
	}
	while(cursorD<=extremidadeD){
	    a[cursor]=custoDecres[cursorD];
	    cursorD++;
	    cursor++;
	}

	//actualizar o vector custoDecres[]
	for(int i=esq;i<=extremidadeD;i++)
	    custoDecres[i]=a[i];
    }

    private void criarConjunto(int v){
	paiConj[v]=v;
    } 
 
    private int encontrarConjunto(int v){ 
	return paiConj[v];
    }
    
    private void unirConjuntos(int p1, int p2) {
	int paip2=paiConj[p2];
	for(int i=1;i<=nverts;i++)
	    if(paiConj[i]==paip2)  
		paiConj[i]=paiConj[p1];
    }
    
    int rendMax(){
	mergesortCustoDecres(0,narestas-1);

	int optimo=0;

	for(int i=1;i<=nverts;i++)
	    criarConjunto(i);

	for(int i=0;i<narestas;i++){ 
	    int v1=custoDecres[i].vertice1;
	    int v2=custoDecres[i].vertice2;

	    if(encontrarConjunto(v1)!=encontrarConjunto(v2)) {
		optimo+=custoDecres[i].custo;
		unirConjuntos(v1,v2);
	    }
	}

	return optimo; 
    }
    /****************Algoritmo de Kruskal****************/
}

class ProblemaA3 {

    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int nverts,narestas,cManutLig;
	int v1,v2,c;

	nverts=sc.nextInt();
	narestas=sc.nextInt();
	cManutLig=sc.nextInt();
	Grafo grafo=new Grafo(nverts,narestas);

	for(int i=0;i<narestas;i++){
	    v1=sc.nextInt();
	    v2=sc.nextInt();
	    c=sc.nextInt();

	    grafo.inserirLigacao(v1,v2,c-cManutLig);
	}

	if(!grafo.haConectividade())
	    System.out.println("impossivel");
	else
	    System.out.println("rendimento optimo: "+grafo.rendMax());
    }
}
