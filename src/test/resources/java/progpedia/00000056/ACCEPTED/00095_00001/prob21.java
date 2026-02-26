//Exercicio feito em conjunto pelos alunos
// Carlos Bernardes up201303743
// Célio Rodrigues up201303171
import java.util.*;

public class prob21 {
	public static void main(String Args[]) {
		Scanner in = new Scanner(System.in);
		
		double[][] mapa;
		double[][] distances;
		
		double[] dist;
		boolean[] nodes; // marca os nos que foram visitados
		
		int sardas = in.nextInt();
		mapa = new double[sardas][2];
		
		int nnodes = mapa.length;
		distances    = new double[nnodes][nnodes];

		for (int j = 0; j < sardas; j++) {
			mapa[j][0] = in.nextDouble();
			mapa[j][1] = in.nextDouble();
		}

		// actualiza distancias entre as coordenadas

		for ( int i = 0; i < nnodes; i++ ) {
			for ( int j = 0; j < nnodes; j++ ) {
				double x = mapa[i][0] - mapa[j][0]; 
				double y = mapa[i][1] - mapa[j][1];
				double d = Math.sqrt(x*x + y*y);
				distances[i][j] = d;
			}
		}

		nodes = new boolean[nnodes]; // nos da arvore
		dist = new double[nnodes];

			//este ciclo mete os nós a infinito
		for (int i=0; i<nnodes; i++) {
			dist[i] = 9999.9;
			nodes[i] = false;
		}

		dist[0] = 0;
		double ink = 0;

		for (int i=0; i<nnodes; i++) {
			int  n_min = -1;
			double min = 999999.9;
			
				//para o no i faz um ciclo para encontrar o no vizinho que tem menor custo de ligacao
				// guarda-o em n_min
			for (int j=0; j<nnodes; j++) {
				if(dist[j]<min && nodes[j]==false) {
					min   = dist[j];
					n_min = j;
				}
			}
				// o no vizinho de menor custo esta descoberto , vamos marcar este com true para nao voltar
				// a ser de novo visitado
			nodes[n_min] = true;
			ink += min; // simplesmente actualizar o custo do caminho tomado ate agora

			// aqui ter em atencao que se deve escolher o caminho com menor custo a partir de todos os vertices
			// previamente visitados antes. temos de considerar todas as ligacoes 

			for ( int j=0; j < nnodes; j++ ) 
				if ( distances[n_min][j] < dist[j] )
					dist[j] = distances[n_min][j];
		}
		System.out.printf("%.2f\n", ink);
	}
}