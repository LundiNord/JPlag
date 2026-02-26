import java.io.*;
import java.util.*;

class Sapato implements Comparable<Sapato> {
    public float duracao,multa,racio;
    public int i,indice;
    Sapato(float d, float m,int i) {
	duracao = d;
	multa = m;
	racio = m/d;
	indice = i;
    }

    @Override
    public int compareTo(Sapato s) {
	if(racio > s.racio) return -1;
	if(racio < s.racio) return +1;
	if(indice < s.indice) return -1;
	return +1;
    }

}

class Sapateiro{
    static int n,conta;       
    static Sapato v[];
          	
    public static void main(String args[]){
	
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt();
	v = new Sapato[n];
	for(int i=0;i<n;i++)
	    v[i] = new Sapato(stdin.nextFloat(),stdin.nextFloat(),i);
	    
	Arrays.sort(v);
	
	for(int j=0;j<n-1;j++)
	    System.out.print(v[j].indice+1 + " " );
	System.out.println(v[n-1].indice+1);
	
    }
}
