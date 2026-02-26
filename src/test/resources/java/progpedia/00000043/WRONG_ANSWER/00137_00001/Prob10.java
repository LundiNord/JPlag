import java.util.Arrays;
import java.util.Scanner;

class Job implements Comparable<Job>{

	int d, m, index;
	public Job(int d, int m, int index) {
		this.d = d;
		this.m = m;
		this.index = index;
	}

	public int compareTo(Job j) {
		Integer j1 = m / d;
		Integer j2 = j.m / j.d;

		if(j2.compareTo(j1) != 0) {
			return j2.compareTo(j1);
		} else {
			return this.index - j.index;
		}
	}

	public String toString() {
		return this.index + "";
	}
}

public class Prob10 {
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);
		
	        int c = stdin.nextInt();
        	Job jobs[] = new Job[c];
        	
	        for (int i = 0; i < c; i++) {
	        	int d = stdin.nextInt();
	        	int m = stdin.nextInt();
	        	
        		jobs[i] = new Job(d, m, i+1);        	
	        }
	        
	        Arrays.sort(jobs);
	        System.out.print(jobs[0]);
	        
	        for(int j = 1; j < jobs.length; j++) 
				System.out.print(" " + jobs[j]);
	        System.out.println();
			if(c > 0) {
				System.out.println();
			}
	}
}