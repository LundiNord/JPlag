import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Vertice {
	int aluno;
	int[] amigos;
	LinkedList<Integer> tamigos = new LinkedList<Integer>();
	int namigos;
	int cor;
	int tempo;
	
	Vertice(int aluno, int namigos) {
		this.aluno = aluno;
		this.amigos = new int[namigos];
	}
	
	void adicionaAmigo(int amigo) {
		amigos[namigos] = amigo;
		namigos++;
	}
	
	void adicionarAmigoTransposto(int amigo) {
		tamigos.add(amigo);
	}
}


public class Sociologia {
	private static Scanner in = new Scanner(System.in);
	private static int tempo;
	private static Stack<Integer> pilha = new Stack<Integer>();
	private static LinkedList<Integer> arvore;
	private static int grupos = 0;
	private static int sozinhos = 0;
	
	public static void main(String args[]) {
		
		int ncasos = in.nextInt();
		for(int i = 1; i < ncasos+1; i++) {
			int nalunos = in.nextInt();
			Vertice vertices[] = new Vertice[nalunos];
			vertices = recolherDados(nalunos, vertices);
			vertices = ordenar(vertices);
			vertices = DFS(vertices);
			
			vertices = calcularTransposta(vertices);
			DFS_invertido(vertices);
			
			System.out.println("Caso #" + i + "\n" + grupos + " " + sozinhos);
		}
		
		
	}
	
	
	private static void DFS_invertido(Vertice[] vertices) {
		grupos = 0;
		sozinhos = 0;
		Vertice u;
		for(Vertice v: vertices) {
			v.cor = 0;
		}
		while(!pilha.isEmpty()) {
			u = vertices[pilha.pop()-1];
			arvore = new LinkedList<Integer>();
			if(u.cor == 0) {
				arvore.add(u.aluno);
				vertices = DFS_visit_invertido(u, vertices);
			}
			
			if(arvore.size() >= 4)
				grupos++;
			else {sozinhos = sozinhos + arvore.size();}
		}
	}
	
	private static Vertice[] DFS_visit_invertido(Vertice v, Vertice[] vertices) {
		v.cor = 1;
		for(int amigo: v.tamigos) {
			if(vertices[amigo-1].cor == 0) {
				arvore.add(amigo);
				vertices = DFS_visit_invertido(vertices[amigo-1], vertices);
			}
		}
		return vertices;
	}
	
	
	
	
	private static Vertice[] calcularTransposta(Vertice[] vertices) {
		for(Vertice v: vertices) {
			for(int aluno: v.amigos) {
				vertices[aluno-1].adicionarAmigoTransposto(v.aluno);
			}
		}
		return vertices;
	}
	
	private static Vertice[] ordenar(Vertice[] vertices) {
		for(int i = 0; i < vertices.length-1; i++) {
			if(vertices[i+1].aluno < vertices[i].aluno) {
				Vertice temp = vertices[i];
				vertices[i] = vertices[i+1];
				vertices[i+1] = temp;
			}
		}
		return vertices;
	}
	
	
	
	private static Vertice[] DFS(Vertice[] vertices) {
		for(Vertice v: vertices) {
			v.cor = 0;
			v.tempo = 0;
		}
		tempo = 0;
		for(Vertice v: vertices) {
			if(v.cor == 0) {
				vertices = DFS_visit(v, vertices);
			}
		}
		return vertices;
	}
	
	private static Vertice[] DFS_visit(Vertice v, Vertice[] vertices) {
		v.cor = 1;
		tempo++;
		v.tempo = tempo;
		
		for(int amigo: v.amigos) {
			if(vertices[amigo-1].cor == 0) {
				vertices = DFS_visit(vertices[amigo-1], vertices);
			}
		}
		tempo++;
		v.tempo = tempo; 
		pilha.push(v.aluno);
		return vertices;
	}
	
	private static Vertice[] recolherDados(int nalunos, Vertice[] vertices) {
		for(int j = 0; j < nalunos; j++) {
			int id = in.nextInt();
			int namigos = in.nextInt();
			vertices[j] = new Vertice(id, namigos);
			for(int h = 0; h < namigos; h++) {
				vertices[j].adicionaAmigo(in.nextInt());
			}	
			
		}
		return vertices;
	}
}
