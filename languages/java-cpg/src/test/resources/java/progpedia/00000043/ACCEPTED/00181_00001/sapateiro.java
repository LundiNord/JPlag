import java.io.*;
import java.util.*;


class sapateiro{
    public static void main(String[] args){

	Scanner stdin = new Scanner(System.in);
	
	int n = stdin.nextInt();

	Servico[] vet = new Servico[n];

	for(int i = 0; i < n; i ++){
	    int dias = stdin.nextInt();
	    vet[i] =  new Servico(i + 1,stdin.nextFloat()/dias);
	}

	Arrays.sort(vet);

	for(int i = 0; i < n-1; i++){
	    System.out.print(vet[i].pos + " ");
	}
	System.out.println(vet[(n-1)].pos);
    }
}


class Servico implements Comparable<Servico> {
    int pos;
    float racio;
    
    Servico(int pos, float racio){
	this.pos = pos;
	this.racio = racio;
    }
    
   
    @Override
    public int compareTo(Servico  p){
	
	if(this.racio == p.racio){
	    if(this.pos < p.pos)
		return -1;
	    else
		return 1;
	}
	else {
	    if (p.racio < this.racio)
		return -1;
	    else
		return 1;
	}
    }
}
