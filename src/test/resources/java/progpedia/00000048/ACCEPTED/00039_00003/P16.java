import java.util.*;

public class P16{

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);


		int nP = teclado.nextInt(); // pontos
		int nL = teclado.nextInt(); // ligacoes
		

		boolean ligados[][] = new boolean[nP+1][nP+1];

		//boolean existe[] = new boolean[nP+1];


		while (teclado.hasNextInt()){
			int v = teclado.nextInt();
			//existe[v] = true;
			int w = teclado.nextInt();
			//existe[w] = true;
			ligados[v][w] = ligados[w][v] = true;
		}


		//vector para indicar se um vertice foi visitado ou nao
		boolean visitado[] = new boolean[nP+1];

		for (int i=1;i<nP+1;i++ ) {
			visitado[i] = false;
		}


		int counter = 0;

		for (int i=1;i<nP+1 ;i++ ) {
			if (visitado[i] == false){
				counter++;
				dfs(ligados,i,visitado,nP);
			}
		}

		// System.out.println(Arrays.toString(existe));
		// System.out.println(Arrays.toString(visitado));
		System.out.println(counter);

	}


	public static void dfs(boolean ligados[][], int v,boolean visitado[],int nP){
		visitado[v] = true;
		for (int w=1;w<nP+1;w++){
			if (ligados[v][w] == true){

				if (visitado[w] !=  true) {
					dfs(ligados,w,visitado,nP);
				}
			}
		}

	}

}