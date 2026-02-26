import java.util.*;

public class Test {
	public static String near (int n, int[] v) {
		int min_d = Integer.MAX_VALUE;
		int min_v = 0;

		for (int i=0; i<v.length; i++) {
			int d = v[i] - n;
			
			int aux_d = d;
			if (aux_d < 0) aux_d = -aux_d;
			if (aux_d < min_d) { min_d = aux_d; min_v = v[i]; }

			if (d == 0 || d >= 1) {
				String s = min_v + "";
				if (d == min_d && v[i] != min_v) s += " " + v[i];
				return s; }}

		return ""; }

	public static void main (String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
	
		// S
		int ns = in.nextInt();
		int[] s = new int[ns];
		for (int i=0; i<ns; i++) s[i] = in.nextInt();
	
		// Todas as somas
		int nss = 0;
		for (int i=1; i<ns; i++) nss = nss+=i;
	
		int[] ss = new int[nss];
		for (int k=0, i=0; i<ns; i++) for (int j=i+1; j<ns; j++, k++) ss[k] = s[i] + s[j];
		Arrays.sort(ss);
	
		// P
		int np = in.nextInt();
		for (int i=0; i<np; i++) System.out.println(near(in.nextInt(), ss)); }}