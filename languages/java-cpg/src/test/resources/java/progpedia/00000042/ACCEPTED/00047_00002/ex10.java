import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Segment {

	int li;
	int ri;
	int l;

	Segment(int li, int ri) {
		this.li = li;
		this.ri = ri;
		this.l = ri - li;
	}

}

class ex10 {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		int m = in.nextInt();
		int n = in.nextInt();
		int end = 0;
		int numlines = 0;

		Segment[] segments = new Segment[n];

		for (int i = 0; i < n; i++) {
			segments[i] = new Segment(in.nextInt(), in.nextInt());
		}

		Arrays.sort(segments, fitEndGoLong);

		int li = segments[0].li;
		int ri = segments[0].ri;

		while (end < m) {
			for (Segment segment : segments) {
				if (segment.li <= end && segment.ri > ri) {
					li = segment.li;
					ri = segment.ri;
				}
			}
			end = ri;
			numlines++;
		}

		System.out.println(numlines);
	}

	static Comparator<Segment> fitEndGoLong = new Comparator<Segment>() {

		@Override
		public int compare(Segment o1, Segment o2) {
			int c = o1.li - o2.li;
			return c;
		}
	};

}