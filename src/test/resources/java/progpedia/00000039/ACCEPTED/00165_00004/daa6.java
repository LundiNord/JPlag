
import java.io.*;
import java.util.*;


class daa6 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int a[] = new int[n];

	for(int i = 0; i < n; i++) {
	    a[i] = in.nextInt();
	}

	int p = in.nextInt();
	int q[] = new int[p];


	for(int j = 0; j < p; j++) {
	    q[j] = in.nextInt();
	}
	int total = (n * (n-1)) / 2;
	int cont = 0;
	int add[] = new int[total];
	for(int i=0; i < n ; i++) {
	    for(int j = i+1; j < n  ; j++) {
		add[cont] = a[i] + a[j];
		cont++;
	    }
	}


	Arrays.sort(add);



	for(int i = 0; i < p; i++) {
	    bin(add, q[i], 0,total-1);

	}
    }

    public static void bin(int[] a, int b, int inicio, int fim) {

        while(inicio <=fim) {
	    int k = (inicio + ((fim - inicio) / 2));
	    if(b == a[k]) {
		System.out.println(a[k]);
		return;
	    }
	    else
		if(inicio == fim) {
		    System.out.println(a[inicio]);
		    return;
		}

		else
		    {
			if(b < a[k]) {
			    if(k-1 >= inicio)
				if(b > a[k-1]) {
				    verifica(b, k, a);
				    return;
				}
			    fim = k;
			}
			else {
			    if(k+1 <= fim)
				if(b > a[k] && b < a[k+1]) {
				    verifica(b, k, a);
				    return;
				}
			inicio = k+1;
			}
		    }
	}
    }

    public static void verifica(int b, int k, int[] c) {

	if(c[k] > b) {
	    int p1 = Math.abs(c[(k - 1)] - b);
	    int p2 = Math.abs(c[k] - b);

	    if(p1 == p2 && c[k-1] != c[k] )
		System.out.println(c[k-1] + " " + c[k]);
	    else
		if(p1 >= p2)
		    System.out.println(c[k]);

		else
		    System.out.println(c[k-1]);
	}

	else
	    {
		if(c[k] < b) {
		    int p1 = Math.abs(c[k] - b);
		    int p2 = Math.abs(c[(k+1)]-b);

		    if(p1 == p2 && c[k] != c[k+1])
			System.out.println(c[k] + " " + c[k+1]);
		    else {
			if(p1 >= p2)
			    System.out.println(c[k+1]);

			else
			    System.out.println(c[k]);

		    }
		}
	    }
    }
}



