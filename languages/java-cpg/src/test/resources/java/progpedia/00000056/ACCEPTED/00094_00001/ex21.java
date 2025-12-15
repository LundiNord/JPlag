import java.util.*;
import java.io.*;

class No{
    double x;
    double y;
    double dist;
    boolean visited;
    
   
    No(double x, double y){
        this.x=x;
        this.y=y;
        visited=false;
        dist=1000000000;
        
    }
}


public class ex21 {
    static int n;
    static double[][] pesos;
    static No[] sardas;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        pesos= new double[n][n];
        sardas = new No[n];
        double distmin=0;
        for(int i=0; i<n; i++)
            sardas[i]= new No(in.nextDouble(), in.nextDouble());
        
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                pesos[i][j]= cdist(sardas[i], sardas[j]);
        
        prim(0);
        
        for(int i=0; i<n; i++)
            distmin+=sardas[i].dist;
        
        System.out.printf("%.2f\n", distmin);
        
        
    }
    static double cdist(No a, No b){
        double x1= a.x - b.x;
        double y1= a.y -b.y;
        x1= x1*x1;
        y1=y1*y1;
        return(Math.sqrt(x1+y1));
    }
    
    static void prim(int k){
        double distmin=11111111;
        int nomin=0;
        sardas[k].dist=0;
        
        while(true){
            distmin=11111111;
            nomin=-1;
            for(int i=k; i<n; i++){
                if(sardas[i].dist<distmin && !sardas[i].visited){
                    nomin=i;
                    distmin=sardas[i].dist;
                }
                
            }
            if(nomin==-1)
                return;
            sardas[nomin].visited=true;
            
            for(int i=0; i<n; i++)
                if(pesos[nomin][i]<sardas[i].dist && !sardas[i].visited)
                    sardas[i].dist=pesos[nomin][i];
        }
    }
    
}
