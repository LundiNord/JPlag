import java.util.*;

public class tres {
	
	public static void main (String[] args) {
		
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int v[] = new int[n];

		for (int i=0; i<n; i++)
			v[i] = s.nextInt();

		System.out.println(mergeSort (v)); 
	}

	static long mergeSort (int[] A) {
		
		long c = 0;

		if (A.length > 1) {
			int q = A.length/2;

			int[] leftArray = Arrays.copyOfRange(A, 0, q);
			int[] rightArray = Arrays.copyOfRange(A, q, A.length);

			c += mergeSort(leftArray);
			c += mergeSort(rightArray);

			c += merge(A, leftArray, rightArray); }

		return c;
	}

	static long merge (int[] a, int[] l, int[] r) {
		
		int totElem = l.length + r.length;
		int i = 0, li = 0, ri = 0;
		long c = 0;

		while (i < totElem) {
			if ((li < l.length) && (ri < r.length)) {
				if (l[li] <= r[ri]) { a[i] = l[li]; i++; li++; }
				else { a[i] = r[ri]; i++; ri++; c += l.length - li; }}

			else {
				if (li >= l.length) 
					while (ri < r.length) { 
						a[i] = r[ri]; 
						i++; ri++;
						}
				if (ri >= r.length) 
					while (li < l.length) { 
						a[i] = l[li]; 
						i++; li++; }
				}
			}
		return c; 
		}
	}
