import java.util.*;

class Node {

      // Valor booleano que indica se foi visitao numa pesquisa
    public double x;
    public double y; 
 
    Node(double x, double y) {
    this.x = x;
    this.y = y;
    }
}

// Classe que representa um grafo
class Graph {
    public static int n;           // Numero de nos do grafo
    static Node nodes[];    // Array para conter os nos

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 
    }
    
    public static void add(int i, Double a, Double b){
	       nodes[i+1] = new Node(a,b);
    }
    
    //--------------algoritmo de prim
    //Come¸car num qualquer n´o Em cada passo adicionar `a ´arvore j´a formada o n´o cujo custo seja
    //menor (que tenha aresta de menor peso a ligar `a ´arvore). Em caso de
    //empate qualquer um funciona
    public static double calcprim(int start){
        double dimensao = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        int min=0;
        boolean first = false;
        for(int i=1; i<=n; i++) if(i!=start) { q.add(i);};
        int end = start;
        double res;
        double resmin=1000000;
        while(q.size() > 0) {
            first = false;
            for(int i: q) if(i!=start) {min=i; break;}
            resmin = Math.sqrt(Math.pow(Math.abs(nodes[min].x - nodes[start].x),2) + Math.pow(Math.abs(nodes[min].y - nodes[start].y),2));
            for(int i:q){
                if(i!=min){
                    res = Math.sqrt(Math.pow(Math.abs(nodes[i].x - nodes[start].x),2) + Math.pow(Math.abs(nodes[i].y - nodes[start].y),2));
                    if(res < resmin){
                        resmin = res;
                        min = i;
                    }
                }
                
            }
            start = min;
            for(int j=0; j<q.size(); j++) if(min==q.get(j)) q.remove(j);
            //System.out.println(min + " " + resmin);
            dimensao = dimensao + resmin;
        }

            return dimensao;     
    }
    
    
    
    }
    
    /*public static int ExtrairMinimo(LinkedList<Integer> q, int start){
        int min;
        double mindist;
        double res;
        LinkedList<Integer> t = new LinkedList<Integer>();
        for(int i: q){ 
            if(nodes[i].visited==false && i!=start) {
               res = Math.sqrt(((nodes[i].x - nodes[start].x)*(nodes[i].x - nodes[start].x)) + ((nodes[i].y - nodes[start].y)*(nodes[i].y - nodes[start].y)));
               if(mindist == 0) {min = i; mindist = res;}
               else if(res<mindist) {min = i; mindist = res;}
               else min = i;  
            }
        }

        return min;
    }*/
    


public class sardas {

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
    
    int n = in.nextInt();       
	Graph g = new Graph(n);
    
	for (int i=0; i<n; i++) 
	   g.add(i, in.nextDouble(), in.nextDouble());

    System.out.printf("%.2f\n", g.calcprim(1));    
    }
}