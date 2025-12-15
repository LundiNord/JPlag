import java.io.*;
import java.util.*;

class daa19 {

    public static void main (String args[]) {
	Scanner in = new Scanner (System.in);
	int numnos = in.nextInt();
	int arestas = in.nextInt();
	Arvore tree = new Arvore(numnos);

	for(int i = 0; i < arestas; i++) {
	    tree.addAresta(in.nextInt(), in.nextInt());
	}

	for(int i = 1; i <= numnos; i++){
	    tree.limpar();
	    tree.bfs(i);
	}

	tree.diametro();
	tree.raio();
	tree.noscentrais();
	tree.nosperi();
    }

}

class Arvore {
    int numnos;
    No nos[];
    int matriznos[][];
    int diametro;
    int raio;


    Arvore(int n) {
	numnos = n;
	matriznos = new int[n+1][n+1];
	nos = new No[n+1];
	for(int i = 1; i<=numnos; i++) {
	    nos[i] = new No();
	}
    }
    public void addAresta(int a, int b) {
	nos[a].adj.add(b);
	nos[b].adj.add(a);
    }


    public void limpar() {
	for(int i = 1; i <= numnos; i++) {
	    nos[i].visitado = false;
	}
    }

    public void bfs(int v) {
	LinkedList<Integer> temp = new LinkedList<Integer>();
	temp.add(v);
	nos[v].visitado = true;
	nos[v].distancia = 0;

	while(temp.size() > 0) {
	    int y = temp.removeFirst();
	    matriznos[v][y] = nos[y].distancia;
	    for(int x : nos[y].adj) {
		if(nos[x].visitado == false) {
		    temp.add(x);
		    nos[x].visitado = true;
		    nos[x].distancia = nos[y].distancia + 1;
		}
	    }
	}
    }

    public void diametro() {
	diametro = 0;
	for(int i = 1; i <= numnos; i++) {
	    for(int j = 1; j <= numnos; j++) {
		if(matriznos[i][j] > diametro)
		    diametro = matriznos[i][j];
	    }
	}
	System.out.println(diametro);
    }

    public void raio() {
	raio = diametro;

	for(int i = 1; i <= numnos; i++) {
	    int cont = 0;
	    for(int j = 1; j <= numnos; j++) {
		if(matriznos[i][j] > cont)
		    cont = matriznos[i][j];
	    }
	    if(cont < raio)
		raio = cont;
	}
	System.out.println(raio);
    }

    public void noscentrais() {

	boolean vis = true;
	for(int i = 1; i <= numnos; i++) {
	    int cont = 0;
	    for(int j = 1; j <= numnos; j++) {
		if(matriznos[i][j] > cont)
		    cont = matriznos[i][j];
	    }
	    if(cont == raio && vis) {
		vis = false;
		System.out.print(i);
	    }
	    else if(cont == raio)
		System.out.print(" " + i);


	}
	System.out.println();
    }


    public void nosperi() {

	boolean vis = true;
	for(int i = 1; i <= numnos; i++) {
	    int cont = 0;
	    for(int j = 1; j <= numnos; j++) {
		if(matriznos[i][j] > cont)
		    cont = matriznos[i][j];
	    }
	    if(cont == diametro && vis) {
		vis = false;
		System.out.print(i);
	    }
	    else if(cont == diametro)
		System.out.print(" " + i);
	}
	System.out.println();
    }

}

class No {
    public LinkedList<Integer> adj;
    public boolean visitado;
    public int distancia;

    No() {
	adj = new LinkedList<Integer>();
	visitado = false;
	distancia = -1;
    }

}



