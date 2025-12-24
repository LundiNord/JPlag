import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prob09 {

	static class Segm implements Comparable<Segm> {
		int start, end;

		public Segm(int s, int e) {
			start = s;
			end = e;
		}

		public int compareTo(Segm segm) {
			if (start < segm.start || (start == segm.start && end > segm.end))
				return -1;
			return 1;
		}
	}

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int m = stdin.nextInt();
		int n = stdin.nextInt();

		LinkedList<Segm> ListSegms = new LinkedList<Segm>();
		PriorityQueue<Segm> S = new PriorityQueue<Segm>();

		for (int i = 0; i < n; i++) {

			int li = stdin.nextInt();
			int ri = stdin.nextInt();
			S.add(new Segm(li, ri));
		}
		int start = 0;

		while (start < m) {
			Segm best = null;
			while (!S.isEmpty() && S.peek().start <= start) {
				Segm current = S.remove();
				if (best == null || current.end > best.end)
					best = current;
			}
			if (best == null)
				break;
			else {
				start = best.end;
				ListSegms.add(best);
			}
		}
		if (start < m)
			System.out.println(0);
		else
			System.out.println(ListSegms.size());

	}
}