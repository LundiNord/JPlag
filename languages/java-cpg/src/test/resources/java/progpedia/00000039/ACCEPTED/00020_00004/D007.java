import java.io.*;
import java.util.*;

public class D007 {
	static int n, v[], somas[], pares;

	static void bsearh(int low, int high, int pi) {
		int middle, rest1, rest2;
		while (low < high) {
			middle = low + (high-low)/2;
			if ( pi < somas[middle]) high = middle;
			else low = middle+1;
		}
		if (low == 0)
			System.out.println(somas[low]);

		else if (somas[low] <= pi) System.out.println(somas[low]);

		else {
			rest1 = pi-somas[low-1];
			rest2 = somas[low]-pi;

			if (rest1 == rest2	)
				System.out.println(somas[low-1] + " " + somas[low]);
			else if (rest1 < rest2)
				System.out.println(somas[low-1]);
			else
				System.out.println(somas[low]);
		}
	}


	static void somas_pares(int v[]) {
		int k = 0;
		somas = new int[pares];

		for (int i=0; i<n; i++)
			for(int j=i+1; j<n; j++) {
				somas[k] = v[i]+v[j];
				k++;
			}

		Arrays.sort(somas);
/*
		System.out.print("Array ordenado: ");
		for (int i=0; i<pares; i++)
			System.out.print(somas[i]+ " ");

		System.out.println("Pares: " + pares);
*/
	}

	static void n_pares(int n) {
		for (int i=0; i<n; i++)
			for(int j=i+1; j<n; j++)
				pares ++;

	}

	public static void main(String args[]) {
		int pi;

		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();

		n_pares(n);

		v = new int[n];

		for(int i=0; i<n; i++)
			v[i] = stdin.nextInt();


		somas_pares(v);



		int p = stdin.nextInt();

		for(int i=0; i<p; i++) {
			pi = stdin.nextInt();
			bsearh(0, pares-1, pi);
		}


	}
}
