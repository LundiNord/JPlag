import java.io.*;
import java.util.*;

public class prat07new {
    
    public static int getMinDist(int pesq, int[] somas) {
	int min = Integer.MAX_VALUE;
	for (int i = 0; i < somas.length; i++) {
	    int d = Math.abs(somas[i] - pesq);
	    if (d < min) min = d;
	}
	return min;
    }

    public static int bsearch (int[] v, int low, int high, int key) {
	while (low <= high) {
	    int middle = low + (high - low) / 2;
	    if (key == v[middle]) return middle;
	    else if (key < v[middle]) high = middle - 1;
	    else low = middle + 1;
	}
	return (-1);
    }
    
    public static int[] removeDuplicates(int[] A) {
	if (A.length < 2)
	    return A;
 
	int j = 0;
	int i = 1;
 
	while (i < A.length) {
	    if (A[i] == A[j]) {
		i++;
	    } else {
		j++;
		A[j] = A[i];
		i++;
	    }
	}
 
	int[] B = Arrays.copyOf(A, j + 1);
 
	return B;
    }

    public static void main (String[] args) {
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	int arr[] = new int[n];
	for (int i = 0; i < n; i++) {
	    arr[i] = stdin.nextInt();
	}
	Arrays.sort(arr);
	int somas[] = new int[(n * (n-1))];
	int s = 0;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (!(i == j)) {
		    somas[s] = arr[i] + arr[j];
		    s++;
		}
	    }
	}
	Arrays.sort(somas);
	int somas_ord[] = removeDuplicates(somas);
	int qs = stdin.nextInt();
	for (int i = 0; i < qs; i++) {
	    int pesq = stdin.nextInt();
	    /* int dist = getMinDist(pesq, somas_ord);
	    int index = bsearch(somas_ord, 0, somas_ord.length, pesq - dist);
	    if (index == -1) index = bsearch(somas_ord, 0, somas_ord.length, pesq + dist);
	    int itemp = index;
	    System.out.print(somas_ord[index]);
	    if (itemp + 1 < somas_ord.length) {
		index = bsearch (somas_ord, itemp + 1, somas_ord.length, pesq - dist);
		if (index == -1) index = bsearch(somas_ord, itemp + 1, somas_ord.length, pesq + dist);
		if (index != -1) System.out.print(" " + somas_ord[index]);
	    }
	    System.out.println();*/
	    int found_index = Arrays.binarySearch(somas_ord, pesq);
	   //System.out.println("debug: found_index" + found_index);
	    if (found_index == -1) found_index = 0;
	   // System.out.println("debug: found_index" + found_index);
	    if (found_index < -1) found_index = (found_index + 1) * (-1) - 1;
	    int found = somas_ord[found_index];
	    System.out.print(found);
	    if (found_index + 1 < somas_ord.length) {
		if (Math.abs(somas_ord[found_index + 1] - pesq) == Math.abs(somas_ord[found_index] - pesq)) {
		    System.out.print(" " + somas_ord[found_index + 1]);
		}
	    }
	    System.out.println();
	}
    }
}
