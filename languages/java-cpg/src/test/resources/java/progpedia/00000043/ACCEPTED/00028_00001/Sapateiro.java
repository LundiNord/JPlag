
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sapateiro {

    public static void main(String args[]) {
        Res r = new Res();
    }
}

class Res {

    int n;
    Sapatos[] lista;

    public Res() {
        this.read();
        this.order();
        this.printCalc();
    }

    private void printCalc() {
        int aux = 0;
        for (int i = 0; i < lista.length; i++) {
            aux = lista[i].getN() + 1;
            if (i == lista.length - 1) {
                System.out.print(aux);
            } else {
                System.out.print(aux + " ");
            }
        }
        System.out.println("");
    }

    private void order() {
        Arrays.sort(this.lista, new SapatosComparator());
    }

    private void read() {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        Sapatos s;
        lista = new Sapatos[n];
        int count = 0;
        while (count < n) {
            s = new Sapatos(count, stdin.nextInt(), stdin.nextInt());
            lista[count] = s;
            count++;
        }
    }
}

class SapatosComparator implements Comparator<Sapatos> {

    @Override
    public int compare(Sapatos s0, Sapatos s1) {
        float a, b;

        a = s0.getDia() / (float) s0.getMulta();
        b = s1.getDia() / (float) s1.getMulta();

        if (a == b) {
            return s0.getN() - s1.getN();
        }

        if (a > b) {
            return 1;
        } else {
            return -1;
        }
    }
}

class Sapatos {

    private int n;
    private int dia;
    private int multa;

    public Sapatos(int n, int dia, int multa) {
        this.n = n;
        this.dia = dia;
        this.multa = multa;
    }

    public int getN() {
        return n;
    }

    public int getDia() {
        return dia;
    }

    public int getMulta() {
        return multa;
    }
}
