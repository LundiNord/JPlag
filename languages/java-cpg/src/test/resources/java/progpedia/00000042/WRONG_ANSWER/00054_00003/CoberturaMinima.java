
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class CoberturaMinima {

    public static void main(String args[]) {
        Solve s = new Solve();
    }
}

class Solve {

    Segmentos[] segmento;
    int m, count, testeMode;

    public Solve() {
        this.testeMode = 0;

        this.count = 0;
        if (this.testeMode == 0) {
            this.read();
        } else {
            this.fakeRead();
        }
        this.order();
        this.calc();
        this.printCount();
    }

    private void read() {
        Scanner stdin = new Scanner(System.in);
        this.m = stdin.nextInt();
        int n = stdin.nextInt();
        segmento = new Segmentos[n];
        for (int i = 0; i < n; i++) {
            segmento[i] = new Segmentos(stdin.nextInt(), stdin.nextInt());
        }
    }

    private void fakeRead() {
        int n = 5;
        this.m = 6;
        this.segmento = new Segmentos[n];
        this.segmento[0] = new Segmentos(0, 2);
        this.segmento[1] = new Segmentos(3, 5);
        this.segmento[2] = new Segmentos(4, 6);
        this.segmento[3] = new Segmentos(3, 8);
        this.segmento[4] = new Segmentos(2, 4);
    }

    private void order() {
        Arrays.sort(this.segmento, new SegmentosComparator());
    }

    private void calc() {
        int newM = 0;
        int flag = 0, index = -1;
        while (this.m >= newM) {
            for (int i = 0; i < this.segmento.length; i++) {
                if (this.segmento[i].getInicio() <= newM && this.segmento[i].getUsed() == 0 && flag == 0) {
                    index = i;
                    flag = 1;
                }
            }
            flag = 0;
            this.segmento[index].setUsed();
            newM += this.segmento[index].getLen();
            this.count = this.count + 1;
        }
    }
    
    private void printCount(){
        System.out.println(this.count);
    }
}

class Segmentos<A, B> {

    int inicio, fim, used, len;

    public Segmentos(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.len = fim-inicio;
        this.used = 0;
    }

    public int getInicio() {
        return this.inicio;
    }

    public int getFim() {
        return this.fim;
    }

    public void setUsed() {
        this.used = 1;
    }

    public int getUsed() {
        return this.used;
    }

    public int getLen() {
        return this.len;
    }

}

class SegmentosComparator implements Comparator<Segmentos> {

    @Override
    public int compare(Segmentos segmentoUm, Segmentos segmentoDois) {
        return segmentoDois.getLen() - segmentoUm.getLen();
    }
}
