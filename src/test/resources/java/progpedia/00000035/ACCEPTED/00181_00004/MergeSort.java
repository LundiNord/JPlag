// Exemplo de implementacao do mergesort
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class MergeSort {

    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int  merge(int v[], int start, int middle, int end) {
	int i, p1, p2,l;
	l=0;
	int aux[] = new int[end+1];

	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2])

		{
		   
		    aux[i++] = v[p1++];// Escolher o menor e adicionar
		    
		}
	    else
		{
		    l+=(p2-i); 
		    aux[i++] = v[p2++];
		   
		}
	}
	while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++)
	    {
		v[i] = aux[i];  // Copiar array aux[] para v[]
		
	    }
	return l;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle,i;
i=0;
	
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;     // Calcular ponto medio
	    
	    i+=mergesort(v, start, middle);   // Ordenar metade esquerda
	    i+=mergesort(v, middle+1, end);   // Ordenar metade direita
	    i+=merge(v, start, middle, end);  // Combinar duas metades ordenadas
	   
	}
	 return i;
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	//	mergesort(v, 0, n-1);

	// Imprimir
	/*for (int i=0; i<n; i++) {
	    if (i>0) System.out.print(' ');
	    System.out.print(v[i]);
	    }*/
	System.out.println((mergesort(v, 0, n-1)));
    }
}
