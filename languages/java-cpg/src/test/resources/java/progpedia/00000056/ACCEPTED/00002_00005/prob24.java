import java.util.*;
import java.io.*;
import java.lang.*;

class par{
    int x;
    double y;
    par(int x, double y){
	this.x=x;
	this.y=y;
    }
}

class prob24{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	double[][] g=new double[N][N];
	double[] x=new double[N];
	double[] y=new double[N];
	for(int i=0;i<N;i++){
	    x[i]=sc.nextDouble();
	    y[i]=sc.nextDouble();
	}
	for(int k=0;k<N;k++){
	    for(int r=k;r<N;r++){
		g[k][r]=g[r][k]=Math.sqrt(Math.pow((x[k]-x[r]),2)+Math.pow((y[k]-y[r]),2));
	    }
	}
	
	
	System.out.printf("%.2f\n",prim(N,g));
    }
    static double prim(int v, double g[][]){
	/*PriorityQueue<double> q =new PriorityQueue<double>(N,new Comparator<double>(){
		public int compare(double e1, double e2){
		    return e1.compareTo(e2); 
		}
			
		});*/


	double t = 0;

	boolean visi[]= new boolean[v];
	Arrays.fill(visi, false);
	double dist[]=new double[v];
	Arrays.fill(dist, 99999999);
	dist[0] = 0;
	

	for(int i=0;i<v;i++) {

	    // EXTRAIR-MINIMO
	    double min = 999999999;
	    int min_v = -1;	    
	    for(int j=0;j<v;j++)
		if(!visi[j] && dist[j]<min) {
		    min = dist[j];
		    min_v = j;
		}
	    visi[min_v] = true;
	    t += min;

	    for(int j=0;j<v;j++)
		if(!visi[j] && g[min_v][j]<dist[j])
		    dist[j] = g[min_v][j];
	    
	    

	}

	//System.out.println(t);
	return t;
    }
}
