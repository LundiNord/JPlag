import java.io.*;
import java.util.*;

public class testInverse {

    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	/*for (int k=start; k<=end; k++) {
	    System.out.print(v[k] + " ");
	    if (k == middle) System.out.print(" ||| ");
	}
	System.out.println();*/
	    

	int i, p1, p2, cont1=0;
	int aux[] = new int[end+1];

	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {// Enquanto de para comparar
	    if (v[p1] <= v[p2])
		aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    else{
		//		System.out.println("!! " + v[p1] + " " + v[p2]);
		aux[i++] = v[p2++];
		cont1 += middle - p1 + 1;
	    }
	}
	
	while (p1<=middle)
	    aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)
	    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]

	//System.out.println(" -> " + cont1);
	
	
	return cont1;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle, cont=0;
	if (start<end) {// Parar quando tamanho do array < 2
	   middle = (start+end)/2;        // Calcular ponto medio
	   cont += mergesort(v, start, middle);   // Ordenar metade esquerda
	   cont += mergesort(v, middle+1, end);   // Ordenar metade direita
	   cont += merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
	return cont;
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();
	

	// Imprimir
	    System.out.print(mergesort(v, 0, n-1));
	    System.out.println();
    }
}
