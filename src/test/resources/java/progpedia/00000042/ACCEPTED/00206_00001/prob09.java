//Joana Silva up200908475


import java.io.*;
import java.util.*;

class Segment implements Comparable<Segment>{
	public int first;
	public int second;
	
	Segment(int l, int r){
		first = l;
		second = r;
	}

	@Override
	public int compareTo(Segment s){
		if(first < s.first) return -1;
		else return -1;
	}
	
}

public class prob09{
	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		int m = in.nextInt(); 
		int n = in.nextInt();
		
		Segment [] s = new Segment[n];
		
		for(int i = 0; i< n; i++){
			s[i] = new Segment(in.nextInt(),in.nextInt());
		}
		
		Arrays.sort(s);
		
		int end= 0; 
		int end2 = 0; 
		int num_segs = 0; 
		
		
		while(end < m){
			for(int i = 0; i < n; i++){
				if(s[i].first <= end)
					if(s[i].second > end2)
						end2 = s[i].second;
			}
			end = end2;
			num_segs++;
			
		}
		System.out.println(num_segs);
	}
}
