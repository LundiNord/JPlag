import java.util.*;

public class InversoesV2 {
    public static long inversoes = 0;
    
    public static void merge(int left[], int right[], int resultado[]) {
	int i = 0, j = 0, k = 0;
	while(i < left.length && j < right.length) {
	    if(left[i] > right[j]) {
		resultado[k++] = right[j++];
		inversoes += left.length - i;
	    }
	    else resultado[k++] = left[i++];
	}

	for(int t = i; t < left.length; t++) resultado[k++] = left[t];
	for(int t = j; t < right.length; t++) resultado[k++] = right[t];
    }
    
    public static void mergesort(int numeros[]) {
	if(numeros.length <= 1) return;
	
	int middle = numeros.length / 2;
	int left[] = new int[middle];
	int right[] = new int[numeros.length - middle];
	
	System.arraycopy(numeros, 0, left, 0, left.length);
	System.arraycopy(numeros, middle, right, 0, right.length);
	
	mergesort(left);
	mergesort(right);
	merge(left, right, numeros);
 
	return;
    }
    
    public static void main(String args[]) {
	Scanner scanf = new Scanner(System.in);

	int n = scanf.nextInt();
	int numeros[] = new int[n];
	for(int i=0; i<n; i++) numeros[i] = scanf.nextInt();

	mergesort(numeros);

	System.out.println(inversoes);
    }
}
