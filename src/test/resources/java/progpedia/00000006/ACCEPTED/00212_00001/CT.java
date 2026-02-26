import java.util.*;

public class CT {
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LinkedList<Integer> lista = new LinkedList<Integer>();
		Stack<Integer> pilha = new Stack<Integer>();
		
		int k = in.nextInt();
		if (k!=0) { 
			while (k!=0) {
				if (!pilha.contains(k)) {
					pilha.push(k);
					
					
				}
				else {
					
					int indice= pilha.indexOf(k);
					
					
					while(pilha.size()!=indice+1) {
						pilha.pop();
					}
					
				}
				k = in.nextInt();			
			}
				
				
		}
		
		int[] vector = new int[pilha.size()];
		int i=0;
		
		while (pilha.size()!=0) {
			vector[i]=pilha.pop();
			i++;
		}
		
		
		
		for (int j=vector.length-1; j>=0; j--) {
			System.out.println(vector[j]);
		}
		
		
		
		
		
		
	}
		
		
}

