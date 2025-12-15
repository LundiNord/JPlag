import java.util.*;
import java.io.*;

/**
 *
 * @author ppetz
 */
public class RedesCircuitos {

    public static boolean[][] adjs;
    public static boolean vist[];
    public static int cont;

    public static void lerScanner() {
        int k = 1, nVert, nArest;
        int l, c;

        Scanner scan = new Scanner(System.in);
        nVert = Integer.parseInt(scan.next());
        nArest = Integer.parseInt(scan.next());

        adjs = new boolean[nVert + 1][nVert + 1];
        vist = new boolean[nVert + 1];

        for (k = 1; k <= nArest; k++) {
            l = Integer.parseInt(scan.next());
            c = Integer.parseInt(scan.next());
			
			//System.out.println("l:"+l+" c:"+c);

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

                    //System.out.print(adjs[i][j]+" ");
                } else {
                    if (adjs[i][j] == true) {
                        System.out.println(i + ":" + j);
                    }
                    //System.out.println(adjs[i][j]);
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
                //st.push(new Integer(i));
                
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        lerScanner();
        //imprimeMatriz();
        System.out.println(pesquisaNConexos());
    }

}
