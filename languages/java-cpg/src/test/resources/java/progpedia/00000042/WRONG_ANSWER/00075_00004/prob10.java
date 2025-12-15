import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Ponto {

	int inicio, fim;

	Ponto(int l, int r) {

		inicio = l;
		fim = r;
	}
}

public class prob10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		int m, n, end, i, k = 0;

		m = scan.nextInt();
		n = scan.nextInt();

		Ponto pontos[] = new Ponto[n];

		for (i = 0; i < n; i++) {
			int inicio = scan.nextInt();
			int fim = scan.nextInt();
			pontos[i] = new Ponto(inicio, fim);
		}

		// ordenado por ordem crecente do inicio
		Arrays.sort(pontos, new Comparator<Ponto>() {

			@Override
			public int compare(Ponto o1, Ponto o2) {
				// TODO Auto-generated method stub
				int c = 0;
				if (o1.inicio < o2.inicio)
					c = -1;

				return c;
			}
		});

		i = 0;
		int aux = 0;
		k = 0;
		end = 0;

		while (end < m && i<n) {
			if (pontos[i].inicio <= end) {
				//System.out.printf(">%d\n", i);
				if (pontos[i].fim > aux) {
					aux = pontos[i].fim;
				}
			} else {
				end = aux;
				aux=pontos[i].fim;
				k++;
			}
			i++;
		}

		i--;
		if (end < m) {
			k++;
		}

		/*
		 * for(i=0;i<n;i++){
		 * System.out.printf("%d %d\n",pontos[i].inicio,pontos[i].fim); }
		 */

		System.out.printf("%d\n", k);
	}

}
