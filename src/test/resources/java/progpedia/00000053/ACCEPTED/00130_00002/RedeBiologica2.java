/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package redebiologica;

import java.util.*;


class No
{
    int numero;
    LinkedList<No> adj;
    boolean visitado;
    int dist;
    boolean analisado;
    
    
    No(int n)
    {
        numero = n;
        adj = new LinkedList<No>();
        dist = 0;
        visitado = false;
    }
	
    void addLigacao(No l)
    {
        adj.addLast(l);
    }
}

class Grafo
{
    No v[];
    int nnos;
    int narcos;
    
    int matrizDistancia[][];
    int diametro = 0;
    int raio = Integer.MAX_VALUE;
    

    Grafo(int nos, int arcos)
    {
        nnos = nos;
        narcos = arcos;
        v =  new No[nos+1];
        
        matrizDistancia = new int[nos+1][nos+1];        
    }
	
    void inicializa()
    {
        for(int i = 1; i<= nnos;i++)
	{
            v[i] =  new No(i);
	}
    }
	
    private void clean()
    {
        for(int i = 1; i<= nnos;i++)
	{
            v[i].visitado = false;
	}
    }
    
    
    public void createGrafo(Scanner in)
    {
        inicializa();
        for(int i = 0; i<narcos; i++)
	{
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            v[n1].addLigacao(v[n2]);
            v[n2].addLigacao(v[n1]);
	}
    }
	
    public void bfs()
    {
        for(int i = 1; i<=nnos; i++)
        {
            //System.out.printf("No inicio %d",i);
            //System.out.println();
            bfs(v[i]);
            clean();
        }
    }
    
    
    private void bfs(No n)
    {
        Queue<No> fila = new LinkedList<No>();
        fila.add(n);
        n.dist = 0;
        n.visitado = true;
        
        while (!fila.isEmpty())
        {
            No u = fila.remove();
            for(No x: u.adj)
            {
                if(!x.visitado)
                {
                    fila.add(x);
                    x.visitado = true;   
                    x.dist = u.dist+1;
                    
                    matrizDistancia[n.numero][x.numero]=x.dist;
                    //System.out.printf("(%d,%d) -> %d",n.numero,x.numero,x.dist);
                    //System.out.println();
                }
            }
        }
    }
    
    public void calcDiametroEoRaio()
    {
        
        for(int i = 1; i<= nnos;i++)
	{
            int maxLinha =0;
            for(int j = 1; j<= nnos;j++)
            {
                if(matrizDistancia[i][j]>maxLinha)
                {
                    maxLinha = matrizDistancia[i][j];
                }
            }
            
            if(maxLinha>diametro)
            {
                diametro = maxLinha;
            }
            
            if(maxLinha<raio)
            {
                raio = maxLinha;
            }
	}
    }
    
    
    public void ImprimeNosCentrais()
    {
        for(int i = 1; i<= nnos;i++)
	{
            int maxLinha =0;
            for(int j = 1; j<= nnos;j++)
            {
                if(matrizDistancia[i][j]>maxLinha)
                {
                    maxLinha = matrizDistancia[i][j];
                }
            }
            
            if(maxLinha == raio)
            {
                if(i>1)
                {
                    System.out.printf(" %d",i);
                }
                else
                {
                    System.out.printf(" %d",i);   
                }
            }
	}
        System.out.println();
        
    }
    
    
    public void ImprimeNosPerifericos()
    {
        for(int i = 1; i<= nnos;i++)
	{
            int maxLinha =0;
            for(int j = 1; j<= nnos;j++)
            {
                if(matrizDistancia[i][j]>maxLinha)
                {
                    maxLinha = matrizDistancia[i][j];
                }
            }
            
            if(maxLinha == diametro)
            {
                if(i>1)
                {
                    System.out.printf(" %d",i);
                }
                else
                {
                    System.out.printf(" %d",i);   
                }
            }
	}
        System.out.println();
    }
    
    
}


public class RedeBiologica2
{

    public static void main(String[] args) 
    {
        Scanner in =  new Scanner(System.in);
	int nnos = in.nextInt();
	int narestas = in.nextInt();
	
        Grafo g = new Grafo(nnos, narestas);
	g.createGrafo(in);
        g.bfs();
        g.calcDiametroEoRaio();
        System.out.println(g.diametro);
        System.out.println(g.raio);
        g.ImprimeNosCentrais();
        g.ImprimeNosPerifericos();
 
    }
    
}
