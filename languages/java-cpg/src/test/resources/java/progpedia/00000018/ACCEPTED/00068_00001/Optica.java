import java.util.*;
class Ligacoes{
	int ponto;
	LinkedList<Ligacoes> adj;
	int peso;
	boolean visitado;
	
	Ligacoes(int p){
		ponto = p;
		adj=new LinkedList<Ligacoes>();
		peso = Integer.MIN_VALUE;
		visitado = false;
	}
	void addLigacao(Ligacoes l){
		adj.addLast(l);
	}
}
class Grafo{
	int nos, nligacoes, custo;
	int G[][];
	static Ligacoes x[];
	
	Grafo(int nnos, int nl, int c){
		nos = nnos;
		nligacoes = nl;
		custo = c;
		G= new int[nnos+1][nnos+1];
		x= new Ligacoes[nnos+1];
	}
	
	private void inicializa(){
		for(int i = 1; i<=nos; i++){
			x[i]= new Ligacoes(i);
		}
	}
	void createGrafo(Scanner in){
		inicializa();
		for(int i = 0; i<nligacoes; i++){
			int p1 = in.nextInt();
			int p2 = in.nextInt();
			int rend = in.nextInt();
			G[p1][p2] = rend;
			G[p2][p1] = rend;
			x[p1].addLigacao(x[p2]);
			x[p2].addLigacao(x[p1]);
		}
	}

	void Prim(){
		x[1].peso = 0;
		
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux = new PriorityQueue<Integer>(nos, cmp);
		aux.add(1);
		
		while(!aux.isEmpty()){
			int u = aux.remove();
			if(!x[u].visitado){
				for(Ligacoes cursor : x[u].adj){
					int k = cursor.ponto;
					if(!x[k].visitado && cursor.peso < G[u][k]){ 
						cursor.peso = G[u][k];
						aux.add(k);
					}
				}
				x[u].visitado= true;
			}
		}
	}
	public static class Cmp implements Comparator<Integer>{
		public int compare(Integer z, Integer y){
			return x[y].peso - x[z].peso;
		}
	}
	void Output(){
		boolean conectado = true;
		int soma = 0;
		for(int i = 2; i<=nos; i++){
			if(x[i].peso == Integer.MIN_VALUE){
				conectado = false;
				break;
			}
			soma += x[i].peso;
		}
		if(!conectado){System.out.println("Impossivel");}
		else{
			int total = soma - (custo*(nos-1));
			System.out.println("Rendimento Optimo: " + total);
		}
	}
}
public class Optica {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nnos = in.nextInt();
		int nl = in.nextInt();
		int custo = in.nextInt();
		Grafo novo = new Grafo(nnos, nl, custo);
		novo.createGrafo(in);
		novo.Prim();
		novo.Output();
	}
}

/*
class SocA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
			int np = in.nextInt();
			GrafoA novo = new GrafoA(np);
			novo.criarGrafo(in);
			novo.DFS();
			novo.DFS_T();
			novo.amigo_um(np);
	}
}

(tudo dentro class grafo)
void criarGrafo(Scanner in){
		for(int i = 1; i<=npessoas; i++){
			g[i] = new NoA(i);
			gt[i] = new NoA(i);
		}
		for(int i = 1; i<=npessoas; i++){
			int namigo = in.nextInt();
			for(int j = 0; j<namigo; j++){
				int amigo = in.nextInt();
				g[i].addLigacao(g[amigo]); 
				gt[amigo].addLigacao(gt[i]);
			}
		}
	}

void amigo_um(int np){
		int amigos_um = 0;
		for(int amigo = 1; amigo<=np; amigo++){
			if(g[amigo].adj.contains(g[1])){
				amigos_um++;
			}
		}
		System.out.println(amigos_um); 
}
*/


/*
class SocB {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
			int np = in.nextInt();
			GraphB novo = new GraphB(np);
			novo.createGrafo(in);
			novo.DFS();
			novo.DFS_T();
			novo.BFS();
			novo.ProblemaB();


	}

}

class NoB{
	int no;
	int tempof;
	LinkedList<NoB> adj;
	boolean visitado;
	int dia;
...
}

(tudo dentro da class grafo)
void createGrafo(Scanner in){
	inicializar();
	for(int i = 1; i<=npessoas; i++){
		//int pessoa = in.nextInt();
		int namigo = in.nextInt();
		for(int j = 0; j<namigo; j++){
			int amigo = in.nextInt();
			g[i].addLigacao(g[amigo]);//trocamos pessoas por i 
			gt[amigo].addLigacao(gt[i]);
		}
	}
}
private void limpar(){
	for(int i=1; i<=npessoas; i++){
		g[i].visitado=false;
	}
}

void BFS(){
	limpar();
	LinkedList<NoB> lista = new LinkedList<NoB>();
	g[1].visitado=true;
	lista.addLast(g[1]);
		
	while(!lista.isEmpty()){
		NoB x = lista.removeFirst();
			for(NoB aux : x.adj){
				if(!aux.visitado){
					aux.visitado=true;
					aux.dia=x.dia+1;
					lista.addLast(aux);
				}
			}
	}	
}
	
private int findmaxday(){
	int max=0;
	for(int i=1; i<=npessoas; i++){
		if(g[i].dia>max) max=g[i].dia;
	}
	return max+1; //depois de descobrir quem soube no ultimo dia basta somar +1 a esse dia
}

void BFS(){
	for(int i=1; i<=npessoas; i++){
		g[i].visitado=false;
	}
	LinkedList<NoB> lista = new LinkedList<NoB>();
	g[1].visitado=true;
	lista.addLast(g[1]);
		
	while(!lista.isEmpty()){
		NoB x = lista.removeFirst();
			for(NoB aux : x.adj){
				if(!aux.visitado){
					aux.visitado=true;
					aux.dia=x.dia+1;
					lista.addLast(aux);
				}
			}
	}	
}

void amigo_um(int np, int qual){
	int amigos_um = 0;
	for(int amigo = 1; amigo<=np; amigo++){
		System.out.println("Passou " + amigo +" vezes");
		if(g[amigo].adj.contains(g[qual])){
			System.out.println( amigo + " Neste");
			amigos_um++;
		}
	}
	System.out.println(amigos_um); 
}
	
void ProblemaB(){
	System.out.println(findmaxday());
}
}*/


/*
class c {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int np = in.nextInt();
		GrafoC novo = new GrafoC(np);
		novo.criarGrafo(in);
		novo.DFS();
		novo.DFS_T();
		novo.output();
	}

}

(dentro class grafo)
void criarGrafo(Scanner in){
	for(int i = 1; i<=npessoas; i++){
		g[i] = new NoC(i);
		gt[i] = new NoC(i);
	}
	for(int i = 1; i<=npessoas; i++){
		int namigo = in.nextInt();
		for(int j = 0; j<namigo; j++){
			int amigo = in.nextInt();
			g[i].addLigacao(g[amigo]); 
			gt[amigo].addLigacao(gt[i]);
		}
	}
}

void output(){
	int ngrupinhos2 = grupos.size();
	System.out.println(ngrupinhos2);
}
*/



