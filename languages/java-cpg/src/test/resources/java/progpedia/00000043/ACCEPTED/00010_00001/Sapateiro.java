import java.io.*;
import java.util.*;

class encomenda implements Comparable<encomenda>{
    double racio;
    int multa;
    

    encomenda(double i, int m){
	racio=i;
	multa=m;
	
    }


    @Override
    public int compareTo(encomenda n) {
	if (racio == n.racio)
	    if (multa > n.multa)
		return 1;
	    else
		return -1;
	else
	    if(racio > n.racio)
		return -1;
	    else 
		return 1;   
    }
}


public class Sapateiro{
   
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	
	int n_encomendas = stdin.nextInt();
	
	encomenda[] e = new encomenda[n_encomendas];

	for(int i=0;i<n_encomendas;i++){
	    int dia = stdin.nextInt();
	    int multa = stdin.nextInt();
	    e[i] = new encomenda((double)multa/dia, i+1);
	}
	
	Arrays.sort(e);
	
	System.out.print(e[0].multa);
	for(int i = 1 ;i<n_encomendas;i++)
	    System.out.print(" "+e[i].multa);
	System.out.println();
    }
}
