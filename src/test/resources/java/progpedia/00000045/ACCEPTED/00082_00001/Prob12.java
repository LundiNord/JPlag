import java.util.*;

public class Prob12 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int d = in.nextInt();
		long count[][] = new long[n + 1][n + 1];
		int c, p;

		// formar a pirâmide (representar )

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == n)
					count[i][j] = 1;
				else
					count[i][j] = -1;
			}
		}

		//actualizar matriz com posições de pedras "estragadas"
		for (int i = 0; i < d; i++) {
			c = in.nextInt();
			p = in.nextInt();
			count[(n + 1) - c][p] = 0;
		}

		System.out.println(waysToGo(n, count));
	}

	public static long waysToGo(int n, long count[][]) {
		for (int i = n - 1; i >= 1; i--)
			for (int j = 1; j <= i; j++)
				if (count[i][j] != 0)
					count[i][j] = count[i + 1][j] + count[i + 1][j + 1];

		return count[1][1];
	}

}
