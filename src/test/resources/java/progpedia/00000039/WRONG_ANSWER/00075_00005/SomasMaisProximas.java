
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SomasMaisProximas {

    public static void main(String args[]) {
        Sol s = new Sol();
    }
}

class Sol {
    
    private ArrayList<Integer> somas = new ArrayList<Integer>();
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
            print(= this.binarySearch(somas, q);)
            int size = print.size();
            if (print.size() == 1)      System.out.println(print.get(0));
            else {
                for (int i = 0; i<size; i++) {
                    if (i<size-1)       System.out.print(print.get(i) + " ");
                    else                System.out.println(print.get(i));
                }
            }
        }
    }

    public List<Integer> binarySearch(List<Integer> lst, int key) {
        int inicio = 0;
        int fim = lst.size();
        int meio;
        List<Integer> sol = new ArrayList<Integer>();
        sol.add(-1);

        while (inicio <= fim) {
            if (inicio == fim){
                sol.clear();
                sol.add(lst.get(inicio));
                return sol;
            }
            if ((fim - inicio) <= 6){
                //return this.aproxMax(lst, key, inicio, fim);
                return this.aproxMax(lst.subList(inicio, fim), key);
            }
            meio = (inicio + fim) / 2;
            if (lst.get(meio) == key) {
                sol.clear();
                sol.add(lst.get(meio));
                return sol;
            }
            int valorMeio = lst.get(meio);
            if (key > valorMeio) {
                inicio = meio;
            } else {
                fim = meio;
            }
        }
        return sol;
    }
    
    public List<Integer> aproxMax(List<Integer> lst, int key){
        int flag = 0;
        int min = 0;
        List<Integer> f = new ArrayList<Integer>();
        for (int i = 0; i<lst.size(); i++){
            int dif = Math.abs(key - lst.get(i));
            if (flag == 0){
                min = dif+1;
                flag = 1;
            }
            if (dif <= min){
                min = dif;
                if (f.size()>0 && min<Math.abs(key - f.get(0))){
                    f.clear();
                }
                if (!f.contains(lst.get(i)))    f.add(lst.get(i));
            }
        }
        return f;
    }
    
    /*
    public List<Integer> aproxMax(List<Integer> lst, int key, int inicio, int fim){
        int flag = 0;
        int min = 0;
        List<Integer> f = new ArrayList<Integer>();
        for (int i = inicio; i<=fim; i++){
            int dif = Math.abs(key - lst.get(i));
            if (flag == 0){
                min = dif+1;
                flag = 1;
            }
            if (dif <= min){
                min = dif;
                if (f.size()>0 && min<Math.abs(key - f.get(0))){
                    f.clear();
                }
                if (!f.contains(lst.get(i)))    f.add(lst.get(i));
            }
        }
        return f;
    }*/

    public void order() {
        Collections.sort(this.somas);
    }

    public void calc() {
        int len = this.lista.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                this.somas.add(lista[i] + lista[j]);
            }
        }
    }

    public void read() {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
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
        this.lista = new int[]{7, 24, 18, 25, 49, 17, 30, 26, 14, 47};
        this.query = new int[]{6, 78, 2, 86, 2, 92, 74, 39, 4, 16, 41, 99, 99, 45, 44, 49, 66, 42, 59, 72};
        //this.query = new int[]{59};
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
        for (int i = 0; i < this.somas.size(); i++) {
            System.out.print(somas.get(i) + " ");
        }
        System.out.println();
    }
}
