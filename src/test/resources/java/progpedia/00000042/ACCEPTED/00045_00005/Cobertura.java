import java.util.*;
import java.io.*;

class Par implements Comparable<Par>{
    int i;
    int f;

    Par(int i, int f){
        this.i = i;
	this.f = f;
    }


    public int compareTo(Par x){

	if(i > x.i)
	    return 1;
	else
	    return -1;
    }
}
    
class Cobertura{
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);

	int M = in.nextInt(); //distância a cobrir
	int N = in.nextInt(); //quantidade de segmentos
	Par[] p = new Par[N]; //arrays com par de coordenadas

	for(int j = 0 ; j < N ; j++)
	    p[j] = new Par(in.nextInt(), in.nextInt());
	
	Arrays.sort(p);
	
	
	int end = 0; 
	int maiorComp = 0;
	int opcao = 0;
	int quant = 0;
	int j = 0;
	
	while(end < M){
	    
	    for( ; j < N && p[j].i <= end ; j++)
		if(p[j].f > maiorComp){
		    opcao = j;
		    maiorComp = p[opcao].f ;
		}
	    
	    quant++;	
	    end = p[opcao].f ;
	    // System.out.println(end);
	}
	System.out.println(quant);
    }
}
