import java.util.Scanner;
import java.io.*;

public class D005 {
  static int n, count,k;
  static int v[];

  //-----------------------------------------------------------

  static int merge(int v[], int start, int middle, int end) {
    int i, p1, p2, count2;
	  int aux[] = new int[end+1];



	  p1=start;     // "Apontador" do array da metade esquerda
	  p2=middle+1;  // "Apontador" do array da metade direita
	  i = start;    // "Apontador" do array aux[] a conter juncao
	  while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];  // Escolher o menor e adicionar
	    else  {
          count += (end-p2)+1;
                      aux[i++] = v[p2++];
      }
	  }
	  while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
	  while (p2<=end)    aux[i++] = v[p2++];

	  for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]

    return count;
  }

  // Ordenar array v[] com mergesort entre posicoes start e end
  static int mergesort(int v[], int start, int end) {
	  int middle;
    count = 0;

    if (start<end) {                 // Parar quando tamanho do array < 2
      middle = (start+end)/2;        // Calcular ponto medio
	    count += mergesort(v, start, middle);   // Ordenar metade esquerda
	    count += mergesort(v, middle+1, end);   // Ordenar metade direita
	    count += merge(v, start, middle, end);  // Combinar duas metades ordenadas
	  }

    return count;
  }

  //-----------------------------------------------------------

  public static void main(String[] args) {

    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();

    v = new int[n];

    for (int i=0; i<n; i++) {
      v[i] = stdin.nextInt();
    }

    // Ordenar
  System.out.println(mergesort(v, 0, n-1));


  }

}
