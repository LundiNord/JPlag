import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Rede {
	
	public static class Node{
		
		int node;
		LinkedList<Integer> neighboors ;
		
		public Node(int n, int neigh){
			node = n;
			neighboors = new LinkedList<Integer>();
			neighboors.add(neigh);
		}

		public Node(int i) {
			node = i;
			neighboors = new LinkedList<Integer>();
		}
	}

	public static void main(String[] args) {
		
		int nNodes, nCon, a, b;
		Scanner in = new Scanner(System.in);
		
		nNodes = in.nextInt();
		nCon = in.nextInt();
		
		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
		
		for(int i=1; i <= nNodes; i++){
			nodes.put(i, new Node(i));
		}
		
		for(int i = 0; i < nCon; i++){
			
			a = in.nextInt();
			b = in.nextInt();
			
			if(!nodes.containsKey(a)){
				nodes.put(a, new Node(a, b));
			}
			else{
				nodes.get(a).neighboors.add(b);
			}
			
			if(!nodes.containsKey(b)){
				nodes.put(b, new Node(b, a));
			}
			else{
				nodes.get(b).neighboors.add(a);
			}
		}

		HashMap<Integer, LinkedList<Integer>> net = new HashMap<Integer, LinkedList<Integer>>();
		
		doMagic(net,nodes, nNodes);
		
		System.out.println(net.size());
	}

	private static void doMagic(HashMap<Integer, LinkedList<Integer>> net, HashMap<Integer, Node> nodes, int nNodes) {
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		Node aux;
		boolean flag = false;
		int netSize = 1;
		
		for(int i = 1; i <= nNodes; i++){
			
			boolean [] visitedNodes = new boolean [nNodes+1];
			flag = false;
			
			for(int n = 1; n <= net.size(); n++){
				if(net.containsKey(n)){
					if(net.get(n).contains(i)){
						flag=true;
					}
				}
			}
			
			if(!flag){
				
				queue.add(i);
				visitedNodes[i] = true;
				net.put(netSize, new LinkedList<Integer>());
				
				while(!queue.isEmpty()){
					
					int n = queue.removeFirst();
					net.get(netSize).add(n);
					aux = nodes.get(n);
					
					for(int j : aux.neighboors){
						if(!visitedNodes[j] && !queue.contains(j)){
							queue.add(j);
							visitedNodes[j] = true;
						}
					}
					
				}
				
				netSize++;
			}
			
		}
	}

}
