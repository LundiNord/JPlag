import java.util.*;

class Piramides {
    static int n, d, count=0;
    static int[][] pir;
    //static int[][] pir1;

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

        pir = new int [n][n];

        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<=i ; j++) {
                pir[i][j] = 1;
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

        System.out.println(pir[0][0]);
    }

    public static void ncam(int c, int p) {
        //System.out.println("pir[" + c + "][" + p + "]");

        if (pir[c][p]==0){
            return;
        }
        if (c==n-1) {
            //count++;
            pir[c][p] = 1;
            return;
        }

        if (pir[c+1][p] > 1 && pir[c+1][p+1] > 1) {
            pir[c][p] = pir[c+1][p] + pir[c+1][p+1];
            return;
        }
        ncam(c+1, p);
        ncam(c+1 ,p+1);

        pir[c][p] = pir[c+1][p] + pir[c+1][p+1];
    }
}
