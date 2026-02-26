import java.io.*;
import java.util.*;


class Pos implements Comparable<Pos>  {
    	public double i;
	public int f;

    Pos(double pi, int pf) {
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
			return -1;
		else 
			return 1;
    }	

}

public class Sapatos{
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int d, m, i;
		Pos v[] = new Pos[n];
		for (i = 0; i<n; i++){
			d = stdin.nextInt();
			m = stdin.nextInt();
			v[i] = new Pos((double)m/d, i+1);
		}

		Arrays.sort(v);
		System.out.print(v[0].f);
		for (i=1; i<n; i++)
			System.out.print(" " + v[i].f);
		System.out.println();
	
	}
}



