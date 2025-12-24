//package com.pratica5.encomenda;
import java.util.*;

class No {
	int comp;
	int val;

	No(int v) {
		val = v;
		comp = 0;
	}
}

class Caminho {
	int maxLarg, maxComp, maxAlt;
	int origem, destino;

	Caminho(int l, int c, int a, int o, int d) {
		maxLarg = l;
		maxComp = c;
		maxAlt = a;
		origem = o;
		destino = d;
	}
}

class Grafo {
	LinkedList<No> nos;
	LinkedList<Caminho> caminhos;

	Grafo() {
		nos = new LinkedList<No>();
		caminhos = new LinkedList<Caminho>();
	}

	void AdicionaNo(int v) {
		for (No i : nos) {
			if (i.val == v)
				return;
		}
		nos.add(new No(v));
	}

	void AdicionaCaminho(int l, int c, int a, int o, int d) {
		caminhos.add(new Caminho(l, c, a, d, o));
		caminhos.add(new Caminho(l, c, a, o, d));
	}
}

public class Encomenda {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Grafo grafo = new Grafo();

		int minLarg = in.nextInt();
		int maxLarg = in.nextInt();
		int minComp = in.nextInt();
		int maxComp = in.nextInt();
		int minAlt = in.nextInt();

		int origem = in.nextInt();
		int destino = in.nextInt();

		int a = in.nextInt();

		while (a != -1) {
			int b = in.nextInt();
			int larg = in.nextInt();
			int comp = in.nextInt();
			int alt = in.nextInt();

			grafo.AdicionaNo(a);
			grafo.AdicionaNo(b);
			grafo.AdicionaCaminho(larg, comp, alt, a, b);

			a = in.nextInt();
		}

		// Dijkstra
		LinkedList<No> nos = new LinkedList<No>();
		for (No i : grafo.nos) {
			if (i.val == origem)
				i.comp = maxComp;
			nos.add(i);
		}

		while (!nos.isEmpty()) {
			// EXTRACT-MAX
			No maximo = new No(0);
			maximo.comp = 0;
			for (No i : nos)
				if (maximo.comp <= i.comp)
					maximo = i;
			nos.remove(maximo);

			for (Caminho c : grafo.caminhos) {
				if (c.origem == maximo.val) {
					if (ExisteNo(nos, c.destino) && c.maxAlt >= minAlt && c.maxComp >= minComp && c.maxLarg >= minLarg) {
						int iOrig = EncontraNo(grafo.nos, c.origem);
						int iDest = EncontraNo(grafo.nos, c.destino);
						int mComp = Math.min(grafo.nos.get(iOrig).comp, c.maxComp);

						grafo.nos.get(iDest).comp = Math.max(mComp, grafo.nos.get(iDest).comp);
					}
				}
			}
		}
		System.out.println(grafo.nos.get(EncontraNo(grafo.nos, destino)).comp);
	}

	static boolean ExisteNo(LinkedList<No> nos, int v) {
		for (No i : nos)
			if (i.val == v)
				return true;
		return false;
	}

	static int EncontraNo(LinkedList<No> nos, int v) {
		for (int i = 0; i < nos.size(); i++) {
			if (nos.get(i).val == v)
				return i;
		}
		return -1;
	}
}