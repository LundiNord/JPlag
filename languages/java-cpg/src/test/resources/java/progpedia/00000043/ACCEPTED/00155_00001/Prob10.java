import java.util.*;
import static java.util.Arrays.*;

class Job implements Comparable<Job>{

	int tempo, custo, index;
	
	public Job(int tempo, int custo, int index){
		this.tempo = tempo;
		this.custo = custo;
		this.index = index;
	}

	public int compareTo(Job j){
		Double j1 = ((double) this.custo)/this.tempo;
		Double j2 = ((double) j.custo)/j.tempo;

		if(j2.compareTo(j1) != 0)
			return j2.compareTo(j1);
		
		else
			return this.index - j.index;
	}

	public String toString(){
		return this.index + "";
	}
}

class Prob10{

	public static void main(String[] args){
		Scanner inp = new Scanner(System.in);

		int nEncomendas = inp.nextInt();

		Job[] jobs = new Job[nEncomendas];

		for(int i=0; i<nEncomendas; i++)
			jobs[i] = new Job(inp.nextInt(), inp.nextInt(), i+1);

		sort(jobs);

		System.out.print(jobs[0]);

		for(int i=1; i<jobs.length; i++)
			System.out.print(" " + jobs[i]);
		
		System.out.println();		
	}
}