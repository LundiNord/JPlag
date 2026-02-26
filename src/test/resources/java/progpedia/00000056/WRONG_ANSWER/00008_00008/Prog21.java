

import java.util.Scanner;

public class Prog21 {


	/* IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 * IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO IMCOMPLETO 
	 *
	 * Demasiado lento para ser aceite pelo mooshak?
	 */


	public static void main(String Args[]) {

		Scanner in = new Scanner(System.in);
		double[][] coordinates;
		double[][] distances;
		double[] dist;
		boolean[] arvore;

		int freckles = in.nextInt();

		coordinates = new double[freckles][2];

		int nnos = coordinates.length;

		distances    = new double[nnos][nnos];

		for (int j = 0; j < freckles; j++) {
			coordinates[j][0] = in.nextDouble();
			coordinates[j][1] = in.nextDouble();
		}

		for (int k = 0; k < nnos; k++) {
			for (int l = 0; l < nnos; l++) {
				double distance = Math.sqrt((Math.pow(coordinates[k][0] - coordinates[l][0], 2) + Math.pow(coordinates[k][1] - coordinates[l][1], 2)));
				distances[k][l] = distance;
			}
		}

		arvore = new boolean[nnos];
		dist = new double[nnos];
		for (int i=0; i<nnos; i++) {
			dist[i] = Double.MAX_VALUE;
			arvore[i] = false;
		}
		dist[0] = 0;


		double cost = 0;
		for (int i=0; i<nnos; i++) {


			int  n_min = -1;
			double min = Double.MAX_VALUE;


			for (int j=0; j<nnos; j++) {
				if(dist[j]<min && arvore[j]==false) {
					min   = dist[j];
					n_min = j;
				}
			}



			arvore[n_min] = true;
			cost += min;
			for (int j=0; j<nnos; j++)
				if (distances[n_min][j] < dist[j])
					dist[j] = distances[n_min][j];



		}

		System.out.println(String.format("%.4g", cost));




	}

}
