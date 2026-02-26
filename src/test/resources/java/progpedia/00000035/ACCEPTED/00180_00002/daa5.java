
import java.util.*;


class daa5 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in. nextInt();
	int v[] = new int[n];

	for(int i=0; i<n; i++) {
	    v[i] = in.nextInt();
	}

	int cont = 0;
	for(int i=0; i<=n-1; i++) {
	    for(int j = i+1; j <= n-1; j++) {
		if(v[i] > v[j])
		    cont += 1;
	    }
	}
	System.out.println(ms(v, 0 ,n-1));
    }


    static int ms(int v[], int min, int max) {
	int m;
	int i = 0;
	if(min<max) {
	    m = (min+max)/ 2;
	    i = ms(v, min, m) + ms(v, m+1, max) + bin(v, min, m, max);

	}
	return i;
    }

    static int bin(int v[], int min, int k, int max) {
	int i, p1, p2;
	int x[] = new int[max+1];
	int j = 0;
	p1 = min;
	p2 = k+1;
	i = min;

	if(min >= max) return 0;

	else {

	    while(p1 <= k && p2 <= max) {

		if(v[p1] <= v[p2]) {

		    x[i++] = v[p1++];
		}
		else {

		    j += (k+1) - p1;
		    x[i++] = v[p2++];
		}
	    }
	}
	while(p1 <= k) {
	    x[i++] = v[p1++];

	}

	while(p2 <= max) {
	    x[i++] = v[p2++];

	}
	for(i = min; i <= max; i++) {
	    v[i] = x[i];
	}
	return j;
    }
}
