import java.util.*;
import java.io.*;

class sapato implements Comparable<sapato>{
    public double dias,multa,razao;
    public int pos;
    sapato(double d, double m, int i){
	dias = d;
	multa = m;
	pos = i;
	razao = multa / dias;
    }

    @Override
	public int compareTo(sapato s){
	if(razao > s.razao) return -1;
	if(razao < s.razao) return +1;
	if(pos < s.pos) return -1;
	if(pos > s.pos) return +1;
	return 0;
    }
}

class p010{
    public static void main(String args[]){
	
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	sapato v[] = new sapato[n];
	
	for(int i=0; i<n;i++)
	    v[i] = new sapato(in.nextDouble(), in.nextDouble(),i+1);

	Arrays.sort(v);
	
	for(int i=0; i<n-1; i++)
	    System.out.print(v[i].pos + " ");
	System.out.print(v[n-1].pos);
    }
}
