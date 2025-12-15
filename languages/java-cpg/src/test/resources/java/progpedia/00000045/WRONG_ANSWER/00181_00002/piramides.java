import java.util.*;
import java.io.*;

class piramides{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int d = stdin.nextInt();
    char p[][] = new char[n][n];
    int camada, pos;
    for(int i = 0; i < n; i++)
      for(int j = 0; j < n; j++)
        if(j <= i)
          p[i][j] = 'p';
        else
          p[i][j] = '#';
    for(int i = 0; i < d; i++){
      camada = stdin.nextInt();
      pos = stdin.nextInt();
      p[n-camada][pos-1] = 'f';

    }
    p[0][0] = 'T';

    for(int i = 0; i < n-1; i++){
      for(int j = 0; j < n; j++){
        if(p[i][j] == 'T'){
          if(p[i+1][j] != 'f')
            p[i+1][j] = 'T';
          if(p[i+1][j+1] != 'f')
            p[i+1][j+1] = 'T';
        }

      }

    }
    int cont = 0;
    for(int i = 0; i < n; i++){
      if(p[n-1][i]=='T')
        cont++;
    }
System.out.println(cont);



  /*  for(int i = 0; i < n; i++)
      for(int j = 0; j < n; j++)
        if(j==n-1)
          System.out.println(p[i][j]);
        else
          System.out.print(p[i][j]);
*/
  }
}
