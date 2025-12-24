import java.io.*;
import java.util.*;

public class DAA05 {

 static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2, count = 0;
	int aux[] = new int[end+1];

	p1=start; 
	p2=middle+1;
	i = start;  
	while (p1<=middle && p2<=end) { 
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];
	    else   {
	    	aux[i++] = v[p2++];
	    	count += middle-p1+1;
	    }
	}
	while (p1<=middle) aux[i++] = v[p1++];
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];
	
	return count;
 }

  static int mergesort(int v[], int start, int end) {
	int middle;
	int count =0;
	if (start<end) {
	    middle = (start+end)/2;     
	    count += mergesort(v, start, middle);
	    count += mergesort(v, middle+1, end);   
	    count += merge(v, start, middle, end);  
	    
	}
	return count;
 }

 public static void main(String args[]) {

	Scanner input = new Scanner(System.in);
		
	int numero   = input.nextInt();
	int v[] = new int[numero];
	for (int i=0; i<numero; i++)
	    v[i] = input.nextInt();

	System.out.println(mergesort(v, 0, numero-1) );
 }
}