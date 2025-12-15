import java.io.*;
import java.util.*;

class No{
    double x, y, dist;
    boolean visited;
    
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


class p21{
    
    static int n;
    static No[] sardas;
    static double[][] wei;
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        n = in.nextInt();
        sardas = new No[n+1];
        wei = new double[n+1][n+1];
        double result = 0;
        
        for(int i = 1; i <= n; i++){
			double g=in.nextDouble();
			double h=in.nextDouble();
			sardas[i] = new No(g,h);
        }
        
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++){
			    No a=sardas[i];
			    No b=sardas[j];	
                wei[i][j] = Math.sqrt(((a.x - b.x) * (a.x - b.x)) +( (a.y - b.y) * (a.y - b.y)));
            }
        
        
		double dist_min = Double.MAX_VALUE;
        int no_min = 0;
        sardas[1].dist = 0;
        
        

        loop:while(true){
            dist_min = Double.MAX_VALUE;
            no_min = -1;
            for(int i = 1; i <= n; i++){
            if(sardas[i].dist < dist_min && !sardas[i].visited){
                no_min = i;
                dist_min = sardas[i].dist;
            }
            }
            if (no_min == -1) break loop;
           
            
            sardas[no_min].visited = true;
            
                
            for(int i=1; i <= n; i++){
            if(wei[no_min][i] < sardas[i].dist  && !sardas[i].visited){
                sardas[i].dist = wei[no_min][i];
        
            }
            }
        }
    
        
        for(int j=1; j<= n; j++){
            result += sardas[j].dist;
        }
        
        System.out.printf("%.2f\n",result);
        
  }
}

//Contei com a ajuda do Fabio Queiros para perceber o exercicio