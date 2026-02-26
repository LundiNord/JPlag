import java.util.*;
import java.io.*;
import java.lang.*;


class Segmento implements Comparable<Segmento>{
  public static int compare(int x, int y) {
      return (x < y) ? -1 : ((x == y) ? 0 : 1);
  }
  int li, ri, tamanho;
  Segmento(int li, int ri){
    this.li = li;
    this.ri = ri;
    tamanho = ri-li;
  }
  @Override
  public int compareTo(Segmento a) {
	   return compare(this.li, a.li);
  }
}


class p9{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int m = stdin.nextInt();
    int n = stdin.nextInt();
    int end, max, contador;
    end = max = contador = 0;
    Segmento[] segmento = new Segmento[n];
    for(int i = 0; i<n; i++) segmento[i] = new Segmento(stdin.nextInt(), stdin.nextInt());
    Arrays.sort(segmento);
    while(end<m){
      for(int i = 0; i<n; i++){
        if(segmento[i].li<=end && segmento[i].ri > max){
          max = segmento[i].ri;
        }
      }
      contador++;
      end = max;
    }

    System.out.println(contador);



  }
}
