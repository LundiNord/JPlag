/* com ajuda de André Cirne */

import java.util.*;

class ParSapato implements Comparable<ParSapato> {
  private int duracao; //duracao do trabalho
  private int multa; //multa de atraso
  private float ratio;
  public int ident;//numero de encomenda
  ParSapato(int ident,int duracao,int multa){
    this.ident=ident;
    this.duracao=duracao;
    this.multa=multa;
    ratio=(float)duracao/multa;
    //System.out.println(ratio);
  }

  @Override
  public int compareTo(ParSapato p){
    if (ratio > p.ratio) return 1;
    if (ratio < p.ratio) return -1;
    //se chega aqui significa que o ratio e igual
    if (ident > p.ident) return 1;
    return  -1;
  }

}

class Sapateiro{

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int n=input.nextInt();
    ParSapato encomendas[] = new ParSapato[n];

    for (int i =0;i<n ;++i ) {
      encomendas[i]= new ParSapato(i+1,input.nextInt(),input.nextInt());
    }

    Arrays.sort(encomendas);
    int i;
    for (i = 0 ; i<n-1;++i){
      System.out.print(encomendas[i].ident+" ");
    }
    System.out.println(encomendas[i].ident);

  }
}
