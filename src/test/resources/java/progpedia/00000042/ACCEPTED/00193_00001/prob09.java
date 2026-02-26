//package Exercicios;
import java.util.*;

public class prob09 {
	
	static class Segment implements Comparable<Segment> {
		int l,r;
		
		public Segment(int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo(Segment s) {
			if(this.l != s.l)
				return this.l - s.l;
			return s.r - this.r;
		}
	}
	
		
	public static void main (String[] args) {

		Scanner in = new Scanner(System.in);
		PriorityQueue<Segment> q = new PriorityQueue<Segment>();
		int m = in.nextInt(); 
		int n = in.nextInt();

		for(int i = 0; i<n; i++) {
			int l = in.nextInt();
			int r = in.nextInt();

			q.offer(new Segment(l,r));
		}

		int left = 0;
		List <Segment> result = new ArrayList<Segment>();

		while(left < m) {
			Segment seg = null; //selected segment
			while(!q.isEmpty() && q.peek().l <= left)
			{ 
				Segment cur = q.poll();					
				if(seg==null || cur.r > seg.r)
					seg=cur;
			}
			
			if(seg == null) 
				break;
			left=seg.r;
			result.add(seg);
		}

		if(left<m) 
			result.clear(); 		
		System.out.println(result.size());
		in.close();
	}
}