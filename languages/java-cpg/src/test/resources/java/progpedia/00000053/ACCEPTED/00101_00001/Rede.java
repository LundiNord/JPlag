import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Rede {

	public static class Node{

		int node;
		int excentricity;
		boolean visited;
		LinkedList<Integer> neighbor;

		public Node(int node, int n) {
			this.node = node;
			visited = false;
			neighbor = new LinkedList<Integer>();
			neighbor.add(n);
		}

	}

	public static void main(String[] args) {

		int nNos, nArestas, a, b;

		Scanner in = new Scanner(System.in);

		nNos = in.nextInt();

		nArestas = in.nextInt();

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		for(int i = 0; i < nArestas; i++){

			a = in.nextInt();
			b = in.nextInt();

			if(!nodes.containsKey(a)){
				nodes.put(a, new Node(a,b));
			}
			else{
				nodes.get(a).neighbor.add(b);
			}

			if(!nodes.containsKey(b)){
				nodes.put(b, new Node(b,a));
			}
			else{
				nodes.get(b).neighbor.add(a);
			}
		}


		int table [][] = new int[nNos+1][nNos+1];
		doYourShit(nodes, table);

		int maxExcent = 0;
		int minExcent = nArestas+1;

		LinkedList<Integer> nodesMax = new LinkedList<Integer>();
		LinkedList<Integer> nodesMin = new LinkedList<Integer>();

		for(int i = 1; i <= nodes.size(); i++){
			Node aux = nodes.get(i);
			if(aux.excentricity > maxExcent){
				maxExcent = aux.excentricity;
				nodesMax = new LinkedList<Integer>();
				nodesMax.add(aux.node);
			}
			else if(aux.excentricity == maxExcent){
				nodesMax.add(aux.node);
			}

			if(aux.excentricity < minExcent){
				minExcent = aux.excentricity;
				nodesMin = new LinkedList<Integer>();
				nodesMin.add(aux.node);
			}
			else if(aux.excentricity == minExcent){
				nodesMin.add(aux.node);
			}
		}

		System.out.println(maxExcent+"\n"+minExcent);

		for(int i : nodesMin){

			if(nodesMin.getLast() == i)
				System.out.println(i);
			else
				System.out.print(i + " ");

		}

		for(int i : nodesMax){

			if(nodesMax.getLast() == i)
				System.out.println(i);
			else
				System.out.print(i + " ");

		}

	}

	private static void doYourShit(HashMap<Integer,Node> nodes, int[][] table) {

		LinkedList<Integer> queue = new LinkedList<Integer>();

		Node aux;

		for(int i = 1; i <= nodes.size(); i++){

			boolean [] bnodes = new boolean[nodes.size()+1];

			queue.add(i);
			bnodes[i] = true;
			table[i][i] = 0;

			while(!queue.isEmpty()){
				int n = queue.removeFirst();
				aux = nodes.get(n);

				for(int j : aux.neighbor){
					if(!queue.contains(j) && !bnodes[j]){
						queue.add(j);
						bnodes[j] = true;
						table[i][j] = table[i][n] + 1;
					}
				}
			}

			calculateExcent(table[i], nodes.get(i));

		}

	}

	private static void calculateExcent(int[] array, Node node) {

		int max = 0;

		for(int i : array){
			if(i > max)
				max = i;
		}

		node.excentricity = max;
	}

}
