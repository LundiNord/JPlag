import java.io.*;
import java.util.*;

public class sapat010 {

    static void ordem(int n, int d[], int m[]){
	float q[] = new float[n];
	float aux[] = new float[n];
	
	for(int i=0; i<n; i++){
	    q[i] = m[i] / d[i];
	    aux[i]=q[i];
	}
	Arrays.sort(q);
	int j = n-1;
	for(int i=0; i<n; i++){
	    j = n-1;
	    while(j>=0 && aux[i] != q[j])
		j--;
	System.out.print(n-j + " ");
	q[j] = -1;
	}
	System.out.println();
    }
    
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();
	int d[] = new int[n];
	int m[] = new int[n];
	
	for (int i=0; i<n; i++){
	    d[i] = stdin.nextInt();
	    m[i] = stdin.nextInt();
	}
	ordem(n, d, m);
    }
}
