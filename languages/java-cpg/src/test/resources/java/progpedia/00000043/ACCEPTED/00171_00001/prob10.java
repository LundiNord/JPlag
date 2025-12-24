//package Exercicios;
import java.util.Arrays;
import java.util.Scanner;

//implementacao do Comparable torna mais facil a abordagem ao problema, dica do meu colega Rui Balau, sem ele nao seria possivel a resolucao do problema

class Encomenda implements Comparable<Encomenda> {
	int time, cost, index;

	public Encomenda(int time, int cost, int index) {
		this.time = time;
		this.cost = cost;
		this.index = index;
	}
	
	public int compareTo(Encomenda j) {

		Double j1 = ((double) this.cost / this.time);
		Double j2 = ((double) j.cost / j.time);

		if(j2.compareTo(j1) != 0) {
			return j2.compareTo(j1);
		} else {
			return this.index -j.index;
		}
	}
	
}

public class prob10 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String out = new String();
		
		Encomenda[] enc = new Encomenda[n];
		for(int i = 0; i<n; i++) {
			enc[i] = new Encomenda(in.nextInt(), in.nextInt(), i+1);
		}
		

		
		Arrays.sort(enc); 		
		for(int i=0; i<enc.length; i++) {
			out += enc[i].index + " ";

		}
		if (out.endsWith(" ")) {
			out = out.substring(0, out.length() - 1);
		}
		
		System.out.println(out);
		in.close();
	}
}
