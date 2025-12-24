import java.util.*;
import java.io.*;

class sardas{
    int nome;
    double x;
    double y;
    int prox;
    double best;
    
    
    sardas(double x, double y, int nome){
	this.x = x;
	this.y = y;
	this.nome = nome;
	prox = 0;
	best = 150;
    }
    
    static void dist(sardas a, sardas b){
	double xm  = a.x - b.x;
	double ym  = a.y - b.y;
	double aux = Math.sqrt((xm*xm) + (ym*ym));
	
	if(aux < a.best && b.prox != a.nome){
	    a.best = aux;	
	    a.prox = b.nome;
	}
    }
    
    static double devBest(sardas s){
	return s.best;
    }

    static int devProx(sardas r){
	return r.prox;
    }
    
    
    
}

class moo21{
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	sardas[] s = new sardas[n+1];
	
	for(int i=1; i<=n; i++){
	    s[i] = new sardas(in.nextDouble(), in.nextDouble(), i);
	}

	double[] out = new double[n+1];



	for(int i=1; i<=n; i++){
	    for(int j=1; j<=n; j++){
		if(i != j)
		    sardas.dist(s[i], s[j]);
	    }
	}

	for(int i=1; i<=n; i++){
	    out[i] = (sardas.devBest(s[i]));
	    //System.out.printf("%.2f\n", out[i]);
	}
        
	double outp = 0;
	int count = 1;
	int i=1;
	int j;
	
	while(count < n){
	    j = sardas.devProx(s[i]);
	    outp += sardas.devBest(s[i]);
	    i=j;
	    count++;
	}
	
	System.out.printf("%.2f\n", outp);
    }
}
