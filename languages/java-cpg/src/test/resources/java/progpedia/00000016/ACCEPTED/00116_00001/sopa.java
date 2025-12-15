import java.io.*;
import java.util.*;

public class sopa {
	static String compara(String p1, String p2) {
		char l1;
		char l2;
		for (int i = 0; i < p1.length(); i++) {
			l1 = p1.charAt(i);
			l2 = p2.charAt(i);
			if(l1!=l2)
				return l1 + "" + l2;
		}
		return "Nenhum";
	}
	public static void main(String[] args) {
		Scanner le = new Scanner(System.in);
		String p1 = le.next();
		String p2 = le.next();
		System.out.println(compara(p1, p2));
	}

}
