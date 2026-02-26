import java.util.*;
import java.io.*;
class Prob5{
    public static int count=0;  
    static void merge(int v[], int start, int mid, int end){

	int i, p1,p2;
	int aux[]= new int[end+1];
	p1=start;
	p2=mid+1;
	i=start;
	while(p1<=mid && p2<=end){
	    if (v[p1]<=v[p2])
		aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    else{
		aux[i++] = v[p2++];
		count+=p2-i;}
	}
	while (p1<=mid)
	    aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)
	    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++)
	    v[i] = aux[i]; 
    }
    static void mergesort(int v[], int start, int end) {
	int mid;
	if (start<end) {                // Parar quando tamanho do array < 2
	    mid = (start+end)/2;        // Calcular ponto medio
	    mergesort(v, start, mid);   // Ordenar metade esquerda
	    mergesort(v, mid+1, end);   // Ordenar metade direita
	    merge(v, start, mid, end);  // Combinar duas metades ordenadas
	}
	
    }
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int v[] = new int[n];
	for(int i=0;i<n;i++){     //ler array
	    v[i]=in.nextInt();
	}
	mergesort(v,0,n-1);       //ordenar array
	System.out.println(count);
    }
    
}

