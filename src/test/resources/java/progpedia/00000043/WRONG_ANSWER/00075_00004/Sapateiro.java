import java.util.*;
import java.io.*;

class Encomenda implements Comparable <Encomenda>{
    int ordem;
    float racio, dias, multa;

    Encomenda(float d, float m, int ord){
	dias  = d;
	multa = m;
	ordem = ord;
	racio = m/d;
    }

    @Override
	public int compareTo(Encomenda n){
	if(racio == n.racio)
	    if (multa < n.multa)
		return -1;
	    else
		return 1;
	else if(racio < n.racio)
	    return 1;
	else 
	    return -1;
        
		
    }
}

class Sapateiro{

    public static void main(String[] args){
	Scanner in = new Scanner (System.in);

	int n = in.nextInt();
	Encomenda[] enc = new Encomenda[n]; 

	for(int i = 0 ; i < n ; i++){
	    enc[i] = new Encomenda(in.nextFloat(), in.nextFloat(), i+1);
	}

	Arrays.sort(enc);

	for(int i = 0; i < n-1; i++)
	    System.out.printf(enc[i].ordem + " ");

	System.out.println(enc[n-1].ordem);
    }
}
