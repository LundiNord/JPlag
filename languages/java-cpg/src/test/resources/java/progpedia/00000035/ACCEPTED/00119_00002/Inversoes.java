import java.util.*;

class Inversoes {
    
    public static int[] aux;
    public static int[] v;
    public static int inversoes;

    public static double merge(int v[], int start, int middle, int end) {
        int contador = 0;

        for (int h = start; h <= end; h++) {
            aux[h] = v[h];
        }
        int i = start, j = middle + 1, k = start;
        while (i <= middle && j <= end) {
            if (aux[i] <= aux[j]) {
                v[k] = aux[i];
                i++;
            } else {
                v[k] = aux[j];
                j++;
                contador += (middle + 1) - i;
            }
            k++;
        }
        while (i <= middle) {
            v[k] = aux[i];
            k++;
            i++;
        }
        return contador;
    }

    static int mergesort(int v[], int start, int end) {
        if (end - start == 0) {
            return 0;
        }

        int middle = (start + end) / 2;

        int contador = 0;

        contador += mergesort(v, start, middle);
        contador += mergesort(v, middle + 1, end);
        contador += merge(v, start, middle, end); 

        return contador;
    }
 
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();
        
        /*int n = 10;
        int v[] = new int[] {10,7,1,3,8,6,4,4,6,9};
        */
        aux = new int[n];
        
	System.out.println(mergesort(v, 0, n-1));
        
    }
}