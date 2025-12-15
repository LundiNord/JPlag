import java.util.Scanner;

public class Sardas {

	public static void main(String Args[]) {

		Scanner stdin = new Scanner(System.in);
		double[][] coord;
		double[][] distancia;
		double[] dist;
		boolean[] arvore;
		int sardas = stdin.nextInt();
		coord = new double[sardas][2];
		int n_nos = sardas;
		distancia    = new double[n_nos][n_nos];
		for (int i = 0; i< sardas; i++) {
			coord[i][0] = stdin.nextDouble();
			coord[i][1] = stdin.nextDouble();
		}
		for (int i = 0; i < n_nos; i++) {
			for (int j = 0; j < n_nos; j++) {
				double trajeto = Math.sqrt(((coord[i][0] - coord[j][0])*(coord[i][0] - coord[j][0])) +((coord[i][1] - coord[j][1]) * (coord[i][1] - coord[j][1])));
				distancia[i][j] = trajeto;
			}
		}
		arvore = new boolean[n_nos];
		dist = new double[n_nos];
		for (int i=0; i<n_nos; i++) {
			dist[i] = 100;
			arvore[i] = false;
		}
		dist[0] = 0;
		double custo = 0;
		for (int i=0; i<n_nos; i++) {
			int  n_min = 0;
			double min = 100;
			for (int j=0; j<n_nos; j++) { 
				if(dist[j]<min && arvore[j]==false) {
					min   = dist[j];
					n_min = j;
				}
			}
			arvore[n_min] = true;
			custo += min;
			for (int j=0; j<n_nos; j++)
				if (distancia[n_min][j] < dist[j])
					dist[j] = distancia[n_min][j];
		}
		System.out.printf("%.2f\n", custo);
	}
}