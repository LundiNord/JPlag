import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class SopaLetras {
	
public static void main (String[] args) {
		
		Scanner console = new Scanner(System.in);
		
		Grafo grafo = new Grafo();

		String x = console.next();
		String y = console.next();
		for (int i=0; i<x.length() && i<y.length(); i++) {
			if (y.charAt(i) != x.charAt(i)) {
				grafo.addEdge(x.charAt(i), y.charAt(i));
				break;
			}
		}
		console.close();
		
		
		String k = grafo.topologicalSort();
		if (k.length() == 0) {
			System.out.println("Nenhum");
		}
		else {
			System.out.println(k);
		}
	}
}


class Grafo {
	Vertice[] grafo;
	private String topologicalSort;
	
	public Grafo () {
		grafo = new Vertice[26];
		for (int i=0; i<grafo.length; i++) {
			grafo[i] = new Vertice((char)(i+'A'));
		}
		topologicalSort = "";
	}
	
	public void addEdge (char i, char j) {
		grafo[i-'A'].ligacoes.addLast(grafo[j-'A']);
		grafo[i-'A'].vst = true;
		grafo[j-'A'].vst = true;
	}
	
	private void dfs () {
		for (int i=0; i<grafo.length; i++) {
			if (grafo[i].vst) {
				dfsvisit(grafo[i]);
			}
		}
	}
	
	private void dfsvisit (Vertice v) {
		v.vst = false;
		Iterator<Vertice> itera = v.ligacoes.iterator();
		while (itera.hasNext()) {
			Vertice x = itera.next();
			if (x.vst) {
				dfsvisit(x);
			}
		}
		topologicalSort = v.data + topologicalSort;
	}
	
	public String topologicalSort () {
		dfs();
		return topologicalSort;
	}
	
	public String toString () {
		String k = "";
		for (int i=0; i<grafo.length; i++) {
			k+=grafo[i].tostring() + "\n";
		}
		return k;
	}
	
	private class Vertice {
		char data;
		boolean vst;
		LinkedList<Vertice> ligacoes;
		
		public Vertice (char d) {
			data = d;
			vst = false;
			ligacoes = new LinkedList<Vertice> ();
		}
		
		public String tostring () {
			return (data) + " -> " + ligacoes.toString();
		}
		
		public String toString() {
			return data + "";
		}
	}
	
}

