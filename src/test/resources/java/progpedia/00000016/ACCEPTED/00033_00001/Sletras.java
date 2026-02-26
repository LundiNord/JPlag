import java.io.*;
import java.util.*;

class Sletras {
	
	public static int compare(String A, String B) {
		for (int i=0; i<A.length(); i++) 
			if (A.charAt(i) != B.charAt(i))
				return i;
		return -1;
	}
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		int i = compare(s1, s2);
		if (i == -1) 	System.out.println("Nenhum");
		else 			System.out.println(s1.charAt(i) + "" + s2.charAt(i));
	}
}