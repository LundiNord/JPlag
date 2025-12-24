import java.util.*;
import java.io.*;

public class cigarras {
	static void anula(int i, int z, int v[]) { 
		for(int j=i+1; j<=z; j++) v[j]=0;
	}
	static void procura(int v[]) { 					
		for(int i=0; i<v.length; i++) {							
			for(int z=i+1; z<v.length; z++) {
				if(v[i]==v[z] && v[i]!=0) {
					anula(i, z, v);
				}
			}
		}
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int n= in.nextInt();
		int v[] = new int[30000];
		int j=0;
		while(n!=0) {
			v[j]=n;
			n=in.nextInt();
			j++;
		}
		procura(v);
		for(int i=0; i<v.length; i++) {
			if(v[i]!=0) System.out.println(v[i]);
		}
	}
}
