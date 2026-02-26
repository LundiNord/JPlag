//package Exercicios;
import java.util.Scanner;

public class DAA21 {

	public static void main(String Args[]) {

		Scanner scan = new Scanner(System.in);
		double coordinates [][];
		double distances [][];
		double dist [];
		boolean arvore [];
		double cost = 0;
		int sardas = scan.nextInt();
		coordinates = new double[sardas][2];
		int n_nos = coordinates.length;
		distances    = new double[n_nos][n_nos];
		for (int j = 0; j < sardas; j++) {
			coordinates[j][0] = scan.nextDouble();
			coordinates[j][1] = scan.nextDouble();
		}
		for (int k = 0; k < n_nos; k++) {
			for (int l = 0; l < n_nos; l++) {
				double distance = Math.sqrt((Math.pow(coordinates[k][0] - coordinates[l][0], 2) + Math.pow(coordinates[k][1] - coordinates[l][1], 2)));
				distances[k][l] = distance;
			}
		}
		arvore = new boolean[n_nos];
		dist = new double[n_nos];
		for (int i=0; i<n_nos; i++) {
			dist[i] = Double.MAX_VALUE;
			arvore[i] = false;
		}
		dist[0] = 0;
		for (int i=0; i<n_nos; i++) {
			int  n_min = -1;
			double min = Double.MAX_VALUE;
			for (int j=0; j<n_nos; j++) {
				if(dist[j]<min && arvore[j]==false) {
					min   = dist[j];
					n_min = j;
				}
			}
			arvore[n_min] = true;
			cost += min;
			for (int j=0; j<n_nos; j++)
				if (distances[n_min][j] < dist[j])
					dist[j] = distances[n_min][j];
		}
		System.out.println(String.format("%.2f", cost));
	}
}