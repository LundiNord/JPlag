import java.io.*;
import java.util.*;

class Segment {
	int start;
	int end;

	Segment (int s, int e) {
		start = s;
		end = e;
	}
}

public class prat10 {
	public static void main (String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int m = Integer.parseInt(reader.readLine());
			int n = Integer.parseInt(reader.readLine());
			Segment[] segs = new Segment[n];
			for (int i = 0; i < n; i++) {
				String line = reader.readLine();
				String[] nums = line.split(" ");
				segs[i] = new Segment(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
			}
			Arrays.sort(segs, new Comparator<Segment>() {
				public int compare (Segment s1, Segment s2) {
					if (Math.abs(s1.start - s2.start) > 0) return (s1.start - s2.start);
					return (s1.end - s2.start);
				}
			});
		/*for (int i = 0; i < n; i++) {
			System.out.println(segs[i].start + " " + segs[i].end);
		}*/
		int end = 0;
		int cont = 0;
		while (end < m) {
			int maxr = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				if (segs[i].start <= end) {
					if (segs[i].end > maxr) {
						maxr = segs[i].end;
					}
				}
			}
			cont++;
			end = maxr;
			//System.out.println("iteration!" + end);
		}
		System.out.println(cont);
	}
	catch (Exception e) {}
	}
}