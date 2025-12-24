import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
class Arco {
    int no_final;
    Arco(int fim){
        no_final = fim;
    }

    int extremo_final() {
	return no_final;
    }
}
class No {
    LinkedList<Arco> adjs;
    int cor;
    int id;
    No(int i) {
        cor=0;//0=branco 1= cinzento 2=preto
	adjs = new LinkedList<Arco>();
        id=i;
    }
}
class Grafo {
    No verts[];
    int nvs, narcos;		
    public Grafo(int n) {
	nvs = n;
	narcos = 0;
	verts  = new No[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new No(i);
    }
    
    public int num_vertices(){
	return nvs;
    }

    public int num_arcos(){
	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
	return verts[i].adjs;
    }
    
    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}
public class Sociologia {
    static Scanner ler=new Scanner(System.in);
    static Grafo g;
    static Grafo gInv;
    static Stack<Integer> pilhaDFS=new Stack<Integer>();
    static Stack<Integer> inversa=new Stack<Integer>();
    static int nverts;
    public static void main(String[] args) {
       int ncasos=ler.nextInt();
       for(int i=0;i<ncasos;i++)
       {            
           System.out.println("CASO Nº"+(i+1)+"#");
           g=lergrafo();
           resolveprob(g);
           escreveresp();
           System.out.println();
       }
    }
    public static Grafo lergrafo()
    {   
        int aluno,amigos,amigo;
        int nalunos=ler.nextInt();
        Grafo temp=new Grafo(nalunos);
        for(int i=0;i<nalunos;i++)
        {
           aluno=ler.nextInt();
           amigos=ler.nextInt();
           for(int j=0;j<amigos;j++)
           {
               amigo=ler.nextInt();
               temp.verts[aluno].adjs.add(new Arco(amigo));
           }
        }
        return temp;
    }
    public static void resolveprob(Grafo g)
    {
        gInv=new Grafo(g.verts.length);
        for(int i=1;i<=g.nvs;i++)
        {
            if(!pilhaDFS.contains(g.verts[i].id))
                DFS(g,g.verts[i]);
        }
        gInv=inverso();
    }
    public static Grafo inverso()
    {
        Grafo temp=new Grafo(g.verts.length);
        for(int i=0;i<g.verts.length;i++)
        {
            for(int j=0;j<g.verts[i].adjs.size();j++)
            {
                int temp2=g.verts[i].adjs.get(j).no_final;
                temp.verts[temp2].adjs.add(new Arco(i));
            }
        }
        while(pilhaDFS.size()>0)
            inversa.push(pilhaDFS.pop());
        return temp;
    }
    public static void escreveresp()
    {
        int ngrupos=0,npessoas,sozinhas=0;
        int v;
        while(!inversa.isEmpty())
        {
            v=inversa.pop();
            nverts=1;
            DFS_INV(gInv,v);
            npessoas=nverts;
            if(npessoas>=4)
                ngrupos++;
            else
                sozinhas=sozinhas+npessoas;
        }
         System.out.println("SOZINHAS =" + sozinhas);
         System.out.println("Grupos = " + ngrupos);
    }
    public static void DFS(Grafo g,No v)
    {
        v.cor=1;
        for(int i=0;i<v.adjs.size();i++)
        {
            if(g.verts[v.adjs.get(i).no_final].cor==0)
            {
                g.verts[v.adjs.get(i).no_final].cor=1;
                DFS(g,g.verts[v.adjs.get(i).no_final]);
            }
        }
        v.cor=2;
        pilhaDFS.push(v.id);
    }
   public static void DFS_INV(Grafo g2,int v)
   {
       g2.verts[v].cor=1;
       
       if(procura(v)!=-1)
       {
           inversa.remove(procura(v));
       }
        
       for(int i=0;i<g2.verts[v].adjs.size();i++)
       {
           if(g2.verts[g2.verts[v].adjs.get(i).no_final].cor==0)
            {
                nverts++;
                DFS_INV(g2,g2.verts[g2.verts[v].adjs.get(i).no_final].id);
            }
       }
       g2.verts[v].cor=2;
   }
   public static int procura(int n)
   {
       for(int i=0;i<inversa.size();i++)
       {
           if(inversa.get(i)==n)
               return i;
       }
       return -1;
   }
}
/*
SOLUÇÃO
4
4
1 3 2 4 3
4 0
2 2 1 3
3 2 2 1
6
1 2 3 5
2 2 3 4
4 1 2
3 2 2 1
6 1 5
5 2 6 1
8
1 4 6 2 4 5
3 1 2
2 2 3 4
4 1 5
6 0
5 3 4 8 7
7 1 5
8 2 5 3
10
1 4 6 2 4 5
3 2 2 1
9 0
2 2 3 4
4 2 5 9
6 1 1
5 3 4 8 7
7 1 5
8 1 5
10 1 9
*/