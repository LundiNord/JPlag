import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sapateiro {

    public static Pair[] v;
    public static int comp = 0;
    
    public static void imprLista(Pair[] lista) {
        int aux = 0;
        for (int i = 0; i < lista.length; i++) {
            aux=lista[i].getN()+1;
            if (i == lista.length - 1) {
                System.out.print(aux);
            } else {
                System.out.print(aux + " ");
            }
        }
        System.out.println("");
    }

    public static void lerScanner() {
        int k = 0;

        Scanner scan = new Scanner(System.in);

        int nVec = Integer.parseInt(scan.next());

        Pair par;

        v = new Pair[nVec];

        while (k < nVec) {
            par = new Pair(k, Integer.parseInt(scan.next()), Integer.parseInt(scan.next()));
            v[k] = par;
            k++;
        }
    }

    public static class Pair {

        private int n;
        private int first;
        private int second;

        public Pair(int n, int first, int second) {
            super();
            this.n = n;
            this.first = first;
            this.second = second;
        }

        public String toString() {
            return n + ": " + "(" + first + "," + second + ")";
        }

        public int getN() {
            return n;
        }

        public void setN(int first) {
            this.n = n;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

    public static class MyPairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair pair0, Pair pair1) {
            float prop1, prop2;

            prop1 = pair0.getFirst() / (float) pair0.getSecond();
            prop2 = pair1.getFirst() / (float) pair1.getSecond();

            if (prop1 == prop2) {
                return pair0.getN() - pair1.getN();
            }

			//System.out.println("pro1:"+prop1+" prop2:"+prop2);
            if (prop1 > prop2) {
                return 1;
            } else {
                return -1;
            }

            //return cmp;
        }
    }

    public static void main(String[] args) {
        lerScanner();
        //imprLista(v);
        Arrays.sort(v, new MyPairComparator());
        //System.out.println("-----------------");
        imprLista(v);
    }

}
