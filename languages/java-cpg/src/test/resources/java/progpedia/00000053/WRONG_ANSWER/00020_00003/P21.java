import java.util.*;

class P21 {

	public static void main(String[] args) {


		//Iniciar o scanner
		Scanner teclado = new Scanner(System.in);

		//Ler numero de vertices e aresta
		int nV = teclado.nextInt(); // numero vertices
		int nE = teclado.nextInt(); // numero edges

		//Iniciar uma fila
		Queue<Integer>      fila        = new LinkedList<Integer>();
		LinkedList<Integer> indices_max = new LinkedList<Integer>();
		LinkedList<Integer> indices_min = new LinkedList<Integer>();


		//Matrix de ligacoes de nos
		ArrayList<ArrayList<Integer>> ligados = new ArrayList<ArrayList<Integer>>(nV+1);

		for ( int i = 0; i < nV+1 ; i++){
			ArrayList<Integer> list = new ArrayList<Integer>();
			ligados.add(list);
		}


		//Ler ligacoes para meter na matrix
		while (teclado.hasNextInt()){
			int v = teclado.nextInt();
			int w = teclado.nextInt();
			ligados.get(v).add(w);
			ligados.get(w).add(v);
		}


		//Vector para indicar se um vertice foi visitado ou nao
		boolean visitado[] = new boolean[nV+1];

		//Colocar tudo como nao visitado
		for (int i=1;i<nV+1;i++ ) {
			visitado[i] = false;
		}


		//Matrix de distancias
		int distancia[][] = new int[nV+1][nV+1];

		//colocar todas as distancias a 0
		for (int i=1;i<nV+1;i++ ){
			for (int j=1;i<nV+1;i++ ){
				distancia[i][j] = 0;
			}
		}


		//Fazer presquisa em largura
		for (int i=1;i<nV+1 ;i++ ) {
			if (visitado[i] == false){
				bfs(ligados,i,visitado,nV,fila,distancia,nV);
			}
		}

		// //Imprimir matrix de distancias
		// for(int i = 0; i <nV+1 ; i++) {
		// 	for(int j = 0; j < nV+1; j++)
		// 		System.out.printf("["+ distancia[i][j] + "]");
		// 	System.out.printf("\n");
		// }

		//Vector para guardar os maximos de cada linha da Matrix
		int maximos[] = new int[nV+1];

		//Preencher o vector com as distancias maximas
		int maximo;
		for (int i=1;i<nV+1 ;i++ ) {
			maximo = 0;
			for (int j=1;j<nV+1 ;j++ ) {
				if (distancia[i][j] > maximo){maximo = distancia[i][j]; }
			}
			maximos[i]=maximo;	
		}




		// Calcular o minimo e o maximo
		int min=maximos[1];
		int max=maximos[1];

		for (int i=2; i<nV+1 ;i++ ) {
			if (maximos[i]<=min) { min=maximos[i]; }
			if (maximos[i]>=max) { max=maximos[i]; }
		}



		//Imprimir o maximo e o minimo
		System.out.println(max);
		System.out.println(min);



		//Imprimir indices
		for (int i=2; i<nV+1 ;i++ ) {
			if (maximos[i]==min) { indices_min.add(i); }
		}
		

        int tamanho_min = indices_min.size();

		for(int i=0;i<tamanho_min-1;i++){
		    System.out.print(indices_min.get(i) + " ");
		}

		System.out.println(indices_min.get(tamanho_min-1));

		for (int i=1; i<nV+1 ;i++ ) {
			if (maximos[i]==max) { indices_max.add(i); }
		}
 		

		int tamanho_max = indices_max.size();

		for(int i=0;i<tamanho_max-1;i++){
    		System.out.print(indices_max.get(i) + " ");
		} 

		System.out.println(indices_max.get(tamanho_max-1));

	}


	public static void bfs(ArrayList<ArrayList<Integer>> ligados, int v,boolean visitado[],int nP, Queue<Integer> fila, int distancia[][],int nV){
		fila.add(v);              		// no v
		distancia[v][v] = 0;
		visitado[v] = true;
		while( !fila.isEmpty() ){
			int u = fila.poll();  // no u
				for (int w : ligados.get(u)){
					if (visitado[w] !=  true) {
						fila.add(w);
							visitado[w] = true;
							distancia[w][v] = distancia[v][w] = distancia[u][v]+1;
					}
				}
		}



		//meter tudo como nao visitado
		for (int i=1;i<nV+1;i++ ) {
			visitado[i] = false;
		}


	}


}