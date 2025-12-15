
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prog09 {

	static class Segment implements Comparable<Segment> {
		int l,r;
		
		public Segment(int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo(Segment s) {
			// TODO Auto-generated method stub
			if(this.l != s.l)return this.l - s.l;
			return s.r - this.r;
		}
	}
	
	
	public static void main (String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		PriorityQueue<Segment> q = new PriorityQueue<Segment>();
		
		int m = in.nextInt(), n = in.nextInt();
		
		for(int i = 0; i<n; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			
			q.offer(new Segment(l,r));
		}
		
		int left = 0;
		List <Segment> result = new ArrayList<Segment>();
		
		while(left < m) {
			Segment sel = null; //selected segment
			while(!q.isEmpty() && q.peek().l <= left) { //"q.peek returns head element of the queue"
				Segment cur = q.poll();					//"q.poll removes head element of the queue"
				if(sel==null || cur.r > sel.r)
					sel=cur;
			}
			if(sel == null) break;
			left=sel.r;
			result.add(sel);
		}
		
		if(left<m) result.clear(); 						//nao chegou ao final reinicia
		System.out.println(result.size());
		
		
	}
	
	
}
