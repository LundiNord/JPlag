//package negocioeletronico;

import java.util.*;

class Zona {
	int valor, dist, custo;
	LinkedList<Zona> ligacoes;
	boolean visitado;

	Zona(int val, int d) {
		this.valor = val;
		ligacoes = new LinkedList<Zona>();
		dist = d;
		custo = Integer.MAX_VALUE;
		visitado = false;
	}
}

public class NegocioEletronico {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Zona[] grafo = new Zona[n + 1];
		int destino = in.nextInt();

		for (int i = 1; i < n + 1; i++) {
			grafo[i] = new Zona(i, 0);
		}

		int orig, dest, dist;

		orig = in.nextInt();
		dest = in.nextInt();
		dist = in.nextInt();

		while (orig != -1) {
			grafo[orig].ligacoes.add(new Zona(dest, dist));
			grafo[dest].ligacoes.add(new Zona(orig, dist));

			orig = in.nextInt();
			if (orig != -1) {
				dest = in.nextInt();
				dist = in.nextInt();
			}
		}

		// DIJKSTRA
		LinkedList<Zona> zonas = new LinkedList<Zona>();
		grafo[destino].custo = 0;
		for (int i = 1; i < n + 1; i++) {
			zonas.add(grafo[i]);
		}

		while (!zonas.isEmpty()) {
			// Minimo
			Zona minimo = new Zona(0, 9);
			for (Zona i : zonas) {
				if (i.custo < minimo.custo) {
					minimo = i;
				}
			}
			zonas.remove(minimo);
			if (zonas.size() == 0)
				System.out.println(minimo.valor);
			else
				System.out.print(minimo.valor + " ");
			for (Zona i : minimo.ligacoes) {
				Zona z = EncontraZona(i, zonas);
				if (z != null && minimo.custo + i.dist < z.custo) {
					z.custo = minimo.custo + i.dist;
					grafo[z.valor] = z;
				}
			}
		}
                /* for (Zona i : grafo) {
                    if (i!=null) {
                    System.out.println (i.valor + " custo " + i.custo + " " + i.dist );
                    }
                } */
	}

	static Zona EncontraZona(Zona i, LinkedList<Zona> lista) {
		for (Zona n : lista)
			if (n.valor == i.valor)
				return n;
		return null;
	}
}
