import java.util.Scanner;
import java.util.LinkedList;

class No {
    
    boolean determinado;
    int key;
    int dist;
    No pai;
    
    No(int valor) {
        dist = Integer.MAX_VALUE;
        key = valor;
        determinado = false;
        pai = null;
    }
     
}

public class Negocio {

   
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int nlojas = stdin.nextInt();
        int source = stdin.nextInt();
        int [][] mat = new int[nlojas+1][nlojas+1];
        No [] grafo = new No[nlojas+1];
        
        for(int i=0; i<=nlojas; i++) {
            grafo[i] = new No(i);
        }
        
        grafo[source].dist = 0;
        
        int key1 = stdin.nextInt();
        int key2;
        int w;
        
        while(key1!=-1) {
            
            key2 = stdin.nextInt();
            w = stdin.nextInt();
            mat[key1][key2] = w;
            mat[key2][key1] = w;
            
            key1 = stdin.nextInt();
           
        }
        
        LinkedList<No> fila = new LinkedList<No>();
        for(int k=1; k<=nlojas; k++) {
            
            fila.addFirst(grafo[k]);
            
        }
        
        while(fila.isEmpty()!=true) {
            
            No u = extractmin(fila);
            for (int m=1; m<=nlojas; m++) {
                
                No n = grafo[m];
                if(mat[u.key][m]!=0) {
                    relax(u,n,mat);
                }
                u.determinado = true;
            }
            if(fila.isEmpty()) {
                System.out.print(u.key);
                
            }
            else {
                System.out.print(u.key + " ");
            }
            
        }
          
    }
    
    public static No extractmin(LinkedList<No> fila) {
        No min = new No(0);
        LinkedList<No> fila2 = new LinkedList<No>();
        while(!fila.isEmpty()) {
            No teste = fila.removeFirst();
            if(teste.dist < min.dist) {
                min = teste;
            }
            fila2.addLast(teste);
        }        
        while(!fila2.isEmpty()) {
            No test = fila2.removeFirst();
            if(test != min) {
                fila.addFirst(test);
            }
        } 
        return min;
        
        
        
        
    }
    
    public static void relax(No u, No v, int[][] mat) {
        if(v.dist > (u.dist + mat[u.key][v.key])) {
            v.dist = (u.dist + mat[u.key][v.key]);
            v.pai = u;
        }
    }
    
}
