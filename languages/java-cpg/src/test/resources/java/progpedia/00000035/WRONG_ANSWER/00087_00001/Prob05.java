import java.util.*;

public class Prob05 {

	static int merge(int v[], int start, int middle, int end) {
		int i, p1, p2;
		int aux[] = new int[end + 1];
		int cnt = 0;

		p1 = start; // "Apontador" do array da metade esquerda
		p2 = middle + 1; // "Apontador" do array da metade direita
		i = start; // "Apontador" do array aux[] a conter juncao

		while (p1 <= middle && p2 <= end) {
			if (v[p1] <= v[p2])
				aux[i++] = v[p1++];
			else
				aux[i++] = v[p2++];

			cnt = cnt + ((middle + 1) - p1);
		}
		while (p1 <= middle) {
			aux[i++] = v[p1++]; // Adicionar o que resta
		}
		while (p2 <= end) {
			aux[i++] = v[p2++];
		}
		for (i = start; i <= end; i++)
			v[i] = aux[i]; // Copiar array aux[] para v[]

		return cnt;
	}

	static int mergesort(int v[], int start, int end) {
		int cnt = 0, middle;

		if (start < end) {
			middle = (start + end) / 2;

			cnt += mergesort(v, start, middle);
			cnt += mergesort(v, middle + 1, end);
			cnt += merge(v, start, middle, end);

			return cnt;
		} else
			return 0;
	}

	public static void main(String args[]) {

		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		int v[] = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = stdin.nextInt();

		int cnt = mergesort(v, 0, n - 1);

		System.out.println(cnt);
	}
}