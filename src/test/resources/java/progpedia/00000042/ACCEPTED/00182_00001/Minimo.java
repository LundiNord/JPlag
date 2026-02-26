import java.io.*;
import java.util.*;


class Pos implements Comparable<Pos>  {
    	public int i;
	public int f;

    Pos(int pi, int pf) {
	i = pi;
	f = pf;
    }

@Override
    public int compareTo(Pos n) {
	if (i == n.i)
			if (f > n.f)
				return 1;
			else
				return -1;
	else
	    	if(i > n.i)
			return 1;
		else
			return -1;
    }

}

public class Minimo{
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int fim = stdin.nextInt();
		int n = stdin.nextInt();
		Pos v[] = new Pos[n];
		for (int i = 0; i<n; i++)
			v[i] = new Pos(stdin.nextInt(), stdin.nextInt());

		Arrays.sort(v);
		int cont = 0;
		int best = -1;
		int end = 0;
		int pos = 0;
		while(end < fim){
			while(pos < n && v[pos].i <=end){
				if(v[pos].f > best)
					best = v[pos].f;
				pos++;
			}
			end = best;
			cont++;
		}
		System.out.println(cont);

	}
}
