import java.io.*;
import java.util.*;

public class Prob5 {
    public static int contador = 0;
    
    
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static void merge(int v[], int start, int middle, int end) {
	//System.out.println("Contador começa a " + contador);
	int i, p1, p2,flag = 0;
	int aux[] = new int[end+1];
	
	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    else{
		//System.out.println("Como " + v[p1] + " > " + v[p2] + ", troca e CONTADOR++");
                aux[i++] = v[p2++];
		contador ++;
		flag++;
		//System.out.println("Contador vai a " + contador);
	    }
	}
	while (p1<=middle){   // Adicionar o que resta
	    //System.out.println("Como v[p2] já terminou, colocando v[p1] e a determinar . . .");
	    aux[i++] = v[p1++];
	    //System.out.println("A verificar se v[p2] tinha só 1 elemento");
	    if(flag != 1)
		contador++;
	    //System.out.println("Contador vai a " + contador);
	}
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
	//System.out.println("Contador acaba a " + contador);
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static void mergesort(int v[], int start, int end) {
	int middle;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    mergesort(v, start, middle);   // Ordenar metade esquerda
	    mergesort(v, middle+1, end);   // Ordenar metade direita
	    merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	// Ordenar
	mergesort(v, 0, n-1);

	// Imprimir
	//for (int i=0; i<n; i++) {
	//  if (i>0) System.out.print(' ');
	//  System.out.print(v[i]);
	//	}
	//System.out.println();
	System.out.println(contador);
    }
}
