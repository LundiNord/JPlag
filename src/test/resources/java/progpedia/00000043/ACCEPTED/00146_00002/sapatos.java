import java.io.*;
import java.util.*;

public class sapatos {
	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		double x, y;
		double[] array= new double[10000];

		for(int i=1; i<=n; i++) {
			x = stdin.nextDouble();
			y = stdin.nextDouble();
			array[i]=y/x;
		}
		for(int i=1; i<=n; i++) {
			int max=0;
			for(int j=1; j<=n; j++) 
				if(array[max] < array[j])
					max=j;
				array[max]=0;

				if(i<n) 
					System.out.print(max + " ");
				else 
					System.out.println(max);
			}
		}
	}