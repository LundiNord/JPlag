//programa realizado em conjunto com Diogo Ribeiro 201305327

import java.util.*;
import java.io.*;
class Sapato{
    double ratio;
    int index;
    Sapato(){
	ratio = 0;
	index = 0;
    }
    Sapato(double r,int i){
	ratio = r;
	index = i;
    }
}
class sapateiro{
    public static void main(String args[]){
	Scanner stdin= new Scanner(System.in);
	int n = stdin.nextInt();
	Sapato sapatos[] = new Sapato[n];
	double ratio;
	for(int i  = 0; i < n;i++){
	    ratio = stdin.nextDouble() / stdin.nextInt();
	    sapatos[i] = new Sapato(ratio ,i);
	    //  System.out.println("--------" + sapatos[i].ratio + "----------");
	    //  System.out.println((i+1)+" - " + ratio[i]);
	}
	Sapato aux = new Sapato();
	for(int i =0; i < n; i++){
	    //System.out.println(ratio[i]);
	    for(int j = i; j<n;j++){
		if(sapatos[i].ratio > sapatos[j].ratio){
		    aux = sapatos[i];
		    sapatos[i] = sapatos[j];
		    sapatos[j] = aux;
		}
		else if(sapatos[i].ratio == sapatos[j].ratio)
		    if(sapatos[i].index > sapatos[j].index){
			aux = sapatos[i];
			sapatos[i] = sapatos[j];
			sapatos[j] = aux;
		    }

	    }
	    if(i <n-1)
		System.out.print((sapatos[i].index+1) + " ");
	    else
		System.out.println((sapatos[i].index+1));
	}
    }



}
