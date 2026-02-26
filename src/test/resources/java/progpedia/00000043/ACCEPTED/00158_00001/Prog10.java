

import java.util.Arrays;

//obvio qnt mais alto o racio de multa para tempo maior prioridade tem o trabalho


import java.util.Scanner;

class Job implements Comparable<Job> {
	int time, cost, index;
	
	//builder
	public Job(int time, int cost, int index) {
		this.time = time;
		this.cost = cost;
		this.index = index;
	}
	
	public int compareTo(Job j) {
		//nao percebo porque  q tem de ser wrapper class....
		Double j1 = ((double) this.cost / this.time);
		Double j2 = ((double) j.cost / j.time);
		
		//implementar a ordenacao do racio
		if(j2.compareTo(j1) != 0) {
			return j2.compareTo(j1);
		} else {
			return this.index -j.index;
		}
	}
	
}

public class Prog10 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String out = new String();
		
		Job[] jobs = new Job[n];
		for(int i = 0; i<n; i++) {
			jobs[i] = new Job(in.nextInt(), in.nextInt(), i+1); //indice tem de ser i+1 pra nao tar a mudar os fors todos e mesmo assim acertar no output
		}
		

		
		Arrays.sort(jobs); //vantagem de implementar o Comparable! grande magia
		

		
		for(int i=0; i<jobs.length; i++) {
			out += jobs[i].index + " ";

		}
		if (out.endsWith(" ")) {
			out = out.substring(0, out.length() - 1);
		}
		
		System.out.println(out);
		
	}
}
