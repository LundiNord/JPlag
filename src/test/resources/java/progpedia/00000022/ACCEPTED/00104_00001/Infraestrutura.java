import java.util.LinkedList;
import java.util.Scanner;

class Arco {
	int noFinal;
	int largMax, compMax, altMax;

	Arco(int fim, int l, int c, int a) {
		noFinal = fim;
		largMax = l;
		compMax = c;
		altMax = a;
	}

	public int getLargMax() {
		return largMax;
	}

	public int getCompMax() {
		return compMax;
	}

	public int getAltMax() {
		return altMax;
	}

	public int getNoFinal() {
		return noFinal;
	}
}

class No {
	// int label;
	LinkedList<Arco> adjs;

	No() {
		adjs = new LinkedList<Arco>();
	}
}

class Grafo {
	No verts[];
	int nvs, narcos;

	public Grafo(int n) {
		nvs = n;
		narcos = 0;
		verts = new No[n + 1];
		for (int i = 0; i <= n; i++)
			verts[i] = new No();
		// para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
	}

	public int getNumVertices() {
		return nvs;
	}

	public int getNumArcos() {
		return narcos;
	}

	public LinkedList<Arco> getAdjsNo(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int f, int l, int c, int a) {
		verts[i].adjs.addFirst(new Arco(f, l, c, a));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : getAdjsNo(i))
			if (adj.getNoFinal() == j)
				return adj;
		return null;
	}
}

public class Infraestrutura {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int nVerts, largMin, largMax, compMin, compMax, altMin, origem, destino;

		nVerts = input.nextInt();
		largMin = input.nextInt();
		largMax = input.nextInt();
		compMin = input.nextInt();
		compMax = input.nextInt();
		altMin = input.nextInt();
		origem = input.nextInt();
		destino = input.nextInt();

		Grafo grafo = construirGrafo(nVerts, input);
		// for(int i = 0; i<grafo.getAdjsNo(1).size(); i++)
		// System.out.println(grafo.getAdjsNo(1).get(i).noFinal);
		arcosPossiveis(grafo, largMin, largMax, compMin, compMax, altMin);
	}

	static void arcosPossiveis(Grafo g, int lMin, int lMax, int cMin, int cMax, int aMin) {
		int arcosPossiveis = 0;
		for (No n : g.verts){
			for (Arco a : n.adjs){
				if(a.getLargMax()>=lMin && a.getCompMax()>=cMin && a.getAltMax()>=aMin)
					arcosPossiveis++;
			}
		}
		System.out.println(arcosPossiveis/2);
	}

	static Grafo construirGrafo(int nVerts, Scanner input) {
		Grafo grafo = new Grafo(nVerts);

		int noI = input.nextInt(), noF, larg, comp, alt;
		while (noI != -1) {
			noF = input.nextInt();
			larg = input.nextInt();
			comp = input.nextInt();
			alt = input.nextInt();

			grafo.insert_new_arc(noI, noF, larg, comp, alt);
			grafo.insert_new_arc(noF, noI, larg, comp, alt);

			noI = input.nextInt();
		}
		return grafo;
	}
}
