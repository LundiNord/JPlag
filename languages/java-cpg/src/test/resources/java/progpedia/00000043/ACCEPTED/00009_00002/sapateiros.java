import java.io.*;
import java.util.*;


class sapatos implements Comparable<sapatos>{
    public int indice;
    public int dias;
    public int multa;
    public double racio;

    sapatos(int dias, int multa, int indice){
	this.indice=indice;
	this.dias =dias;
	this.multa=multa;
	this.racio=(double)dias/multa; 
   
    }
    
    @Override
    public int compareTo(sapatos other){
	if(this.racio==other.racio){
	    if(this.indice>other.indice){return 1;}
	    else{return -1;}
	}
    if(this.racio>other.racio){return 1;}
    else{return -1;}
    }
}




class sapateiros{








    public static void main(String args[]){


	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();
	sapatos[] s = new sapatos[n];

	for(int i=0; i<n; i++){
	    s[i]= new sapatos(stdin.nextInt(),stdin.nextInt(), i+1);
	}
	Arrays.sort(s);
	for(int i=0; i<n-1; i++){
	    System.out.print(s[i].indice+ " ");
	    
	}
	System.out.println(s[n-1].indice);

    }
}
