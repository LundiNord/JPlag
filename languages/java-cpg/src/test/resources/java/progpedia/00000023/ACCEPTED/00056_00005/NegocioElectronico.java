import java.util.*;

class Node {

	int regiao;
	int dist, custo;
	boolean visitado;
	LinkedList<Node> ligacoes;

	Node(int r, int d) {
		regiao = r;
		dist = d;
		ligacoes = new LinkedList<Node>();
		custo = Integer.MAX_VALUE;
		visitado = false;
	}
}

class Grafo {

	LinkedList<Node> arvore;

	Grafo() {
		arvore = new LinkedList<Node>();
	}

	void colocaGrafo(int r, int dist) {
		arvore.add(new Node(r, dist));
	}

	void adicionaLigacao(int o, int d, int dist) {
		for (Node n : arvore)
			if (n.regiao == o)
				n.ligacoes.add(new Node(d, dist));
	}

	void Dijkstra(int rdestino) {
		LinkedList<Node> fila = new LinkedList<Node>();
		for (Node t : arvore)
			if (t.regiao == rdestino)
				t.custo = 0;
		for (Node n : arvore)
			fila.add(n);
		while (fila.isEmpty() == false) {
			Node minimo = new Node(0, 0);
			for (Node n : fila)
				if (minimo.custo > n.custo)
					minimo = n;
			for (Node n : minimo.ligacoes) {
				for (Node t : arvore) {
					if (t.regiao == n.regiao) {

						if (t.custo > minimo.custo + n.dist)
							t.custo = minimo.custo + n.dist;
					}
				}
			}
			fila.remove(minimo);
		}
	}
}

public class NegocioElectronico {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int origem, destino, dist;
		Grafo grafo = new Grafo();

		int nRegioes = in.nextInt();
		int rDestino = in.nextInt();

		for (int i = 1; i <= nRegioes; i++)
			grafo.colocaGrafo(i, 0);

		origem = in.nextInt();
		destino = in.nextInt();
		dist = in.nextInt();

		while (origem != -1) {
			grafo.adicionaLigacao(origem, destino, dist);
			grafo.adicionaLigacao(destino, origem, dist);
			origem = in.nextInt();
			if (origem != -1) {
				destino = in.nextInt();
				dist = in.nextInt();
			}
		}

		grafo.Dijkstra(rDestino);
		int count = 0;
		for (int i = 0; i < grafo.arvore.size(); i++) {
			int minimo = Integer.MAX_VALUE;
			int regiao = 0;
			for (Node n : grafo.arvore) {
				if (minimo > n.custo && n.visitado == false) {
					minimo = n.custo;
					regiao = n.regiao;
				}
			}
			for (Node n : grafo.arvore){
				if (n.regiao == regiao) {
					n.visitado = true;
					count++;
					if (count == grafo.arvore.size())
						System.out.print(regiao);
					else
						System.out.print(regiao + " ");
				}
			}

		}
		System.out.println();
	}
}