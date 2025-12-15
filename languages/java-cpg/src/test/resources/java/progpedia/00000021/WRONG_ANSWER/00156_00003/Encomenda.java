
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 *
 * @author jose
 */
class Eixo{
    public Vertice destino; // segundo vertice do eixo
    public double custo;    // custo do vertice
    
    Eixo( Vertice destino, double custo ){
        this.destino = destino;
        this.custo = custo;
    }
}

class Vertice{
    public String nome;      // nome do vertice
    public double dist;      // distancia do vertice calc por algoritmos
    public Vertice prev;     // vertice anterior a este
    public List<Eixo> adj;   // lista de adjacencias do vertice
    public boolean visitado; // 
    
    Vertice( String nome ){
        this.nome = nome;
        adj = new LinkedList<Eixo>();
        reset();
    }
    
    public void reset(){
        dist = Grafo.INFINITO;
        prev = null;
        visitado = false;
    }
}
class Path implements Comparable<Path>
{
    public Vertice     destino;   // w
    public double     custo;   // d(w)
    
    public Path( Vertice d, double c )
    {
        destino = d;
        custo = c;
    }
    
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.custo;
        
        return custo < otherCost ? -1 : custo > otherCost ? 1 : 0;
    }
}
class Grafo{
    public static final double INFINITO = Double.MAX_VALUE;
    Map<String,Vertice> mapaVertice = new HashMap<String,Vertice>();
    
    private Vertice getVertice( String nome){
        Vertice v = mapaVertice.get(nome);
        if( v == null ){
            v = new Vertice(nome);
        }
        mapaVertice.put(nome, v);
        
        return v;
    }
    
    public void addEixo(String origem, String fim, double peso){
        Vertice v = getVertice(origem);
        Vertice w = getVertice(fim);
        v.adj.add(new Eixo(w,peso));
        w.adj.add(new Eixo(v,peso));
    }
    
    public void clearALL(){
        for(Vertice e: mapaVertice.values()){
            e.dist = INFINITO;
        }
    }
    public void dijkastraEncomenda(String partida,
            String termino){
        
        PriorityQueue<Path> fp = new PriorityQueue<Path>();
        Vertice temp = getVertice(partida);
        Vertice fim = getVertice(termino);
        fp.add(new Path(temp,0)); temp.dist = INFINITO;
        
        int verticesVisitados = 0;
        
        while(!fp.isEmpty() && verticesVisitados <= mapaVertice.size()){
            Path u = fp.remove();
            Vertice v = u.destino;           
            //if( v.visitado == true )
               // break;
            if( v == fim )
                break;
            
            v.visitado = true;
            verticesVisitados ++;
            
            for(Eixo e: v.adj){
                Vertice w = e.destino;
                if(!w.visitado){
                    double cvw = e.custo;
                    if(v.dist > cvw ){
                        v.dist = cvw;
                    }
                    if(w.dist > v.dist){
                        w.dist = v.dist;
                    }
                        
                }    
                fim.dist = w.dist;
                fp.add(new Path(w,w.dist));  
            }
           
        }
        System.out.println(fim.dist);
    }
    
}

public class Encomenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo g = new Grafo();
        //try{
            //FileReader ficheiro = new FileReader(args[0]);
            Scanner pasta = new Scanner( System.in );
            String linha = pasta.nextLine();
            StringTokenizer st = new StringTokenizer( linha );
            
            int larguraMinima = Integer.parseInt(st.nextToken());
            
            int larguraMaxima = Integer.parseInt(st.nextToken());
            int comprimentoMinimo = Integer.parseInt(st.nextToken());
            int comprimentoMaximo = Integer.parseInt(st.nextToken());
            int altura = Integer.parseInt(st.nextToken());
     
            linha = pasta.nextLine( );
            st = new StringTokenizer( linha );
            String origem = st.nextToken( );
           
            String destino = st.nextToken( );
  
            while(pasta.hasNextLine()){
                linha = pasta.nextLine( );
                st = new StringTokenizer( linha );
                String origemg = st.nextToken( );
                
                if( Integer.parseInt(origemg)!= -1 ){ 
                    String destinog = st.nextToken( );
                    
                    int largurag = Integer.parseInt(st.nextToken());
                    int comprimentog = Integer.parseInt(st.nextToken());
                    int alturag = Integer.parseInt(st.nextToken());
                    if(largurag <= larguraMaxima && largurag >= larguraMinima){
                        if(comprimentog <= comprimentoMaximo && 
                                comprimentog >= comprimentoMinimo){
                            if(alturag >= altura){
                                g.addEixo(origemg, destinog, comprimentog);
                            }
                        }
                    }
                } 
            }
            g.dijkastraEncomenda(origem, destino); 
        //}
       // catch( IOException e){
          //  System.out.print( e );
       // }
       }
    }

