
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Herlander Silva
 */
class MergeSort {
    static int inv = 0;
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
        int i, p1, p2;
        int aux[] = new int[end + 1];

        p1 = start;     // "Apontador" do array da metade esquerda
        p2 = middle + 1;  // "Apontador" do array da metade direita
        i = start;    // "Apontador" do array aux[] a conter juncao
        while (p1 <= middle && p2 <= end) {            // Enquanto de para comparar
            if (v[p1] <= v[p2]) {
                aux[i++] = v[p1++];  // Escolher o menor e adicionar
            } else {
                inv++;
                aux[i++] = v[p2++];
            }
        }
        while (p1 <= middle) {
            aux[i++] = v[p1++];     // Adicionar o que resta
        }
        while (p2 <= end) {
            aux[i++] = v[p2++];
        }

        for (i = start; i <= end; i++) {
            v[i] = aux[i];  // Copiar array aux[] para v[]
        }
        return inv;
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    public static int mergesort(int v[], int start, int end) {
        int inv = 0;
        int middle;
        if (start < end) {                 // Parar quando tamanho do array < 2
            middle = (start + end) / 2;        // Calcular ponto medio
            inv += mergesort(v, start, middle);   // Ordenar metade esquerda
            inv += mergesort(v, middle + 1, end);   // Ordenar metade direita
            inv += merge(v, start, middle, end);  // Combinar duas metades ordenadas
        }
        return inv;
    }
}

public class P05_Inversoes {

    static int calcInvertionsBruteForce(int[] v) {
        int inv = 0;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                if (v[i] > v[j]) {
                    inv++;
                }
            }
        }
        return inv;
    }

    static int calcInvertionsMergeSort(int[] v) {
        return MergeSort.mergesort(v, 0, v.length-1);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner stdin = new Scanner(System.in);
//        @SuppressWarnings("resource")
//        Scanner stdin = new Scanner(new File("Problemas/P05_Inversoes.in"));
        int dim = stdin.nextInt();

        int[] v = new int[dim];
        int idx = 0;
        while (idx <= dim - 1) {
            v[idx] = stdin.nextInt();
            idx++;
        }

//        System.out.println(calcInvertionsBruteForce(v));
        System.out.println(calcInvertionsMergeSort(v));
    }
}
