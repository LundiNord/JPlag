import java.util.Scanner;

public class Cigarras {
	public static void main (String[] args) {
		Scanner console = new Scanner(System.in);
		int v[] = new int[30];
		v[0]= console.nextInt();
		for (int i=1; v[i-1]!=0; i++) {
			v[i] = console.nextInt();
			int p = lastHit(v,i);
			if (p != i)
				i = p;
		}
//		console.close();
		for (int i=0; v[i] != 0; i++) {
			System.out.println(v[i]);
		}
	}

	private static int lastHit (int[] v, int p) {
		for (int i=0; i<p; i++) {
			if (v[i] == v[p])
				return i;
		}
		return p;
	}

}
