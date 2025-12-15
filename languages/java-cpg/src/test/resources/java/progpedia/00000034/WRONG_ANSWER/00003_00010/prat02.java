import java.io.*;
import java.util.*;

public class prat02 {
    static void merge (int v[], int start, int med, int end) {
	int i,j,k;
	i = start;
	j = med + 1;
	k = 0;
	int[] tmp = new int [end-start+1];
	while (i <= med && j <= end) {
	    if (v[i] < v[j]) {
		tmp[k] = v[i];
		k++;
		i++;
	    }
	    else {
		tmp[k] = v[j];
		k++;
		j++;
	    }
	}
  
	while (i <= med) {
	    tmp[k] = v[i];
	    k++;
	    i++;
	}
  
	while (j <= end) {
	    tmp[k] = v[j];
	    k++;
	    j++;
	}
  
	for (i = 0; i < k; i++) {
	    v[i + start] = tmp[i];
	}
    }

    static void mergesort (int v[], int start, int end) {
	int med = (start + end) / 2;
	if (start == end) return;
	mergesort (v, start, med);
	mergesort (v, med+1, end);
	merge (v, start, med, end);
    }

    public static void main (String[] args) {
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(reader.readLine());
	    int[] array = new int[n];
	    for (int i = 0; i < n; i++) {
		array[i] = Integer.parseInt(reader.readLine());
	    }
	    mergesort(array,0,n-1);
	    for (int i = 0; i < n; i++) {
		System.out.print(array[i]);
		if (i + 1 != n) System.out.print(" ");
	    }
	    System.out.println();
	}
	catch (Exception e) {}
    }
}
