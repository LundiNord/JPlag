import java.util.*;
import java.io.*;

class inversoes{
  static int merge(int v[], int start, int middle, int end) {
  	int i, p1, p2;
  	int aux[] = new int[end+1];
    int cont=0;
  	p1=start;     // "Apontador" do array da metade esquerda
  	p2=middle+1;  // "Apontador" do array da metade direita
  	i = start;    // "Apontador" do array aux[] a conter juncao
  	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
  	    if (v[p1] <= v[p2]){ aux[i++] = v[p1++];cont++;}  // Escolher o menor e adicionar
  	    else               { aux[i++] = v[p2++];cont++;}

  	}

  	while (p1<=middle) {aux[i++] = v[p1++];cont++;}    // Adicionar o que resta
  	while (p2<=end)    {aux[i++] = v[p2++];cont++;}
  	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    return cont;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
    	int middle;
      int cont=0;
    	if (start<end) {                 // Parar quando tamanho do array < 2
    	    middle = (start+end)/2;        // Calcular ponto medio
    	    cont += mergesort(v, start, middle);   // Ordenar metade esquerda
    	    cont += mergesort(v, middle+1, end);   // Ordenar metade direita
    	    cont += merge(v, start, middle, end);  // Combinar duas metades ordenadas
          return cont;
    	}
      return 0;
    }

  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int v[] = new int[n];
    for(int i = 0; i < n; i++){
      v[i] = stdin.nextInt();
    }
    System.out.println(mergesort(v,0,n-1) - 3);
  }
}
