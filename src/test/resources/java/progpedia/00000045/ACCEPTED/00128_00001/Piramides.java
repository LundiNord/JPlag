import java.util.*;

public class Piramides {
	static int n;
	static long[][] pir;

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		pir = new long[n][n];
		int d = in.nextInt();

		for(int i=0; i<n; i++){
			pir[n-1][i] = 1;
		}


		for (int i = 0; i < d; i++) {
			int c = in.nextInt();
			int p = in.nextInt();
			pir[n-c][p-1] = -1;
		}

		calcular();


		if(pir[0][0] ==-1)
			pir[0][0] = 0;

		System.out.println(pir[0][0]);
	}

	static void calcular(){
		for (int i = n-2; i >= 0; i--) {
			for (int j = 0; j <=i; j++) {
				if(pir[i][j] != -1){
					if(pir[i+1][j] != -1)
						pir[i][j] += pir[i+1][j];
					if(pir[i+1][j+1] != -1)
						pir[i][j] += pir[i+1][j+1];
				}
			}
		}
	}
}