// Exemplo de implementacao do mergesort
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Merge {
    static int sum = 0;
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]){
		sum++;
		p2++;
	    }
	    else p1++;
	    }  
	if (p2==end+1)
	    for(int j=0;j<middle-1;j++){
		if(v[j]>v[j+1])
		    sum++;
	    }
	else
	    for (int j=middle;j<end-1; j++){
		if (v[j]>v[j+1])
		    sum++;
	    }
	return sum;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    sum+=mergesort(v, start, middle);   // Ordenar metade esquerda
	    sum+=mergesort(v, middle+1, end);   // Ordenar metade direita
	    sum+= merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
	return sum;
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	int res = mergesort(v, 0, n-1);

	// Imprimir
	/*for (int i=0; i<n; i++) {
	    if (i>0) System.out.print(' ');
	    System.out.print(v[i]);
	    }*/
	System.out.println(res);
    }
}
