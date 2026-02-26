import java.util.*;

class No {
  LinkedList<Ligacao> ligacoes = new LinkedList<Ligacao>();
  int distancia = Integer.MAX_VALUE;
  boolean visitado = false;
  int nome;
  No(int nome) {
    this.nome = nome;
  }
}

class Ligacao {
  No no;
  int distancia;
  Ligacao(No no, int distancia) {
    this.no = no;
    this.distancia = distancia;
  }
}

class Negocio {
  
  static void sort(No nos[]) {
    int i, j;
    No aux;
    for(i=2; i<nos.length; i++) {
      j = i;
      while(j>1 && nos[j].distancia <= nos[j-1].distancia) {
        if(nos[j].distancia == nos[j-1].distancia && nos[j-1].nome<nos[j].nome) {
          j--;
          continue;
        }
        aux = nos[j];
        nos[j] = nos[j-1];
        nos[j-1] = aux;
        j--;
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int dest = in.nextInt();
    No[] nos = new No[n+1];
    for(int i=1; i<=n; i++)
      nos[i] = new No(i);
    int a, b, c;
    while(true) {
      a = in.nextInt();
      if(a==-1)
        break;
      b = in.nextInt();
      c = in.nextInt();
      nos[a].ligacoes.add(new Ligacao(nos[b], c));
      nos[b].ligacoes.add(new Ligacao(nos[a], c));
    }
    
    nos[dest].distancia = 0;
    nos[dest].visitado = true;
    LinkedList<Ligacao> aver = new LinkedList<Ligacao>();
    aver.addAll(nos[dest].ligacoes);
    for(Ligacao l : aver)
      l.no.distancia = l.distancia;
      
    Ligacao l;
    while(aver.size()>0) {
      l = aver.removeFirst();
      if(l.no.visitado)
        continue;
      l.no.visitado = true;
      aver.addAll(l.no.ligacoes);
      for(Ligacao l2 : l.no.ligacoes)
        if(l2.no.distancia > l2.distancia+l.no.distancia)
          l2.no.distancia = l2.distancia+l.no.distancia;
    }
    sort(nos);
    for(int i=1; i<=n; i++)
        System.out.print(nos[i].nome+" ");
    System.out.println();
  }
}
