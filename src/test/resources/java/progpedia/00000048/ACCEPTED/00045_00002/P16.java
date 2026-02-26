import java.util.*;

class P16{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int nNOS=in.nextInt();
		int nL=in.nextInt();
		int count=0;

		boolean matriz[][]= new boolean[nNOS+1][nNOS+1]; // matriz de booleano

		while ( nL > 0) {
			int ptX = in.nextInt();
			int ptY = in.nextInt();
			matriz[ptX][ptY] = true; // preenca mariz com pontos x para y arestas
			matriz[ptY][ptX] = true; // sentido contrario y para x arestas
			nL--;
		}


		boolean visitado[]= new boolean[nNOS+1];

		for (int i=0; i<nNOS+1; i++) {
			visitado[i] = false;
		}

		for (int i=1; i<nNOS+1; i++) {
			if (visitado[i]==false) {
				count++;
				//pesquisa a partir do i n pesquisado antes
				pesquisa(matriz,visitado,i,nNOS); 
			}			
		}
		System.out.println(count);
	}
		//fincao para qpesquisa dos nos.
	static void pesquisa(boolean matriz[][], boolean visitado[],int noVisitado, int nNOS){

		visitado[noVisitado]=true;

		for (int i=1; i<nNOS+1; i++) {
			if (matriz[noVisitado][i] == true) {
			
				if (visitado[i] == false) {						
					pesquisa(matriz,visitado,i,nNOS);	
				}
			}		


		}
	}
}
