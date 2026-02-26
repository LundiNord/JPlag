import java.util.Scanner;

/**
 *
 * @author ppetz
 */
public class SomasProximas {

    public static int[] inicial;
    public static int[] aux;
    public static int[] v;
    
    static Scanner scan = new Scanner(System.in);

    public static void imprLista(int[] lista) {
        for (int i = 0; i < lista.length; i++) {
            if (i == lista.length - 1) {
                System.out.print(lista[i]);
            } else {
                System.out.print(lista[i] + " ");
            }
        }
        System.out.println("");
    }

    public static void mergesort(int v[], int start, int end) {
        if (end - start == 0) {
            return;
        }

        int middle = (start + end) / 2;

        mergesort(v, start, middle);
        mergesort(v, middle + 1, end);
        merge(v, start, middle, end); /*fazer o merge das duas partições anteriores*/

    }

    public static void merge(int v[], int start, int middle, int end) {
        for (int h = start; h <= end; h++) {
            aux[h] = v[h];
        }

        int i = start, j = middle + 1, k = start;
        while (i <= middle && j <= end) {
            if (aux[i] <= aux[j]) {
                v[k] = aux[i];
                i++;
            } else {
                v[k] = aux[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            v[k] = aux[i];
            k++;
            i++;
        }
    }

    public static void lerScanner() {
        int k = 0;

        int nVec = Integer.parseInt(scan.next());

        inicial = new int[nVec];

        int nVecT = (nVec * (nVec - 1)) / 2;

        v = new int[nVecT];
        aux = new int[nVecT];

        while (k < nVec) {
            inicial[k] = Integer.parseInt(scan.next());
            k++;
        }
    }

    public static void lerScanner2() {
        int k = 0;

        int nPerg = Integer.parseInt(scan.next());

        while (k < nPerg) {
            pesqBin(v, 0, v.length - 1, Integer.parseInt(scan.next()));
            k++;
        }
    }

    public static void pesqBin(int v[], int low, int high, int key) {
        int middle, diffT = Math.abs(v[low + (high - low) / 2] - key) + 1, diff;
        int[] sol = new int[2];
        int nNum = 0;
        while (low <= high) {
            middle = low + (high - low) / 2;

            if (key == v[middle]) {
                System.out.println(key);
                return;
            }

            diff = Math.abs(v[middle] - key);

            if (diff < diffT) {
                nNum = 1;
                diffT = diff;
                sol[0] = v[middle];
            } else {
                if (diff == diffT && sol[0] != v[middle]) {
                    nNum = 2;
                    sol[1] = v[middle];
                }
            }

            if (low == high) {
                break;
            }

            if (key < v[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        if (nNum == 1) {
            System.out.println(sol[0]);
        } else {
            if (sol[0] < sol[1]) {
                System.out.println(sol[0] + " " + sol[1]);
            } else {
                System.out.println(sol[1] + " " + sol[0]);
            }
        }
    }

    private static void criarSomas() {
        int cont = 0;

        for (int i = 0; i < inicial.length; i++) {
            for (int j = i + 1; j < inicial.length; j++) {
                v[cont] = (inicial[i] + inicial[j]);
                cont++;
            }
        }
    }

    public static void main(String[] args) {
        lerScanner();

        criarSomas();

        //imprLista(inicial);
        //imprLista(v);
        mergesort(v, 0, v.length - 1);

        lerScanner2();

        //imprLista(v);
    }

}
