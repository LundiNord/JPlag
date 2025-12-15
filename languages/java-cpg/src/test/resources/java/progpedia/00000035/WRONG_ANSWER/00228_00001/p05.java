//Exercicio feito em conjunto pelos alunos
// Carlos Bernardes up201303743
// Célio Rodrigues up201303171
// Claudia Correia up201304727

import java.io.*;
import java.util.*;

public class p05 {
	static	int count=0;
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];
	int c=0;
	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]){
	     aux[i++] = v[p1++];  // Escolher o menor e adicionar
	     //count++;
	 	}
	    else{ //v[p1] e maior que v[p2] e p2> p1
            aux[i++] = v[p2++];
            c++;
            //System.out.println(v[p1]+">"+v[p2]);
        }
	}

	while (p1<=middle){
	 	aux[i++] = v[p1++];     // Adicionar o que resta
	 	//count++;
		}

	while (p2<=end){
		 aux[i++] = v[p2++];
		 //count++;
		}   	

	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    return c;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
	int middle;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    mergesort(v, start, middle);   // Ordenar metade esquerda
		mergesort(v, middle+1, end);   // Ordenar metade direita
	    count += merge(v, start, middle, end);  // Combinar duas metades ordenadas
		System.out.println("valor de count"+count);
		}
	return count+2;
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	//mergesort(v, 0, n-1);

	System.out.println(mergesort(v,0,n-1));
    }
}