import java.util.*;

class segment implements Comparable<segment>{
	int ini, fim;
	
	public segment(int x, int y){
		ini=x;
		fim=y;
	}
	
	//@Override
	public int compareTo(segment n){
		if(ini < n.ini)
			return -1;
		else 
			if(ini == n.ini){
				if(fim > n.fim)
					return -1;
				else 
					return 1;
			}
			else		
				return 1;	
	}	
}

public class prob9 {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int m = in.nextInt(); // tempo para satisfazer
		int n = in.nextInt(); // nº de segmentos
		
		segment s[] = new segment[n];
		
		for(int i=0; i<n; i++){
			s[i] = new segment(in.nextInt(), in. nextInt());
		}
		
		Arrays.sort(s);	
		/*
        for(int i=0; i<n; i++){
			System.out.println(s[i].ini + " " + s[i].fim);
		}
		*/
		
		int end = 0;
		int count = 0;
		int tempo = 0;

		while(tempo <= m && end < m){
			//System.out.println("tempo: " + tempo);
			if(tempo <= end){
				for(int i=0; i<n; i++){
					if(tempo == s[i].ini){
						end = s[i].fim;
						count++;
						//System.out.println("Index = " + i);
						break;
					}
				}
				//System.out.println("End passou a: " + end);
			}
			tempo++;
		}
		System.out.println(count);
	}
}