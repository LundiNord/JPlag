import java.util.*;
import java.io.*;

public class Somas {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int [] a = new int [n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		
		int [] s = new int [n*n];		
		int d = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				s[d] = a[i] + a[j];
				d++;
			}
		}
		Arrays.sort(s,0,d);
		
		int p = in.nextInt();
		while (p-- != 0) {
			int query = in.nextInt();
			int b = 0, e = d, m = 0, min = s[0];
			
			//pesquisa binaria
			while (b <= e) {
				m  = b + (e-b) / 2;				
				if (Math.abs(s[m] - query) < Math.abs(query - min))
					min = s[m];
				if (s[m] == query)
					break;
				else if (query > s[m])
					b = m+1;
				else
					e = m-1;
			}
			
			int other = -1;
			if (min < query && Arrays.binarySearch(s, 0, d, query + (query-min)) >= 0)
				System.out.println(min + " " + (query + (query-min)) );
			else if (min > query && Arrays.binarySearch(s, 0, d, query + (query-min)) >= 0)
				System.out.println((query + (query-min)) + " " + min);
			else
				System.out.println(min);
		}
	}

}
