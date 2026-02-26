import java.util.*;

class Piramides {
    static int n, d, count=0;
    static long[][] pir;
    static long[][] pir1;

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();

        if (d==0) {
            int tmp=1;
            for(int i=0 ; i<n-1 ; i++){
                tmp = tmp*2;
            }
            System.out.println(tmp);
            return;
        }

        pir = new long [n][n];
        pir1 = new long [n][n];

        // Inicializar piramide
        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<=i ; j++) {
                pir[i][j] = 1;
            }
        }

        // Inicializar copia piramide
        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<=i ; j++) {
                pir1[i][j] = -1;
            }
        }

        for (int i=0; i<d ; i++) {
            pir[n-sc.nextInt()][sc.nextInt()-1] = 0;
        }

        //System.out.println("----------------");

        /*for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<=i ; j++) {
                System.out.print(pir[i][j] + " ");
            }
            System.out.println();
        }*/

        ncam(0, 0);

        //System.out.println("----------------");

        System.out.println(pir1[0][0]);
    }

    public static void ncam(int c, int p) {
        //System.out.println("pir[" + c + "][" + p + "]");

        /*if (pir[c][p]!=-1)
            return;*/

        if (pir[c][p]==0){
            pir1[c][p] = 0;
            return;
        }
        if (c==n-1) {
            //count++;
            pir1[c][p] = 1;
            return;
        }


        if (pir1[c+1][p] > -1 && pir1[c+1][p+1] > -1) {
            pir1[c][p] = pir1[c+1][p] + pir1[c+1][p+1];
            return;
        }

        ncam(c+1, p);
        ncam(c+1 ,p+1);

        pir1[c][p] = pir1[c+1][p] + pir1[c+1][p+1];
    }
}
