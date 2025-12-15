/*-------------------------------------------------------------------*\
|  Definicao de grafos com pesos (int)                                |
|     Assume-se que os vertices sao numerados de 1 a |V|.             |
|                                                                     |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012    |
|   Last modified: 2013.01.03                                         |
\--------------------------------------------------------------------*/

import java.util.LinkedList;
import java.util.Scanner;

class Arco {
	int no_final;
	int valor;

	Arco(int fim, int v) {
		no_final = fim;
		valor = v;
	}

	int extremo_final() {
		return no_final;
	}

	int valor_arco() {
		return valor;
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

	public int num_vertices() {
		return nvs;
	}

	public int num_arcos() {
		return narcos;
	}

	public LinkedList<Arco> adjs_no(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int j, int valor_ij) {
		verts[i].adjs.addFirst(new Arco(j, valor_ij));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : adjs_no(i))
			if (adj.extremo_final() == j)
				return adj;
		return null;
	}
}

public class main {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int nLocais = stdin.nextInt();
		Grafo g = new Grafo(nLocais + 1);
		int lMinima = stdin.nextInt();
		int lMaxima = stdin.nextInt();
		int cMinimo = stdin.nextInt();
		int cMaximo = stdin.nextInt();
		int aMinima = stdin.nextInt();

		int origem = stdin.nextInt();
		int destino = stdin.nextInt();

		int ext1;
		int ext2;
		int lMaximaTroco;
		int cMaximoTroco;
		int aMaximaTroco;
		
		while ((ext1 = stdin.nextInt()) != -1) {
			ext2 = stdin.nextInt();
			lMaximaTroco = stdin.nextInt();
			cMaximoTroco = stdin.nextInt();
			aMaximaTroco = stdin.nextInt();
			if (lMaximaTroco >= lMinima && cMaximoTroco >= cMinimo
					&& aMaximaTroco >= aMinima) {
				g.insert_new_arc(ext1, ext2, lMaximaTroco);
				g.insert_new_arc(ext2, ext1, lMaximaTroco);
			}
		}
		System.out.println(g.num_arcos()/2);
	}

}
