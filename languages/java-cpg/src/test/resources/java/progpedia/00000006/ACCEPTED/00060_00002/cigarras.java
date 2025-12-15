import java.io.*;
import java.util.*;

class cigarras{
	
	public static void main (String[] args){
		
		Scanner in = new Scanner(System.in);
		
		int[] v = new int[30];
		v[0] = 0;
		int j=0;
		
		int x = in.nextInt();
		
		while (x != 0){
			
			j = procura(v, x);
			v[j] = x;
			v[j+1] = 0;
			x = in.nextInt();
		}
		
		j=0;
		while (v[j] !=0){
			System.out.println(v[j]);
			j++;
		}
		
		
	}

	private static int procura(int[] v, int x) {
		int j=0;
		
		while (v[j] != x && v[j] != 0){
			j++;
		}
	
		return j;
	}
	
}