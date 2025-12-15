import java.io.*;
import java.util.*;



public class D007 {

    static int valores[];
    static int pares[];
    static int query[];
    static int n;
    static int k;
    static int best[];

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
	best = new int[k];
	for (int i = 0; i < k; i++){
	    best[0] = pares[0];
	    int l = search(query[i], 0, n-1);
	    System.out.print(best[0]);
	    for (int j = 1; j <= l ; j++)
		System.out.print(" " + best[j]);
	    System.out.println();
	}
    }

    public static int search(int key, int inicio, int fim){
	int i = 0;
	while(inicio <= fim){
	    int meio = (fim - inicio)/2 +inicio;
	    int val;
	    val = best[0] - key;
	    if (val < 0)
		val = val * -1;
	    if(pares[meio] == key){
		best[0] = pares[meio];
		return 0;
	    }
	    else if(pares[meio]>key){
		if ((pares[meio] - key) == val && best[0] != pares[0]){
		    i++;
		    int flag = 0;
		    for(int j = 0; j < i && flag == 0; j++){
			if (best[j] == pares[meio]){
			    flag = 1;
			    i--; 
			}
			else if(best[j] > pares[meio]){
			    int aux = best[j];
			    best[j] = pares[meio];
			    best[j+1] = aux;
			}
			else{
			    best[i] = pares[meio];
			}
		    }
		}
		else if((pares[meio] - key) < val){
		    best[0] = pares[meio];
		    i = 0;
		}
		fim = meio-1; 
	    }
	    else{
		if ((key - pares[meio]) == val && best[0] != pares[0]){
		    i++;
		    int flag = 0;
		    for(int j = 0; j < i && flag == 0; j++){
			if (best[j] == pares[meio]){
			    flag = 1;
			    i--; 
			}
			else if(best[j] > pares[meio]){
			    int aux = best[j];
			    best[j] = pares[meio];
			    best[j+1] = aux;
			}
			else{
			    best[i] = pares[meio];
			}
		    }
		}
		else if((key - pares[meio]) < val){
		    best[0] = pares[meio];
		    i = 0;
		}
		inicio = meio + 1;
	    }
	}
	return i;
    }

    public static void main(String args[]) {

	
	lerInput();
	criarPares();
	solve();		
	
    }
}


