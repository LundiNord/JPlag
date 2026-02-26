import java.util.*;
import java.io.*;

class Segment {
	int inicio;
	int fim;

	Segment (int s, int e) {
		inicio = s;
		fim = e;
	}
}

public class prob09 {
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
					if (Math.abs(s1.inicio - s2.inicio) > 0) return (s1.inicio - s2.inicio);
					return (s1.fim - s2.inicio);
				}
			});
		
		int end = 0;
		int cont = 1;
		while (end < m) {
			int maxr = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				if (segs[i].inicio <= end) {
					if (segs[i].fim > maxr) {
						maxr = segs[i].fim;
					}
				}
			}
			cont++;
			end = maxr;
					}
		System.out.println(cont);
	}
	catch (Exception e) {}
	}
}
