import java.util.*;

class negocioelec
{
  public static void main(String[] args)
  {
    Scanner stdin=new Scanner(System.in);
    int numl=stdin.nextInt();
    int dest=stdin.nextInt();
    stdin.nextLine();
    int[][] ligas=new int[numl][numl];
    boolean flag=true;
    while (flag== true)
    {
      int s=stdin.nextInt();
      if (s==-1)
        flag=false;
      else
      {
        int t=stdin.nextInt();
        int peso=stdin.nextInt();
        ligas[s-1][t-1]=peso;
        ligas[t-1][s-1]=peso;
      }
    }
    int[] order=new int[numl];
    order[0]=dest;
    int pos=1;
    int i=dest-1;
    flag=true;
    while (flag)
    {
      int min=Integer.MAX_VALUE;
      int mem=0;
      for (int j=0;j<numl;j++)
      {
        if (ligas[i][j]>0 && ligas[i][j]<min)
        {
          mem=j;
          min=ligas[i][j];
        }
      }
      if (mem!=0)
      {
        ligas[i][mem]=0;
        ligas[mem][i]=0;
        order[pos]=mem+1;
        i=mem;
        pos++;
      }
      else
        flag=false;
    }
    System.out.print(order[0]);
    for (int k=1;k<pos;k++)
    {
      if (order[k]!=0)
      {
        System.out.print(" " + order[k]);
      }
      else
        k=pos;
    }
  }
}