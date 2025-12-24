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
	LinkedList<Integer> posicoes = new LinkedList<Integer>();
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
		    
	/*		    
		for(int i=0; i<n; i++){
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
	int j=0;
	posicoes.add(0);
	g[0].arvore=true;
	for(int i=0; i<n-1; i++){
	for(int pos :posicoes){
	    //	System.out.println(pos+ " ");
		//	System.out.println();
		for( j=0; j<n; j++){
		    if(g[pos].dist[j]<min && g[pos].dist[j]!=0 && g[j].arvore==false){
		
			min=g[pos].dist[j];
			//		System.out.println(min);
			posi=pos;
			posj=j;
			//	System.out.println(j+"!!");
		    }
		}
	}
	//	System.out.println(min);
	posicoes.add(posj);
	    min=10000.0;
	    soma=soma+g[posi].dist[posj];
	    //   System.out.println(soma+ "!");
	     findno++;
	     g[posj].arvore=true;
	    g[posi].dist[posj]=0;
	    g[posj].dist[posi]=0;
	   
	}
	System.out.printf("%.2f\n",soma);
}

	  
}
