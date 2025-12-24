import java.io.*;
import java.util.*;



public class D007 {

    static int valores[];
    static int pares[];
    static int query[];
    static int n;
    static int k;
    static int best;

    public static void  lerInput(){
	Scanner stdin = new Scanner(System.in);
        n  = stdin.nextInt();	
	valores = new int[n];
	for(int i = 0; i < n; i++){
	    valores[i] = stdin.nextInt();
	}
	pares = new int[n*(n-1)/2];

	k = stdin.nextInt();
	query = new int[k];
	for ( int i = 0; i < k ; i++)
	    query[i] = stdin.nextInt();
    }

    public static void criarPares(){
	int pos = 0;
	for (int i = 0; i < n-1; i++)
	    for(int j = i+1; j < n; j++){
		pares[pos] = valores[i] + valores[j];
		pos++;
	    }
	Arrays.sort(pares);
	n = pos;
    }

    public static void solve(){
	for (int i = 0; i < k; i++){
	    best = pares[0];
	    System.out.println(search(query[i], 0, n-1));
	}
    }

    public static int search(int key, int inicio, int fim){
	while(inicio <= fim){
	    int meio = (fim - inicio)/2 +inicio;
	    int val;
	    val = best - key;
	    if (val < 0)
		val = val * -1;
	    if(pares[meio] == key)
		return pares[meio];
	    else if(pares[meio]>key){
		if ((pares[meio] - key) == val && best != pares[0]){
		    System.out.print(best + " ");
		    best = pares[meio];
		}
		else if((pares[meio] - key) < val)
		    best = pares[meio];
		fim = meio - 1; 
	    }
	    else{
		if ((key - pares[meio]) == val && best != pares[0]){
		    System.out.print(best + " ");
		    best = pares[meio];
		}
		else if((key - pares[meio]) < val)
		    best = pares[meio];
		inicio = meio + 1;
	    }
	}
	return best;
    }

    public static void main(String args[]) {

	
	lerInput();
	criarPares();
	solve();		
	
    }
}


