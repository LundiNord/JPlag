import java.io.*;
import java.util.*;


class Sapato implements Comparable<Sapato> {
    public int index;
    public int days;
    public float racio;
    
    Sapato(int index, int days, float racio){
	this.index = index;
	this.days = days;
	this.racio = racio;
    }
    
    
    // Definir como comparar dois elementos da classe Name
    // compareTo e uma funcao que compara objecto com outro objecto "n"
    //  - numero < 0 se objecto for menor que objecto "n"
    //  - numero > 0 se objecto for maior que objecto "n"
    //  - zero, se objecto for igual ao objecto "n"
    @Override
    public int compareTo(Sapato n) {
	
	if(racio == n.racio)
	    if(days == n.days)
		if(index < n.index) return -1;
		else return 1;
	
	    else
		if(days < n.days) return -1;
		else return 1;
	else
	    if(racio < n.racio) return 1;
	    else return -1;
	    
    }
}

class Sapateiro{
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);
	
        /*NUMERO DE BOTAS P'ARRANJAR (sandalias po'rui)*/
	int n = in.nextInt();
	Sapato x [] = new Sapato[n];
	
	for(int i=0 ; i<n ; i++){
	    int d = in.nextInt();
	    int p = in.nextInt();
	    x[i] = new Sapato(i+1, d, p/d);
	}

	Arrays.sort(x);

	for(int i=0 ; i<n ; i++)
	    System.out.print(x[i].index + " ");
	System.out.println();
    }
}

	
	    
	
