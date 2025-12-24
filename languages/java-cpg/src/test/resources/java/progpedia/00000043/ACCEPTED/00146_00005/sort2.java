import java.io.*;
import java.util.*;
import java.lang.*;


class Orders implements Comparable<Orders> {
	public int duration, fine, line;

	Orders(int a, int b, int c) {
		duration = a;
		fine = b;
		line=c;
	}

	@Override
	public int compareTo(Orders p) {
		if(duration*p.fine >= p.duration*fine) return 1;
		else return -1;
	}

} 


public class sort2 {
	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		Orders[] v= new Orders[n];
		for(int i=0; i<n; i++) {
			v[i] = new Orders(stdin.nextInt(), stdin.nextInt(),i+1); 
		}
		Arrays.sort(v);

		for(int i=0; i<n-1; i++) 
			System.out.print(v[i].line + " ");

		System.out.print(v[n-1].line);
		System.out.println();
	}
}