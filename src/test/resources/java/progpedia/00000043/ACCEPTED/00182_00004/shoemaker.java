import java.util.*;
import java.io.*;

class Encomenda implements Comparable<Encomenda>{
  int tempo, multa, n;
  double racio;
  Encomenda(int n, int tempo, int multa){
    this.n = n;
    this.tempo = tempo;
    this.multa = multa;
    racio = tempo/multa;
  }
  @Override
  public int compareTo(Encomenda t){
    if(multa/(float)tempo > t.multa/(float)t.tempo) return -1;
    if(multa/(float)tempo < t.multa/(float)t.tempo) return 1;
    else{
      if(n < t.n) return -1;
      if(n > t.n) return 1;
    else return -1;
    }
  }
}


class shoemaker{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    Encomenda[] encomenda = new Encomenda[n];
    for(int i = 0; i<n; i++) encomenda[i] = new Encomenda(i+1, stdin.nextInt(), stdin.nextInt());
    Arrays.sort(encomenda);
    for(int i = 0; i<n; i++){
      if(i+1 != n) System.out.print(encomenda[i].n + " ");
      else System.out.print(encomenda[i].n);
    }
    System.out.println();


  }
}
