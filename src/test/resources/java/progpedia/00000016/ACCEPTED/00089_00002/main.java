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
	// char val;
	// boolean visitado;
	LinkedList<Arco> adjs;

	No() {
		// this.val = val;
		// visitado = false;
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
	static LinkedList<Integer> l = new LinkedList<Integer>();

	public static void DFS(Grafo g, int v) {
		l.add(v);
		char val = 0;
		val = (char) ((char) v + 'A');
		System.out.print(val);
		
		for (Arco a : g.adjs_no(v)) {
			if (!l.contains(a.no_final)) {
				DFS(g, a.no_final);
			}
		}

	}

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		String s1 = stdin.nextLine();
		String s2 = stdin.nextLine();
		int min = Math.min(s1.length(), s2.length());
		int flag = 0;
		char diff = 0;
		Grafo g = new Grafo(27);
		for (int i = 0; i < min; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);

			if (c1 != c2) {
				diff = c1;
				g.insert_new_arc(c1 - 'A', c2 - 'A', 0);

				flag = 1;
				// System.out.println(c1 + "" + c2);
				break;
			}

		}
		if (flag == 1) {
			DFS(g, diff - 'A');
			System.out.println();
		} else if (flag == 0) {
			System.out.println("Nenhum");
		}
	}

}
