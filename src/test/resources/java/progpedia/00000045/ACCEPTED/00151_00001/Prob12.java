import java.util.Scanner;

public class Prob12 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int D = in.nextInt();
		long count[][] = new long[N+1][N+1];
		int C, P;

		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if (i == N) 
					count[i][j] = 1;
				 else
					count[i][j] = -1;
			}
		}

		for (int i = 0; i < D; i++) {
			C = in.nextInt();
			P = in.nextInt();

			count[(N+1)-C][P] = 0;
		}
		in.close();

		// Calc(N, count);
		System.out.println(Calc(N, count));
	}

	public static long Calc(int n, long count[][]) {
		for (int i = n - 1; i >= 1; i--) 
			for (int j = 1; j <= i; j++) 
				if (count[i][j] != 0) 
					count[i][j] = count[i + 1][j] + count[i + 1][j + 1];
		
		return count[1][1];
	}

}
