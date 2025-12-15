import java.io.*;
import java.util.*;

public class piramides {
    static int n;
    static int count[][];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int d = in.nextInt();
        int c[] = new int [d];
        int p[] = new int [d];
        count = new int [n][n];
        for(int i=0;i<d;i++) {
            c[i] = in.nextInt() - 1;
            p[i] = in.nextInt() - 1;
            count[c[i]][p[i]] = -1;
        }
        /*for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++)
                System.out.print(count[i][j] + " ");
            System.out.println();
        }
        */
        caminhos(0,0);
        System.out.println(count[n-1][0]);
        /*for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++)
                System.out.print(count[i][j] + " ");
            System.out.println();
        }
        */
    }

    static int caminhos(int i,int j) {
        if (i == n - 1 && j == 0) {
            return 1;
        }
        else {
            if (j == 0) {
                if (avanca(i, j, 0)) {
                    count[i + 1][j]++;
                    return caminhos(i + 1, j);
                }
                else
                    return caminhos(i,j+1);
            }
            else if (j > 0 && j < n - i - 1) {
                if (avanca(i, j, 0)) {
                    count[i + 1][j]++;
                    caminhos(i + 1, j);
                }
                if (avanca(i, j, 1)) {
                    count[i + 1][j - 1]++;
                    caminhos(i + 1, j - 1);
                }
                if(!avanca(i,j,0) || !avanca(i,j,1))
                    return caminhos(i,j+1);
            }
            else  if(j==n-i){
                if (avanca(i, j, 1)) {
                    count[i + 1][j - 1]++;
                    caminhos(i + 1, j - 1);
                }
                else
                    return 0;
            }
        }
        return 0;
    }

    static boolean avanca(int i,int j,int dir){
        if(dir == 0){
            if(count[i+1][j]!=-1)
                return true;
            else
                return false;
        }
        else {
            if (count[i+1][j-1]!=-1)
                return true;
            else
                return false;
        }
    }
}
