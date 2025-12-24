// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node 
{
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa

    Node() 
    {
	   adj = new LinkedList<Integer>();
	   visited = false;
	   distance = -1;
    }
}

// Classe que representa um grafo
class Graph 
{
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    int [][] matrix;
    
    public Graph(int n) 
    {
	   this.n = n;
	   nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	   matrix = new int[n+1][n+1];
       for (int i=1; i<=n; i++)
       {
	       nodes[i] = new Node();
       }
    }

    public void addLink(int a, int b) 
    {
	   nodes[a].adj.add(b);
	   nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v, int s) 
    {
        
	    LinkedList<Integer> q = new LinkedList<Integer>();
        LinkedList<Integer> q2 = new LinkedList<Integer>();
        
        q.add(v);
        q2.add(v);  
    
	   nodes[v].visited = true;
	   nodes[v].distance = 0;

	   while (q.size() > 0) 
       {
	        int u = q.removeFirst();
	        
            matrix[v][u] = nodes[u].distance;
            
            for(int w : nodes[u].adj)
            {
		          if (!nodes[w].visited) 
                  {
		              q.add(w);
		              nodes[w].visited  = true;
		              nodes[w].distance = nodes[u].distance + 1; 
		          }	    
            }
       }
       
       nodes[v].visited = false;
       nodes[v].distance = 0;
       
       for(int i = 1; i <= s; i++)
       {
           for(int w : nodes[i].adj)
           {
               if(nodes[w].visited == true)
               {
                   nodes[w].visited = false;
                   nodes[w].distance = 0;
               }
           }
       }
    }
}


public class prob19 
{
    
    static int [] excen;
    static int xRaio = 0;
    static int xDiametro = 0;
    
    
    public static void main(String args[]) 
    {
	   Scanner input = new Scanner(System.in);
    
        int n = input.nextInt();
	    Graph g = new Graph(n);
	    int   e = input.nextInt();
	
        for (int i=0; i<e; i++) 
        {
            int a = input.nextInt();
            int b = input.nextInt();
	        g.addLink(a, b);
        }
        
        // Pesquisa em largura a partir do no 1
        for(int i = 1; i <= n; i++)
        {
            g.bfs(i,n);
        }
        
        diametro(g.matrix,n);
        Arrays.sort(excen);
        int diametro = excen[n];
        System.out.println(diametro);
        
        int raio = excen[1];
        System.out.println(raio);
        
        nosCentrais(g.matrix,raio,n);
        //System.out.println();
        nosPerifericos(g.matrix,diametro,n);
        //System.out.println();
    }
    
    public static void diametro(int[][] matrix, int n)
    {
        int max = 0;
        excen = new int[n+1];
        
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(matrix[i][j] > max)
                {
                    max = matrix[i][j];
                }
            }
            excen[i] = max;
            max = 0;
        }
    }
    
    public static void nosCentrais(int[][] matrix, int raio, int n)
    {
        int cont = 0;
        boolean[] centrais = new boolean[n+1];
        int cont2 = 1;
        
        for(int i = 1; i<= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(matrix[i][j] <= raio)
                {
                    cont++;
                }
            }
            if(cont == n)
            {
                centrais[i] = true;
                xRaio++;
            }
            cont = 0;
        }
        
        int [] finalResult2 = new int[xRaio+1];
        for(int i =1; i <= n; i++)
        {
            if(centrais[i] == true)
            {
                finalResult2[cont2] = i;
                cont2++;
            }
        }
        
        for(int i = 1; i <= xRaio; i++)
        {
            if(i == xRaio)
            {
                System.out.println(finalResult2[i]);
            }else
            {
                System.out.println(finalResult2[i]+" ");
            }
        }
    }
    public static void nosPerifericos(int[][] matrix, int diametro, int n)
    {
        boolean[] perifericos = new boolean[n+1];
        int cont = 1;
        for(int i = 1; i <= n; i++)
        {
    	   for(int j = 1; j <= n; j++)
           {
    			if(matrix[i][j] == diametro)
                {
    				perifericos[i]=true;
    				xDiametro++;
    				break;
    			}

    		}
    	}
        int[] finalResult = new int[xDiametro+1];
    	for(int i = 1; i <= n; i++)
        {
    		if(perifericos[i] == true)
            {
    			finalResult[cont] = i;
    			cont++;
    		}
    	}

    	for(int i = 1; i <= xDiametro; i++)
        {
    		if(i==xDiametro)
            {
    			System.out.print(finalResult[i]);
            }else
    		{	
                System.out.print(finalResult[i]+" ");
            }
    	}
    }
}
