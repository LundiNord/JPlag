

import java.util.*;

public class Prob5 {
    public static int cont=0;
    static void merge(int a1[], int inicio, int meio, int fim) {
	int i, p1, p2;
	int a2[] = new int[fim+1];
       
        
	p1=inicio;     // "Apontador" do array da metade esquerda
	p2=meio+1;  // "Apontador" do array da metade direita
	i = inicio;    // "Apontador" do array aux[] a conter juncao
	while (p1<=meio && p2<=fim) {            // Enquanto de para comparar
	    if (a1[p1] <= a1[p2]) {
                a2[i++] = a1[p1++];  // Escolher o menor e adicionar
                }
	    else            {   
                a2[i++] = a1[p2++];
                cont+=p2-i;
            }
	}
	while (p1<=meio)
            a2[i++] = a1[p1++];     // Adicionar o que resta
	while (p2<=fim)   
            a2[i++] = a1[p2++];
	
	for (i=inicio; i<=fim; i++)
            a1[i] = a2[i];  // Copiar array aux[] para v[]
        
      
     
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int a1[], int inicio, int fim) {
        int meio;
        if(inicio<fim){
            meio= (inicio+fim)/2;
        mergesort(a1, inicio, meio);
        mergesort(a1, meio+1, fim);
        merge(a1, inicio, meio, fim);

	}
        return cont;
    }
    
   
    public static void main(String[] args) {
        Scanner le= new Scanner(System.in);
        int n= le.nextInt();
        int a[]= new int[n];
        for(int i=0; i<n; i++)
            a[i]=le.nextInt();
        System.out.println(mergesort(a, 0, n-1));
    }
    
}
