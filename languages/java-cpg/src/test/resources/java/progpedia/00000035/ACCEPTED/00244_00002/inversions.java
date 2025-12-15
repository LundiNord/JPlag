// Exemplo de implementacao do mergesort
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.util.Scanner;

public class inversions {

    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
		int i, p1, p2, ctr = 0;
		int aux[] = new int[end+1];

		p1=start;     // "Apontador" do array da metade esquerda
		p2=middle+1;  // "Apontador" do array da metade direita
		i = start;    // "Apontador" do array aux[] a conter juncao
		while (p1<=middle && p2<=end) {            // Enquanto de para comparar
		    if (v[p1] <= v[p2]) { 			 	   // Escolher o menor e adicionar				
		    	aux[i++] = v[p1++]; 
		    	//ctr++; 
		    } 
		    else { 
		    	aux[i++] = v[p2++]; 
		    	ctr+=p2-i; 
		    }
		}
		while (p1<=middle) 
			aux[i++] = v[p1++];     // Adicionar o que resta
		while (p2<=end) {
			aux[i++] = v[p2++];
			//ctr++;
		}

		for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]

		return ctr;
	}
   		

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle, ctr = 0;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    ctr += mergesort(v, start, middle);   // Ordenar metade esquerda
	    ctr += mergesort(v, middle+1, end);   // Ordenar metade direita
	    ctr += merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
	return ctr;
    }
 
    public static void main(String args[]) {
    int inv = 0;
	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	inv = mergesort(v, 0, n-1);

	System.out.println(inv);
    }
}