import java.io.*;
import java.util.*;

public class D009 {
	static int end, n, m, l[], r[];

	static void solve() {
			int maior=0, count=0;
			end =0;
			while (end < m) {
				for (int i=0; i<n; i++) {
					if (l[i] <= end && r[i] > maior) {
							maior = r[i];
					}
				}
				end = maior;
				count++;
			}
			System.out.println(count);
	}



	public static void main(String args[]) {

		Scanner stdin = new Scanner(System.in);

		m = stdin.nextInt();

		n = stdin.nextInt();

		l = new int[n];
		r = new int[n];

		for (int i=0; i<n; i++) {
			l[i] = stdin.nextInt();
			r[i] = stdin.nextInt();
		}

		solve();
	}
}
