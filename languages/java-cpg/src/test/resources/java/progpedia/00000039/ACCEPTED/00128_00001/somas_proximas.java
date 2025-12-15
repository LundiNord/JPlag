import java.util.Arrays;
import java.util.Scanner;

public class somas_proximas {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		// ## INICIO LEITURA
		int nS = stdin.nextInt();
		int s[] = new int[nS];
		for (int i = 0; i < nS; i++)
			s[i] = stdin.nextInt();

		int nP = stdin.nextInt();
		int p[] = new int[nP];
		for (int i = 0; i < nP; i++)
			p[i] = stdin.nextInt();

		// ## FIM LEITURA

		Arrays.sort(s);

		// formula do numero triangular: 1, 3, 6, 10, 15, 21
		int nSomas = ((nS - 1) * nS) / 2;

		int somas[] = new int[nSomas];
		int k = 0;

		// Calculo das somas entre todos os pares
		for (int i = 0; i < nS; i++) {
			for (int j = i + 1; j < nS; j++) {
				somas[k] = s[i] + s[j];
				k++;
			}
		}

		Arrays.sort(somas);

		// imprimir para efeitos de debug
		/*for (int i = 0; i < nSomas; i++)
			System.out.print(somas[i] + " ");*/

		for (int i = 0; i < nP; i++)
			bsearch_conditional(somas, p[i]);

	}

	static void bsearch_conditional(int v[], int key) {
		int lo = 0;
		int hi = v.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;

			if (key <= v[mid])
				hi = mid;
			else
				lo = mid + 1;
		}
		if (lo > 0 && key != v[lo]) {
			int dist_para_seguinte = Math.abs(key - v[lo]);
			int dist_para_anterior = Math.abs(key - v[lo - 1]);
			if (dist_para_anterior < dist_para_seguinte)
				System.out.println(v[lo - 1]);
			else if (dist_para_anterior == dist_para_seguinte)
				System.out.println(v[lo - 1] + " " + v[lo]);
			else
				System.out.println(v[lo]);

		} else {
			if (key <= v[lo])
				System.out.println(v[lo]);
			else
				return;
		}
	}

	int bsearch(int v[], int key) {
		int lo = 0;
		int hi = v.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;

			if (key < v[mid])
				hi = mid - 1;
			else if (key > v[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;

	}

}
