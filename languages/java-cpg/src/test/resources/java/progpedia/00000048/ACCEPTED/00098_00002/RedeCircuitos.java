import java.util.*;

public class RedeCircuitos {

    public static boolean[][] adjs;
    public static boolean vist[];
    public static int cont;

    public static void lerScanner() {
        int k = 1, nVert, nArest, l, c;

        Scanner scan = new Scanner(System.in);
        nVert = scan.nextInt();
        nArest = scan.nextInt();

        adjs = new boolean[nVert + 1][nVert + 1];
        vist = new boolean[nVert + 1];

        for (k = 1; k <= nArest; k++) {
            l = scan.nextInt();
            c = scan.nextInt();
            adjs[l][c] = true;
            adjs[c][l] = true;
        }
    }

    public static void imprimeMatriz() {
        int i, j;
        for (i = 1; i < adjs.length; i++) {
            for (j = 1; j < adjs.length; j++) {
                if (j != adjs.length - 1) {
                    if (adjs[i][j] == true) {
                        System.out.println(i + ":" + j);
                    }
                } else {
                    if (adjs[i][j] == true) {
                        System.out.println(i + ":" + j);
                    }
                }
            }
        }
    }

    public static int pesquisaNConexos() {
        cont = 0;

        for (int i = 1; i < vist.length; i++) {
            if (!vist[i]) {
                visita(i);
                vist[i] = true;
                cont++;
            }
        }
        
        return cont;
    }

    public static void visita(int no) {
        Stack st = new Stack();

        for (int i = 1; i < adjs.length; i++) {
            if(!vist[i] && adjs[no][i]){
				vist[i]=true;
                visita(i);              
            }
        }
    }

    public static void main(String[] args) {
        lerScanner();
        System.out.println(pesquisaNConexos());
    }

}