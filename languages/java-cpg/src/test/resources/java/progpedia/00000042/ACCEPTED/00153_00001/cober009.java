import java.io.*;
import java.util.*;

public class cober009 {

    static int cobrir(int m, int n, int l[], int r[]){
        int fim  = 0;
	int cont = 0;
	int maior = 0;
	int result= 0;
	while(fim < m){
	    
	    for(int i=0; i<n; i++)
		if(l[i] <= fim && r[i] > maior)
		    maior = r[i];

	    fim = maior;
	    result++;
	}
	return result;
    }
    
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int m = stdin.nextInt();
	int n = stdin.nextInt();
	int l[] = new int[n];
	int r[] = new int[n];
	
	for (int i=0; i<n; i++){
	    l[i] = stdin.nextInt();
	    r[i] = stdin.nextInt();
	}
	int result = cobrir(m, n, l, r);
	System.out.println(result);
    }
}
