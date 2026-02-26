import java.util.*;
import java.io.*;

class sort{

  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int cont[] = new int[1000000];
    int v[] = new int[n];
    int ola;
    for(int i = 0; i < 1000000;i++)
      cont[i]=0;
    int num;
    int max=0;
    for(int i = 0; i < n; i++){
      num = stdin.nextInt();
      cont[num] = cont[num]+1;
      if(num > max)
        max = num;
    }
    int x=0;
    for(int i = 0; i <= max; i++){
      if(cont[i]==1){
        v[x]=i;
        x++;
      }
      else if(cont[i]>1 && cont[i]!=0)
        for(int j = 0; j < cont[i]; j++){
          v[x]=i;
          x++;
        }
    }
    for(int i = 0; i < n; i++){
      if(i < n-1)
        System.out.print(v[i] + " ");
      else
        System.out.print(v[i]);
    }
    System.out.println();
  }
}
