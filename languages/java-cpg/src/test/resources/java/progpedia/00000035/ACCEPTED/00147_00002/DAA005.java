import java.util.Scanner;

public class DAA005 {
    
    public static int n, v[], shift;
    
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];

    shift = 0;
        
	p1=start;     // "Apontador" do array da metade esquerda
	p2=middle+1;  // "Apontador" do array da metade direita
	i = start;    // "Apontador" do array aux[] a conter juncao
	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
	    if (v[p1] <= v[p2]) { aux[i++] = v[p1++]; }  // Escolher o menor e adicionar
	    else                { aux[i++] = v[p2++]; shift += middle+1-p1; }
	}
	while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    
    return shift;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
    shift = 0;
	int middle;
	if (start<end) {                 // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        // Calcular ponto medio
	    shift += mergesort(v, start, middle);   // Ordenar metade esquerda
	    shift += mergesort(v, middle+1, end);   // Ordenar metade direita
	    shift += merge(v, start, middle, end);  // Combinar duas metades ordenadas
	}
    return shift;
    }
    
    public static void main (String args[]) {
        
        Scanner in = new Scanner(System.in);
        
        n = in.nextInt();
        
        v = new int[n];
        
        for (int i=0; i<n; i++) {
            
            v[i] = in.nextInt();
        }
        
        //Implementação da solução n^2
        
        /*shift = 0;
        
        for (int i=0; i<n; i++)
            for (int j=i+1; j<n; j++)
                if (a[i] > a[j]) shift++;
        
        System.out.println(shift);*/
        
        System.out.println(mergesort(v, 0, n-1));
        
    }
}