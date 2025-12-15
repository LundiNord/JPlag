import java.util.Scanner;

public class Inversoes {
	public final static int MAX = 100005;

	public static int merge(int[] v, int start, int middle, int end) {
		int i, p1, p2, c = 0;
		int[] aux = new int[MAX];
		p1 = start;
		p2 = middle + 1;
		i = start;
		while(p1 <= middle && p2 <= end) {
			if(v[p1] <= v[p2]) {
				aux[i++] = v[p1++];
			} else {
				aux[i++] = v[p2++];
				c += middle - p1 + 1;
			}
		}
		while(p1 <= middle) {
			aux[i++] = v[p1++];
		}
		while(p2 <= end) {
			aux[i++] = v[p2++];
		}

		for(i = start; i <= end; i++) {
			v[i] = aux[i];
		}
		return c;
	}

	public static int mergesortinv(int[] v, int start, int end) {
		int middle;
		int c = 0;
		if(start < end) {
			middle = (start + end) / 2;
			c += mergesortinv(v, start, middle);
			c += mergesortinv(v, middle + 1, end);
			c += merge(v, start, middle, end);
		}
		return c;
	}

	public static void main(String[] args) {
		int n, c;
		int[] v = new int[MAX];


		n = STDIN_SCANNER.nextInt();
		for(int i = 0; i < n; i++) {
			v[i] = STDIN_SCANNER.nextInt();
		}

		c = mergesortinv(v, 0, n - 1);
		System.out.println(c);
	}

	public final static Scanner STDIN_SCANNER = new Scanner(System.in);
}