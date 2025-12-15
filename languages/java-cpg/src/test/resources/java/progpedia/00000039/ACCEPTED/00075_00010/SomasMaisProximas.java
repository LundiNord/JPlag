
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SomasMaisProximas {

    public static void main(String args[]) {
        Sol s = new Sol();
    }
}

class Sol {
    
    private int[] somas;
    private int[] lista;
    private int[] query;
    
    private int testeMode = 0;

    public Sol() {
        if (testeMode == 0) this.read();
        else                this.fakeRead();
        this.calc();
        this.order();
        if (testeMode == 1) this.printDebug();
        this.solve();
    }
    

    public void solve() {
        List<Integer> print = new ArrayList<Integer>();
        for (Integer q : query) {
            this.binarySearch(this.somas, q);
        }
    }

    
    public void binarySearch(int v[], int key) {
        int low = 0;
        int high = v.length -1;
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
    
    public void order() {
        Arrays.sort(this.somas);
    }

    public void calc() {      
        int len = this.lista.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                this.somas[count] = lista[i] + lista[j];
                count++;
            }
        }
    }

    public void read() {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int aux = (n * (n - 1)) / 2;
        this.somas = new int[aux];
        this.lista = new int[n];
        for (int i = 0; i < n; i++) {
            this.lista[i] = stdin.nextInt();
        }
        n = stdin.nextInt();
        this.query = new int[n];
        for (int i = 0; i < n; i++) {
            this.query[i] = stdin.nextInt();
        }
    }

    public void fakeRead() {
        this.lista = new int[]{71, 4, 50, 55, 7, 82, 33, 85, 98, 60, 47, 69, 17, 13, 6, 88, 16, 44, 80, 43};
        this.query = new int[]{147, 183, 183, 122, 3, 127, 182, 26, 197, 5, 181, 199, 59, 187, 32, 143, 72, 29, 97, 183, 75, 66, 152, 33, 130, 9, 20, 145, 104, 200};
        //this.query = new int[]{147};
        int aux = (lista.length * (lista.length - 1)) / 2;
        this.somas = new int[aux];
    }

    public void printDebug() {
        System.out.println("Lista: ");
        for (int i = 0; i < lista.length; i++) {
            System.out.print(lista[i] + " ");
        }
        System.out.println();

        System.out.println("Query: ");
        for (int i = 0; i < query.length; i++) {
            System.out.print(query[i] + " ");
        }
        System.out.println();

        System.out.println("Somas: ");
        for (int i = 0; i < this.somas.length; i++) {
            System.out.print(somas[i] + " ");
        }
        System.out.println();
    }
}
