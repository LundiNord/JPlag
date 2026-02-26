import java.io.*;
import java.util.*;

class Cigarras {	
	public static final int LEN = 10000;
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int Graph[] = new int[LEN];
		int x1, x2, ini;
		
		for (int i=0; i<LEN; i++) Graph[i] = 0;
		
		x1 = ini = scn.nextInt(); 
		x2 = scn.nextInt();

		while (x2 != 0) {
			Graph[x1] = x2;
			x1 = x2;
			x2 = scn.nextInt();
		}
		
		while (ini != 0) {
			System.out.println(ini);
			ini = Graph[ini];
		}
	}
}