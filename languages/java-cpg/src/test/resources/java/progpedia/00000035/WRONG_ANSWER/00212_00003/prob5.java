import java.util.*;

public class prob5{
	/* public static void main(String args[]){
	/*contador   0
	Para i   0 ate n 􀀀 1 fazer
	Para j   i + 1 ate n 􀀀 1 fazer
	Se v[i] > v[j] ent~ao
	contador   contador + 1
	escrever(contador) 
	Scanner input=new Scanner(System.in);
	int n=input.nextInt();
	int v[]=new int[n];
	for(int i=0;i<n;i++){
		v[i]=input.nextInt();
	}
	int contador=0;
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			if(v[i]>v[j]){
				contador=contador+1;
			}
		}
	}
	System.out.println(contador);
	}*/

// Exemplo de implementacao do mergesort
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------
    static int merge(int a[], int low,int middle, int high) {
    int count=0;
    for (int i = low, lb = low, hb = middle; i < high; i++) {

            if ((hb <= high || lb >= middle && a[lb] >= a[hb])){
               count = count + (middle - lb); 
            } 
                
    }
    return count;
}
    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end, int contador) {
	int middle=(start+end)/2;
	if (start<end) { 
	contador += mergesort(v, start, middle,contador);
	contador += mergesort(v, middle+1, end,contador);
	contador += merge(v, start, middle, end);
	
	}
	return contador;
    }

 
    public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);
	int contador=0; 
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	int vito=mergesort(v, 0, n-1,0);
	System.out.println(vito);
    }
}

