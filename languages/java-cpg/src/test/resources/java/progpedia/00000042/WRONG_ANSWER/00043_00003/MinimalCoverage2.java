import java.io.*;
import java.util.*;

public class MinimalCoverage2 {

	static class Line implements Comparable<Line> {
		int start, end, count;

		public Line(int s, int e) {
			start = s;
			end = e;
		}

		public int compareTo(Line P) {
			if (start < P.start || (start == P.start && end < P.end))
				return -1;
			return 1;
		}

	}

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int m = stdin.nextInt();
		int n = stdin.nextInt();
		int count = 1;
		int fim = 0;

		Line v[] = new Line[n];

		for (int i = 0; i < n; i++)
			v[i] = new Line(stdin.nextInt(), stdin.nextInt());

		Arrays.sort(v);

		
		int j ;
		
		for (int i = 0; i < n; i++) {
			if (v[i].end < m) {
				for (j = n - 1; j > i; j--) {
					if (v[j].start <= v[i].end && v[i].start > v[j].start) {
						count++;
						i = j - 1;
						break;
					}
				}
			}

		}


		System.out.println(count);

	}
}