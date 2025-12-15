//package sardas_21;

import java.util.Scanner;

public class Sardas {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double coord[][] = new double[n][2];
		for(int l=0; l<n; l++){
			coord[l][0] = in.nextDouble();
			coord[l][1] = in.nextDouble();
		}
		int nos = coord.length;
		double dist[][] = new double[nos][nos];
		for(int i=0; i<nos; i++){
			for(int j=0; j<nos; j++){
				double distance = Math.sqrt((Math.pow(coord[i][0] - coord[j][0], 2) + Math.pow(coord[i][1] - coord[j][1], 2)));
				dist[i][j] = distance;
			}
		}
		boolean arv[] = new boolean[nos];
		double d[] = new double[nos];
		for(int i=0; i<nos; i++){
			d[i] = Double.MAX_VALUE;
			arv[i] = false;
		}
		d[0] = 0;
		double c = 0;
		for(int i=0; i<nos; i++){
			int nmin = -1;
			double min = Double.MAX_VALUE;
			for(int j=0; j<nos; j++){
				if(d[j]<min & arv[j] == false){
					min = d[j];
					nmin = j;
				}
			}
			arv[nmin] = true;
			c += min;
			for(int j=0; j<nos; j++)
				if(dist[nmin][j] < d[j])
					d[j] = dist[nmin][j];
		}
		System.out.println(String.format("%.2f",  c));
	}

}
