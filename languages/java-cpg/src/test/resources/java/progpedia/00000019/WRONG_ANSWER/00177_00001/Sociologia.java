import java.util.*;

class No {
	int id;
	boolean visited;
	int pai;
	int tempof;
	int tempoi;
	LinkedList<No> adj;
	
	No (int i) {
		id = i;
		adj = new LinkedList<No> ();
		tempof=0;
		tempoi=0;
		visited = false;
		pai=-1;
	}
	public void adiciona (No amigo) {
		adj.addLast(amigo);
	}
}

class Grafo3 {
	int time;
	int nAlunos;
	No alunos [];
	No alunosT [];
	LinkedList<Integer> grupos;
	
	
	Grafo3 (int nA) {
		time =0;
		nAlunos = nA;
		alunos = new No [nAlunos+1];
		alunosT = new No [nAlunos+1];
		grupos = new LinkedList<Integer> ();
	}
	
	public void inicializa ( ) {
		for(int i=1; i<=nAlunos; i++) {
			alunos[i] = new No(i);
			alunosT[i] = new No (i);
			
		}
		
	}
	
	public void criaGrafo (Scanner kb) {
		inicializa();
		for(int i=0; i<nAlunos; i++) {
			int alunoid = kb.nextInt();
			int nAmigos = kb.nextInt();
			for(int j=0; j<nAmigos; j++) {
				int amigo = kb.nextInt();
				alunos[alunoid].adiciona(alunos[amigo]);
				alunosT[amigo].adiciona(alunosT[alunoid]);
				
			}
		}
	}
	public void DFS () {
		alunos[1].visited=true;
		
	
		for(int i=1; i<=nAlunos; i++) {
			if(!alunos[i].visited) {
				DFSvisit(alunos[i]);
				
			}
		}
	}
	private void DFSvisit (No u) {

		time++;
		u.tempoi=time;
		
		u.visited=true;
		for(No cursor: u.adj) {
			if(!cursor.visited) {
				cursor.pai=u.id;
				DFSvisit(cursor);
			}
		}
		time++;
		u.tempof=time;
		alunosT[u.id].tempof=time;
		// para aplicar depois o DFS ao grafo transposto por ordem de tempos finais
	}
	
	private boolean allVisited () {

		for(int i=1; i<nAlunos; i++) {
			if(!alunosT[i].visited)
				return false;
		}
		return true;
		
	}
	
	private No findMax () {
		
		int maximo = 0;
		No max = new No (0);
		
		for(int i=1; i<=nAlunos; i++) {
			if(!alunosT[i].visited) {
				if(alunosT[i].tempof > maximo) {
					maximo=alunosT[i].tempof;
					max = alunosT[i];
				}
			}
		}
		
		
		return max;
	}
	public void DFS_T () {

				while (!allVisited()) {
				
				No Tmax = findMax();
				System.out.println(Tmax.id);
				int nElementos = NelementosGrupo(Tmax);
				//System.out.println(nElementos);
				grupos.addLast(nElementos);
				Tmax.tempof=0;
				}
		}
	
	private int NelementosGrupo(No x){
		int count = 1;
		x.visited = true;
		for(No cursor: x.adj){
			
			if(cursor.visited==false)
				count +=NelementosGrupo(cursor);
		}
		return count;
	}
	
	
	
	
	public void output () {
		int nGrupos=0;
		int nfora=0;
		
		for(int cursor : grupos) {
		if(cursor>=4) 
			nGrupos++;
		else
			nfora+=cursor;
		
		}
		System.out.println(nGrupos + " " + nfora);
	}
}

	



public class Sociologia {

	
	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		int nCasos = kb.nextInt();
		//for(int i=1; i<=nCasos; i++) {
		int nAlunos = kb.nextInt();
		Grafo3 novo = new Grafo3 (nAlunos);
		novo.criaGrafo(kb);
		novo.DFS();
		novo.DFS_T();
		//System.out.println("Caso # " + i);
		novo.output();
		
	}

}
