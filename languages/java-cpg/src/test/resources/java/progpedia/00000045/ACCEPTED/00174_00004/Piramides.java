//package piramides_12;

import java.util.Arrays;
import java.util.Scanner;

public class Piramides {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int d = in.nextInt();
		long cam[][] = new long[n][n];
		for (long[] row: cam) Arrays.fill(row, -1);
		for(int i = 0; i<d; i++){
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			cam[y][x] = 0;
		}
		for(int i = 0; i < n; i++){
			if(cam[i][0]!= 0){
				cam[i][0] = 1;
			}
		}
		for(int i = 1; i < n; i++){
			for(int j = 0; j < n-i; j++){
				if(cam[j][i] != 0){
					cam[j][i] = (cam[j][i-1] + cam[j+1][i-1]);
				}
			}
		}
		System.out.println(cam[0][n-1]);
		in.close();
	}
}
