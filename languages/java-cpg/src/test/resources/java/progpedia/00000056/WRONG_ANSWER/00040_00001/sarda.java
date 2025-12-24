import java.io.*;
import java.util.*;

 class no{
    public double x;
    public double y;
    public double dist[];
    public boolean arvore;

    public  no(double x, double y, int n){
	this.x=x;
	this.y=y;
	this.dist=new double[n];
	this.arvore = false;
    }

}







class sarda{
    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	int n= stdin.nextInt();
	no[] g= new no[n];
	for(int i=0; i<n; i++){
	    g[i]= new no(stdin.nextDouble(),stdin.nextDouble(), n);
	}

	for(int i=0; i<n; i++){
	    for(int j=0; j<n; j++){
		g[i].dist[j] = Math.sqrt(Math.pow(Math.abs(g[i].x-g[j].x),2)+ Math.pow(Math.abs(g[i].y-g[j].y),2));
		    }
	}
		    
		    
	/*	for(int i=0; i<n; i++){
	    	System.out.println("(" + g[i].x + " " + g[i].y + ")");
	    for(int j=0; j<n; j++){
			System.out.print(String.format( "%.2f ",g[i].dist[j]));
	    }
	    System.out.println();
	}
	*/
    

	
        
    
    
   
	double min = 100000.0;
	int posi=0;
	int posj=0;
	double soma=0;
	int findno=0;
	for(int limiter=1; limiter<=n-1; limiter++){
 if(findno==n){break;}
	    for(int i=0; i<limiter; i++){
		//	System.out.print(i+ " ");
		//	System.out.println();
		for(int j=0; j<n; j++){
		    if(g[i].dist[j]<min && g[i].dist[j]!=0){
		
			min=g[i].dist[j];
			//	System.out.println(min);
				posi=i;
			posj=j;
		    }
		}
	    }
	    min=10000.0;
	    soma=soma+g[posi].dist[posj];
	     findno++;
	    g[posi].dist[posj]=0;
	    g[posj].dist[posi]=0;
	   
	}
	System.out.printf("%.2f\n",soma);
}

	  
}
