import java.util.*;
import java.io.*;

class Node
{
    public LinkedList<Integer> adjacente;
    public boolean visited;
    public int distance;
    
    Node()
    {
        adjacente = new LinkedList<Integer>();
        visited = false;
        distance = -1;
    }
}
class Grafo
{
    int n;
    Node nodes[];
    int[][] matriz;
    
    public Grafo(int n)
    {
        this.n = n;
        nodes = new Node[n + 1];
        matriz = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
        {
            nodes[i] = new Node();
        }
    }
    
    public void addLink(int a, int b)
    {
        nodes[a].adjacente.add(b);
        nodes[b].adjacente.add(a);
    }
    
    public void bfs(int a, int b)
    {
        LinkedList<Integer> l = new LinkedList<Integer>();
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        
        l.add(a);
        l2.add(a);
        
        nodes[a].visited = true;
        nodes[a].distance = 0;
        
        while(l.size() > 0)
        {
            int n = l.removeFirst();
            
            matriz[a][b] = nodes[n].distance;
            for(int m : nodes[n].adjacente)
            {
                if(!nodes[m].visited)
                {
                    l.add(m);
                    nodes[m].visited = true;
                    nodes[m].distance = nodes[n].distance + 1;
                }
            }
        }
        
        nodes[a].visited = false;
        nodes[a].distance = 0;
        
        for(int i = 1; i <= b; i++)
        {
            for( int m : nodes[i].adjacente)
            {
                if(nodes[m].visited == true)
                {
                    nodes[m].visited = false;
                    nodes[m].distance = 0;
                }
            }
        }
    }
}
class ex19
{
    static int[] vector;
    static int xR = 0;
    static int xdiameter = 0;
    
    public static void diameter(int[][] matriz, int n)
    {
        int maximo = 0;
        vector = new int[n + 1];
        
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(matriz[i][j] > maximo)
                {
                    maximo = matriz[i][j];
                }
            }
            vector[i] = maximo;
            maximo = 0;
        }
    }
    
    public static void Central_nodes(int[][] matriz, int r, int n)
    {
        int contador1 = 0;
        boolean [] central = new boolean[n + 1];
        int contador2 = 1;
       
        
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(matriz[i][j] <= r)
                {
                    contador1++;
                }
            }
            if(contador1 == n)
            {
                central[i] = true;
                xR++;
            }
            contador1 = 0;
        }
        
        int[] result_final1 = new int[xR + 1];
        for(int i = 1; i <= n; i++)
        {
            if(central[i] == true)
            {
                result_final1[contador2] = i;
                contador2++;
            }
            
        }
        for(int i = 1; i <= xR; i++)
        {
            if(i == xR)
            {
                System.out.println(result_final1[i]);
            }else
            {
                System.out.println(result_final1[i] + " ");
            }
        }
    }
    
    public static void Periferic_nodes(int[][] matriz, int diameter, int n)
    {
        boolean[] periferics = new boolean[n + 1];
        int xdiameter = 0;
        int contador3 = 1;
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(matriz[i][j] == diameter)
                {
                    periferics[i] = true;
                    xdiameter++;
                    break;
                }
            }
        }
        
        int[] result_final2 = new int[xdiameter + 1];
        for(int i = 1; i<= n;i++)
        {
            if(periferics[i] == true)
            {
                result_final2[contador3] = i;
                contador3++;
            }
        }
        for(int i = 1; i <= xdiameter; i++)
        {
            if(i == xdiameter)
            {
                System.out.print(result_final2[i]);
            }else{
                System.out.print(result_final2[i] + " ");
            }
        }
    }
    
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        Grafo g = new Grafo(n);
        int e = input.nextInt();
        
        for(int i = 0; i < e; i++)
        {
            int a = input.nextInt();
            int b = input.nextInt();
            g.addLink(a, b);
        }
        
        for(int i = 1; i <= n; i++)
        {
            g.bfs(i, n);
        }
        
        diameter(g.matriz, n);
        Arrays.sort(vector);
        int diameter = vector[n];
        System.out.println(diameter);
        
        int r = vector[1];
        System.out.println(r);
        
        Central_nodes(g.matriz, r, n);
        //System.out.println();
        Periferic_nodes(g.matriz, diameter, n);
        System.out.println();
    }
}