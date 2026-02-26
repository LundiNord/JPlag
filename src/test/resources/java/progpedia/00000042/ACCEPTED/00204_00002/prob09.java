import java.io.*;
import java.util.*;

class Segment implements Comparable<Segment>{
	public int start;
	public int end;
	
	Segment(int l, int r){
		start = l;
		end = r;
	}

	@Override
	public int compareTo(Segment s){
		if(start < s.start) return -1;
		else return -1;
	}
	
}



public class prob09{
	public static void main(String [] args){

		Scanner in = new Scanner(System.in);

		int size = in.nextInt();
		int nSegs = in.nextInt();
		Segment [] s = new Segment[nSegs];
		int t1, t2;

		for(int i=0; i<nSegs; i++){
			t1=in.nextInt();
			t2=in.nextInt();
			s[i] = new Segment(t1,t2);
		}
		
		Arrays.sort(s);
		
		int end= 0;
		int newEnd = 0;
		int minSegs = 0;
		
		
		while(end<size){
			
			for(int i=0; i<nSegs; i++){
				if(s[i].start<=end)
					if(s[i].end>=newEnd)
						newEnd=s[i].end;
			}

			end = newEnd;
			minSegs++;
		}

		System.out.println(minSegs);
	
	}
}