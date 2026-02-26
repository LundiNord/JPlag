import java.io.*;
import java.util.*;

class Seg implements Comparable<Seg>{
    int pi;
    int pf;
    
    Seg ( int pi, int pf){
        this.pi = pi;
	this.pf = pf;
    }
    @Override
    public int compareTo(Seg x){
	if(pi > x.pi) return 1;
	else return -1;
    }
}


class CobMin{
    public static void main(String []args){

	Scanner in = new Scanner(System.in);
	int fim = in.nextInt(); //ate onde queremos cobrir
	int n = in.nextInt(); //numero de segmentos
	Seg[] v= new Seg[n];

	for(int i=0;i<n;i++) //guardar os segmentos
	    v[i]= new Seg(in.nextInt(), in.nextInt());

	Arrays.sort(v);
	
	int conta=0;//numero d segmentos
 	int fimtemp=0;
	int maior=0;
	while(fimtemp < fim){
	    
	    for(int i=0; i<n && v[i].pi <=fimtemp;i++){
		if(v[i].pf>maior) maior=v[i].pf;
		
	    }
	    fimtemp = maior; 
	    conta++;
	    		

	}
	
	System.out.println(conta);
    }
}
