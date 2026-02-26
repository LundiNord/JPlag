import java.util.*;

class GNode
{
  public int nome=0;
  public int peso=0;
  public ArrayList<Integer> filhos=new ArrayList<Integer>();
}


class Dij
{
  void Dijkstra(GNode[] G, int [][] P, GNode o)
  {
    o.peso=0;
    ArrayList<GNode> Resultado= new ArrayList<GNode>();
    ArrayList<GNode> Fila=new ArrayList<GNode>();
    for (int i=0;i<G.length;i++)
      Fila.add(G[i]);
    while (!Fila.isEmpty())
    {
    int min=Integer.MAX_VALUE;
    int index=Integer.MIN_VALUE;
    for (int i=0;i<Fila.size();i++)
    {
      if(Fila.get(i).peso<min)
      {
        min=Fila.get(i).peso;
        index=i;
      }
    }
    GNode no=Fila.get(index);
    Resultado.add(no);
    for(int i=0;i<no.filhos.size();i++)
    {
      GNode ver=G[(no.filhos.get(i))-1];
      Relax(no,ver,P[(no.nome)-1][(ver.nome)-1]);
    }
    Fila.remove(index);
    }
    
    for(int i=0;i<Resultado.size();i++)
    {
      if(i==(Resultado.size()-1))
      {
        System.out.print(Resultado.get(i).nome);
        System.out.println();
      }
      else
      {
        System.out.print(Resultado.get(i).nome + " ");
      }
    }
  }
  void Relax(GNode pai, GNode filho,int peso)
  {
    if(filho.peso>(pai.peso + peso))
      filho.peso=pai.peso+peso;
  }
}

class negocioelectronico
{
  public static void main(String args[])
  {
    Scanner stdin=new Scanner(System.in);
    int nn=stdin.nextInt();
    GNode[] grafo=new GNode[nn];
    int[][] mp=new int[nn][nn];
    for (int i=0;i<nn;i++)
    {
      for (int j=0;j<nn;j++)
      {
        mp[i][j]=Integer.MIN_VALUE;
      }
      mp[i][i]=0;
      grafo[i]=new GNode();
      grafo[i].nome=i+1;
    }
    int cli=stdin.nextInt();
    boolean flag=true;
    while (flag)
    {
      int s=stdin.nextInt();
      if (s==-1)
        flag=false;
      else
      {
        int t=stdin.nextInt();
        int p=stdin.nextInt();
        mp[s-1][t-1]=p;
        mp[t-1][s-1]=p;
        grafo[s-1].filhos.add(t);
        grafo[t-1].filhos.add(s);
      }
    }
    
    Dij alg=new Dij();
    alg.Dijkstra(grafo,mp,grafo[cli-1]);
  }
}