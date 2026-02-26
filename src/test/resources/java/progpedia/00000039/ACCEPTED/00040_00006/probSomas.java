import java.util.*;
import java.io.*;

class probSomas{
  public static int binSearchM(int soma[], int val, int low, int high){
    int half;
    while(low < high){
        half = low + (high - low) /2;
        if(soma[half] == val)
          return half;
        else if(soma[half] > val)
          high = half;
        else
          low = half +1;
    }
    if(soma[low]>val) return low;
    return high;
  }
  public static int binSearchm(Integer soma[], int val, int low, int high){
    int half;
    while(low < high){
        half = low + (high - low) /2;
        if(soma[half] == val)
          return half;
        else if(soma[half] < val)
          high = half;
        else
          low = half + 1;
    }
    if(soma[low]<val) return low;
    return high;
  }
   public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int v[] = new int[n];
    int somatorio = 0;
    for(int i = 0 ; i < n; i++){
      v[i] = stdin.nextInt();
      somatorio += i;
    }
    int somas[] = new int[somatorio];
    Integer aux[] = new Integer[somatorio];
    int p = stdin.nextInt();
    int cont=0;
    for(int i = 0; i < n; i++){
      for(int j = i+1; j < n; j++){
        aux[cont]=v[i]+v[j];
        somas[cont++] = v[i] + v[j];

        //System.out.println(somas[cont]);
      }
    }
  //  somas[0]=v[0]+v[1];
    Arrays.sort(somas);
    Arrays.sort(aux, Collections.reverseOrder());
  //  binSearchM
  //  binSearchm
  int perg,menor=0,maior;

  for(int i = 0; i < p; i++){
    perg = stdin.nextInt();
    maior = binSearchM(somas,perg,0,somatorio-1);
    menor = binSearchm(aux,perg,0,somatorio-1);
    if(somas[maior] == aux[menor])
      System.out.println(somas[maior]);
    else if(Math.abs(perg - somas[maior]) == Math.abs(perg - aux[menor]))
      System.out.println(aux[menor] + " " + somas[maior]);
    else if(Math.abs(perg - somas[maior]) < Math.abs(perg - aux[menor]))
      System.out.println(somas[maior]);
    else if(Math.abs(perg - somas[maior]) > Math.abs(perg - aux[menor]))
      System.out.println(aux[menor]);
    //System.out.println(perg + "\n" +somas[maior] + " " + aux[menor]);
  }
  }

}
