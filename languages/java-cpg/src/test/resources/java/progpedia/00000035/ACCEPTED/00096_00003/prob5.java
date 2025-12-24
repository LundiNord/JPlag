import java.util.*;

class prob5 {
    static int count = 0;
    static void merge(int v[], int start, int middle, int end) {
        int i, p1, p2;
        int aux[] = new int[end+1];

        p1=start;     // "Apontador" do array da metade esquerda
        p2=middle+1;  // "Apontador" do array da metade direita
        i = start;    // "Apontador" do array aux[] a conter juncao
        while (p1<=middle && p2<=end) {            // Enquanto de para comparar
            if (v[p1] <= v[p2]) {aux[i++] = v[p1++]; }  // Escolher o menor e adicionar
            else                {count+=(middle-p1)+1; aux[i++] = v[p2++]; }
        }
        while (p1<=middle) {aux[i++] = v[p1++];}     // Adicionar o que resta
        while (p2<=end)    {aux[i++] = v[p2++];}
        
        for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
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

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num[] = new int[n];
        
        for(int i=0; i<n; i++) 
            num[i] = in.nextInt();
        
        mergesort(num, 0, n-1);

        System.out.println(count);


            
    }
}