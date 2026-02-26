import java.io.*;
import java.util.*;

class Sapatos implements Comparable<Sapatos>
{
	public int d;
	public int m;
	public int pos;
	public double k;


	Sapatos(int d , int  m, int pos){
		this.d=d;
		this.m=m;
		this.pos=pos;
		this.k = (double)this.d/this.m;
	}
	@Override
	public int compareTo(Sapatos v){
		if (this.k>v.k) return 1;
		else if(this.k<v.k) return -1;
		return 0;

	}
}


public class Prob10 {





	public static void main (String [] args){
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		Sapatos v[] = new Sapatos[n];

		for (int i = 0 ; i<n; i++){

			v[i] = new Sapatos(stdin.nextInt(),stdin.nextInt(),i+1);

		}
		Arrays.sort(v);
//		System.out.println();
		for(int i = 0; i<n ; i++){
			
			System.out.print(v[i].pos+ " ");
		}

	}


}