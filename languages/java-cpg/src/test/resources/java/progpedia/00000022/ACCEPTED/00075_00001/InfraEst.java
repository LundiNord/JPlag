import java.io.*;
import java.util.*;

class InfraEst {
	
	public static int lmin, cmin, amin;
	
	public static void readDim(Scanner scn) {
				scn.nextInt();	// nNodes
		lmin = 	scn.nextInt();
				scn.nextInt();	// lmax
		cmin = 	scn.nextInt();
				scn.nextInt();	// cmax
		amin = 	scn.nextInt();
				scn.nextInt();	// source
				scn.nextInt();	// target
	}
	
	public static int count(Scanner scn) {
		int x, n=0;
		while ((x = scn.nextInt()) != -1) {
			int y = scn.nextInt();
			int l = scn.nextInt();
			int c = scn.nextInt();
			int a = scn.nextInt();
			if (l >= lmin && c >= cmin && a >= amin) 
				n++;
		}
		return n;
	}
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		readDim(scn);
		System.out.println(count(scn));
	}
}