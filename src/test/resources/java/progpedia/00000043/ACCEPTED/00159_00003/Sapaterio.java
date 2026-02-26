//package sapateiro_10;

import java.util.Scanner;
import static java.util.Arrays.*;

class Encomendas implements Comparable<Encomendas>{
	int d;
	int m;
	int indice;
	public Encomendas(int d, int m, int indice){
		this.d = d;
		this.m = m;
		this.indice = indice;
	}
	public int compareTo(Encomendas e){
		Double e1 = ((double)this.m)/this.d;
		Double e2 = ((double)e.m)/e.d;
		if(e2.compareTo(e1) != 0)
			return e2.compareTo(e1);
		else
			return this.indice - e.indice;
	}
	public String toString(){
		return this.indice + "";
	}
}
public class Sapaterio {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Encomendas enc[] = new Encomendas[n];
		for(int i=0; i<n; i++){
			enc[i] = new Encomendas(in.nextInt(), in.nextInt(), i+1);
		}
		sort(enc);
		
		System.out.print(enc[0]);
		for(int i=1; i<enc.length; i++)
			System.out.print(" " + enc[i]);
		System.out.println();
		in.close();
	}

}
