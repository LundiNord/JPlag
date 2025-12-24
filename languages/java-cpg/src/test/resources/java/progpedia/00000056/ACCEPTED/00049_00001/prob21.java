import java.lang.*;
import java.io.*;
import java.util.*;

class No{
    double x;
    double y;
    boolean visited;
    double dist;
    
    
    No(double x,double y, boolean v, double d){
    this.x = x;
    this.y = y;
    this.visited = v;
    this.dist = d;
    }
    
    No(double x,double y){
    this.x =   x;
    this.y =   y;
    this.visited = false;
    this.dist = Double.MAX_VALUE;
    }
}


class prob21{
    
    static int n;
    static No[] sardas;
    static double[][] pesos;

    static double calcula(No a, No b){
        double x_dist = (a.x - b.x) * (a.x - b.x);
        double y_dist = (a.y - b.y) * (a.y - b.y);
        return Math.sqrt((x_dist + y_dist));
        }
    
    static void Prim(int r){
        double dist_min = Double.MAX_VALUE;
        int no_min = 0;
        
        sardas[r].dist = 0;
        
        while(true){
            dist_min = Double.MAX_VALUE;
            no_min = -1;
            for(int i = 1; i <= n; i++){
            if(sardas[i].dist < dist_min && !sardas[i].visited){
                no_min = i;
                dist_min = sardas[i].dist;
            }
            }
            if (no_min == -1) return;
           
            
            sardas[no_min].visited = true;
            
                
            for(int i = 1; i <= n; i++){
            if(pesos[no_min][i] < sardas[i].dist  && !sardas[i].visited){
                sardas[i].dist = pesos[no_min][i];
        
            }
            }
        }
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        n = input.nextInt();
        sardas = new No[n + 1];
        pesos = new double[n+1][n+1];
        double result = 0;
        
        for(int i = 1; i <= n; i++)
            sardas[i] = new No(input.nextDouble(),input.nextDouble());
        
        
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
            pesos[i][j] = calcula(sardas[i],sardas[j]);
        
        
        
        Prim(1);
        
        for(int i = 1; i <= n; i++){
            result += sardas[i].dist;
        }
        System.out.printf("%.2f\n",result);
        }
    //Com ajuda

}


