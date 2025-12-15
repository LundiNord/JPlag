import java.util.*;


public class problema6 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int d = in.nextInt();
        long[][] piramide = new long[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                piramide[i][j] = 1;
            }
        }
        for(int i = 0; i < d; i++) {
         int x = in.nextInt();
         int y = in.nextInt();
         piramide[n-x][y-1] = 0;
        }
        
        if(n >= 3) {
            for(int i = n-2; i>=0; i--) {
                for(int j = 0; j <=i; j++) {
                    if(piramide[i][j] > 0)
                        piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1];
                }
            }
        }
        else
            if(n == 1 && d == 0)
                piramide[0][0] = 1;
            else
                piramide[0][0] = piramide[1][0] + piramide[1][1];
        System.out.println(piramide[0][0]);
    }
}
    