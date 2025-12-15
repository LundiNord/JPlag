import java.util.*;
import java.io.*;

class Pares implements Comparable<Pares>{
    public int dia, multa, index;
    public float ratio;
    Pares(int d, int m, int i){
	dia = d;
	multa = m;
	index = i;
	ratio = (float)multa/dia;
    }

    @Override
    public int compareTo(Pares p) {
	if(ratio < p.ratio) return 1;
	if(ratio > p.ratio) return -1;
	return 0;
    }

}

//------------------------------------------------------------------------------

public class Prob010 {
    static int n; //número de encomendas
      
     static void readInput(){
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt(); //número de encomendas

	//segmentos
	Pares v[] = new Pares[n];
	
	for(int i=0;i<n;i++){
	    v[i] = new Pares(stdin.nextInt(), stdin.nextInt(),i+1);
	   
	}

	Arrays.sort(v);
	
	/*
	//input ordenado
	for(int i=0;i<n;i++){
	    System.out.println(v[i].dia + " " + v[i].multa + " | " + v[i].index);
	}
	System.out.println("-----------------------");
	*/

	//output
	for(int i=0;i<n-1;i++){
	    System.out.print(v[i].index + " ");
	}
	System.out.println(v[n-1].index);
	
     }
    
    //----------------------------------------------------
    
    public static void main(String args[]) {
	
	readInput();

    }
}
