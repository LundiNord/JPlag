import java.util.*;
import java.io.*;

class Encomenda implements Comparable<Encomenda>{
  int tempo, multa, n;
  Encomenda(int n, int tempo, int multa){
    this.n = n;
    this.tempo = tempo;
    this.multa = multa;
  }
  @Override
  public int compareTo(Encomenda a){
    if(a.multa*tempo == this.multa*tempo){
      if(a.n>this.n) return -1;
      if(a.n<this.n) return 1;
    }

    if(a.multa*tempo > this.multa*tempo){
      return 1;
    }
    else if(a.multa*tempo < this.multa*tempo){
      return -1;
    }
    return 0;
  }
}

class p10{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    Encomenda[] encomenda = new Encomenda[n];
    for(int i = 0; i<n; i++) encomenda[i] = new Encomenda(i+1, stdin.nextInt(), stdin.nextInt());
    Arrays.sort(encomenda);
    for(int i = 0; i<n; i++) System.out.print(encomenda[i].n + " ");


  }
}
