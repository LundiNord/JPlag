import java.io.*;
import java.util.*;
class Encomenda implements Comparable<Encomenda>{
    public double duracao,multa,razao=0.0;
    public int ordem;

    Encomenda(double d, double m, int o){
	duracao=d;
	multa=m;
	ordem=o;
	razao=m/d;
    }
    @Override
    public int compareTo(Encomenda e) {
	if (razao > e.razao) return -1;
	else if (razao < e.razao) return +1;
	else if(ordem < e.ordem) return -1;
	else if(ordem > e.ordem) return +1;
	return 0;
    }
}
class p010{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	Encomenda e[] = new Encomenda[n];
	for(int i=0;i<n;i++)
	    e[i] = new Encomenda(in.nextDouble(),in.nextDouble(),i+1);
	Arrays.sort(e);
	for(int i=0;i<n-1;i++)
	    System.out.print(e[i].ordem+" ");
	System.out.println(e[n-1].ordem);
    }
}
