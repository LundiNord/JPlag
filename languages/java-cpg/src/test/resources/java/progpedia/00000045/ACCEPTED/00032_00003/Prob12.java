import java.util.Scanner;

public class Prob12 {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int N = stdin.nextInt();
		int D = stdin.nextInt();
		long contagem[][] = new long[N + 1][N + 1];
		int C, P;

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == N)
					contagem[i][j] = 1;
				else
					contagem[i][j] = -1;
			}
		}

		for (int i = 0; i < D; i++) {
			C = stdin.nextInt();
			P = stdin.nextInt();

			contagem[(N + 1) - C][P] = 0;
		}

		System.out.println(Calculo(N, contagem));
	}

	public static long Calculo(int n, long contagem[][]) {
		for (int i = n - 1; i >= 1; i--)
			for (int j = 1; j <= i; j++)
				if (contagem[i][j] != 0)
					contagem[i][j] = contagem[i + 1][j] + contagem[i + 1][j + 1];

		return contagem[1][1];
	}

}
