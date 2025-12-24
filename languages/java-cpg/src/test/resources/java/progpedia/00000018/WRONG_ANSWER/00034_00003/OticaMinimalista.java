//package com.pratica5.oticaminimalista;

import java.util.*;

class No {
	int val;
	int bruto;
	LinkedList<No> ligacoes;
	boolean visitado;

	No(int v, int b) {
		val = v;
		bruto = b;
		ligacoes = new LinkedList<No>();
		visitado = false;
	}
}

public class OticaMinimalista {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int nNos = in.nextInt();
		int nLigacoes = in.nextInt();
		int custo = in.nextInt();

		if (nLigacoes < nNos - 1)
			System.out.println("Impossivel");
		else {
			// Cria grafo
			No[] grafo = new No[nNos + 1];
			for (int i = 1; i <= nNos; i++)
				grafo[i] = new No(i, 0);

			// Adiciona ligacoes
			for (int i = 0; i < nLigacoes; i++) {
				int origem = in.nextInt();
				int destino = in.nextInt();
				int bruto = in.nextInt();

				grafo[origem].ligacoes.add(new No(destino, bruto));
				grafo[destino].ligacoes.add(new No(origem, bruto));
			}

			grafo[1].bruto = 0;
			LinkedList<No> lista = new LinkedList<No>();
			LinkedList<No> arvore = new LinkedList<No>();
			lista.add(grafo[1]);

			while (!lista.isEmpty()) {
				// Remove maximo
				No maximo = new No(0, -1);
				for (No i : lista)
					if (maximo.bruto < i.bruto)
						maximo = i;
				lista.remove(maximo);
				for (No i : maximo.ligacoes) {
					if (!grafo[i.val].visitado && grafo[i.val].bruto < i.bruto) {
						grafo[i.val].bruto = i.bruto;
						if (!lista.contains(grafo[i.val]))
								lista.add(grafo[i.val]);
					}
				}

				grafo[maximo.val].visitado = true;
				arvore.add(new No(maximo.val, maximo.bruto));
			}

			if (arvore.size() < nNos)
				System.out.println("Impossivel");
			else {
				// Rendimento total
				int total = 0;
				for (No i : arvore) {
					total += i.bruto;
					System.out.println("No: " + i.val);
					System.out.println("Custo: " + i.bruto);
				}
				System.out.println("rendimento optimo: " + (total - (2*(nNos-1))));
			}
		}
	}
}