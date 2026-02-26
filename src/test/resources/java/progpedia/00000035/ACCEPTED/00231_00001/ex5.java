import java.io.*;
import java.util.*;

public class ex5{
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2, cont1=0;
	int aux[] = new int[end+1];
	p1=start;
	p2=middle+1;
	i = start;
	while (p1<=middle && p2<=end) {
	    if (v[p1] <= v[p2])
		aux[i++] = v[p1++];
	    else{
		aux[i++] = v[p2++];
		cont1 += middle - p1 + 1;
	    }
	}
	while (p1<=middle)
	    aux[i++] = v[p1++];
	while (p2<=end)
	    aux[i++] = v[p2++];
	for (i=start; i<=end; i++)
	    v[i] = aux[i];
	return cont1;
    }
    static int mergesort(int v[], int start, int end) {
    	int middle, cont=0;
	if (start<end){
	    middle = (start+end)/2;
	    cont += mergesort(v, start, middle);
	    cont += mergesort(v, middle+1, end);
	    cont += merge(v, start, middle, end);
	}
	return cont;
    }
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();
	System.out.print(mergesort(v, 0, n-1));
	System.out.println();
    }
}

//fiz com ajuda de um colega