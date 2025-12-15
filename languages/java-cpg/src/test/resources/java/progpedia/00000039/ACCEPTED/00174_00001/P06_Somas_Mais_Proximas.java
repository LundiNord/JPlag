
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Herlander Silva
 */
class MergeSort2 {

    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static void merge(int v[], int start, int middle, int end) {
        int i, p1, p2;
        int aux[] = new int[end + 1];

        p1 = start;     // "Apontador" do array da metade esquerda
        p2 = middle + 1;  // "Apontador" do array da metade direita
        i = start;    // "Apontador" do array aux[] a conter juncao
        while (p1 <= middle && p2 <= end) {            // Enquanto de para comparar
            if (v[p1] <= v[p2]) {
                aux[i++] = v[p1++];  // Escolher o menor e adicionar
            } else {
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
    }

    // Ordenar array v[] com mergesort entre posicoes start e end
    public static void mergesort(int v[], int start, int end) {
        int middle;
        if (start < end) {                 // Parar quando tamanho do array < 2
            middle = (start + end) / 2;        // Calcular ponto medio
            mergesort(v, start, middle);   // Ordenar metade esquerda
            mergesort(v, middle + 1, end);   // Ordenar metade direita
            merge(v, start, middle, end);  // Combinar duas metades ordenadas
        }
    }
}

/**
 *
 * @author Herlander Silva
 */
public class P06_Somas_Mais_Proximas {

    static int[] somas(int[] v) {
        int[] arraySomas = new int[(v.length - 1) / 2 * (v.length) + (((v.length - 1) % 2) * (int) Math.ceil((double) (v.length - 1) / 2))];
        int idx = 0;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                arraySomas[idx] = v[i] + v[j];
                idx++;
            }
        }
        return arraySomas;
    }

    static void bsearch(int[] v, int low, int high, int key) {
        if (key < v[low]) {
            System.out.println(v[low]);
        } else if (key > v[high]) {
            System.out.println(v[high]);
        } else {
            int middle;
            while (low < high) {
                middle = low + (high - low) / 2;
                if (key < v[middle]) {
                    high = middle;
                } else {
                    low = middle + 1;
                }
//                System.out.println("low:"+low+" high:"+high);
            }
            int diffLow = key - v[low-1];
            int diffHigh = v[high] - key;
            if (diffLow == diffHigh) {
                System.out.println(v[low-1] + " " + v[high]);
            } else if (diffLow < diffHigh) {
                System.out.println(v[low-1]);
            } else {
                System.out.println(v[high]);
            }
        }
    }

    static void near(int[] vSums, int[] quest) {
        for (int i = 0; i < quest.length; i++) {
//            System.out.print(quest[i]+" ");
            bsearch(vSums,0,vSums.length-1,quest[i]);
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin = new Scanner(System.in);
//        @SuppressWarnings("resource")
//        Scanner stdin = new Scanner(new File("Problemas/P06_Somas_Mais_Proximas.in"));
        int dim = stdin.nextInt();

        int[] v = new int[dim];
        int idx = 0;
        while (idx <= dim - 1) {
            v[idx] = stdin.nextInt();
            idx++;
        }
        int[] somas = somas(v);
//        MergeSort2.mergesort(somas, 0, somas.length-1);
        Arrays.sort(somas);

//        for (int i = 0; i < somas.length - 1; i++) {
//            System.out.print(somas[i] + " ");
//        }
//        System.out.println("");

        int nQuest = stdin.nextInt();
        int[] vQuest = new int[nQuest];
        idx = 0;
        while (idx <= nQuest - 1) {
            vQuest[idx] = stdin.nextInt();
            idx++;
        }
        near(somas, vQuest);
    }
}
