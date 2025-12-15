import java.util.*;

class Node {

	int num;
	int peso;
	LinkedList<Node> ligacoes;
	boolean visitado;

	Node(int n, int p) {
		num = n;
		peso = p;
		ligacoes = new LinkedList<Node>();
		visitado = false;
	}
}

public class OticaMinimalista {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int custo = 0;

		LinkedList<Node> arvoreMinima = new LinkedList<Node>();

		int nNos = in.nextInt();
		int nLig = in.nextInt();
		int manutencao = in.nextInt();
		Node[] grafo = new Node[nNos + 1];
		for (int i = 0; i <= nNos; i++) {
			grafo[i] = new Node(i, 0);
		}

		for (int i = 1; i <= nLig; i++) {
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int peso = in.nextInt();
			grafo[n1].ligacoes.add(new Node(n2, peso));
			grafo[n2].ligacoes.add(new Node(n1, peso));
		}
		if (nLig < nNos - 1)
			System.out.println("impossivel");

		else {
			// Prim
			LinkedList<Node> fila = new LinkedList<Node>();
			fila.add(grafo[1]);
			while (fila.isEmpty() == false) {
				Node NoMax = new Node(0, -1);
				for (Node l : fila)
					if (l.peso >= NoMax.peso)
						NoMax = l;
				fila.remove(NoMax);
				for (Node n : NoMax.ligacoes) {
					if (n.peso > grafo[n.num].peso
							&& grafo[n.num].visitado == false) {
						grafo[n.num].peso = n.peso;
						if (fila.contains(grafo[n.num]) == false)
							fila.add(grafo[n.num]);
					}
				}
				grafo[NoMax.num].visitado = true;
				arvoreMinima.add(new Node(NoMax.num, NoMax.peso));
			}

			if (arvoreMinima.size() < nNos)
				System.out.println("impossivel");
			else {
				for (Node n : arvoreMinima)
					custo += n.peso;
				System.out.println("rendimento optimo: "
						+ (custo - ((nNos - 1) * manutencao)));
			}
		}
	}
}