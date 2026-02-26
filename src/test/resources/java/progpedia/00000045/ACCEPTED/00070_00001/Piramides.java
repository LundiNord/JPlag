/**
 *
 * @author Luis Graça, up201304954
 */
import java.util.*;


// 0 são pedras deteriozadas
// 1 são pedras intactas
public class Piramides {
    public static void main (String [] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int d = stdin.nextInt();
        long piramide[][] = new long[n+1][n+1];
        for (int i = 0; i < d; i++) {
            int c = stdin.nextInt();
            int p = stdin.nextInt();
            piramide[c][p] = -1;
        }
        for (int i = 1; i <= n; i++) {
            if (piramide[1][i] == -1){
                piramide[1][i] = 0;
            }
            else {
                piramide[1][i] = 1;
            }
        }      
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 1 + n-i; j++) {
                if (piramide[i][j] == -1) piramide[i][j] = 0;
                else piramide[i][j] = piramide[i-1][j] + piramide[i-1][j+1];                
            }
        }
        System.out.println(piramide[n][1]);
  
    }
}