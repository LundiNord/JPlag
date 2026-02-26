import java.awt.print.Book;
import java.util.*;


public class RedesCircuitosElectronicos {
	
	static int[][] matriz;
	
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
				
		int pontos = in.nextInt();
		int ligacoes = in.nextInt();
		
		matriz = new int[pontos][pontos];

		
		for (int i = 0; i < pontos; i++) {
			for(int j=0; j< pontos; j++){
				matriz[i][j] = 0;
			}
		}
		
		for (int j = 0; j < ligacoes; j++) {
			int first = in.nextInt();
			int second = in.nextInt();
			
			matriz[first-1][second-1] = 1;
			matriz[second-1][first-1] = 1;
		}
		
		int conta =  conexos(matriz, pontos);
		
		System.out.println(conta);
	}
	
	static Boolean[] visited;

	
	static int conexos(int[][] matriz, int pontos){
		int count = 0;
		
		visited = new Boolean[pontos];

		
		for (int i = 0; i < pontos; i++) {
				visited[i] = false;
		}
		
		for (int i = 0; i < pontos; i++) {
				if(visited[i]==false){
					//System.out.println(".........");
					count++;
					dfs(i, pontos);
				}
		}
		
		
		return count;
	}
	
	static void dfs(int i, int pontos){
		//System.out.println(i+1);
		
		visited[i] = true;
		for(int k=0; k<pontos; k++){
			if(matriz[i][k] == 1 && visited[k] == false){
				dfs(k, pontos);
			}
		}
	}
}
