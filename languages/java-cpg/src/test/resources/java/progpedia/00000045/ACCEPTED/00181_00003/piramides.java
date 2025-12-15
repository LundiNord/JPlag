import java.util.*;
import java.io.*;

class piramides{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int d = stdin.nextInt();
    int p[][] = new int[n][n];
    boolean soltas[][] = new boolean[n][n];
    int camada, pos;
    for(int i = 0; i < d; i++){
      camada = stdin.nextInt();
      pos = stdin.nextInt();
      soltas[n-camada][pos-1] = true;
    }
    long cont[][] = new long[n][n];
    for(int i = n-1; i>=0;i--){
      for(int j = 0; j <= i; j++){
        if(soltas[i][j] == true)
          cont[i][j] = 0;
        else if(i == n-1)
          cont[i][j] = 1;
        else
          cont[i][j] = cont[i+1][j] + cont[i+1][j+1];
      }
    }
    System.out.println(cont[0][0]);
  }
}
